<template>
  <body id="poster">
    <show-header></show-header>
    <!--创建一个echarts的容器-->
   <div ref="echarts-one" style="width: 500px; height: 500px"></div>
    <div>
      <el-button @click="getData">按钮</el-button>
    </div>
    <show-footer></show-footer>
  </body>
</template>

<script>
    import showHeader from '@/components/common/ShowHeader.vue'
    import showFooter from '@/components/common/ShowFooter.vue'
    export default {
      name: "Show",
      components: {
        showHeader,
        showFooter
      },
      // 数据定义
      data() {
        return {

        }
      },
      // 钩子函数
      mounted() {

      },
      // 方法
      methods: {
        // 获取数据
        getData() {
          this.$axios
            .post('/api/getData')
            .then(res => {
              if (res) {
                this.show1(res.data.data)
              }
            })
            .catch(err => {
              alert(err.data.message)
            })
        },
        show1(echartsData) {
          // 基于准备好的dom，初始化echarts实例化
          var myChart = this.$echarts.init(this.$refs['echarts-one']);
          // 绘制图表
          var option = {
            title: {text: 'ECharts 入门示例'},
            tooltip: {},
            xAxis: {
              data: echartsData.names
            },
            yAxis: {},
            series: [{
              name: '销量',
              type: 'bar',
              data: echartsData.values
            }]
          };
          myChart.setOption(option);
        }
      }
    }
</script>

<style>
  #poster {
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
