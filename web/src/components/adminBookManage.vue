<template>
    <div>
        <h1>图书管理</h1>
        <hr />

        <!-- search -->
        <el-collapse>
            <el-collapse-item title="图书查询">
                <el-form :inline="true" :model="searchForm" ref="searchForm" style="width: 100%">
                    <el-form-item label="书籍名称">
                        <el-input v-model="searchForm.bookName" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="书籍种类">
                        <el-cascader clearable v-model="selectTypes" @change="handleTypeChange" :options="bookTypes" :props="{ expandTrigger: 'hover' }">
                        </el-cascader>
                    </el-form-item>
                    <el-form-item label="作者">
                        <el-input v-model="searchForm.author" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="出版社">
                        <el-input v-model="searchForm.publisher" placeholder="请输入内容" />
                    </el-form-item>
                    <el-form-item label="库存">
                        <el-select v-model="searchForm.stock" clearable placeholder="请选择">
                            <el-option key="0" label="无" value="0"></el-option>
                            <el-option key="1" label="有" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getBooks()">查询</el-button>
                        <el-button type="" @click="clearSearch()">清空</el-button>
                    </el-form-item>
                </el-form>
            </el-collapse-item>
        </el-collapse>

        <!-- table -->
        <el-button type="primary" @click="openAddForm" class="top-button">新增图书</el-button>
        <el-button type="danger" @click="DelBook" class="top-button">删除图书</el-button>
        <el-table @selection-change="handleSelectionChange" :data="books" stripe>
            <el-table-column type="selection" width="50" />
            <el-table-column label="序号" width="50" align="center">
                <template slot-scope="scope">
                    <p>{{scope.$index + 1 + (searchForm.page - 1)*searchForm.limit}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="ID" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="bookName" label="书籍名称" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="bookType" label="书籍类型" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="author" label="作者" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="publisher" label="出版社" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="price" label="定价" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="stock" label="在库数" :show-overflow-tooltip="true" align="center" />
            <el-table-column prop="total" label="库存" :show-overflow-tooltip="true" align="center" />
            <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                    <el-button @click="EditBook(scope.row)">编辑</el-button>
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
            <el-dialog title="图书详情" :visible.sync="updateFormVisible"  width="40%">
                <el-form ref="bookForm" :model="bookForm" :rules="rules" label-width="100px">
                    <el-form-item prop="id" label="ID" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.id" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="bookName" label="书籍名称" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.bookName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="bookType" label="书籍种类" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.bookType" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="author" label="作者" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.author" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="publisher" label="出版社" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.publisher" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="price" label="定价" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.price" placeholder="请输入内容" :min="1"></el-input>
                    </el-form-item>
                    <el-form-item prop="stock" label="在库数" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.stock" placeholder="请输入内容" :min="0"></el-input>
                    </el-form-item>
                    <el-form-item prop="total" label="库存" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.total" placeholder="请输入内容" :min="1"></el-input>
                    </el-form-item>
                    <el-form-item prop="detail" label="简介" style="width: 90%" >
                        <el-input type="textarea" v-model="bookForm.detail" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="updateBook">提交</el-button>
                        <el-button type="" @click="closeUpdateForm">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

        <!-- AddForm -->
        <div>
            <el-dialog title="新增图书" :visible.sync="addFormVisible"  width="40%">
                <el-form ref="bookForm" :model="bookForm" :rules="rules" label-width="100px">
                    <el-form-item prop="bookName" label="书籍名称" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.bookName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="bookType" label="书籍种类" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.bookType" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="author" label="作者" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.author" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="publisher" label="出版社" style="width: 90%" >
                        <el-input type="text" v-model="bookForm.publisher" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item prop="price" label="定价" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.price" placeholder="请输入内容" :min="1"></el-input>
                    </el-form-item>
                    <el-form-item prop="stock" label="在库数" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.stock" placeholder="请输入内容" :min="0"></el-input>
                    </el-form-item>
                    <el-form-item prop="total" label="库存" style="width: 90%" >
                        <el-input type="number" v-model="bookForm.total" placeholder="请输入内容" :min="1"></el-input>
                    </el-form-item>
                    <el-form-item prop="detail" label="简介" style="width: 90%" >
                        <el-input type="textarea" v-model="bookForm.detail" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item style="width: 90%" >
                        <el-button type="primary" @click="AddBook('bookForm')">提交</el-button>
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
        var checkMoney = (rule, value, callback) => {
            if (value <= 0) {
                callback(new Error('数值必须大于0'));
            } else {
                callback();
            }
        };

        return {
            books:[],
            bookForm:{
                id: "",
                bookName: "",
                bookType: "",
                author: "",
                publisher: "",
                price: "",
                stock: "",
                total: "",
                detail: ""
            },
            updateFormVisible: false,
            addFormVisible: false,
            total:0,
            searchForm:{
                bookName:"",
                bookType:"",
                author:"",
                publisher:"",
                stock:"",
                limit:5,
                page:1
            },
            bookTypes:[],
            selectTypes:[],
            multipleSelection: [],
            rules: {
                bookName: [
                    { required: true, message: '请输入书籍名称', trigger: 'blur' }
                ],
                bookType: [
                    { required: true, message: '请输入书籍分类', trigger: 'blur' }
                ],
                price: [
                    { required: true, message: '请输入书籍定价', trigger: 'blur' },
                    { validator: checkMoney, trigger: 'blur' }
                ],
                stock: [
                    { required: true, message: '请输入书籍在库数', trigger: 'blur' },
                    { validator: checkMoney, trigger: 'blur' }
                ],
                total: [
                    { required: true, message: '请输入书籍库存', trigger: 'blur' },
                    { validator: checkMoney, trigger: 'blur' }
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
        this.getBooks();
        this.getBookTypes();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getBooks();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getBooks();
        },
        getBookTypes(){
            this.$axios.get('api/library/book/getBookTypes')
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取图书类型失败");
                }
                else if (res.data.total != 0){
                    this.bookTypes = res.data.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        getBooks(){
            this.$axios.post('api/library/admin/getBooks', this.searchForm)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取记录失败");
                }
                else if (res.data.total != 0){
                    this.books = res.data.data;
                    this.total = res.data.total;
                }
                else{
                    this.books = [];
                    this.total = 0;
                } 
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        clearSearch(){
            this.searchForm.bookName = "";
            this.searchForm.bookType = "";
            this.searchForm.author = "";
            this.searchForm.publisher = "";
            this.searchForm.stock = "";
            this.selectTypes = "";
            this.getBooks();
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },
        handleTypeChange(){
            if (this.selectTypes != '')
                this.searchForm.bookType = this.selectTypes[this.selectTypes.length - 1];
            else
                this.searchForm.bookType = '';
        },
        EditBook(book){
            this.bookForm = book;
            this.updateFormVisible = true;
        },
        AddBook(formName){
            this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios.post("api/library/admin/addBook", this.bookForm)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getBooks();
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
        DelBook(){
            if (this.multipleSelection.length == 0)
            this.$message("未选中任何数据");
            else{
                this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/library/admin/deleteBook', this.multipleSelection)
                    .then(res => {
                        if (res.data == ""){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getBooks();
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
        updateBook(){
            this.$axios.post('api/library/admin/updateBook', this.bookForm)
            .then(res => {
                if (res.data == ""){
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.getBooks();
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
            this.bookForm = {
                id: "",
                bookName: "",
                bookType: "",
                author: "",
                publisher: "",
                price: "",
                stock: "",
                total: "",
                detail: ""
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