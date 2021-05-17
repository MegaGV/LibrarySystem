<template>
    <div>
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
                    <el-menu-item index="1">系统消息</el-menu-item>
                </el-menu>
                <el-row style="float:left;margin-top:20px">
                    <el-button>删除</el-button>
                    <el-button type="primary">设为已读</el-button>
                    <el-button type="danger">设为未读</el-button>
                </el-row>
            </div>
            <!-- table -->
            <template>
                <el-table @selection-change="handleSelectionChange" :data="messages.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe key='1'>
                    <el-table-column type="selection" width="50" />
                    <el-table-column label="序号" align="center" width="50">
                        <template slot-scope="scope">
                            <p>{{scope.$index + 1 + (currentPage - 1)*pagesize}}</p>
                        </template>
                    </el-table-column>
                    <el-table-column prop="title" label="标题" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column label="发送者" :show-overflow-tooltip="true" align="center">
                        系统
                    </el-table-column>
                    <el-table-column prop="receiveDate" label="接收日期" :show-overflow-tooltip="true" align="center"/>
                    <el-table-column prop="status" label="状态" :show-overflow-tooltip="true" align="center">
                        <template slot-scope="scope">
                        <el-tag :type="scope.row.status == '0' ? 'danger' : ''" disable-transitions>
                            {{scope.row.status == '0' ? '未读' : '已读'}}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column  label="操作" width="160" align="center">
                        <template slot-scope="scope">
                            <el-button @click="viewMessage(scope.row)">查看详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>

            <!-- page -->
            <div class="page-part">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage"
                    :page-sizes="[5,10,15,20]"
                    :page-size="pagesize"
                    layout="total, sizes,prev, pager, next,jumper"
                    :total="messages.length">
                </el-pagination>
            </div>

            <!-- MessageDetail -->
            <div>
                <el-dialog title="通知信息" :visible.sync="messageVisible"  width="40%">
                    <el-form :model="messageForm" label-width="100px">
                        <el-form-item label="" style="width: 90%" >
                            <h2>{{messageForm.title}}</h2>
                        </el-form-item>
                        <el-form-item label="" style="width: 90%" >
                            <span v-html="messageForm.content" />
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
        return {
            logo:require("../assets/libLog.jpg"),
            activeIndex: '1',
            currentPage: 1,
            pagesize: 5,
            messages: [],
            messageForm:"",
            messageVisible: false,
            multipleSelection: [],

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
        this.getMessages();
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
        handleSizeChange(val) {
            this.pagesize = val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        getMessages(){
            var userid = sessionStorage.getItem("user");
            this.$axios.get('api/library/message/getMessages?userId=' + userid)
            .then(res => {
                if (res.data == ""){
                    this.$message.error("获取通知失败");
                }
                else if (res.data.total != 0){
                    this.messages = res.data.data;
                }
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        viewMessage(message){
            this.messageForm = message;
            this.messageVisible = true;
        },
        handleSelectionChange(val){
            this.multipleSelection = [];
            for (var i = 0; i < val.length; i++){
                this.multipleSelection[i] = val[i].id;
            }
        },

    }
}
</script>

<style scoped>
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