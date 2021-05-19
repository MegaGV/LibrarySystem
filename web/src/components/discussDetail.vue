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
        <div>
            <!-- title -->
            <div style="text-align:left;">
                <h1>
                    <el-avatar icon="el-icon-back" @click.native="toDiscussHome" />
                    {{discussForm.title}}
                    </h1>
                <el-button type="primary" @click="openCommentForm">评论</el-button>
                <el-button type="danger" v-if="isDiscussOwner" @click="DelDiscuss">删除帖子</el-button>
            </div>

            <!-- comments -->
            <div>
                <el-card shadow="hover" style="margin-top:20px"  :body-style="{ padding: '10px' }">
                    <div>
                        <p style="text-align:left">
                            {{discussForm.content}}
                            <br/>
                            <span style="float:right"> 
                                {{discussForm.publishDate}}
                            </span>
                        </p>
                    </div>
                </el-card>
                <el-card v-for="comment in comments" v-bind:key="comment.id" shadow="hover" style="margin-top:20px"  :body-style="{ padding: '10px' }">
                    <div>
                        <p style="text-align:left">
                            {{comment.content}}
                            <br/>
                            <span style="float:right">
                                <el-button type="text" v-if="isDiscussOwner" style="margin-right:10px" @click="DelComment(comment.id)">删除回复</el-button>
                                {{comment.publishDate}}
                            </span>
                        </p>
                    </div>
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
                <el-dialog title="评论内容" :visible.sync="commentFormVisible"  width="40%">
                    <el-form ref="commentForm" :model="commentForm" :rules="addRules" label-width="100px">
                        <el-form-item prop="content" label="内容" style="width: 90%" >
                            <el-input type="textarea" v-model="commentForm.content" placeholder="请输入内容"></el-input>
                        </el-form-item>
                        <el-form-item style="width: 90%" >
                            <el-button type="primary" @click="publishComment('commentForm')">提交</el-button>
                            <el-button type="" @click="closeCommentForm">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
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
            discussForm:"",
            comments: [],
            total: 0,
            commentForm:{
                userId:"",
                discussId:"",
                content:"",
            },
            commentFormVisible:false,
            searchForm:{
                discussId:"",
                limit:5,
                page:1
            },
            addRules: {
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
            },
        }
    },
    computed: {
        id () {
            return this.$route.params.id
        },
        isDiscussOwner () {
            return this.discussForm.userId == sessionStorage.getItem("user");
        },
        
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
        this.getDiscuss();
        this.getComments();
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
        toDiscussHome(){
            this.$router.push('/discussHome');
        },
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getComments();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getComments();
        },
        getDiscuss(){
            this.$axios.get('api/library/discuss/getDiscuss?discussId='+this.id)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取讨论失败");
                }
                else {
                    this.discussForm = res.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        getComments(){
            this.searchForm.discussId = this.id;
            this.$axios.post('api/library/discuss/getComments', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取评论失败");
                }
                else if (res.data.total != 0){
                    this.comments = res.data.data;
                    this.total = res.data.total;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        DelDiscuss(){
            var userId = sessionStorage.getItem("user");
            this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$axios.post('api/library/discuss/deleteDiscuss?userId=' + userId + "&discussId=" + this.id)
                .then(res => {
                    if (res.data == ""){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.toDiscussHome();
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
        },
        DelComment(cid){
            var userId = sessionStorage.getItem("user");
            this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$axios.post('api/library/discuss/deleteComment?userId=' + userId + "&commentId=" + cid)
                .then(res => {
                    if (res.data == ""){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getComments();
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
        },
        publishComment(formName){
            this.commentForm.userId = sessionStorage.getItem("user");
            this.commentForm.discussId = this.id;
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/discuss/publishComment", this.commentForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getComments();
                            this.closeCommentForm();
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
            this.commentForm = {
                userId:"",
                discussId:"",
                content:"",
            }
        },
        openCommentForm(){
            this.resetForm();
            this.commentFormVisible = true;
        },
        closeCommentForm(){
            this.commentFormVisible = false;
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