<template>
    <el-container>
        <el-header style="background: #99a9bf;">
            <h1>图书馆后台管理系统</h1>
        </el-header>
        <el-container>
            <el-aside width="200px" class="nav-left">
                <el-menu>
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-menu" />数据管理
                        </template>
                        <el-menu-item index="2">用户管理</el-menu-item>
                        <el-menu-item index="3">图书管理</el-menu-item>
                        <el-submenu index="4">
                            <template slot="title">记录管理</template>
                            <el-menu-item index="4-1">借阅记录管理</el-menu-item>
                            <el-menu-item index="4-2">归还记录管理</el-menu-item>
                            <el-menu-item index="4-3">罚款记录管理</el-menu-item>
                        </el-submenu>     
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-setting" />系统管理 
                        </template>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <adminUserManage ></adminUserManage>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>
import adminUserManage from '@/components/adminUserManage'
export default {
    components:{adminUserManage},
    data(){
        return {
            selectIndex:"",
        }
    },
    mounted(){
        var id = sessionStorage.getItem("user") ;
        if (id == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        this.$axios.get('api/library/user/getUserInfo?id=' + id)
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

    },
    methods:{
        
    }
}
</script>

<style scoped>
.nav-left{
    background-color: rgb(238, 241, 246);
    min-height: 700px;
}
</style>