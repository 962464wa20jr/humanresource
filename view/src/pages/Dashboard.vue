<template>
    <el-container style="height:2000px;">
        <el-header class="adtip" style="height:100px;">
            <el-col :span="8" class="tip">
                <p class="goom">早上好,{{ currentUser.name }}</p>
                <p>您目前有<span>15</span>条待办,<span>1</span>条考勤异常,<span>2</span>条通知！</p>
            </el-col>
            <el-col :span="16" class="adv">
                <p style="margin:0;">公司统一通告在这边展示</p>
            </el-col>
        </el-header>
        <el-main style="background-color:#FFFFFF">
            <el-container>
                <el-header class="rig_lm01" style="height:100%;">
                    <el-row class="title">
                        <i class="el-icon-s-grid"></i>&nbsp;通知
                        <span class="red_numb">2</span>
                    </el-row>
                    <el-row class="detail">
                        <el-row class="dat01">
                            <span class="datti">
                                <span>20</span>
                                <br>
                                <span class="jan">jan</span>
                            </span>
                            <span class="sqdeta">
                                <img src="../assets/img/bs_03.jpg">
                                 您想王晓磊提交的换班申请已经通过，交换班次时间为2014-02-09  09:00-18:00，请按时上班！
                            </span>
                            <p class="linkbut">
                                <span>查看</span>
                                <span>知道了</span>
                            </p>
                        </el-row>
                        <el-row class="dat02">
                            <span class="datti">
                                <span>20</span>
                                <br>
                                <span class="jan">jan</span>
                            </span>
                            <span class="sqdeta">
                                <img src="../assets/img/bs_04.jpg">
                                 您提交的请假申请未审批通过
                            </span> 
                            <p class="linkbut">
                                <span>查看</span>
                                <span>知道了</span>
                            </p>
                        </el-row>
                    </el-row>
                </el-header>
                <el-main class="rig_lm02">
                    <el-row class="title">
                        <i class="el-icon-s-grid"></i>&nbsp;本月考勤情况
                    </el-row>
                    <el-row class="detail">
                        <div class="det_inner">
                            <el-col :span="9" class="jd">
                                <div id="pieChart" style="height:200px;"></div>
                            </el-col>
                            <el-col :span="3">
                                <p class="ywc scordeti">28</p>
                                <h3 class="scordetih3">已完成打卡天数</h3>
                            </el-col>
                            <el-col :span="3">
                                <p class="wwc scordeti">1</p>
                                <h3 class="scordetih3">未完成打卡天数</h3>
                            </el-col>
                            <el-col :span="3">
                                 <p class="ycd scordeti">1</p>
                                 <h3 class="scordetih3">异常打卡天数</h3>
                            </el-col>

                        </div>
                    </el-row>
                </el-main>
                <el-footer class="rig_lm03">
                    <el-row class="title">
                        <i class="el-icon-s-grid"></i>&nbsp;待办事项
                    </el-row>
                    <el-row class="detail">
                        <el-table
                            :data="tableData"
                            v-loading="pageLoading"
                            style="width: 100%">

                            <el-table-column
                                label="待办事项名称"
                                align="center">
                                <template slot-scope="scope">
                                <span>{{ scope.row.name }}</span>
                                </template>
                            </el-table-column>

                            <el-table-column
                                label="创建时间"
                                align="center">
                                <template slot-scope="scope">
                                <span>{{ scope.row.createTime }}</span>
                                </template>
                            </el-table-column>

                            <el-table-column
                                label="当前处理人"
                                align="center">
                                <template slot-scope="scope">
                                <span>{{ scope.row.approver }}</span>
                                </template>
                            </el-table-column>

                            <el-table-column
                                label="状态"
                                width="100"
                                align="center">
                                <template slot-scope="scope">
                                <el-button
                                    size="mini"
                                    :type="scope.row.status == 1 ? 'info' : 'success'"
                                    @click="handleApproval(scope.$index, scope.row)">{{scope.row.status == 1 ? '待审核' : '已注册'}}</el-button>
                                </template>
                            </el-table-column>

                            <el-table-column label="操作" align="center" min-width="100px">
                                <template slot-scope="scope">
                                <el-button
                                    size="mini"
                                    type="warning"
                                    @click="handleSee(scope.$index, scope.row)">查看</el-button>
                                <el-button
                                    v-if="currentLoginType != 'student'"
                                    size="mini"
                                    class="btn-blue"
                                    @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
                                <el-button
                                    v-if="currentLoginType != 'student'"
                                    size="mini"
                                    type="danger"
                                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                            </el-table>


                            <el-pagination
                            background
                            layout="prev, pager, next, jumper,->, total, slot"
                            @current-change="handleCurrentChange"
                            :page-size="size"
                            :total="total"
                            style="float:right;margin-top: 10px;">
                            </el-pagination>
                    </el-row>
                </el-footer>

            </el-container>
        </el-main>
    </el-container>

