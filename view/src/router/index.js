import Vue from 'vue'
import Router from 'vue-router'
const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}
Vue.use(Router)

import Login from '@/pages/Login'
import Main from '@/pages/Main'
import Dashboard from '@/pages/Dashboard'
import Transaction from '@/pages/Transaction'
import PersonalFile from '@/pages/PersonalFile'
import Department from '@/pages/Department'
import SalaryDetail from '@/pages/SalaryDetail'
import Awards from '@/pages/Awards'
import Punish from '@/pages/Punish'
import CheckOnRecord from '@/pages/CheckOnRecord'
import CheckOnTransaction from '@/pages/CheckOnTransaction'
import AderviceFor from '@/pages/AderviceFor'
import TalentPool from '@/pages/TalentPool'

let routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    component: Main,
    hidden: true,
    children: [{
      path: '/home',
      component: Dashboard,
      name: '首页'
    }]
  },
  {
    path : '/',
    name : '人事管理',
    component : Main,
    children : [
      {
        path : 'transaction',
        name : '人事事务办理',
        component : Transaction
      },
      {
        path : 'personalFile',
        name : '人事档案',
        component : PersonalFile
      }
    ]
  },
  {
    path : '/',
    name : '部门管理',
    component : Main,
    children : [
      {
        path : 'department',
        name : '查看部门',
        component : Department
      }
    ]
  },
  {
    path : '/',
    name : '薪酬管理',
    component : Main,
    children : [
      {
        path : 'salaryDetail',
        name : '薪资明细',
        component : SalaryDetail
      },
      {
        path : 'awardsManagement',
        name : '奖励管理',
        component : Awards
      },
      {
        path : 'punishManagement',
        name : '惩罚管理',
        component : Punish
      }
    ]
  },
  {
    path : '/',
    name : '考勤管理',
    component : Main,
    children : [
      {
        path : 'checkOnRecord',
        name : '考勤记录',
        component : CheckOnRecord
      },
      {
        path : 'checkOnTransaction',
        name : '考勤业务办理',
        component : CheckOnTransaction
      }
    ]
  },
  {
    path : '/',
    name : '招聘管理',
    component : Main,
    children : [
      {
        path : 'aderviceFor',
        name : '招聘流程管理',
        component : AderviceFor
      },
      {
        path : 'talentPool',
        name : '企业人才库',
        component : TalentPool
      }
    ]
  },
  {
    path : '/',
    name : '系统管理'
  }
]

const router = new Router({
  mode: 'history',
  base: '/humanresource/',
  routes: routes
})

router.beforeEach((to, from, next) => {
  console.log('--------------to.path:' + to.path)
  if (to.path.startsWith('/login')) {
    window.sessionStorage.removeItem('token')
    next()
  } else {
    let token = window.sessionStorage.getItem('token')
    if (!token) {
      //不存在token信息则说明用户未登录，跳转到登录页面，带上当前的页面地址，登录完成后做回跳，
      //判断是否是localhost：8080
      if(to.path != '/'){
        next({
          path: '/login',
          //将跳转的路由 path 作为参数，登录成功后跳转到该路由
          query: {redirect: to.fullPath}
        })
      }else{
        next('/login')
      }

    } else {
      next()
    }
  }
})

export default router
