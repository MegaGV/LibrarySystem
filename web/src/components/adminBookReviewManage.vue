<template>
    <div>
        <h1>书评管理</h1>
        <hr />

        <!-- search -->
        <el-collapse>
            <el-collapse-item title="书评查询">
                <el-form :inline="true" :model="searchForm" ref="searchForm" style="width: 100%">
                    <el-form-item label="用户Id">
                        <el-input v-model="searchForm.userId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="图书Id">
                        <el-input v-model="searchForm.bookId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="内容">
                        <el-input v-model="searchForm.detail" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="searchForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="待审核" value="0"></el-option>
                            <el-option key="1" label="已过审" value="1"></el-option>
                            <el-option key="2" label="未过审" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getBookReviews()">查询</el-button>
                        <el-button type="" @click="clearSearch()">清空</el-button>
                    </el-form-item>
                </el-form>
            </el-collapse-item>
        </el-collapse>

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增书评</el-button>
        <el-button type="danger" @click="DelBookReview" class="top-button">删除书评</el-button>
        <el-table @selection-change="handleSelectionChange" :data="bookReviews" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="userId" label="用户ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="bookId" label="图书ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="title" label="标题" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="content" label="内容" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="300" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditBookReview(scope.row)">编辑</el-button>
                    <el-button v-if="scope.row.status == 0" type="success" @click="processExamine(scope.row.id)">过审</el-button>
                    <el-button v-if="scope.row.status == 0" type="danger" @click="notExamine(scope.row.id)">不过审</el-button>
                    <el-button v-else type="primary" @click="reExamine(scope.row.id)">重审</el-button>
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
            <el-dialog title="书评详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="bookReviewForm" :model="bookReviewForm" :rules="updateRules" label-width="110px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="bookId" label="图书ID" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.bookId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="textarea" v-model="bookReviewForm.title" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="bookReviewForm.content" ></el-input>
                    </el-form-item>
                    <el-form-item prop="status" label="状态" style="width: 90%" >
                        <el-select v-model="bookReviewForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="未审核" value="0"></el-option>
                            <el-option key="1" label="已过审" value="1"></el-option>
                            <el-option key="2" label="未过审" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="UpdateBookReview">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新增书评" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="bookReviewForm" :model="bookReviewForm" :rules="addRules" label-width="110px">
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="bookId" label="图书ID" style="width: 90%" >
                        <el-input type="text" v-model="bookReviewForm.bookId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="textarea" v-model="bookReviewForm.title" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="bookReviewForm.content" ></el-input>
                    </el-form-item>
                    <el-form-item prop="status" label="状态" style="width: 90%" >
                        <el-select v-model="bookReviewForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="未审核" value="0"></el-option>
                            <el-option key="1" label="已过审" value="1"></el-option>
                            <el-option key="2" label="未过审" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddBookReview('bookReviewForm')">提交</el-button>
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
            bookReviews:[],
            bookReviewForm:{
                id: "",
                userId: "",
                bookId: "",
                title: "",
                content: "",
                status: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                userId:"",
                bookId:"",
                detail:"",
                status:"",
                limit:5,
                page:1
            },
            multipleSelection: [],
            updateRules: {
                userId: [
                    { required: true, message: '请输入用户ID', trigger: 'blur' }
                ],
                bookId: [
                    { required: true, message: '请输入图书ID', trigger: 'blur' }
                ],
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
                status: [
                    { required: true, message: '请输入状态', trigger: 'blur' }
                ],
            },
            addRules: {
                userId: [
                    { required: true, message: '请输入用户ID', trigger: 'blur' }
                ],
                bookId: [
                    { required: true, message: '请输入图书ID', trigger: 'blur' }
                ],
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
                status: [
                    { required: true, message: '请输入状态', trigger: 'blur' }
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
        this.getBookReviews();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getBookReviews();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getBookReviews();
        },
        getBookReviews(){
            this.$axios
            .post('api/library/admin/getBookReviews', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取书评列表失败");
                }
                else if (res.data.total != 0){
                    this.bookReviews = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.bookReviews = [];
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
            this.getBookReviews();
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        EditBookReview(discuss){
            this.bookReviewForm = discuss;
            this.updateFormVisible = true;
        },
        AddBookReview(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/admin/addBookReview", this.bookReviewForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getBookReviews();
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
        DelBookReview(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteBookReview', this.multipleSelection)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getBookReviews();
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
        UpdateBookReview(){
            this.$axios.post('api/library/admin/updateBookReview', this.bookReviewForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getBookReviews();
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
            this.bookReviewForm = {
                id: "",
                userId: "",
                bookId: "",
                title: "",
                content: "",
                status: ""
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
        processExamine(reviewId){
            this.$axios
            .post('api/library/admin/processExamine?reviewId=' + reviewId)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '审核成功',
                        type: 'success'
                    });
                    this.getBookReviews();
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
        notExamine(reviewId){
            this.$axios
            .post('api/library/admin/notExamine?reviewId=' + reviewId)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '审核成功',
                        type: 'success'
                    });
                    this.getBookReviews();
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
        reExamine(reviewId){
            this.$axios
            .post('api/library/admin/reExamine?reviewId=' + reviewId)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '审核成功',
                        type: 'success'
                    });
                    this.getBookReviews();
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