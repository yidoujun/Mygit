<template>
    <div id="china">
      <div id="chinaMap" style="height: 800px; width: 1000px;" ref="myEcharts"></div>
      <div>
        <el-button @click="getChina">按钮</el-button>
      </div>
    </div>

</template>

<script>
    require('echarts/map/js/province/beijing');   // 引入北京地图
    require('echarts/map/js/province/zhejiang');  // 引入浙江地图
    require('echarts/map/js/province/hunan');     // 引入湖南地图
    export default {
        name: "ChinaMapShow",
        data() {
          return {

          }
        },
      mounted() {

      },
      computed :{
          dataFormat: () => (arr) => arr.map((i) =>
            ({name: i.provinceName, value: i.provinceNum}))
      },
      methods: {
          // 获取地图数据
          getChina() {
            this.$axios
                .post('/api/getChina')
                .then( res => {
                  if (res) {
                    this.chinamap(res.data.data)
                  }

                })
                .catch(err =>{

                })
          },
          chinamap(chianData) {
            console.log(chianData);
            /*function map(chianData){
              return chianData.map((i) => ({name: i.provinceName, value: i.provinceNum}));
            }*/
            // 处理数据
            var mapData = this.dataFormat(chianData);
            // 基于准备好的dom，初始化echarts实例化
            var myChart = this.$echarts.init(this.$refs['myEcharts']);
            var options = {
              backgroundColor: '#87CEFA',  //设置背景颜色
              title: {
                show:true,
                text: '中国地图',
                left:'center'
              },
              tooltip: {
                trigger: 'item'
              },
              //左侧小导航图标
              visualMap: {
                show : true,
                x: 'left',
                y: 'bottom',
                splitList: [
                  {start: 10, end:20},
                  {start: 6, end: 10},
                  {start: 0, end: 6},
                ],
                color: ['#1E90FF', '#7FFFAA', '#F0E68C']
              },
              // 配置属性
              series: [{
                name: '数量',
                type: 'map',
                map: 'china',
                roam: false,
                zoom:1.2,
                label: {
                  normal: {
                    show: true ,
                  },
                  emphasis: {
                    show: false
                  }
                },
                data: mapData
              }]
            };
            // var count = -1;
            /*setInterval(function () {
                var curr = count % 34;    // 数字为cityArr.lenth
                myChart.dispatchAction({
                  type: 'downplay',
                  seriesIndex: 0.
                });
                myChart.dispatchAction({
                  type: 'highlight',
                  seriesIndex: 0,
                  dataIndex: curr
                });
                myChart.dispatchAction({
                  type: 'showTip',
                  seriesIndex: 0,   // 因为只有一组数据，所以此处应为0
                  dataIndex: curr
                });
                count++;
            }, 500);*/
            myChart.setOption(options);
            // 获取随机数
            function randomData() {
              return Math.round(Math.random() * (12 - 1) + 1)
            }
            /*myChart.on('mouseover', function (params) {
              var dataIndex = params.dataIndex;
            });*/
            myChart.on('dblclick', function (chinaParam) {
              if(chinaParam.name == "北京" || chinaParam.name == "浙江" || chinaParam.name == "湖南"){
                var option = myChart.getOption();
                console.log(option);
                console.log(chinaParam)
                option.title[0].text = chinaParam.name + "地图";
                option.series[0].map = chinaParam.name;
                option.series[0].mapType  = chinaParam.name;
                option.series[0].data = cityData();
                myChart.clear();
                myChart.setOption(option,true);
              } else {
                alert(chinaParam.name+"区域还未开通！");
              }
            });
            myChart.on('click', function (params) {
              myChart.clear();
              myChart.setOption(options,true);
            });

            function cityData() {
              var data = [
                {name: '长沙市',value:randomData()},
                {name: '岳阳市',value:randomData() },
                {name: '永州市',value:randomData() },
                {name: '邵阳市',value:randomData() },
              ];
              return data;
            }
          }
      }
    }
</script>

<style scoped>

</style>
