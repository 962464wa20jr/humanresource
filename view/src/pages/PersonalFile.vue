<template>
   <div class="mainDiv">
       <el-container>
            <el-header style="height:100%;">
                <el-row>
                    <span class="title-text">
                        <i class="el-icon-notebook-1"></i>&nbsp;人事档案查询
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
            </el-header>
            <el-main :style="display">
                   <el-col :span="20">
                            <el-row id="anchor-0" class="anchor">
                                <el-col :span="4">
                                    <img src="../assets/photo.jpg" class="f-l head-pic">
                                </el-col>
                                <el-col :span="8" class="f-l">
                                    <el-row>
                                        <span class="name">{{currentUser.name}}</span>
                                    </el-row>
                                    <el-row style="padding: 10px 0;">
                                        <span>{{currentUser.sexStr}}</span>
                                        <span class="ver-line">|</span>
                                        <span>{{currentUser.age}}</span>
                                        <span class="ver-line">|</span>
                                        <span>广州</span>
                                    </el-row>
                                    <el-row>
                                        <span>+86 {{currentUser.phoneNumber}}</span>
                                        <span class="ver-line">|</span>
                                        <span>{{currentUser.email}}</span>
                                    </el-row>
                                </el-col>
                            </el-row>
                            <el-row id="anchor-1" class="description">
                                <el-row>
                                    <el-col :span="18">
                                        <el-row class="module-title">
                                            <p class="baseTitle">
                                                <span class="line"></span>
                                                基本信息
                                            </p>
                                        </el-row>
                                    </el-col>
                                    <el-col :span="2" style="float:right;padding-top:10px;">
                                        <el-button type="primary" icon="el-icon-edit" circle @click="editAnchor('anchor1')"></el-button>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-form ref="basicForm" :model="basicForm" label-width="100px" :disabled="disabledAnchor1">
                                        <el-col :span="10">
                                            <el-form-item label="出生日期:">
                                                    <el-date-picker
                                                        type="date"
                                                        value-format="yyyy-MM-dd"
                                                        placeholder="选择出生日期"
                                                        v-model="basicForm.birthday"
                                                        style="width: 100%;">
                                                    </el-date-picker>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                                <el-form-item label="籍贯:">
                                                    <el-input v-model="basicForm.nativePlace"></el-input>
                                                </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="国籍:">
                                                <el-input v-model="basicForm.country"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="民族:">
                                                <el-input v-model="basicForm.nation"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="婚姻状况:">
                                                <el-input v-model="basicForm.isMarriedStr"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="政治面貌:">
                                                <el-input v-model="basicForm.politic"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="学历">
                                                <el-input v-model="basicForm.educationStr"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="毕业院校">
                                                <el-input v-model="basicForm.school"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="20">
                                            <el-form-item label="户籍所在地:">
                                                <el-input v-model="basicForm.domicile"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="20">
                                            <el-form-item label="现居地址:">
                                                <el-input v-model="basicForm.address"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="20">
                                            <el-form-item label="身份证号码:">
                                                <el-input v-model="basicForm.idCard"></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-form>
                                </el-row>
                               <el-row :style="isEditAnchor1">
                                   <el-button type="info" @click="cancel('anchor1')">取 消</el-button>
                                   <el-button
                                    type="success"
                                    @click="handleSubmitUpdate"
                                    :loading="formLoading">确 定
                                   </el-button>
                               </el-row>
                               

                                   
                            </el-row>
                            <el-row id="anchor-2" class="description">
                                <el-row>
                                    <el-col :span="18">
                                        <el-row class="module-title"> 
                                            <p class="baseTitle">
                                                <span class="line"></span>
                                                职位信息
                                            </p>
                                        </el-row>
                                    </el-col>
                                    <el-col :span="2" style="float:right;padding-top:10px;">
                                        <el-button type="primary" icon="el-icon-edit" circle @click="editAnchor('anchor2')"></el-button>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-form ref="basicForm" :model="basicForm" label-width="100px" :disabled="disabledAnchor2">
                                        <el-col :span="10">         
                                            <el-form-item label="入职时间">
                                                <el-date-picker
                                                        type="date"
                                                        value-format="yyyy-MM-dd"
                                                        placeholder="选择入职日期"
                                                        v-model="basicForm.createTime"
                                                        style="width: 100%;">
                                                </el-date-picker>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="部门">
                                                <el-input v-model="basicForm.departmentName"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="10">
                                            <el-form-item label="职称">
                                                <el-input v-model="basicForm.roles[0].role"></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-form>
                                </el-row>
                                
                                <el-row :style="isEditAnchor2">
                                   <el-button type="info" @click="cancel('anchor2')">取 消</el-button>
                                   <el-button
                                    type="success"
                                    @click="handleSubmitUpdate"
                                    :loading="formLoading">确 定
                                   </el-button>
                               </el-row>
                            </el-row>
                                <el-row id="anchor-3" class="descrption">
                                    <el-row>
                                        <el-col :span="18">
                                            <el-row class="module-title"> 
                                                <p class="baseTitle">
                                                    <span class="line"></span>
                                                    记事栏
                                                </p>
                                            </el-row>
                                        </el-col>
                                    </el-row>
                                        <el-table
                                            :data="tableData"
                                            v-loading="pageLoading"
                                            style="width: 100%">

                                            <el-table-column
                                                label="日期"
                                                align="center">
                                                <template slot-scope="scope">
                                                <span>{{ scope.row.date }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="事件（岗位变动、合同续签、奖惩）"
                                                align="center">
                                                <template slot-scope="scope">
                                                <span>{{ scope.row.event }}</span>
                                                </template>
                                            </el-table-column>

                                            <el-table-column
                                                label="备注"
                                                align="center">
                                                <template slot-scope="scope">
                                                <span>{{ scope.row.remark }}</span>
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
                                    
                            
                    </el-col>
                    <!--
                    <el-col :span="4">
                        <div style="height: 300px;">
                                <el-steps direction="vertical" :active="1">
                                    <el-step title="步骤 1"></el-step>
                                    <el-step title="步骤 2"></el-step>
                                    <el-step title="步骤 3" description="这是一段很长很长很长的描述性文字"></el-step>
                                </el-steps>
                            </div>
                    </el-col>
                    -->
            </el-main>
       </el-container>
   </div>

               
</template>

<style scoped>
    .anchor {
        position: relative;
        padding: 32px;
        border-bottom: 1px dashed #e8e8ea;
        width: 92%;
    }
    .head-pic {
        margin-right: 24px;
        width: 88px;
        height: 88px;
        border-radius: 50%;
    }
    .f-l {
        float: left;
    }
    .name {
        font-size: 24px;
        color: #252527;
    }
    .ver-line {
        margin: 0 16px;
        color: #e8e8ea;
        font-size: 14px;
        font-weight: 400;
    }
    .description {
        position: relative;
        padding-top: 24px;
        padding-bottom: 12px;
        border-bottom: 1px dashed #e8e8ea;
        width: 92%;
    }
    .module-title {
        padding-left: 32px;
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
    .data-wrap {
        position: relative;
        margin-top: 12px;
        padding: 12px 32px 12px 47px;
        word-break: break-all;
    }
    .data-wrap span {
        font-size: 16px;
    }
    .data-wrap .el-row {
        padding : 20px 0;
    }
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

<style>
.description .el-input__inner {
    border-color: rgb(247, 161, 180);
}
.description .el-input.is-disabled .el-input__inner {
    background-color: #ffffff;
    border-color: #E4E7ED;
    color: #464748;
    cursor: not-allowed;
    }   

.el-button--primary {
    color: #FFF;
    background-color: rgb(188, 214, 240);
    border-color: rgb(188, 214, 240);
}
</style>

<script>
let handleSee = function() {

}

let handleUpdate = function() {

}

let handleDelete = function() {

}

let handleCurrentChange= function() {

}
let editAnchor =  function(code){
    //console.log("进来了");
   switch(code){
       case 'anchor1': {
           this.disabledAnchor1 = false;
           this.isEditAnchor1.display = '';
           break;
        }
        case 'anchor2': {
            this.disabledAnchor2 = false;
            this.isEditAnchor2.display = '';
            break;
        }
       default : break;
   }
}
let cancel = function(code) {
     switch(code){
       case 'anchor1': {
           this.disabledAnchor1 = true;
           this.isEditAnchor1.display = 'none';
           break;
        }
        case 'anchor2': {
            this.disabledAnchor2 = true;
            this.isEditAnchor2.display = 'none';
            break;
        }
       default : break;
   }

}
let handleSubmitUpdate = function() {

}
let getUser = function(user) {
      var userJsonStr = sessionStorage.getItem('currentUser');
      this.currentUser = JSON.parse(userJsonStr);
      console.log(this.currentUser);
      this.basicForm = Object.assign({},this.currentUser)
  } 


export default {
    data() {
        return {
            filtersForm: {
                code : ''
            },
            display: {
                display : ''
            },
            basicForm: {
                birthday: '',
                age: '',
                nativePlace: '',
                country: '',
                nation: '',
                isMarriedStr : '',
                politic: '',
                domicile: '',
                address: '',
                idCard: '',
                createTime: '',
                educationStr: '',
                school: '',
                departmentName: '',
                roles: ''
            },
            formLoading:false,
            disabledAnchor1: true,
            disabledAnchor2: true,
            isEditAnchor1: {
                display: 'none',
                float: 'right',
                right: '16%'
            },
            isEditAnchor2: {
                display: 'none',
                float: 'right',
                right: '16%'
            },
            size: 10,
            page: 1,
            total: 0,
            pageLoading: false,
            currentUser: ''


        }
    },
    methods: {
        editAnchor,
        cancel,
        getUser
    },
    mounted: function() {
        this.getUser();
    }
}
</script>