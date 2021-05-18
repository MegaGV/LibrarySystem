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
        <!-- table -->
        <div>
            <div slot="header" class="clearfix" style="padding-bottom:30px">
                <h1>讨论区</h1>
                <el-button type="primary" style="float:left;margin-bottom:10px" @click="openAddForm">发布新讨论</el-button>
            </div>
            <el-card v-for="discuss in discusses" v-bind:key="discuss.id" shadow="hover" style="margin-top:20px"  :body-style="{ padding: '10px' }">
                <router-link style="text-decoration: none;color:black;text-align:left" :to="{ path: '/discussHome/discussDetail/' + discuss.id}">
                    <h3>
                        {{discuss.title}}
                    </h3>
                </router-link>
                <span style="float:right">最新回复：{{discuss.publishDate}}</span>
            </el-card>
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

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新讨论" :visible.sync="discussFormVisible"  width="40%">
                <el-form ref="discussForm" :model="discussForm" :rules="addRules" label-width="100px">
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="text" v-model="discussForm.title" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="discussForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="publishDiscuss('discussForm')">提交</el-button>
                        <el-button type="" @click="closeAddForm">取消</el-button>
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
        return{
            logo:require("../assets/libLog.jpg"),
            discusses:[],
            discussForm: {
                userId:"",
                title:"",
                content:"",
            },
            discussFormVisible: false,
            total:0,
            searchForm:{
                limit:5,
                page:1
            },
            addRules: {
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
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
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        this.getDiscusses();
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
        getDiscusses(){
            this.$axios.post('api/library/discuss/getDiscusses', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.discusses = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.discusses = [];
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
        publishDiscuss(formName){
            this.discussForm.userId = sessionStorage.getItem("user");
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/discuss/publishDiscuss", this.discussForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getDiscusses();
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
        resetForm(){
            this.discussForm = {
                title: "",
                content: ""
            }
        },
        openAddForm(){
            this.resetForm();
            this.discussFormVisible = true;
        },
        closeAddForm(){
            this.discussFormVisible = false;
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