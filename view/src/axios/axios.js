import axios from 'axios'
import {Message} from 'element-ui'
import router from '../router'

// 切换环境
if(process.env.NODE_ENV == 'development') {
    axios.defaults.baseURL = 'http://127.0.0.1:8080/development';
}else if(process.env.NODE_ENV == 'debug') {
    axios.defaults.baseURL = 'http://127.0.0.1:8080/debug';
}else if(process.env.NODE_ENV == 'production') {
    axios.defaults.baseURL = 'http://127.0.0.1:8080/production';
}

// 配置axios，axios.create返回的是axios实例
let http = axios.create({
    baseURL : axios.defaults.baseURL,
    withCredentials : false,
    headers : {
        'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
    },
    transformRequest : [function(data) {
        let newData =  ''
        for(let k in data) {
            if(data.hasOwnProperty(k) === true) {
                newData += encodeURIComponent(k) + '=' +encodeURIComponent(data[k]) + '&';
            }
        }
        return newData;
    }]
});

// http request拦截器
http.interceptors.request.use(config => {
    console.log(config.url);
    if(config.url.indexOf('login') >= 0){
        return config;
    }else{
        let token = sessionStorage.getItem("token");
        if(token) {
            config.headers.token = token;
        }
        return config;
    }
},
    err => {
        return Promise.reject(err);
    }
);

// http response拦截器，在这里设置登录失败的提示
http.interceptors.response.use(res => {
    if(res.data.code == 2 || res.data.code == 3) {
        Message({
            showClose : true,
            message : '登录信息失效，请重新登录',
            type : 'error',
            duration : 2000
        })

        // 先把当前页面的url作为参数保存，以便在登录完成后可以跳回当前界面
        router.replace({
            path : '/login',
            query : {
                redirect : router.currentRoute.fullPath
            }
        });
    }
    if(res.status === 200) {
        return Promise.resolve(res);
    }else {
        return Promise.reject(res);
    }
},
    err => {
        return Promise.reject(err);
    });

// 封装http请求方法
function apiAxios(method,url,params,response) {
    http({
        method : method,
        url : url,
        data : method === 'POST' || method === 'PUT' ? params : null,
        params : method === 'GET' || method === 'DELETE' ? params : null
    }).then(function(res) {
        response(res);
    }).catch(function(err) {
        response(err);
    })
}

// 导出方法
export default {
    get : function(url,params,response) {
        return apiAxios('GET',url,params,response)
    },
    post : function(url,params,response) {
        return apiAxios('POST',url,params,response)
    },
    put : function(url,params,response) {
        return apiAxios('PUT',url,params,response)
    },
    delete : function(url,params,response) {
        return apiAxios('DELETE',url,params,response)
    }
}