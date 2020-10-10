<template>
    <div :style="background" >
        <div class="reg-log" >
            <el-form ref="user" :model="user" class="login-box" id="login">
                <div class="items">       
                    <a class="active">账号登录</a>
                </div>
                <div class="padding-cont pt-login">
                    <el-form-item >
                        <el-input v-model="user.username" type="text" placeholder="账号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.password" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submit" style="height: 50px;width:420px;">登录</el-button>
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

<script>
export default {
  data() {
      return {
        background:{
            width: '1920px',
            height: '911px',
            margin:'0',
            backgroundImage: "url(" + require("../img/login-bg2.jpg") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{
            username:"",
            password:"",
            role:""
        },
    }
  },
  methods: {
    submit(){
        this.$axios
            .get("api/library/user/login", this.user)
            .then(res => {
                if(res.data == null)
                    alert("用户名或密码错误");
                else{
                    console.log(res.data);
                    window.sessionStorage.setItem("user",JSON.stringify(res.data));
                    this.user.role = res.data.role;
                    this.jumpToList();
                }
            })
            .catch(err => {
                alert("系统繁忙，请稍后再试");
                console.log(err);
            });
        },
        jumpToList() {
            alert("登录成功");
            if(this.user.role == '0')
                this.$router.push('/userHome')
            else
                this.$router.push('/adminHome')
        }
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

.auto-login:hover {
    background-position: left -34px;
    color: #4d90fe;
}
.forget:hover {
    color: #4d90fe;
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