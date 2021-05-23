<template>
    <div>
        <h1>讨论管理</h1>
        <hr />

        <!-- search -->
        <el-collapse>
            <el-collapse-item title="评论查询">
                <el-form :inline="true" :model="searchForm" ref="searchForm" style="width: 100%">
                    <el-form-item label="用户Id">
                        <el-input v-model="searchForm.userId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="讨论Id">
                        <el-input v-model="searchForm.discussId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getComments()">查询</el-button>
                        <el-button type="" @click="clearSearch()">清空</el-button>
                    </el-form-item>
                </el-form>
            </el-collapse-item>
        </el-collapse>

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增评论</el-button>
        <el-button type="danger" @click="DelComment" class="top-button">删除评论</el-button>
        <el-table @selection-change="handleSelectionChange" :data="comments" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="userId" label="用户ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="discussId" label="讨论ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="content" label="内容" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="publishDate" label="回复日期" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditComment(scope.row)">编辑</el-button>
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
            <el-dialog title="讨论详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="commentForm" :model="commentForm" :rules="updateRules" label-width="110px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="discussId" label="讨论ID" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.discussId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="commentForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="publishDate" label="最新回复日期" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.publishDate" ></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="UpdateComment">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新增评论" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="commentForm" :model="commentForm" :rules="addRules" label-width="110px">
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="discussId" label="讨论ID" style="width: 90%" >
                        <el-input type="text" v-model="commentForm.discussId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="commentForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddComment('commentForm')">提交</el-button>
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
            comments:[],
            commentForm:{
                id: "",
                userId: "",
                discussId: "",
                content: "",
                publishDate: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                userId:"",
                discussId:"",
                limit:5,
                page:1
            },
            multipleSelection: [],
            updateRules: {
                userId: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
                discussId: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
                ],
                publishDate: [
                    { required: true, message: '请输入日期', trigger: 'blur' }
                ],
            },
            addRules: {
                userId: [
                    { required: true, message: '请输入用户ID', trigger: 'blur' }
                ],
                discussId: [
                    { required: true, message: '请输入讨论ID', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入内容', trigger: 'blur' }
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
        this.getComments();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getComments();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getComments();
        },
        getComments(){
            this.$axios
            .post('api/library/admin/getComments', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取评论列表失败");
                }
                else if (res.data.total != 0){
                    this.comments = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.comments = [];
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
            this.searchForm.title = "";
            this.searchForm.detail = "";
            this.getComments();
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        EditComment(discuss){
            this.commentForm = discuss;
            this.updateFormVisible = true;
        },
        AddComment(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/admin/addComment", this.commentForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getComments();
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
        DelComment(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteComment', this.multipleSelection)
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
            }
        },
        UpdateComment(){
            this.$axios.post('api/library/admin/updateComment', this.commentForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getComments();
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
            this.commentForm = {
                id: "",
                userId: "",
                discussId: "",
                content: "",
                publishDate: ""
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