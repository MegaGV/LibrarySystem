<template>
    <div>
        <h1>用户管理</h1>
        <hr />

        <!-- table -->
        <el-button type="primary" @click="AddUser" class="top-button">新增用户</el-button>
        <el-button type="danger" @click="DelUser" class="top-button">删除用户</el-button>
        <el-table stripe :data="users" @selection-change="handleSelectionChange">>
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
    </div>
</template>

<script>
export default {
    data(){
        return {
            users:[],
            userForm:"",
            userFormVisible: false,
            total:0,
            searchForm:{
                limit:5,
                page:1
            },
            multipleSelection: [],
        }
    },
    mounted(){
        var id = sessionStorage.getItem("user") ;
        if (id == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
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
            this.userFormVisible = true;
        },
        AddUser(){
            
        },
        DelUser(){

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
.top-button{
    float:left;
    margin-right: 5px;
}
</style>