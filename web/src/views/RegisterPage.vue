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
        >
          <el-form-item prop="tel">
            <el-input  class="width-define" auto-complete="off" placeholder="请输入手机号"></el-input>
          </el-form-item>

          <el-form-item prop="smscode" class="code">
            <el-input placeholder="验证码" style="width: 250px;"></el-input>
            <el-button
              type="primary"
              class="bt1"
              >{{ buttonText }}</el-button
            >
          </el-form-item>
          <el-form-item prop="pass">
            <el-input class="width-define"
              type="password"
              v-model="ruleForm2.pass"
              auto-complete="off"
              placeholder="输入密码"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              class="width-define"
              type="password"
              v-model="ruleForm2.checkPass"
              auto-complete="off"
              placeholder="确认密码"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="submitForm('ruleForm2')"
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
            ruleForm2:{},
            buttonText:'发送验证'
        }
    }
}
</script>
