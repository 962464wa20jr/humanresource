<template>
    <div class="mainDiv">
        <el-row>
             <span class="title-text">
                 <i class="el-icon-present"></i>&nbsp;奖励管理
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
        label="奖金项目"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reason }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="获奖日期"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.date }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="奖金数目"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.account }}</span>
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
            page: 1


        }
    }
}
</script>