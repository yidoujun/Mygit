import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
// import Show from '@/components/Show'
// import ChinaMapShow from '@/components/ChinaMapShow'
const HelloWorld = () => import('@/components/HelloWorld');
const Show = () => import('@/components/Show');
const ChinaMapShow = () => import('@/components/ChinaMapShow');
const Index = () => import('@/components/Index');
const Login = () => import('@/components/Login');

Vue.use(Router);

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/show',
      name: 'Show',
      component: Show
    },
    {
      path: '/china',
      name: 'ChinaMapShow',
      component: ChinaMapShow
    },
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
