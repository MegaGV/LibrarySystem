<template>
    <div>
        <h1>管理</h1>
        <hr />

        <!-- table -->


        <!-- page -->
        

        <!-- UpdateForm -->
        

        <!-- UpdateForm -->
        
    </div>
</template>

<script>
export default {
    data(){
        return {
            total:0,
            searchForm:{
                
            },
            multipleSelection: [],
            updateRules: {
                
            },
            addRules: {
                
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
        this.getUsers();
    },
    methods:{
        handleSizeChange(val) {
            this.searchForm.limit = val;
            this.searchForm.page=1;
            this.getUsers();
        },
        handleCurrentChange(val) {
            this.searchForm.page = val;
            this.getUsers();
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