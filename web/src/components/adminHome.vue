<template>
    
</template>
<script>
export default {
    mounted(){
        var id = sessionStorage.getItem("user") ;
        if (id == null ){
            alert("未登录，即将回到登录界面");
            this.$router.push('/');
        }
        var role = sessionStorage.getItem("role");
        if (role == 0){
            alert("普通用户无法使用管理员界面，即将回到用户主页");
            this.$router.push('/userHome');
        }
    },
    methods:{
        
    }
}
</script>

<style scoped>

</style>