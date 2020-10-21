<template>
    <div>
        <div class="nav">
            <ul class="nav-left">
                <li class="nav-left-item">
                    <el-image class="logo" :src="logo" style="width: 200px; height: 50px" @click="toUserHome"></el-image>
                </li>
            </ul>
            <ul class="nav-right">
                <li class="nav-right-item">
                    <el-dropdown style="margin-top:10px">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item icon="el-icon-setting" @click.native="toUserInfo">个人设置</el-dropdown-item>
                            <el-dropdown-item icon="el-icon-switch-button" @click.native="logout">退出账户</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </li>
            </ul>
        </div>

        <div class="contents">
            <!-- nav -->
            <div>
                <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
                    <el-menu-item index="1">借阅记录</el-menu-item>
                    <el-menu-item index="2">归还记录</el-menu-item>    
                    <el-menu-item index="3">罚款记录</el-menu-item>
                </el-menu>
                <div class="line"></div>
            </div>
            
            <!-- table -->
            <div v-if="activeIndex == 1">
            <template>
                <el-table :data="records.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe key='1'>
                    <el-table-column label="序号" align="center">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 + (currentPage - 1)*pagesize}}</p>
                        </template>
                    </el-table-column>
                    <el-table-column prop="book.bookName" label="书籍" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                        <el-tag type="info" @click="viewBook(scope.row.book)" style="cursor:pointer">
                            {{scope.row.book.bookName}}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="borrowDate" label="借出日期" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column prop="returnDate" label="归还日期" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                        <el-tag :type="scope.row.status == '已超时' ? 'danger' : ''" disable-transitions>
                            {{scope.row.status}}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column  label="操作" width="160" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewRecord(scope.row, 'borrow')">查看详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
            </div>

            <div v-if="activeIndex == 2">
            <template>
                <el-table :data="records.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe key='2'>
                    <el-table-column label="序号" align="center">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 + (currentPage - 1)*pagesize}}</p>
                        </template>
                    </el-table-column>
                    <el-table-column label="借阅记录" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewBorrowRecord(scope.row.borrowId)">查看详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="returnDate" label="归还日期" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                        <el-tag :type="scope.row.status == '超时归还' ? 'danger' : ''" disable-transitions>
                            {{scope.row.status}}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column  label="操作" width="160" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewRecord(scope.row, 'return')">查看详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
            </div>

            <div v-if="activeIndex == 3">
            <template>
                <el-table :data="records.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe key='3'>
                    <el-table-column label="序号" align="center">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 + (currentPage - 1)*pagesize}}</p>
                        </template>
                    </el-table-column>
                    <el-table-column label="借阅记录" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewBorrowRecord(scope.row.borrowId)">查看详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column label="归还记录" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewReturnRecord(scope.row.returnId)">查看详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="days" label="超时天数" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column prop="fine" label="罚款金额" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column  label="操作" width="160" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewRecord(scope.row, 'fine')">查看详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
            </div>

            <!-- page -->
            <div class="page-part">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize"
                    layout="total, sizes,prev, pager, next,jumper"
                    :total="records.length">
                </el-pagination>
            </div>
            
            <!-- bookDetail -->
            <div>
                <el-dialog title="书籍信息" :visible.sync="bookVisible"  width="40%">
                    <el-form :model="bookForm" label-width="100px">
                        <el-form-item   label="书名：" style="width: 90%" >
                            <span>{{bookForm.bookName}}</span>
                        </el-form-item>
                        <el-form-item label="图书类型：" style="width: 90%" >
                            <span>{{bookForm.bookType}}</span>
                        </el-form-item>
                        <el-form-item label="作者：" style="width: 90%" >
                            <span>{{bookForm.author}}</span>
                        </el-form-item>
                        <el-form-item label="出版社：" style="width: 90%" >
                            <span>{{bookForm.publisher}}</span>
                        </el-form-item>
                        <el-form-item label="定价：" style="width: 90%" >
                            <span>{{bookForm.price}}</span>
                        </el-form-item>
                        <el-form-item label="在库数：" style="width: 90%" >
                            <span>{{bookForm.stock}}</span>
                        </el-form-item>
                        <el-form-item label="库存：" style="width: 90%" >
                            <span>{{bookForm.total}}</span>
                        </el-form-item>
                        <el-form-item label="简介：" style="width: 90%" >
                            <span v-html="bookForm.detail" />
                        </el-form-item>
                        
                    </el-form>
                </el-dialog>
            </div>

            <!-- borrowDetail -->
            <div>
                <el-dialog title="借阅记录详情" :visible.sync="borrowVisible"  width="40%">
                    <el-form :model="borrowForm" label-width="100px">
                        <el-form-item label="借出日期" style="width: 90%" >
                            <span>{{borrowForm.borrowDate}}</span>
                        </el-form-item>
                        <el-form-item label="归还日期" style="width: 90%" >
                            <span>{{borrowForm.returnDate}}</span>
                        </el-form-item>
                        <el-form-item label="状态" style="width: 90%" >
                            <el-tag :type="borrowForm.status == '已超时' ? 'danger' : ''" disable-transitions >
                                {{borrowForm.status}}
                            </el-tag>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

            <!-- returnDetail -->
            <div>
                <el-dialog title="归还记录详情" :visible.sync="returnVisible"  width="40%">
                    <el-form :model="returnForm" label-width="100px">
                        <el-form-item label="归还日期" style="width: 90%" >
                            <span>{{returnForm.returnDate}}</span>
                        </el-form-item>
                        <el-form-item label="状态" style="width: 90%" >
                            <el-tag :type="returnForm.status == '超时归还' ? 'danger' : ''" disable-transitions>
                                {{returnForm.status}}
                            </el-tag>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

            <!-- fineDetail -->
            <div>
                <el-dialog title="罚款记录详情" :visible.sync="fineVisible"  width="40%">
                    <el-form :model="fineForm" label-width="100px">
                        <el-form-item label="超时天数" style="width: 90%" >
                            <span>{{fineForm.days}}</span>
                        </el-form-item>
                        <el-form-item label="罚款金额" style="width: 90%" >
                            <span>{{fineForm.fine}}</span>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

        </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
            logo:require("../assets/libLog.jpg"),
            currentPage: 1,
            pagesize: 5,
            activeIndex: '1',
            records:[],
            bookForm:"",
            borrowForm:"",
            returnForm:"",
            fineForm:"",
            bookVisible: false,
            borrowVisible: false,
            returnVisible: false,
            fineVisible: false
        }
    },
    mounted(){
        var id = window.sessionStorage.getItem("user") ;
        if (id == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        this.getRecord('borrow');
    },
    methods:{
        logout() {
            this.$confirm('将退出登录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                window.sessionStorage.removeItem("user");
                this.$router.push('/login')
            });
        },
        toUserInfo(){
            this.$router.push('/userInfo')
        },
        toUserHome(){
            this.$router.push('/userHome');
        },
        handleSelect(key, keyPath) {
            this.activeIndex = key;
            this.currentPage = 1;
            this.records = [];
            switch(key){
                case '1': this.getRecord('borrow');break;
                case '2': this.getRecord('return');break;
                case '3': this.getRecord('fine');break;
                default: console.log("wrong record type");
            }
            console.log(this.records);
            //console.log(key, keyPath);
        },
        handleSizeChange(val) {
            this.pagesize = val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        getRecord(recordType){
            var id = window.sessionStorage.getItem("user");
            this.$axios.get('api/library/record/getRecords?id=' + id + "&recordType=" + recordType)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.records = res.data.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        viewRecord(record ,recordType){
            switch(recordType){
                case "borrow": this.borrowForm = record; this.borrowVisible = true; break;
                case "return": this.returnForm = record; this.returnVisible = true; break;
                case "fine": this.fineForm = record; this.fineVisible = true; break;
                default: console.log("wrong recordType");
            }
        },
        viewBorrowRecord(recordId){
            var userId = window.sessionStorage.getItem("user");
            this.$axios.get('api/library/record/getBR?userId=' + userId + "&recordId=" + recordId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else {
                    this.borrowForm = res.data;
                    this.borrowVisible = true;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        viewReturnRecord(recordId){
            var userId = window.sessionStorage.getItem("user");
            this.$axios.get('api/library/record/getRR?userId=' + userId + "&recordId=" + recordId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else {
                    this.returnForm = res.data;
                    this.returnVisible = true;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        viewBook(book){
            this.bookForm = book;
            this.bookVisible = true;
        },
    }
}
</script>

<style scoped>
.nav {
    z-index: 2;
    padding: 0 4%;
    box-sizing: border-box;
    box-shadow: 0 0 10px #ccc;
    background-color: #fff;
    position: fixed;
    top: 0;
    width: 100%;
    height: 80px;
}
.nav-left {
    margin-left: 0;
    margin-top: 5px;
    float:left;
}
.nav-right {
    margin-right: 0;
    margin-top: 5px;
    float:right;
}
.nav-left-item {
    float: left;
    margin-top: 8px;
    margin-left: 15px;
    position: relative;
    height: 70px;
    line-height: 70px;
    cursor:pointer;
}
.nav-right-item{
    float:right;
    margin-top: 8px;
    margin-right: 15px;
    height: 70px;
    line-height: 70px;
}
ul {
    padding: 0;
    margin: 0;
    list-style: none;
}
li {
    display: list-item;
    list-style: none;
}
.contents {
    height: 100%;
    overflow: hidden;
    padding: 85px 4% 100px;
}

</style>