</template>

<script>
 // 引入基本模板
  let echarts = require('echarts/lib/echarts')
  // 引入柱状图组件
  require('echarts/lib/chart/pie')
  // 引入提示框和title组件
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')
  require("echarts/lib/component/legend");
let initPieChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('pieChart'))
    var option = {
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
        orient: 'vertical',
        left: 10,
        top: 40,
        data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [
                {value: 335, name: '直接访问'},
                {value: 310, name: '邮件营销'},
                {value: 234, name: '联盟广告'},
                {value: 135, name: '视频广告'},
                {value: 1548, name: '搜索引擎'}
            ]
        }
    ]
};


    myChart.setOption(option);
  }

let handleApproval = function() {

}

let handleSee = function() {

}

let handleUpdate = function() {

}

let handleDelete = function() {

}

let handleCurrentChange = function() {

}
let getUser = function() {
    var userJsonStr = sessionStorage.getItem('currentUser');
      this.currentUser = JSON.parse(userJsonStr);

}
export default {
    data() {
        return {
            tableData: [],
            pageLoading: false,
            size: 10,
            total: 0,
            page: 1,
            currentUser: '',

        }
    },
    methods:{
        initPieChart,
        getUser

    },
    mounted: function() {
        this.initPieChart();
        this.getUser();
    }
    
}
</script>

<style scoped="scoped" lang="scss">
    .adtip {
        background: none repeat scroll 0 0 #FFFFFF;
        border-bottom: 1px solid #C8C7C7;
    }
    .tip {
    float: left;
    }
    .goom {
    font-size: 14px !important;
    padding-bottom: 5px;
    }
    .tip p {
    font-size: 16px;
    line-height: 22px;  
    }
    .tip p span {
    color: #638EC7;
    font-size: 16px;
    font-weight: bold;
    padding: 0 5px;
    }
    .adv {
    background: none repeat scroll 0 0 #FFEDBB;
    border: 1px solid #DCC074;
    float: right;
    height: 40px;
    line-height: 40px;
    margin-right: 20px;
    margin-top: 20px;
    padding: 0 16px;
    width: 450px;
    }
    .adv p {
    float: left;
    }
    .title {
    height: 35px;
    line-height: 35px;
    border-bottom: 1px dashed #C8C7C7;
    }
    .red_numb {
    background: none repeat scroll 0 0 #DB6969;
    color: #FFFFFF;
    font-weight: bold;
    margin-left: 10px;
    padding: 1px 6px;
    }
    .detail {
    border-top: 1px dashed #C8C7C7;
    margin-top: 2px;
    padding: 5px;
    }
    .rig_lm01, .rig_lm02, .rig_lm03 {
    padding: 0px 20px 0px 15px;
    }
    .rig_lm01 .dat01 {
    border: 1px solid #D5D5D5;
    height: 50px;
    padding: 8px 10px;
    margin-top: 10px;
    }
    .dat01 span {
    float: left;
    }
    .datti span {
    color: #BBBBBB;
    font-size: 20px;
    font-weight: bold;
    }
    .jan {
    font-size: 12px !important;
    font-weight: normal !important;
    padding-left: 2px;
    }
    .sqdeta {
    font-size: 14px;
    line-height: 30px;
    padding-left: 25px;
    margin-top: 10px;
    }
    .linkbut span {
    color: #1d79c7;
    padding: 0 8px;
    }
    .linkbut {
        float: right;
    }
    .rig_lm01 .dat02 {
    border: 1px solid #D5D5D5;
    border-top: none;
    height: 50px;
    padding: 8px 10px;
    margin-bottom: 10px;
    }
    .dat02 span {
    float: left;
    }
    .rig_lm02 .detail .det_inner {
    border: 1px solid #D5D5D5;
    height: 200px;
    margin-bottom: 10px;
    margin-top: 10px;
    }
    .jd {
    float: left;    
    }
    .scordeti ul li {
    border: 1px solid #DDDDDD;
    float: left;
    height: 130px;
    margin-right: 30px;
    margin-top: 7px;
    width: 155px;
    }
    .scordeti {
    font-size: 30px;
    line-height: 90px;
    text-align: center;
    border: 1px solid #DDDDDD;
    float: left;
    width: 155px;
    margin-bottom: 0;
    }
    .ywc {
    color: #76bd42;
    }
    .scordetih3 {
    background: none repeat scroll 0 0 #EFEFEF;
    color: #666666;
    font-size: 14px;
    font-weight: bold;
    line-height: 40px;
    text-align: center;
    border: 1px solid #DDDDDD;
    float: left;
    width: 155px;
    margin-top: 0;
    }
    .wwc {
    color: #f3a056;
    }
    .ycd {
    color: #db6969;
    }
</style>