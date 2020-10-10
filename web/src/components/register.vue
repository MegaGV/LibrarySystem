<template>
    <div :style="background" >
        <div class="reg-logs">
            <el-form ref="user" :model="user" class="login-box">
                <div class="title">
                    <h3 style="float:left">注册</h3>
                </div>
                <div class="padding-cont">
                    <el-form-item>
                        <el-input type="text" v-model="user.username" placeholder="帐号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="password" v-model="user.password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="text" v-model="user.nickname" placeholder="昵称"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="register" style="width:420px;">注册</el-button>
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
            backgroundImage: "url(" + require("../img/reg-bg.png") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{
            username: "",
            password: "",
            nickname: "",
        }
    }
  },
  methods: {
      register(){
          this.$axios
            .post("api/library/user/register", this.user)
            .then(res => {
                alert(res.data);
                this.$router.push('/');
                })
            .catch(err => {
                alert("系统繁忙，请稍后再试");
                console.log(err);
                });
        }
    },
  
}
</script>

<style >
.reg-logs{
    width: 480px;
    height: 515px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-65%);
}
.identity-box {
    width: 480px;
    height: 504px;
    padding: 47px 41px 0;
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    box-sizing: border-box;
    border-radius: 4px;
}
.identity-box h3 {
    font-size: 32px;
    font-weight: 400;
    color: #3B3D45;
    text-align: left;
}
.identity-box .choose {
    margin-top: 70px;
    margin-left: 15px;
}
.identity-box .choose a {
    display: block;
    width: 330px;
    font-size: 16px;
    color: #fff;
    height: 64px;
    line-height: 64px;
    text-align: center;
    background: #32BAF0;
    border-radius: 4px;
    margin-left: 70px;
    position: relative;
}
.identity-box .choose a.student {
    margin-top: 70px;
}
.img{
    top: 2px;
    width: 60px;
    height: 60px;
    float: left;
}
.btn-login{
    float: right;
    margin-top: 24px;
    text-decoration: none;
}
.login-box {
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    border-radius: 4px;
}
.back {
    width: 40px;
    height: 40px;
    display: inline-block;
    margin-right: 22px;
    margin-top:32px;
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