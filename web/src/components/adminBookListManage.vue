<template>
    <div>
        <h1>书单管理</h1>
        <hr />

        <!-- search -->
        <el-collapse>
            <el-collapse-item title="书单查询">
                <el-form :inline="true" :model="searchForm" ref="searchForm" style="width: 100%">
                    <el-form-item label="用户Id">
                        <el-input v-model="searchForm.userId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="书单名">
                        <el-input v-model="searchForm.listName" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getUserBookLists()">查询</el-button>
                        <el-button type="" @click="clearSearch()">清空</el-button>
                    </el-form-item>
                </el-form>
            </el-collapse-item>
        </el-collapse>

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增书单</el-button>
        <el-button type="danger" @click="DelUserBookList" class="top-button">删除书单</el-button>
        <el-table @selection-change="handleSelectionChange" :data="userBookLists" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="userId" label="用户ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="listName" label="书单名" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="description" label="描述" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditUserBookList(scope.row)">编辑</el-button>
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

        <!-- UpdateForm -->
        <div>
            <el-dialog title="书单详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="userBookListForm" :model="userBookListForm" :rules="updateRules" label-width="110px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="userBookListForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="userBookListForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="listName" label="标题" style="width: 90%" >
                        <el-input type="text" v-model="userBookListForm.listName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="description" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="userBookListForm.description" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="books" label="图书" style="width: 90%" >
                        <el-input type="textarea" v-model="userBookListForm.books" ></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="UpdateUserBookList">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新增书单" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="userBookListForm" :model="userBookListForm" :rules="addRules" label-width="110px">
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="userBookListForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="listName" label="书单名" style="width: 90%" >
                        <el-input type="text" v-model="userBookListForm.listName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="description" label="描述" style="width: 90%" >
                        <el-input type="textarea" v-model="userBookListForm.description" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddUserListForm('userBookListForm')">提交</el-button>
                        <el-button type="" @click="closeAddForm">取消</el-button>
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
            userBookLists:[],
            userBookListForm:{
                id: "",
                userId: "",
                listName: "",
                description: "",
                books: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                userId:"",
                listName:"",
                limit:5,
                page:1
            },
            multipleSelection: [],
            updateRules: {
                userId: [
                    { required: true, message: '请输入用户ID', trigger: 'blur' }
                ],
                listName: [
                    { required: true, message: '请输入书单名', trigger: 'blur' }
                ],
            },
            addRules: {
                userId: [
                    { required: true, message: '请输入用户ID', trigger: 'blur' }
                ],
                listName: [
                    { required: true, message: '请输入书单名', trigger: 'blur' }
                ],
            },

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
        this.getUserBookLists();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getUserBookLists();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getUserBookLists();
        },
        getUserBookLists(){
            this.$axios
            .post('api/library/admin/getUserBookLists', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取书单列表失败");
                }
                else if (res.data.total != 0){
                    this.userBookLists = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.userBookLists = [];
                    this.total = 0;
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        clearSearch(){
            this.searchForm.userId = "";
            this.searchForm.listName = "";
            this.getUserBookLists();
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        EditUserBookList(discuss){
            this.userBookListForm = discuss;
            this.updateFormVisible = true;
        },
        AddUserListForm(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/admin/addUserBookList", this.userBookListForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getUserBookLists();
                            this.closeAddForm();
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
              else {
                  return false;
                }
            });
        },
        DelUserBookList(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteUserBookList', this.multipleSelection)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getUserBookLists();
                        }
                        else{
                            this.$message.error(res.data);
                        } 
                    })
                    .catch(err => {
                        this.$message.error("系统繁忙，请稍后再试");
                        console.log(err);
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                });
            }
        },
        UpdateUserBookList(){
            this.$axios.post('api/library/admin/updateUserBookList', this.userBookListForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getUserBookLists();
                    this.closeUpdateForm();
                }
                else{
                   this.$message.error(res.data);
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        resetForm(){
            this.userBookListForm = {
                id: "",
                userId: "",
                listName: "",
                description: "",
                books: ""
            }
        },
        closeUpdateForm(){
            this.updateFormVisible = false;
        },
        openAddForm(){
            this.resetForm();
            this.addFormVisible = true;
        },
        closeAddForm(){
            this.addFormVisible = false;
        },
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
.top-button{
    float:left;
    margin-right: 5px;
}
</style>