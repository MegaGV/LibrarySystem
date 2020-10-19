<template>
    <div :style="background" >
        <div class="reg-log" >
            <el-form ref="user" :model="user" :rules="rules" class="login-box">
                <div class="items">       
                    <a class="active">账号登录</a>
                </div>
                <div class="padding-cont">
                    <el-form-item prop="username">
                        <el-input v-model="user.username" type="text" placeholder="账号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="user.password" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('user')" style="height: 50px;width:420px;">登录</el-button>
                    </el-form-item>
                    
                    <router-link to="/register" class="btn-register">
                        <span class="str1">还没有账号？</span>
                        <span class="str2">去注册</span>
                    </router-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script scope>
export default {
  data() {
      return {
        background:{
            width: '1920px',
            height: '911px',
            margin:'0',
            backgroundImage: "url(" + require("../assets/login-bg2.jpg") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{
            username:"",
            password:"",
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
    }
  },
  methods: {
      submitForm(formName) {
          this.$refs[formName].validate((valid) => {
              if (valid) {
                  this.$axios
                    .post("api/library/user/login", this.user)
                    .then(res => {
                        console.log(res.data);
                        if(res.data == ""){
                             this.$message.error("用户名或密码错误");
                        }
                        else{
                            window.sessionStorage.setItem("user",JSON.stringify(res.data.id));
                            this.$message({
                                    message: '登录成功',
                                    type: 'success'
                                });
                            if(res.data.role == '0')
                                this.$router.push('/userHome');
                            else
                                this.$router.push('/adminHome');
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

<style >
.reg-log{
    width: 480px;
    height: 515px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-65%);
}
.login-box {
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    border-radius: 4px;
}
.items {
    padding-top: 47px;
    padding-bottom: 58px;
}
.items a.active {
    font-size: 26px;
    font-family: PingFangSC-Medium;
    font-weight: 500;
}
.btn-register {
    float: right;
    text-decoration: none;
    margin-top: 24px;
}
.btn-register .str1 {
    color: #AFB1B3;
}
.btn-register .str2 {
    color: #32BAF0;
}
.login-box .padding-cont {
    padding: 0 30px 60px;
}

</style>
