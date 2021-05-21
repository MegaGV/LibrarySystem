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
        <el-avatar icon="el-icon-back" style="float:left" @click.native="toBooks" />
        <!-- bookDetail -->
        <div>
            <div style="float:left;margin-left:20px;width:30%;">
                <el-card style="margin:0 auto;text-align:left">
                    <div slot="header">
                        <el-button type="info" @click="viewBookList" style="float:right;margin-left:10px;">加到书单</el-button>
                        <el-button type="primary" @click="borrowBook()" style="float:right">借阅</el-button>
                        <h1>图书详情</h1>
                    </div>
                    <p>书名：{{bookForm.bookName }}</p>
                    <p>图书类型：{{bookForm.bookType}}</p>
                    <p>作者：{{bookForm.author}}</p>
                    <p>出版社：{{bookForm.publisher}}</p>
                    <p>定价：{{bookForm.price}}</p>
                    <p>在库数：{{bookForm.stock}}</p>
                    <p>库存：{{bookForm.total}}</p>
                    <p>简介</p>
                    <p><span v-html="bookForm.detail" /></p>
                </el-card>
            </div>
            <div style="float:left;width:50%;margin-left:280px">
                <h1 style="text-align:left">
                    相关书评
                    <el-button type="primary" @click="openReviewForm()" style="float:right">撰写书评</el-button>
                </h1>
                <div v-if="bookReviews.length == 0">暂无相关书评</div>
                <div v-else>
                    <el-card v-for="bookReview in bookReviews" v-bind:key="bookReview.id" style="margin-top:10px" :body-style="{ padding: '10px' }">
                        <h2>{{bookReview.title}}</h2>
                        <p style="text-align:center">{{bookReview.user.nickname}}</p>
                        <p><span v-html="bookReview.content" /></p>
                    </el-card>
                </div>
            </div>
        </div>

        <!-- booklist -->
        <div>
            <el-dialog title="书单一览" :visible.sync="booklistVisible"  width="60%">
                <el-table  :data="booklists" stripe key='1'>
                    <el-table-column label="序号" align="center" width="50">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 }}</p>
                        </template>
                    </el-table-column>
                    <el-table-column prop="listName" label="书单名" :show-overflow-tooltip="true" align="center" />
                    <el-table-column prop="description" label="书单描述" :show-overflow-tooltip="true" align="center" />
                    <el-table-column label="操作" width="360" align="center">
                        <template slot-scope="scope">
                            <el-button type="primary" @click="addBook(scope.row.id)">加到书单</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>
        </div>

        <!-- AddBookReview -->
        <div>
            <el-dialog title="撰写书评" :visible.sync="bookRevieFormVisible"  width="40%">
                <el-form ref="bookReviewForm" :model="bookReviewForm" :rules="addRules" label-width="100px">
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.title" placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="bookReviewForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="publishBookReview('bookReviewForm')">提交</el-button>
                        <el-button type="" @click="closeBookReviewForm">取消</el-button>
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
            bookForm: "",
            bookReviews: [],
            booklists: [],
            booklistsize: 0,
            booklistVisible: false,
            bookRevieFormVisible: false,
            bookReviewForm:{
                userId: "",
                bookId: "",
                title: "",
                content: "",
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
    computed: {
        id () {
            return this.$route.params.id
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
        this.getBookDetail();
        this.getBookReviews();
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
        toBooks(){
            this.$router.push('/books');
        },
        getBookDetail(){
            this.$axios.get('api/library/book/getBookDetail?bookId='+this.id)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取图书失败");
                }
                else {
                    this.bookForm = res.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        getBookReviews(){
            this.$axios.get('api/library/bookReview/getBookReviewsByBook?bookId='+this.id)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取书评失败");
                }
                else if (res.data.total != 0){
                    this.bookReviews = res.data.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        getUserBookLists(){
            var userId = sessionStorage.getItem("user");
            this.$axios.get('api/library/userBookList/getUserBookLists?userId=' + userId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.booklists = res.data.data;
                    this.booklistsize = res.data.total;
                }
                else{
                    this.booklists = [];
                    this.booklistsize = 0;
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        viewBookList(){
            this.getUserBookLists();
            this.booklistVisible = true;
        },
        addBook(listId){
            this.$axios.post('api/library/userBookList/addBook?bookId=' + this.id + "&userBookListId=" + listId)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
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
        },
        publishBookReview(formName){
            this.bookReviewForm.userId = sessionStorage.getItem("user");
            this.bookReviewForm.bookId = this.id;
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/bookReview/publishBookReview", this.bookReviewForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功，请等待通过审核',
                                type: 'success'
                            });
                            this.closeReviewForm();
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
            this.bookReviewForm = {
                userId:"",
                bookId:"",
                title: "",
                content:"",
            }
        },
        openReviewForm(){
            this.resetForm();
            this.bookRevieFormVisible = true;
        },
        closeReviewForm(){
            this.bookRevieFormVisible = false;
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