<template>
    <div :style="background" >
        <div class="reg-logs">
            <el-form ref="user" :model="user" :rules="rules" class="register-box">
                <div class="title">
                    <h3 style="float:left">注册</h3>
                </div>
                <div class="padding-cont">
                    <el-form-item prop="username">
                        <el-input type="text" v-model="user.username" placeholder="帐号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" v-model="user.password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item prop="nickname">
                        <el-input type="text" v-model="user.nickname" placeholder="昵称"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('user')" style="width:420px;">注册</el-button>
                    </el-form-item>
                    <router-link to="/" class="btn-login">
                        <span style="color: #AFB1B3;">已有账号？</span>
                        <span style="color: #32BAF0;">去登录</span>
                    </router-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
  data() {
      return {
          background:{
            width: '1920px',
            height: '911px',
            margin:'0',
            backgroundImage: "url(" + require("../assets/reg-bg.png") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{
            username: "",
            password: "",
            nickname: "",
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '请输入昵称', trigger: 'blur' }
          ]
        }
    }
  },
  methods: {
      submitForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/user/register", this.user)
                    .then(res => {
                            if (res.data == ""){
                                this.$message({
                                    message: '注册成功，跳往登录页面',
                                    type: 'success'
                                });
                                this.$router.push('/');
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
    },
    
  
}
</script>

<style scope>
.reg-logs{
    width: 480px;
    height: 515px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-65%);
}
.register-box {
    width: 480px;
    height: 504px;
    padding: 47px 41px 0;
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    box-sizing: border-box;
    border-radius: 4px;
}
.register-box h3 {
    font-size: 32px;
    font-weight: 400;
    color: #3B3D45;
    text-align: left;
}
.btn-login{
    float: right;
    margin-top: 24px;
    text-decoration: none;
}
.title h3 {
    line-height: 40px;
    font-size: 32px;
    color: #3B3D45;
}
.padding-cont {
    margin-top: 25px;
}

</style>