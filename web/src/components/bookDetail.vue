<template>

<div class="bge">
    <div class="nav">
        <ul class="nav-left">
            <li class="nav-left-item">
                <el-image class="logo" :src="logo" style="width: 200px; height: 50px"></el-image>
            </li>
        </ul>
        <ul class="nav-right">
            <li class="nav-right-item">
                <el-dropdown style="margin-top:10px">
                    <el-avatar icon="el-icon-user-solid"></el-avatar>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item icon="el-icon-setting" @click.native="toUserInfo">个人设置</el-dropdown-item>
                        <el-dropdown-item icon="el-icon-bell" @click.native="toUserMessage">消息通知</el-dropdown-item>
                        <el-dropdown-item icon="el-icon-switch-button" @click.native="logout">退出账户</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </li>
        </ul>
    </div>
    <div class="contents">
        
    </div>
</div>

</template>
<script>
export default {
    data(){
        return{
            logo:require("../assets/libLog.jpg"),
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
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
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
        toUserMessage(){
            this.$router.push('/userMessage')
        },
        toUserHome(){
            this.$router.push('/userHome');
        },
    }
}
</script>

<style scoped>
.bge {
    height: 100%;
    width: 100%;
}
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