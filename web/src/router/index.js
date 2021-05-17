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
      path: '/userInfo',
      name: 'usereInfo',
      component: () => import("../components/userInfo.vue")
    },
    {
      path: '/adminHome',
      name: 'adminHome',
      component: () => import('../components/adminHome.vue')
    },
    {
      path: '/books',
      name: 'books',
      component: () => import("../components/books.vue")
    },
    {
      path: '/records',
      name: 'records',
      component: () => import("../components/records.vue")
    },
    //========================================================
    {
      path: '/userMessage',
      name: 'userMessage',
      component: () => import("../components/userMessage.vue")
    },
    {
      path: '/discussHome',
      name: 'discussHome',
      component: () => import("../components/discussHome.vue")
    },
    {
      path: '/discussHome/discussDetail/:id',
      name:'discussDetail',
      component: () => import("../components/discussDetail.vue")
    },
    {
      path: '/booklistHome',
      name: 'booklistHome',
      component: () => import("../components/booklistHome.vue")
    },
    {
      path: '/booklistHome/booklistDetail/:id',
      name:'booklistDetail',
      component: () => import("../components/booklistDetail.vue")
    },
    {
      path: '/bookDetail/:id',
      name:'bookDetail',
      component: () => import("../components/bookDetail.vue")
    },
  ]
})
