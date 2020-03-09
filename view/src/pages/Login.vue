<template>
  <!--背景图-->



  <div class="note" :style="note">
    <div class="top-row">
      <span class="logo-text">人力资源后台管理系统</span>
    </div>
    <div class="login-form">
      <el-form ref="loginForm" :model="account" :rules="rules" label-position="left" label-width="0px" class="demo-ruleForm login-container">
        <div class="titleSty">
          <el-col :span="8"><img src="../assets/logo.png" class="logoImg"></el-col>
          <el-col :span="16" class="login-text"><span>欢迎登录</span></el-col>
        </div>

        <el-form-item prop="staffCode" style="margin-bottom: 30px;">
          <el-input prefix-icon="el-icon-user" type="text" v-model="account.staffCode" auto-complete="off" placeholder="请输入您的账号"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="margin-bottom: 30px;">
          <el-input prefix-icon="el-icon-lock" type="password" v-model="account.password" auto-complete="off" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        
        <el-form-item style="width:100%;margin-bottom: 40px;">
          <el-row>
            <el-col :span="1">
              <span> &nbsp;</span>
            </el-col>
            <el-col :span="9">
              <el-button type="success" style="width: 95%;float: left;" @click.native.prevent="handleLogin" :loading="logining">登录</el-button>
            </el-col>
            <el-col :span="3">
              <span> &nbsp;</span>
            </el-col>
            <el-col :span="9">
              <el-button type="warning" style="width:95%;float: right;" @click.native.prevent="handleRegister" :loading="logining">注册</el-button>
            </el-col>
            <el-col :span="2">
              <span> &nbsp;</span>
            </el-col>
          </el-row>

        </el-form-item>
      </el-form>
    </div>

    <el-dialog title="账号注册" :visible.sync="registerFormVisible" width="25%" top="20vh" :close-on-click-modal="false" @close="closeDialog">
      <el-form :model="registerForm" :rules="rules" ref="registerForm">
        <el-form-item prop="registerName" label="姓名" :label-width="formLabelWidth">
          <el-input prefix-icon="el-icon-user" v-model="registerForm.registerName" placeholder="请输入姓名" style="width: 300px;"></el-input>
        </el-form-item>
        <el-form-item prop="registerPassword" label="密码" :label-width="formLabelWidth">
          <el-input prefix-icon="el-icon-lock" v-model="registerForm.registerPassword" type="password" placeholder="请输入密码" show-password style="width: 300px;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="register" :loading="logining">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  const rules = {

    staffCode:[{
      required: true,
      message: '请输入工号',
      trigger: 'blur'
    },
    {
      min: 1,
      max: 100,
      message: '长度在 1 到 100 个字符',
      trigger: 'blur'
    }],
    password:[{
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    },
    {
      min: 6,
      max: 20,
      message: '长度在 6 到 20 个字符',
      trigger: 'blur'
    }],
    registerName:[{
      required: true,
      message: '请输入姓名',
      trigger: 'blur'
    },
      {
        min: 1,
        max: 100,
        message: '长度在 1 到 100 个字符',
        trigger: 'blur'
      }],
    registerPassword:[{
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    },
      {
        min: 6,
        max: 20,
        message: '长度在 6 到 20 个字符',
        trigger: 'blur'
      }]
  }

  let closeDialog = function(){
    this.registerFormVisible = false
    this.$refs.registerForm.resetFields()
  }
  let handleRegister = function(){
    this.registerForm = {}
    this.registerFormVisible = true
  }

  let handleLogin = function() {

    this.$refs.loginForm.validate((valid) => {
      if (valid) {

        this.logining = true

        let httpUrl = this.GLOBAL.httpRequestUrl + '/login'
        //sessionStorage.setItem('currentUser',this.account.staffCode);

        
        // if(this.account.name == 'admin'){
        //   httpUrl = this.GLOBAL.httpRequestUrl + '/teacher/teacherLogin'
        //   sessionStorage.setItem('userType', 'admin');
        // }else{
        //   if(this.account.type == '1'){
        //     httpUrl = this.GLOBAL.httpRequestUrl + '/student/studentLogin'
        //     sessionStorage.setItem('userType', 'student');
        //   }else{
        //     httpUrl = this.GLOBAL.httpRequestUrl + '/teacher/teacherLogin'
        //     sessionStorage.setItem('userType', 'teacher');
        //   }
        // }

        var loginParams = {
          staffCode: this.account.staffCode,
          password: this.account.password
        };

        this.$api.post(httpUrl, loginParams, response => {
          this.logining = false;
          if (response.data.code == 0) {


            var vo = response.data.content;
            sessionStorage.setItem('token', vo.token);
            sessionStorage.setItem('currentUser', JSON.stringify(vo));
            this.$router.push({
              path: '/home'
             
            })
            this.currentUser = vo;
            console.log(this.currentUser);
            

            this.$message({
              type: 'success',
              message: '欢迎登录：' + vo.name
            })
            //this.$common.recordLogs("系统登录")
          } else {
            this.$message({
              message: response.data.message,
              type: 'error'
            });
          }


        });
        //this.GLOBAL.Event.$emit('transferUser',this.currentUser);
        //this.$emit('transferUser',this.currentUser);
      } else {
        return false;
      }
    });
  }

  let register = function(){

    this.$refs.registerForm.validate((valid) => {
      if (valid) {

        this.logining = true

        var registerParams = {
          name: this.registerForm.registerName,
          password: this.registerForm.registerPassword
        };

        this.$api.post(this.GLOBAL.httpRequestUrl + '/student/register', registerParams, response => {
          this.logining = false;
          if (response.data.code == 0) {

            this.$message({
              type: 'success',
              message: '注册成功'
            })

          } else {
            this.$message({
              message: response.data.message,
              type: 'error'
            });
          }
          this.registerFormVisible = false
        });
      } else {
        return false;
      }
    });
  }


  export default {
    name: 'login',
    data() {
      return {
        rules:rules,
        formLabelWidth:'100px',
        registerFormVisible:false,
        registerForm:{
          registerName:'',
          registerPassword:''
        },
        logining: false,
        note: {
          position: "absolute",
          top: "5%",
          left: "0px",
          width: "100%",
          height: "90%",
          backgroundImage: "url(" + require("../assets/8589.jpg") + ")",
          backgroundSize: "100% 100%",
          backgroundRepeat: "no-repeat",


        },
        account: {
          staffCode: '',
          password: '',
          type:'1'
        },
        activeName:'first',
        currentUser: ''
      }

    },
    methods: {
      register,
      closeDialog,
      handleRegister,
      handleLogin

    }
  }
