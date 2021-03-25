<template>
    <div>
        <h1>用户管理</h1>
        <hr />

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增用户</el-button>
        <el-button type="danger" @click="DelUser" class="top-button">删除用户</el-button>
        <el-table @selection-change="handleSelectionChange" :data="users" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="username" label="用户名" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="password" label="密码" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="nickname" label="昵称" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="role" label="角色" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="borrowed" label="借书数" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="balance" label="余额" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditUser(scope.row)">编辑</el-button>
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
            <el-dialog title="用户详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="userForm" :model="userForm" :rules="updateRules" label-width="100px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="userForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="username" label="用户名" style="width: 90%" >
                        <el-input type="text" v-model="userForm.username" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码" style="width: 90%" >
                        <el-input type="text" v-model="userForm.password" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="nickname" label="昵称" style="width: 90%" >
                        <el-input type="text" v-model="userForm.nickname" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="role" label="角色" style="width: 90%" >
                        <el-select v-model="userForm.role" clearable placeholder="请选择">
                            <el-option key="0" label="0" value="0"></el-option>
                            <el-option key="1" label="1" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="borrowed" label="借书数" style="width: 90%" >
                        <el-input v-model.number="userForm.borrowed" type="number"  placeholder="请输入内容" :min="0" :max="5" />
                    </el-form-item>
                    <el-form-item prop="status" label="状态" style="width: 90%" >
                        <el-select v-model="userForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="0" value="0"></el-option>
                            <el-option key="1" label="1" value="1"></el-option>
                            <el-option key="2" label="2" value="2"></el-option>
                            <el-option key="3" label="3" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="balance" label="余额" style="width: 90%" >
                            <el-input v-model.number="userForm.balance" type="number"  placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="UpdateUser">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新增用户" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="userForm" :model="userForm" :rules="addRules" label-width="100px">
                    <el-form-item prop="username" label="用户名" style="width: 90%" >
                        <el-input type="text" v-model="userForm.username" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码" style="width: 90%" >
                        <el-input type="text" v-model="userForm.password" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="nickname" label="昵称" style="width: 90%" >
                        <el-input type="text" v-model="userForm.nickname" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddUser('userForm')">提交</el-button>
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
            users:[],
            userForm:{
                id: "",
                username: "",
                password: "",
                nickname: "",
                role: "",
                borrowed: "",
                status: "",
                balance: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                limit:5,
                page:1
            },
            multipleSelection: [],
            updateRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                nickname: [
                    { required: true, message: '请输入昵称', trigger: 'blur' }
                ],
                role: [
                    { required: true, message: '请选择角色', trigger: 'blur' }
                ],
                borrowed: [
                    { required: true, type: 'number', message: '请输入借书数', trigger: 'blur' },
                ],
                status: [
                    { required: true, message: '请选择状态', trigger: 'blur' },
                ],
                balance: [
                    { required: true, type: 'number', message: '请输入余额', trigger: 'blur' },
                ],
            },
            addRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                nickname: [
                    { required: true, message: '请输入昵称', trigger: 'blur' }
                ]
            },

        }
    },
    mounted(){
        var userId = sessionStorage.getItem("user");
        if (userId == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        this.$axios.get('api/library/user/getUserInfo?userId=' + userId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取用户失败，即将回到登录界面");
                    this.$router.push('/');
                }
                else{
                    if (res.data.role != '管理员'){
                        alert("用户权限不足，即将回到登录界面");
                        this.$router.push('/');
                    }
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        this.getUsers();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getUsers();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getUsers();
        },
        getUsers(){
            this.$axios.get('api/library/admin/getUsers?limit=' + this.searchForm.limit + "&page=" + this.searchForm.page)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取用户列表失败");
                }
                else if (res.data.total != 0){
                    this.users = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.users = [];
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
        EditUser(user){
            this.userForm = user;
            this.updateFormVisible = true;
        },
        AddUser(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/admin/addUser", this.userForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getUsers();
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
        DelUser(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteUser', this.multipleSelection)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getUsers();
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
        UpdateUser(){
            this.$axios.post('api/library/admin/updateUser', this.userForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getUsers();
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
            this.userForm = {
                id: "",
                username: "",
                password: "",
                nickname: "",
                role: "",
                borrowed: "",
                status: "",
                balance: ""
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