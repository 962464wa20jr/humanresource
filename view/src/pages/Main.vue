<template>
  <section>
    <el-container class="container">
      <!--左边-->
      <el-aside :width="'330px'">
        <el-container>
          <el-header style="float:left;background:#2553a0">
            <el-col :span="2">
            <img src="../assets/logo.png" class="logoImg" @click="$router.push('/home')">
            </el-col>
            <el-col :span="22">
            <span class="system-name" @click="$router.push('/home')">人力资源后台管理系统</span>
            </el-col>
          </el-header>
          <el-main>
            <el-container style="border-right:1px solid #eaeef2">
                <el-header class="lm01" style="background:#fdfeff;">
                 <el-row>
                    <el-col :span="8" class="peptx">
                      <el-avatar :size="80">
                        <img src="../assets/photo.jpg" style="width:80px;">
                      </el-avatar>
                        <!--<img src="../assets/photo.jpg" class="peptx">-->
                    </el-col>
                    <el-col :span="16" class="pepdet">
                        <el-row class="pepname">{{ currentUser.name }}</el-row>
                        <el-row class="spname">{{ currentUser.name}}</el-row>
                        <el-row class="spname">
                          <template v-for="role in currentUser.roles">
                            {{role.role}}
                          </template>
                        </el-row>
                    </el-col>
                 </el-row>
                </el-header>
                
                <el-main>
                    <el-row class="title">
                        <i class="el-icon-date" style="padding-left:10px;"></i> 日历
                    </el-row>
                    <el-row class="detail">
                        <calendar :width="width"></calendar>
                    </el-row>
                </el-main>
                    
            </el-container>

          </el-main>
        </el-container>
      </el-aside>
      <!--内容-->
      <el-container>
        <!--页眉-->
        <el-header class="header">
          <el-row>
            <el-col :span="16" class="header-title">
              <el-menu
  :default-active="$route.path"
  class="el-menu-demo"
  mode="horizontal"
  background-color="#2553a0"
  text-color="#fff"
  active-text-color="#fff">
  <el-menu-item index="1" @click="addTab(editableTabsValue,'/home','首页')">首页</el-menu-item>
  <template v-for="(item,index) in menus">
    <el-submenu :index="index+1+''" v-if="!item.leaf" :key="item.name">
      <template slot="title">
        <span>{{item.name}}</span>
      </template>
      <template v-for="child in item.children">
        <el-menu-item :index="child.path" :key="child.path" @click="addTab(child.path,child.name)">
          {{child.name}}
        </el-menu-item>
      </template>
    </el-submenu>
  </template>
</el-menu>

            </el-col>

            <el-col :span="8" style="font-size: 16px;text-align: right;">
            

              <el-dropdown>
                <el-button type="primary btn-transparent">
                  <i class="el-icon-user"></i>  {{currentUser.name}}
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>个人中心</el-dropdown-item>
                  <el-dropdown-item>我的日程</el-dropdown-item>
                  <el-dropdown-item>我的消息</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>

              <el-dropdown>
                <el-button type="primary btn-transparent" @click="logout">
                  <i class="el-icon-switch-button"></i>  退出
                </el-button>
              </el-dropdown>
            </el-col>

          </el-row>
        </el-header>
        <!--中间-->
        <el-main class="main">
          <el-container>
            <el-header class="tab">
              <el-tabs v-model="editableTabsValue" type="border-card" closable @tab-remove="removeTab" @tab-click="clickTab">
                <template  v-for="item in editableTabs">
                 <el-tab-pane
                   
                    :key="item.name"
                    :label="item.title"
                    :name="item.name"
                    v-if="editableTabsValue!='0'"
                    
                 >
              <transition :name="transitionName" mode="out-in">
                  <router-view></router-view>
              </transition>
                 </el-tab-pane>
                </template>
                </el-tabs>
            </el-header>
            <el-main>
              
            </el-main>
          </el-container>
        </el-main>
      </el-container>
    </el-container>
  </section>
</template>

