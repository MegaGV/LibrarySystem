import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../components/register.vue')
    },
    {
      path: '/userHome',
      name: 'userHome',
      component: () => import('../components/userHome.vue')
    },
    {
      path: '/adminHome',
      name: 'adminHome',
      component: () => import('../components/adminHome.vue')
    },
  ]
})
