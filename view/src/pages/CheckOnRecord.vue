<template>
    <div class="mainDiv">
        <el-row>
             <span class="title-text">
                 <i class="el-icon-alarm-clock"></i>&nbsp;考勤管理
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
                        <el-date-picker
                            v-model="filtersForm.date"
                            type="daterange"
                            align="right"
                            unlink-panels
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            :picker-options="pickerOptions">
                        </el-date-picker>
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

                </el-form>
            </el-col>
        </el-row>

        <el-row>
             <el-table
      :data="tableData"
      v-loading="pageLoading"
      style="width: 100%">

      <el-table-column
        label="员工"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="员工工号"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="上班打卡时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.startWorkTime }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="下班打卡时间"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.endWorkTime }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="是否迟到或早退"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.isLateOrEarly }}</span>
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

let handleSee = function() {

}

let handleReset = function() {

}

let handleAdd = function() {

}

let handleDelete = function() {

}

let handleUpdate = function() {

}

let handleCurrentChange = function() {

}
export default {
    data() {
        return {
            filtersForm: {
                code: ''
            },
            tableData: [],
            pageLoading: false,
            size: 10,
            total : 0,
            page: 1,
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                    }
                }]
        },


        }
    }
}
</script>