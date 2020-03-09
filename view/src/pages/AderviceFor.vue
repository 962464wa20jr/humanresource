<template>
    <div class="mainDiv">
        <el-row>
             <span class="title-text">
                 <i class="el-icon-present"></i>&nbsp;招聘流程管理
             </span>
             <el-divider></el-divider>
        </el-row>
        <el-row style="padding:10px 50px;">
            <el-col>
                <el-steps :active="1">
                    <el-step title="简历筛选" icon="el-icon-edit"></el-step>
                    <el-step title="笔试" icon="el-icon-upload"></el-step>
                    <el-step title="一轮面试" icon="el-icon-picture"></el-step>
                    <el-step title="二轮面试" icon="el-icon-picture"></el-step>
                    
                </el-steps>
            </el-col>
        </el-row>

        <el-row style="padding-top:50px;">
             <el-table
      :data="tableData"
      v-loading="pageLoading"
      style="width: 100%">

      <el-table-column
        label="应聘人员姓名"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>


      <el-table-column
        label="毕业院校"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.school }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="应聘岗位"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.date }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="电话"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>

       <el-table-column
        label="邮箱"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center" min-width="100px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleSee(scope.$index, scope.row)">查看简历</el-button>
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