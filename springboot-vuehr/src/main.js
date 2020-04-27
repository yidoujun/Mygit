// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import iView from "iview"
// import 'iview/dist/styles/iview.css'  // 使用CSS
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 全局引入Echarts
import * as echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/bar'        // 柱状图
import 'echarts/map/js/china'         // 中国地图
// 引入提示框组件、标题组件、工具箱、legend组件
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/title'
import 'echarts/lib/component/toolbox'
import 'echarts/lib/component/legend'

// 设置反向代理，前端请求默认发生到http://localhost:8888
var axios = require('axios');

axios.defaults.baseURL = process.env.API_ROOT;
// 全局注册，之后在其他组件中通过this.$axios发送数据
Vue.prototype.$axios = axios;

Vue.prototype.$echarts = echarts;

// Vue.use(iView);
Vue.use(ElementUI);

Vue.config.productionTip = false;      // 阻止启动生产消息，常用作指令

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
