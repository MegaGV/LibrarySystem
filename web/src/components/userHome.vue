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
    },
    methods:{
        
    }
}
</script>

<style scoped>

</style>