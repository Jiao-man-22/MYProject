<template>
    <!-- 注册页面 -->
    <div id="gen" class="image-defined">
        <!-- <div class="head">头部</div>
        <div class="nav"> 导航</div> -->
      <div class="register-wrapper">
      <div id="register">
        <p class="title"> 注册</p>
        <el-form
          status-icon
          ref="ruleForm2"
          label-position="left"
          label-width="40px"
          class="demo-ruleForm"
          :model="regsiterForm"
          :rules="ruleForm"
        >
          <el-form-item  prop="iphone">
            <el-input  class="width-define" auto-complete="off" placeholder="请输入手机号" v-model="regsiterForm.iphone" oninput="value=value.replace(/^\.+|[^\d.]/g,'')"></el-input>
          </el-form-item>

          <el-form-item class="code" prop="code">
            <el-input v-model="regsiterForm.code" placeholder="验证码" style="width: 250px;" oninput="value=value.replace(/^\.+|[^\d.]/g,'')"></el-input>
            <el-button
              type="primary"
              class="bt1"
              >{{ buttonText }}</el-button
            >
          </el-form-item>
          <el-form-item  prop="password">
            <el-input class="width-define"
              type="password"
              v-model="regsiterForm.password"
              auto-complete="off"
              placeholder="输入密码"
            ></el-input>
          </el-form-item>
          <el-form-item  prop="checkPass">
            <el-input
              class="width-define"
              type="password"
              v-model="regsiterForm.checkPass"
              auto-complete="off"
              placeholder="确认密码"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="submitForm('regsiterForm')"
              style="width:80%;"
              >注册</el-button
            >
            <p class="login" @click="gotoLogin">已有账号？立即登录</p>
          </el-form-item>
        </el-form>
      </div>
    </div>

    </div>

</template>
<style scoped>
html,
body {
  height: 100%;
  width: 100%;
  margin: 0, 0;
}

.image-defined {
  background-image: url(../assets/reg_bg_min.jpg);
  background-position: center;
  background-size: cover;
  background-attachment: fixed;
  height: 600px;
  margin: 0, 0;
  padding: 0, 0;
  position: relative;
}
.register-wrapper{
    background-color: white;
    opacity: 0.85;
    width: 40% ;
    height: 400px ;
    margin: auto   auto;
    position: absolute;
    left: 30%;
    top: 10%;
    padding: 10px;
    border-radius: 5%;

}
.code{
    display: inline-block;
}
.bt1{
    position: absolute;
    margin-left:2px;/* right: 0; */    
}
.title{
    margin-left: 40px;
}
.width-define{
    width: 80%;
}
</style>

<script>
export default {
    name:"register",
    data(){
        return {
            regsiterForm:{
              iphone:'15312487946',
              code:1234,
              password:123456,
              checkPass:123456
            },
            ruleForm:{
              iphone:[
              { required: true, message: '请输入手机号', trigger: 'blur' },
              { len:11, message: '需要11位数字', trigger: 'blur' }
            ], 
            code:[
            { required: true, message: '请输入验证码', trigger: 'blur' },
            ],
            password:[
            { required: true, message: '请输入密码', trigger: 'blur' },
            ],
            checkPass:[
            { required: true, message: '请确认密码', trigger: 'blur' },
            ]
            },
            buttonText:'发送验证'
        }
    },
    methods:{
      gotoLogin(){
        console.log("gotoLogin")
      }
    }
}
</script>
