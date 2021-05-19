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

        <div v-if="activeIndex == 1">
            <div slot="header" class="clearfix" style="padding-bottom:40px;margin-top:20px" />

            <!-- lists -->
            <el-card v-for="booklist in booklists" v-bind:key="booklist.id" class="booklist_card" :body-style="{ padding: '0px' }">
                <div @click="viewBooks(booklist.id)">
                    <h2 style="text-align:center">{{booklist.listName}}</h2>
                </div>
                <p class="booklist_description">
                    {{booklist.description}}
                </p>
                <div class="booklist_card_foot">
                    <el-button type="primary" @click="openUpdateForm(booklist)">编辑书单</el-button>
                    <el-button type="danger" @click="delBooklist(booklist.id)">删除书单</el-button>
                </div>
            </el-card>
            <el-card class="booklist_card" :body-style="{ padding: '0px' }">
                <div>
                    <div style="text-align:center;margin:100px;cursor:pointer" >
                        <h2 @click="openAddForm">+<br>新增书单</h2>
                    </div>
                </div>
            </el-card>

            <!-- booksTable -->
            <div>
                <el-dialog title="书单书目" :visible.sync="booksVisible"  width="60%">
                    <el-table  :data="books" stripe key='1'>
                        <el-table-column label="序号" align="center" width="50">
                            <template slot-scope="scope">
                                <p>{{scope.$index + 1 }}</p>
                            </template>
                        </el-table-column>
                        <el-table-column prop="bookName" label="书籍名称" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="bookType" label="书籍类型" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="author" label="作者" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="publisher" label="出版社" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="price" label="定价" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="stock" label="在库数" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="total" label="库存" :show-overflow-tooltip="true" align="center" />
                        <el-table-column label="操作" width="360" align="center">
                            <template slot-scope="scope">
                                <el-button v-if="scope.row.bookName != null">
                                    <router-link style="text-decoration: none;color:black" :to="{ path: '/bookDetail/' + scope.row.id}" >
                                    查看详情
                                    </router-link>
                                </el-button>
                                <el-button v-else type="text">该图书已失效</el-button>
                                <el-button type="danger" @click="removeBook(scope.row.id)">移出书单</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-dialog>
            </div>

            <!-- AddForm -->
            <div>
                <el-dialog title="新建书单" :visible.sync="AddFormVisible"  width="40%">
                    <el-form ref="booklistForm" :model="booklistForm" :rules="rules" label-width="100px">
                        <el-form-item prop="listName" label="书单名" style="width: 90%" >
                            <el-input type="text" v-model="booklistForm.listName" placeholder="请输入书单名称"></el-input>
                        </el-form-item>
                        <el-form-item prop="description" label="书单名" style="width: 90%" >
                            <el-input type="textarea" v-model="booklistForm.description" placeholder="请输入书单描述"></el-input>
                        </el-form-item>
                        <el-form-item style="width: 90%" >
                            <el-button type="primary" @click="addBooklist('booklistForm')">提交</el-button>
                            <el-button type="" @click="closeAddForm">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

            <!-- UpdateForm -->
            <div>
                <el-dialog title="新建书单" :visible.sync="UpdateFormVisible"  width="40%">
                    <el-form ref="booklistForm" :model="booklistForm" :rules="rules" label-width="100px">
                        <el-form-item prop="listName" label="书单名" style="width: 90%" >
                            <el-input type="text" v-model="booklistForm.listName" placeholder="请输入书单名称"></el-input>
                        </el-form-item>
                        <el-form-item prop="description" label="书单名" style="width: 90%" >
                            <el-input type="textarea" v-model="booklistForm.description" placeholder="请输入书单描述"></el-input>
                        </el-form-item>
                        <el-form-item style="width: 90%" >
                            <el-button type="primary" @click="updateBooklist('booklistForm')">提交</el-button>
                            <el-button type="" @click="closeUpdateForm">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

        </div>

        <div v-if="activeIndex == 2">
            <div slot="header" class="clearfix" style="padding-bottom:40px;margin-top:20px" />

            <!-- lists -->
            <div>
                <el-card v-for="booklist in booklists" v-bind:key="booklist.id" class="booklist_card" :body-style="{ padding: '0px' }">
                    <div @click="viewBooks(booklist.id)">
                        <h2 style="text-align:center">{{booklist.listName}}</h2>
                    </div>
                    <p class="booklist_description">
                        {{booklist.description}}
                    </p>
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

            <!-- booksTable -->
            <div>
                <el-dialog title="书单书目" :visible.sync="booksVisible"  width="60%">
                    <el-table  :data="books" stripe key='1'>
                        <el-table-column label="序号" align="center" width="50">
                            <template slot-scope="scope">
                                <p>{{scope.$index + 1 }}</p>
                            </template>
                        </el-table-column>
                        <el-table-column prop="bookName" label="书籍名称" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="bookType" label="书籍类型" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="author" label="作者" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="publisher" label="出版社" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="price" label="定价" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="stock" label="在库数" :show-overflow-tooltip="true" align="center" />
                        <el-table-column prop="total" label="库存" :show-overflow-tooltip="true" align="center" />
                        <el-table-column label="操作" width="360" align="center">
                            <template slot-scope="scope">
                                <el-button v-if="scope.row.bookName != null">
                                    <router-link style="text-decoration: none;color:black" :to="{ path: '/bookDetail/' + scope.row.id}" >
                                    查看详情
                                    </router-link>
                                </el-button>
                                <el-button v-else type="text">该图书已失效</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
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
            booklists:[],
            books:[],
            activeIndex:'1',
            total:0,
            searchForm:{
                limit:5,
                page:1
            },
            booklistForm:{
                userId:"",
                listName:"",
                description:"",
                books:"",
            },
            AddFormVisible: false,
            UpdateFormVisible: false,
            booksVisible:false,
            currentBookList:"",
            rules: {
                listName: [
                    { required: true, message: '请输入书单名称', trigger: 'blur' },
                ],
            }

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
            var userId = sessionStorage.getItem("user");
            this.$axios.get('api/library/userBookList/getUserBookLists?userId=' + userId)
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
        resetForm(){
            this.booklistForm = {
                userId:"",
                listName:"",
                description:"",
                books:"",
            }
        },
        closeAddForm(){
            this.AddFormVisible = false;
        },
        openAddForm(){
            this.resetForm();
            this.AddFormVisible = true;
        },
        addBooklist(formName){
            this.booklistForm.userId = sessionStorage.getItem("user");
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/userBookList/addUserBookList", this.booklistForm)
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
        openUpdateForm(booklist){
            this.booklistForm = booklist;
            this.UpdateFormVisible = true;
        },
        closeUpdateForm(){
            this.UpdateFormVisible = false;
            this.getUserBookLists();
        },
        updateBooklist(formName){
            this.booklistForm.userId = sessionStorage.getItem("user");
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/userBookList/updateUserBookList", this.booklistForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '编辑成功',
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
                    });
                } 
              else {
                  return false;
                }
            });
        },
        delBooklist(userBookListId){
            var userId = sessionStorage.getItem("user");
            this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$axios.post('api/library/userBookList/deleteUserBookList?userId=' + userId + "&userBookListId=" + userBookListId)
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
        },
        viewBooks(booklistId){
            this.$axios.get('api/library/userBookList/getListBooks?userBookListId=' + booklistId)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取图书失败");
                }
                else if (res.data.total != 0){
                    this.books = res.data.data;
                }
                else {
                    this.books = [];
                }
                this.currentBookList = booklistId;
                this.booksVisible = true; 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        removeBook(bookId){
            this.$axios.post('api/library/userBookList/removeBook?bookId=' + bookId + "&userBookListId=" + this.currentBookList)
            .then(res => {
                    if (res.data == ""){
                        this.$message({
                            type: 'success',
                            message: '移出成功!'
                        });
                        this.viewBooks(this.currentBookList);
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
        getAllBookLists(){
            this.$axios.post('api/library/userBookList/getAllUserBookLists', this.searchForm)
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
            this.getAllBookLists();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getAllBookLists();
        },
        handleSelect(key, keyPath) {
            this.activeIndex = key;
            this.searchForm.page = 1;
            this.searchForm.limit = 5;
            this.booklists = [];
            this.total = 0;
            switch(key){
                case '1': this.getUserBookLists();break;
                case '2': this.getAllBookLists();break;
                default: console.log("wrong type");
            }
            console.log(this.booklists);
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
.page-part{
    display: flex;
    float: left;
    margin-left: 35%;
    width: 100%;
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