</script>

<style scoped>
  .login-container {
    padding: 60px;
  }
  .titleSty{
    margin-bottom: 60px;
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    color: white;
    letter-spacing: 10px;
    padding-left:10px;
    padding-right:18px;
    height: 60px;
    line-height: 60px;
  }
  .logoImg{
    width: 65px;
    height: 60px;
    margin-left: 15px;
    float: left;
  }
  .top-row{
    position: absolute;
    z-index: 1000;
    margin-top: 10px;
    right : 100px;
  }
  .content-text-large{
    position: absolute;
    top: 300px;
    color: white;
    left: 100px;
    font-size: 45px;
    letter-spacing: 8px;
  }
  .content-text-small{
    position: absolute;
    top: 400px;
    color: white;
    left: 100px;
    font-size: 20px;
    width: 600px;
    letter-spacing: 6px;
    line-height: 50px;
  }
  .login-form{
    width: 400px;
    height: 500px;
    position: absolute;
    left: 100px;
    top: 50%;
    transform: translateY(-50%);
    background: #546075b5;
    border-radius: 10px;
    z-index: 999;
  }

  .logo-text{
    padding: 5px;
    color: rgb(245,247,250);
    font-size: 60px;
    display: block;
    margin-top: 30px;
    margin-left: 100px;
    border: 2px solid;
    border-image: -webkit-linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 60 60;
    border-image: -moz-linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 60 60;
    border-image: linear-gradient(left,rgb(48,230,255),rgb(6,40,60)) 60 60;
    letter-spacing: 3px;
  }
  .left-text{
    position: absolute;
    left: 100px;
    top: 300px;
    font-size: 25px;
  }
</style>