<script>
import calendar from '@/components/Calendar'
  let data = () => {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      width:'width:300px;',
      collapsed: false,
      userName: 'admin',
      menus: [],
      transitionName:'',
      isLoading:true,
      currentTime:'',
      currentName:'',
      currentLoginType:'',
      isSelect: false,
      editableTabsValue: '1',
      editableTabs: [{
        title : '首页',
        name : '1',
        content : '/home'
      }],
      tabIndex: 1,
      headPicUrl: 'src/assets/photo.JPG',
      currentUser: ''
      }
    }
  

  let initMenu = function() {
    for (let i in this.$router.options.routes) {
      let root = this.$router.options.routes[i]
      if (root.hidden)
        continue
      let children = []
      for (let j in root.children) {
        let item = root.children[j]
        if (item.hidden)
          continue
        children.push(item)
      }
      if (children.length < 1)
        continue

      this.menus.push(root)
      this.$router.push('/home');

      root.children = children
    }
    //  this.GLOBAL.Event.$on('transferUser',vo => {
    //      this.currentUser = vo;
    //      console.log(this.currentUser);
    //    })
      // this.GLOBAL.Event.$on('transferUser',val => {
      //     console.log(val);
      //     this.currentUser = val;
      // })
      // console.log(this.currentUser);
  }


  let addTab = function(path,title) {
    let isAdd = true;
    let tabs = this.editableTabs;
    tabs.forEach((tab,index) => {
      if(tab.title == title) {
        this.editableTabsValue = tab.name;
        this.$router.push(tab.content);
        //console.log("targetName:"+targetName+"tab.name:"+tab.name)
        isAdd = false;
      }
    })
    console.log("进来了")
    if(isAdd) {

        let newTabName = ++this.tabIndex + '';
        this.editableTabs.push({
          title: title,
          name: newTabName,
          content : path
        });
        this.editableTabsValue = newTabName;
        
        
        this.$router.push(path);
      } 
  }

  let removeTab = function(targetName) {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
               this.$router.push(nextTab.content);
              }
            }
          });
        }
        
        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }

  let clickTab =function(targetTab) {
      let tabs = this.editableTabs;
      tabs.forEach((tab,index) => {
        if(tab.name === targetTab.name) {
          this.$router.push(tab.content);
        }
      })
     }

  let getUser = function(user) {
      var userJsonStr = sessionStorage.getItem('currentUser');
      this.currentUser = JSON.parse(userJsonStr);
      console.log(this.currentUser);
  } 



  
  let logout = function () {
    this.$confirm('确定退出系统吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {


      //this.$common.recordLogs("系统退出")

      this.$api.post(this.GLOBAL.httpRequestUrl + '/logout', null, res => {
        if (res.data.code == 0) {

          sessionStorage.setItem('token', '');
          sessionStorage.setItem('currentUser', '');

          this.$message({
            type: 'success',
            message: '退出成功！'
          })

          this.$router.push({
            path: '/login'
          });
        } else {
          this.$message({
            type: 'error',
            message: '退出失败！'
          })
        }
      });

    }).catch(e => {})
  }
  export default {
    components: { calendar },
    data: data,
    methods: {
      initMenu,
      logout,
      addTab,
      removeTab,
      clickTab,
      getUser
    },
    mounted: function() {
    //   this.currentName = sessionStorage.getItem('name');
    //   this.currentLoginType = sessionStorage.getItem('userType');
       this.initMenu();
       this.getUser();

      
    },
    watch: {//使用watch 监听$router的变化
      $route(to, from) {
        this.transitionName = 'slide-right';
      }
    }
  }
</script>

<style scoped="scoped" lang="scss">
  $width: 100%;
  $height: 100%;
  $background-color: #2553a0;
  $header-color: #fff;
  $header-height: 60px;

  .container {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
  .el-aside {
  .el-header {
    line-height: $header-height;
    background-color: $background-color;
    color: $header-color;
    text-align: center;
  }
  .el-container {
    height: $height;
  .el-main {
    padding: 0px !important;
  }
  }
  }

  .main {
    width: $width;
    height: $height;
  }
  .el-main{
    padding: 0px !important;
  }

  .menu-button {
    width: 14px;
    cursor: pointer;
  }

  .userinfo-inner {
    cursor: pointer;
  }

  .el-menu {
    height: $height;
  }

  .header {
    background-color: $background-color;
    color: $header-color;
    text-align: center;
    line-height: $header-height;
    padding: 0;

  .header-title {
    text-align: left;
    color:rgb(43,69,140);
  span {
    padding: 0 20px;
  }
  }
  }

  .system-name {
    font-size: 20px;
    font-weight: bold;
    padding-right: 20px;

  }
  }
  .logoImg{
    width: 42px;
    height: 40px;
    padding-top:10px;
  }
  // 新加的
  .peptx {
    float: left;
    padding-left: 35px;
    padding-top: 30px;
  }
  .lm01 {
    padding-bottom: 150px;
  }
  .pepdet {
    float:left;
    padding-left: 10px;
    padding-top:18px;
  }
  .pepname {
    color: #000000;
    font-size: 16px;
    padding-right:48px;
    font-family: "Microsoft YaHei";
    font-weight: bold;
  }
  .spname {
    font: 13px/1.5em Arial;
    padding-right: 56px;
    color: #000;
  }
  .title {
    height: 35px;
    line-height: 35px;
    border-bottom: 1px dashed #C8C7C7;
  }
  .detail {
    border-top: 1px dashed #C8C7C7;
    margin-top: 2px;
    padding: 5px;
  }
  .is-selected {
    color: #1989FA;
  }
  .tab{
    padding: 0px;
  }
  

  .mainDiv{
    min-height: 750px;
    margin: 30px;
    background-color: #fff;
    box-shadow: 0 5px 20px rgba(220,222,231,.65);
    padding: 30px;
  }

  @keyframes slideInLeft {
    from {
      transform: translate3d(100%, 0, 0);
      opacity: 1;
    }
    to {
      transform: translate3d(0, 0, 0);
      opacity: 1;
    }
  }
  @keyframes slideInRight {
    from {
      transform: translate3d(-100%, 0, 0);
      opacity: 1;
    }
    to {
      transform: translate3d(0, 0, 0);
      opacity: 1;
    }
  }
  @keyframes slideLeftOut {
    from {
      transform: translate3d(0, 0, 0);
      opacity: 0;
    }
    to {
      transform: translate3d(100%, 0, 0);
      opacity: 0;
    }
  }
  @keyframes slideRightOut {
    from {
      transform: translate3d(0, 0, 0);
      opacity: 0;
    }
    to {
      transform: translate3d(-100%, 0, 0);
      opacity: 0;
    }
  }
  .slide-left-enter-active {
    animation: slideInLeft .3s forwards;
    z-index:5;
  }

  .slide-left-leave-active {
    animation: slideLeftOut .3s forwards;
    z-index:3;
  }
  .slide-right-enter-active {
    animation: slideInRight .3s forwards;
    z-index:5;
  }

  .slide-right-leave-active {
    animation: slideRightOut .3s forwards;
    z-index:3;
  }

  .top-right-btn{
    color:rgb(43,69,140);
  }

  .el-submenu__title{
    padding-left: 35px !important;
  }

</style>
