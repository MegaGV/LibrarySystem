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
                            <el-dropdown-item icon="el-icon-switch-button" @click.native="logout">退出账户</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </li>
            </ul>
        </div>
        <div class="contents">
            <el-form label-width="90px" class="userInfo-form" :model="userInfo">
                <el-row>
                    <el-col :span="8">
                        <el-form-item>
                            <el-avatar icon="el-icon-user-solid"></el-avatar>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="用户名：">
                            <span v-text="userInfo.nickname"></span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8" >
                        <el-form-item label="用户昵称：">
                            <span v-text="userInfo.username"></span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="密码：">
                            <el-button  @click="passwordVisible=true">点击修改密码</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="已借图书：">
                            <div v-if="userInfo.borrowed < 5">
                                <span v-text="userInfo.borrowed" style="color:green"></span>
                            </div>
                            <div v-else>
                                <span v-text="userInfo.borrowed" style="color:red"></span>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="状态：">
                            <div v-if="userInfo.status === '正常'">
                                <span v-text="userInfo.status" style="color:green"></span>
                            </div>
                            <div v-else>
                                <span v-text="userInfo.status" style="color:red"></span>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="余额：">
                            <div v-if="userInfo.balance >= 0">
                                <span v-text="userInfo.balance" style="color:green"></span>
                            </div>
                            <div v-else>
                                <span v-text="userInfo.balance" style="color:red"></span>
                            </div>
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="creditVisible=true">充值</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item>
                            <el-button type="primary" @click="toUserHome">
                                返回首页
                            </el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>

        <div >
            <el-dialog title="修改密码" :visible.sync="passwordVisible"  width="40%">
                <el-form :model="passwordForm" :rules="passwordRules" ref="passwordForm" label-width="100px">
                    <el-form-item prop="originPassword"  label="原密码" style="width: 90%" >
                        <el-input v-model="passwordForm.originPassword"  placeholder="请输入原密码" show-password />
                    </el-form-item>
                    <el-form-item  prop="newPassword" label="新密码" style="width: 90%" >
                        <el-input v-model="passwordForm.newPassword"  placeholder="请输入新密码" show-password />
                    </el-form-item>
                    <el-form-item prop="newPasswordCheck"  label="确认密码" style="width: 90%" >
                        <el-input v-model="passwordForm.newPasswordCheck"  placeholder="请再次输入新密码" show-password />
                    </el-form-item>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="resetPasswordForm()">取 消</el-button>
                    <el-button type="primary" @click="submitPasswordForm('passwordForm')">确定修改</el-button>
                </div>
            </el-dialog>
        </div>
        
        <div >
        <el-dialog title="充值" :visible.sync="creditVisible"  width="35%" :show-close="false" :close-on-click-modal="false">
            <el-form :model="creditForm" label-width="100px" :rules="creditRules" ref="creditForm">
            <el-form-item prop="money"  label="金额" style="width: 80%" >
                <el-input v-model.number="creditForm.money" type="number"  placeholder="请输入充值金额"  />
            </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer" style="width: 95%">
                <el-button @click="resetcreditForm()">取 消</el-button>
                <el-button type="primary" @click="submitCreditForm('creditForm')">确定充值</el-button>
            </div>
        </el-dialog>
        </div>

    </div>
</template>
<script>
export default {
    data(){

        var checkPassword = (rule, value, callback) => {
          if (value !== this.passwordForm.newPassword) {
            callback(new Error('密码不一致'))
          } else {
            callback()
          }
        };
        var checkMoney = (rule, value, callback) => {
            if (value <= 0) {
                callback(new Error('充值金额必须大于0'));
            } else {
                callback();
            }
        };

        return {
            logo:require("../assets/libLog.jpg"),
            passwordRules: {
                originPassword: [
                    { required: true, message: '请输入原密码', trigger: ['blur', 'change'] },
                ],
                newPassword: [
                    { required: true, message: '请输入新密码', trigger: ['blur', 'change'] },
                ],
                newPasswordCheck: [
                    { required: true, message: '请再次输入新密码', trigger: ['blur', 'change'] },
                    { validator: checkPassword, trigger: 'blur' }
                ],
            },
            creditRules:{
                money:[
                    { required: true, type: 'number', message: '请输入金额', trigger: 'blur' },
                    { validator: checkMoney, trigger: 'blur' }
                ]
            },
            userInfo:{},
            passwordForm:{
                userId:"",
                originPassword: "",
                newPassword: "",
                newPasswordCheck: "",
            },
            creditForm:{
              money:"",
              userId:"",
            },
            passwordVisible: false,
            creditVisible: false,
            
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
        this.getUserInfo();
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
        toUserHome(){
            this.$router.push('/userHome');
        },
        getUserInfo(){
            var userId = sessionStorage.getItem("user");
            this.$axios.get('api/library/user/getUserInfo?userId=' + userId)
            .then(res => {
                this.userInfo = res.data;
                // console.log(this.userInfo);
            })
            .catch(err => {
                this.$message.error("系统繁忙，请稍后再试");
                console.log(err);
            })
        },
        submitPasswordForm(formName) {
          this.passwordForm.userId = sessionStorage.getItem("user");
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/user/updatePassword", this.passwordForm)
                    .then(res => {
                        if(res.data == ""){
                             this.$message({
                                    message: '密码修改成功',
                                    type: 'success'
                                });
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
        resetPasswordForm(){
          this.passwordVisible = false;
          this.$refs['passwordForm'].resetFields();
        },
        resetcreditForm(){
            this.creditVisible = false;
            this.$refs['creditForm'].resetFields();
        },
        submitCreditForm(formName) {
          this.creditForm.userId = sessionStorage.getItem("user");
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/user/credit", this.creditForm)
                    .then(res => {
                        if(res.data == ""){
                            this.$message({
                                    message: '充值成功',
                                    type: 'success'
                                });
                            this.getUserInfo();
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
.userInfo-form{
    padding-left: 40%;
    padding-top: 1%;
}
</style>