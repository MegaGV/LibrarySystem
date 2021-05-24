<template>
    <div>
        <h1>通知管理</h1>
        <hr />

        <!-- search -->
        <el-collapse>
            <el-collapse-item title="通知查询">
                <el-form :inline="true" :model="searchForm" ref="searchForm" style="width: 100%">
                    <el-form-item label="用户Id">
                        <el-input v-model="searchForm.userId" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="内容">
                        <el-input v-model="searchForm.detail" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="searchForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="未读" value="0"></el-option>
                            <el-option key="1" label="已读" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getMessages()">查询</el-button>
                        <el-button type="" @click="clearSearch()">清空</el-button>
                    </el-form-item>
                </el-form>
            </el-collapse-item>
        </el-collapse>

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增通知</el-button>
        <el-button type="danger" @click="DelMessage" class="top-button">删除通知</el-button>
        <el-table @selection-change="handleSelectionChange" :data="messages" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="userId" label="用户ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="title" label="标题" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="content" label="内容" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="receiveDate" label="接收日期" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditMessage(scope.row)">编辑</el-button>
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
            <el-dialog title="通知详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="messageForm" :model="messageForm" :rules="updateRules" label-width="110px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="messageForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="messageForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="text" v-model="messageForm.title" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="messageForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="messageForm.status" clearable placeholder="请选择">
                            <el-option key="0" label="未读" value="0"></el-option>
                            <el-option key="1" label="已读" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="UpdateMessage">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- UpdateForm -->
        <div>
            <el-dialog title="新增通知" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="messageForm" :model="messageForm" :rules="addRules" label-width="110px">
                    <el-form-item prop="userId" label="用户ID" style="width: 90%" >
                        <el-input type="text" v-model="messageForm.userId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="title" label="标题" style="width: 90%" >
                        <el-input type="text" v-model="messageForm.title" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="内容" style="width: 90%" >
                        <el-input type="textarea" v-model="messageForm.content" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddMessage('messageForm')">提交</el-button>
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
            messages:[],
            messageForm:{
                id: "",
                userId: "",
                title: "",
                content: "",
                receiveDate: "",
                status: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                userId:"",
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
        this.getMessages();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getMessages();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getMessages();
        },
        getMessages(){
            this.$axios
            .post('api/library/admin/getMessages', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取通知列表失败");
                }
                else if (res.data.total != 0){
                    this.messages = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.messages = [];
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
            this.getMessages();
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        EditMessage(discuss){
            this.messageForm = discuss;
            this.updateFormVisible = true;
        },
        AddMessage(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/admin/addMessage", this.messageForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getMessages();
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
        DelMessage(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteMessage', this.multipleSelection)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getMessages();
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
        UpdateMessage(){
            this.$axios.post('api/library/admin/updateMessage', this.messageForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getMessages();
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
            this.messageForm = {
                id: "",
                userId: "",
                title: "",
                content: "",
                receiveDate: "",
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