<template>

<div class="bge">
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
                        <el-dropdown-item icon="el-icon-bell" @click.native="toUserMessage">消息通知</el-dropdown-item>
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
                <el-menu-item index="1">我的书单</el-menu-item>
                <el-menu-item index="2">书单广场</el-menu-item>    
            </el-menu>
            <div class="line"></div>
        </div>

        <!-- lists -->
        <div v-if="activeIndex == 1">
            <div slot="header" class="clearfix" style="padding-bottom:40px;margin-top:20px">
            </div>

            <el-card v-for="booklist in booklists" v-bind:key="booklist.id" class="booklist_card" :body-style="{ padding: '0px' }">
                <div>
                    <strong >
                        <router-link style="text-decoration: none;color:black" :to="{ path: '/booklistHome/booklistDetail/' + booklist.id}">
                        <h2 style="text-align:center">{{booklist.listName}}</h2>
                        </router-link>
                    </strong>
                </div>
                <p class="booklist_description">
                    {{booklist.description}}
                </p>
                <div class="booklist_card_foot">
                    <el-button type="primary">编辑书单</el-button>
                    <el-button type="danger">删除书单</el-button>
                </div>
            </el-card>
            <el-card class="booklist_card" :body-style="{ padding: '0px' }">
                <div>
                    <div style="text-align:center;margin:100px;cursor:pointer" >
                        <h2>+<br>新增书单</h2>
                    </div>
                </div>
            </el-card>
        </div>

        <div v-if="activeIndex == 2">

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
        
    </div>
</div>

</template>
<script>
export default {
    data(){
        return{
            logo:require("../assets/libLog.jpg"),
            booklists:[],
            activeIndex:'1',
            total:0,
            searchForm:{
                limit:5,
                page:1
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
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        this.getUserBookLists();
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
        getUserBookLists(){
            var userid = sessionStorage.getItem("user");
            this.$axios.get('api/library/userBookList/getUserBookLists?userId=' + userid)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.booklists = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.booklists = [];
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
            this.searchForm.page=1;
            this.getDiscusses();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getDiscusses();
        },
        handleSelect(key, keyPath) {
            this.activeIndex = key;
            this.currentPage = 1;
            this.records = [];
            switch(key){
                case '1': this.getUserBookLists();break;
                case '2': break;
                default: console.log("wrong record type");
            }
            console.log(this.records);
            //console.log(key, keyPath);
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
.booklist_card{
    width: 300px;
    height: 250px;
    background: rgb(243, 241, 241);
    position: relative;
    border: 1px solid #E2E6ED;
    border-radius: 8px;
    float:left;
    margin:15px;
}
.booklist_description{
    margin-top: 15px;
    padding: 14px;
    height: 105px;
    border-bottom: 1px solid rgba(226,230,237,1);
}
.booklist_card_foot{
    width: 80%;
    position: absolute;
    bottom: 20px;
    left: 30px;
    right: 16px;
}

</style>