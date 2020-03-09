<template>
    <div class="mainDiv">
        <el-container>
            <el-header style="height:100%;">
                <el-row>
                    <span class="title-text">
                        <i class="el-icon-office-building"></i>&nbsp;查看部门
                    </span>
                    <el-divider></el-divider>
                 </el-row>
                 <el-row>
                    <el-col>
                        <el-form :inline="true" ref="filtersForm" :model="filtersForm">

                        <el-form-item>
                          <el-select v-model="filtersForm.name" clearable placeholder="部门名称">
                            <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                          </el-select>
                        </el-form-item>
                            
                            <el-form-item>
                            <el-button type="blue" @click="handleQuery" icon="el-icon-search">查询</el-button>
                            </el-form-item>

                            <el-form-item>
                            <el-button type="success" @click="handleReset" icon="el-icon-refresh">重置</el-button>
                            </el-form-item>

                            <el-form-item v-if="currentLoginType != 'student'">
                            <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">添加</el-button>
                            </el-form-item>

                        </el-form>
                    </el-col>
                 </el-row>
            </el-header>
            <el-main>
                <el-row>
                         
                     <el-row class="module-title">
                        <p class="baseTitle">
                            <span class="line"></span>
                                 部门基本情况
                        </p>
                     </el-row>
                        
                    <el-row class="baseMessage">
                        <el-row>
                            <span>部门名称：</span>
                            <span>闪烁之光项目组</span>
                        </el-row>
                        <el-row>
                            <span>部门人数：</span>
                            <span>15</span>
                        </el-row>
                        <el-row>
                            <span>创建时间：</span>
                            <span>2020-02-24</span>
                        </el-row>
                        <el-row>
                            <span>部门简介：</span>
                            <span>《闪烁之光》是诗悦网络研发的放置玩法的策略卡牌手游。游戏中拥有多位英雄，可以DIY养成专属英雄团，自定义阵容战斗，不同英雄拥有不同的技能，还有趣味历练，冠军赛等玩法。
光、暗、风、水、火五大元素属性相互克制，英雄和阵容的选择更是花式烧脑！
                            </span>
                        </el-row>
                    </el-row>
                </el-row>
                <el-row>
                    <el-row class="module-title">
                        <p class="baseTitle">
                            <span class="line"></span>
                                 部门统计
                        </p>
                     </el-row>
                     <el-row>
                         <el-row>
                                <el-card :body-style="{ padding: '0px',height:'100%' }">
                                    <el-row>
                                        <p>近半年部门考勤情况</p>
                                    </el-row>
                                    <el-row>
                                        <div id="barChart" style="height:500px;"></div>
                                    </el-row>
                                    <el-row>
                                        <p>部门人员构成情况</p>
                                    </el-row>
                                    <el-row style="padding-top:20px;">
                                        <el-col :span="12">
                                           <div id="funnelChart" style="height:500px;"></div>
                                        </el-col>
                                        <el-col :span="12">
                                            <div id="pieeChart" style="height:500px;"></div>
                                        </el-col>
                                    </el-row>
                                    
                                </el-card>
                        </el-row>
                     </el-row>
                     <el-row>
                          <el-row class="module-title">
                            <p class="baseTitle">
                                <span class="line"></span>
                                    部门具体人员
                            </p>
                          </el-row>
                          <el-row>
                              <el-table
                                        :data="tableData"
                                        v-loading="pageLoading"
                                        style="width: 100%">

                                        <el-table-column
                                            label="员工工号"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.code }}</span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column
                                            label="员工姓名"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.name }}</span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column
                                            label="职位"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.role }}</span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column
                                            label="电话"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.phoneNumber }}</span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column
                                            label="邮箱"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.email }}</span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column
                                            label="入职时间"
                                            align="center">
                                            <template slot-scope="scope">
                                            <span>{{ scope.row.enplyTime }}</span>
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
                </el-row>
                </el-row>
            </el-main>
                
        </el-container>
                 
    </div>
</template>

<style scoped>
    .btn-blue{
    color: rgb(255, 255, 255);
    background-color: #409eff;
    border-color: #409eff;
  }
  .btn-blue:hover{
    color: #fff;
    background-color: #409eff;
    border-color: #409eff;
  }
  .title-text {
      font-size: 20px;
  }
  .module-title {
        padding-right: 32px;
        line-height: 26px;
    }
    .baseTitle {
        float: left;
        position: relative;
        padding-left: 12px;
        font-size: 20px;
        color: #252527;
    }
    .line {
        position: absolute;
        content: "";
        height: 17px;
        top: 22%;
        left: 0;
        border-left: 3px solid #6aa2e4
    }
    .baseMessage {
        padding: 20px 0;
        border-bottom: 1px dashed #c0d6f0
    }
    .baseMessage span {
        font-size: 18px;
        line-height: 30px;
    }
    .baseMessage .el-row {
        margin: 20px 0;
    }
    .el-card  p {
        padding-top: 20px;
        padding-left: 20px;
        font-size: 18px;
        font-weight: bold;
        font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    }
</style>

<script>
// 引入基本模板
  let echarts = require('echarts/lib/echarts')
  // 引入柱状图组件
  require('echarts/lib/chart/bar')
  require('echarts/lib/chart/pie')
  require('echarts/lib/chart/funnel')
  // 引入提示框和title组件
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')
  require("echarts/lib/component/legend");
