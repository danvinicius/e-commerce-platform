import { createRouter, createWebHistory } from 'vue-router'

import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import AdminLoginView from './views/AdminLoginView.vue'
import ProductView from './views/ProductView.vue'
import CategoryView from './views/CategoryView.vue'
import AdminView from './views/AdminView.vue'
import AdminProductView from './views/AdminProductView.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/login', component: LoginView },
  { path: '/admin', component: AdminView },
  { path: '/admin/login', component: AdminLoginView },
  { path: '/admin/product', component: AdminProductView },
  { path: '/product/:product', component: ProductView },
  { path: '/:category', component: CategoryView },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;