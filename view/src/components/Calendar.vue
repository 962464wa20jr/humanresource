<template>
    <div class="calendar-com"  :style="width">
        <!-- 日历组件 -->
        <div class="calendar">
            <el-calendar v-model="calendarVal">
                <template slot="dateCell" slot-scope="{ date, data }">
                    <p :class="remark(data)">
                        {{
                            data.day
                                .split('-')
                                .slice(2)
                                .join('-')
                        }}
                    </p>
                </template>
            </el-calendar>
        </div>
        
    </div>
</template>

<script>
export default {
    props: ['width'],
    data() {
        return {
            calendarVal: new Date(),
        }
    },
    methods: {
    
        remark(data) {
            let str = ''
            if (data.isSelected) {
                str = 'is-selected'
            }
            return str
        },
       
       
    },
   
}
</script>

<style lang="less">
.calendar {
    .el-calendar-table tr td:first-child {
        border-left: none;
    }
    .el-calendar-table tr:first-child td {
        border-top: none;
    }
    .el-calendar__header {
        border-bottom: none;
        border-left: none;
    }
    .el-calendar-table td {
        width: 30px;
        border-bottom: none;
        border-right: none;
    }
    .el-calendar-table td.is-selected {
        background: none;
    }
    .el-calendar-table td.is-today {
        background: none;
        > div > p {
            color: #fff;
            background: #00cc8b;
        }
    }
    .el-calendar-table .el-calendar-day {
        margin: auto;
        width: 30px;
        height: 30px;
        padding: 0;
        text-align: center;
    }
    .el-calendar-day > p {
        margin: auto;
        width: 25px;
        height: 25px;
        border-radius: 25px;
        text-align: center;
        line-height: 25px;
    }
    .el-calendar__body {
        width: 280px;
    }
    .el-calendar__header {
        width: 280px;
    }
    
}
</style>

<style lang="less">

.calendar {
    .is-selected {
        background: #00cc8b;
    }
    .isremark {
        position: relative;
    }
    .isremark::before {
        content: '';
        position: absolute;
        width: 4px;
        height: 4px;
        border-radius: 4px;
        bottom: 0;
        left: 50%;
        margin-left: -2px;
        background: #00cc7b;
    }
    .calendar_title {
        display: flex;
        justify-content: space-around;
    }
}

</style>