let initBarChart = function() {
    let myChart = echarts.init(document.getElementById('barChart'));
    var posList = [
    'left', 'right', 'top', 'bottom',
    'inside',
    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
];

app.configParameters = {
    rotate: {
        min: -90,
        max: 90
    },
    align: {
        options: {
            left: 'left',
            center: 'center',
            right: 'right'
        }
    },
    verticalAlign: {
        options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
        }
    },
    position: {
        options: echarts.util.reduce(posList, function (map, pos) {
            map[pos] = pos;
            return map;
        }, {})
    },
    distance: {
        min: 0,
        max: 100
    }
};

app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
        var labelOption = {
            normal: {
                rotate: app.config.rotate,
                align: app.config.align,
                verticalAlign: app.config.verticalAlign,
                position: app.config.position,
                distance: app.config.distance
            }
        };
        myChart.setOption({
            series: [{
                label: labelOption
            }, {
                label: labelOption
            }, {
                label: labelOption
            }, {
                label: labelOption
            }]
        });
    }
};


var labelOption = {
    show: true,
    position: app.config.position,
    distance: app.config.distance,
    align: app.config.align,
    verticalAlign: app.config.verticalAlign,
    rotate: app.config.rotate,
    formatter: '{c}  {name|{a}}',
    fontSize: 16,
    rich: {
        name: {
            textBorderColor: '#fff'
        }
    }
};

var option = {
    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['Forest', 'Steppe', 'Desert', 'Wetland']
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {show: false},
            data: ['2012', '2013', '2014', '2015', '2016']
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: 'Forest',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            data: [320, 332, 301, 334, 390]
        },
        {
            name: 'Steppe',
            type: 'bar',
            label: labelOption,
            data: [220, 182, 191, 234, 290]
        },
        {
            name: 'Desert',
            type: 'bar',
            label: labelOption,
            data: [150, 232, 201, 154, 190]
        },
        {
            name: 'Wetland',
            type: 'bar',
            label: labelOption,
            data: [98, 77, 101, 99, 40]
        }
    ]
};
myChart.setOption(option);
}

let initFunnelChart = function() {
    let myChart = echarts.init(document.getElementById('funnelChart'));
    var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    legend: {
        data: ['展现','点击','访问','咨询','订单']
    },

    series: [
        {
            name:'漏斗图',
            type:'funnel',
            left: '10%',
            top: 60,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
                show: true,
                position: 'inside'
            },
            labelLine: {
                length: 10,
                lineStyle: {
                    width: 1,
                    type: 'solid'
                }
            },
            itemStyle: {
                borderColor: '#fff',
                borderWidth: 1
            },
            emphasis: {
                label: {
                    fontSize: 20
                }
            },
            data: [
                {value: 60, name: '访问'},
                {value: 40, name: '咨询'},
                {value: 20, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
            ]
        }
    ]
};
myChart.setOption(option);

}

let initPieChart = function() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('pieeChart'))
    var option = {
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        x: 'left',
        y: 'center',
        textStyle: {
          fontSize: 14,
          color:'#efc651'
        },
        data:['跑步','跳高','跳远','游泳','标枪','跨栏','射箭','鞍马'],
        top: 40
      },
      toolbox: {
        show : false
      },
      series : [

        {
          name:'比赛项目',
          type:'pie',
          radius : [30, 80],
          center : ['50%', '30%'],
          roseType : 'radius',
          labelLine:{
            lineStyle:{
              color:'#efc651'
            }
          },
          data:[
            {value:35, name:'跑步',label:{
                color:'#efc651'
              }},
            {value:25, name:'跳高',label:{
                color:'#efc651'
              }},
            {value:22, name:'跳远',label:{
                color:'#efc651'
              }},
            {value:20, name:'游泳',label:{
                color:'#efc651'
              }},
            {value:19, name:'标枪',label:{
                color:'#efc651'
              }},
            {value:15, name:'跨栏',label:{
                color:'#efc651'
              }},
            {value:10, name:'射箭',label:{
                color:'#efc651'
              }},
            {value:8, name:'鞍马',label:{
                color:'#efc651'
              }}
          ]
        }
      ]
    };
    console.log("pie1")
    myChart.setOption(option);
    console.log("pie3")
  }

  let handleSee = function() {

  }

  let handleUpdate = function() {

  }

  let handleDelete = function() {

  }

  let handleCurrentChange = function() {

  }

export default {
    data() {
        return {
            filtersForm: {
                name: ''
            },
            size: 10,
            page: 1,
            total: 0,
            pageLoading: false,
             options: [{
                value: '选项1',
                label: '黄金糕'
                }, {
                value: '选项2',
                label: '双皮奶'
                }, {
                value: '选项3',
                label: '蚵仔煎'
                }, {
                value: '选项4',
                label: '龙须面'
                }, {
                value: '选项5',
                label: '北京烤鸭'
            }],

        }
    },
    methods : {
        initBarChart,
        initFunnelChart,
        initPieChart
    },
    mounted: function() {
        this.initBarChart();
        this.initFunnelChart();
        this.initPieChart();
    }
}
</script>