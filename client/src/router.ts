import { createRouter, createWebHistory } from 'vue-router'

import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import AdminLoginView from './views/AdminLoginView.vue'
import ProductView from './views/ProductView.vue'
import CategoryView from './views/CategoryView.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/login', component: LoginView },
  { path: '/admin/login', component: AdminLoginView },
  { path: '/product/:product', component: ProductView },
  { path: '/:category', component: CategoryView },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;