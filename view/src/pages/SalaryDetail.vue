<template>
    <div class="mainDiv">
        <el-row>
             <span class="title-text">
                 <i class="el-icon-coin"></i>&nbsp;薪资明细
             </span>
             <el-divider></el-divider>
        </el-row>
        <el-row>
            <el-col>
                <el-form :inline="true" ref="filtersForm" :model="filtersForm">

                    <el-form-item>
                        <el-input v-model="filtersForm.code" placeholder="员工工号" style="width: 200px;" />
                    </el-form-item>

                     <el-form-item>
                          <el-select v-model="filtersForm.departmentName" clearable placeholder="部门名称">
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

                    <el-form-item>
                        <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">添加</el-button>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="info" @click="handleDownload" icon="el-icon-printer">下载</el-button>
                    </el-form-item>

                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-table
                    :data="tableData"
                    stripe
                    border
                    v-loading="pageLoading"
                    style="width: 100%">

                                            <el-table-column
                                                label="员工编号"
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
                                                label="发薪日期"
                                                align="center">
                                                <template slot-scope="scope">
                                                <span>{{ scope.row.date }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="基本工资"
                                                align="center">
                                                <template slot-scope="scope">
                                                <span>{{ scope.row.basicAccount }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="公积金"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span>{{ scope.row.housingFund }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="社保"
                                                align="center">
                                                <template  slot-scope="scope">
                                                    <span> {{ scope.row.socialInsurance }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="餐补"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.foodSub }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="加班补贴"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.overWork }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="出差补贴"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.toBeAway }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="报销补贴"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.expense }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="节日补贴"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.holidaySub }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="奖金"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.awards }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="扣款"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.punish }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="个人所得税"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.tax }} </span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="实发工资"
                                                align="center">
                                                <template slot-scope="scope">
                                                    <span> {{ scope.row.actualSalary }} </span>
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
    </div>
</template>
<style scoped>
 .btn-blue{
    color: #fff;
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
</style>

<script>
let handleQuery = function() {

}

let handleReset =  function() {
    
}

let handleAdd = function() {

}

let handleDownload = function() {

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
            filtersForm : {
                code: '',
                departmentName: '',
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
    }
}
</script>