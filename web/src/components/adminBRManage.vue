<template>
    <div>
        <h1>借阅记录管理</h1>
        <hr />

        <!-- table -->
        <el-table @selection-change="handleSelectionChange" :data="records" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="userId" label="用户ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="bookId" label="图书ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="borrowDate" label="借出日期" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="returnDate" label="归还日期" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" @click="ReturnBook(scope.row.id)" :disabled="scope.row.status == 2">归还</el-button>
                    <el-button @click="ViewRecord(scope.row)">查看详情</el-button>
                </template>
            </el-table-column>
        </el-table>

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

        <!-- ViewForm -->
        <div>
            <el-dialog title="借阅记录详情" :visible.sync="FormVisible"  width="40%">
                <el-form ref="recordForm" :model="recordForm" label-width="100px" disabled>
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="recordForm.id"></el-input>
                    </el-form-item>
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="recordForm.userId"></el-input>
                    </el-form-item>
                    <el-form-item prop="bookId" label="图书ID" style="width: 90%" >
                        <el-input type="text" v-model="recordForm.bookId" ></el-input>
                    </el-form-item>
                    <el-form-item prop="borrowDate" label="借出日期" style="width: 90%" >
                        <el-input type="text" v-model="recordForm.borrowDate" ></el-input>
                    </el-form-item>
                    <el-form-item prop="returnDate" label="归还日期" style="width: 90%" >
                        <el-input type="text" v-model="recordForm.returnDate" ></el-input>
                    </el-form-item>
                    <el-form-item prop="status" label="状态" style="width: 90%" >
                        <el-select v-model="recordForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="0" value="0"></el-option>
                            <el-option key="1" label="1" value="1"></el-option>
                            <el-option key="2" label="2" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
        
    </div>
</template>

<script>
export default {
    data(){
        return {
            records:[],
            recordForm:{
                id: "",
                userId: "",
                bookId: "",
                borrowDate: "",
                returnDate: "",
                status: ""
            },
            FormVisible: false,
            total:0,
            searchForm:{
                recordType:"borrow",
                userId:"",
                statusStr:"",
                limit:5,
                page:1,
            },
            multipleSelection: [],
        }
    },
    mounted(){
        var userId = sessionStorage.getItem("user");
        if (userId == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        this.$axios.get('api/library/user/userCheck?userId=' + userId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取用户失败，即将回到登录界面");
                    this.$router.push('/');
                }
                else{
                    if (res.data != '管理员'){
                        alert("用户权限不足，即将回到登录界面");
                        this.$router.push('/');
                    }
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        this.getRecords();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getRecords();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getRecords();
        },
        getRecords(){
            this.$axios
            .post('api/library/admin/getRecords', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取借阅记录列表失败");
                }
                else if (res.data.total != 0){
                    this.records = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.records = [];
                    this.total = 0;
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        ViewRecord(record){
            this.recordForm = record;
            this.FormVisible = true;
        },
        resetForm(){
            this.recordForm = {
                id: "",
                userId: "",
                bookId: "",
                borrowDate: "",
                returnDate: "",
                status: ""
            }
        },
        ReturnBook(bid){
            this.$axios.post('api/library/book/returnBook?borrowId=' + bid)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                         message: '归还成功',
                         type: 'success'
                    });
                    this.getRecords();
                }
                else{
                    this.$message.error(res.data);
                    }
                })
                .catch(err => {
                    this.$message.error("系统繁忙，请稍后再试");
                    console.log(err);
                });
        }
    }
}
</script>

<style>
.page-part{
    display: flex;
    float: left;
    justify-content: flex-end;
    margin-top: 2%;
    margin-left:30%;
}
</style>