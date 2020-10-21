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
            <!-- search -->
            <div class="search-box">
                <el-form :model="searchForm" ref="searchForm" >
                    <el-form-item label="书籍名称">
                        <el-input v-model="searchForm.bookName" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="书籍种类">
                        <el-cascader clearable v-model="selectTypes" @change="handleTypeChange" :options="bookTypes" :props="{ expandTrigger: 'hover' }">
                        </el-cascader>
                    </el-form-item>
                    <el-form-item label="作者">
                        <el-input v-model="searchForm.author" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="出版社">
                        <el-input v-model="searchForm.publisher" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="库存">
                        <el-select v-model="searchForm.stock" clearable placeholder="请选择">
                            <el-option key="0" label="无" value="0"></el-option>
                            <el-option key="1" label="有" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getBookList()">查询</el-button>
                    </el-form-item>
                </el-form>
                
            </div>

            <!-- bookList -->
            <div class="result-box">
                <el-table stripe :data="books">
                    <el-table-column label="序号" width="50" align="center">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                        </template>
                    </el-table-column>
                    <el-table-column prop="bookName" label="书籍名称" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="bookType" label="书籍类型" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="author" label="作者" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="publisher" label="出版社" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="price" label="定价" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="stock" label="在库数" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="total" label="库存" :show-overflow-tooltip="true" align="center" />
                    <el-table-column label="操作" width="250" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewBook(scope.row)">查看详情</el-button>
                            <el-button type="primary" @click="borrowBook(scope.row)" :disabled="scope.row.stock=='0'">借阅</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            
            <!-- page -->
            <div class="page-part">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="searchForm.page"
                    :page-sizes="[5,10,15,20]"
                    :page-size="searchForm.limit"
                    layout="total, sizes,prev, pager, next,jumper"
                    :total="total">
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

        </div>

    </div>
</template>
<script>
export default {
    data(){
        return {
            logo:require("../assets/libLog.jpg"),
            searchForm:{
                bookName:"",
                bookType:"",
                author:"",
                publisher:"",
                stock:"",
                limit:5,
                page:1
            },
            selectTypes:[],
            bookTypes:[],
            books:[],
            total:0,
            bookVisible: false,
            bookForm:"",
        }
    },
    mounted(){
        var id = window.sessionStorage.getItem("user") ;
        if (id == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        this.getBookList();
        this.getBookTypes();
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
        getBookTypes(){
            this.$axios.get('api/library/book/getBookTypes')
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取图书类型失败");
                }
                else if (res.data.total != 0){
                    this.bookTypes = res.data.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        getBookList(){
            this.$axios.post('api/library/book/getBookList', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.books = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.books = [];
                    this.total = 0;
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.getBookList();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getBookList();
        },
        handleTypeChange(){
            if (this.selectTypes != '')
                this.searchForm.bookType = this.selectTypes[this.selectTypes.length - 1];
            else
                this.searchForm.bookType = '';
        },
        viewBook(book){
            this.bookForm = book;
            this.bookVisible = true;
        },
        borrowBook(book){
            if (book.stock == '0')
                this.$message('该书库存不足，无法借阅');
            var userId = window.sessionStorage.getItem("user") ;
            this.$axios.post('api/library/book/borrowBook?userId=' + userId +"&bookId=" + book.id)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '借阅成功',
                        type: 'success'
                    });
                    this.getBookList();
                }
                else {
                    this.$message.error(res.data);
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        }
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

.search-box{
    display: flex;
    float: left;
    width: 15%;
    align-items: center;
    border-right: gainsboro solid 1px;
}
.result-box{
    display: flex;
    float: right;
    width: 80%;
}
.page-part{
    display: flex;
    float: left;
    justify-content: flex-end;
    margin-top: 2%;
    margin-left:30%;
}
</style>