import { createRouter, createWebHistory } from 'vue-router'

import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'
import AdminLoginView from './views/AdminLoginView.vue'
import ProductView from './views/ProductView.vue'
import CategoryView from './views/CategoryView.vue'
import AdminView from './views/AdminView.vue'
import AdminProductView from './views/AdminProductView.vue'

const routes = [
  { path: '/', component: HomeView, name: 'home' },
  { path: '/login', component: LoginView, name: 'login' },
  { path: '/admin', component: AdminView, name: 'admin' },
  { path: '/admin/login', component: AdminLoginView, name: 'adminLogin' },
  { path: '/admin/product', component: AdminProductView, name: 'adminProduct' },
  { path: '/product/:product', component: ProductView, name: 'product' },
  { path: '/:category', component: CategoryView, name: 'category' },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

import useLogin from "./composables/useLogin";
import useLocalStorage from "./composables/useLocalStorage";
const { isAdmin } = useLogin();
const { getItem } = useLocalStorage();

router.beforeEach(async (to, from, next) => {
  const token = (getItem('token'))
  if ((to.name == 'admin' || to.name == 'adminProduct') && (!token || (!(await isAdmin(token))))) next({ name: 'adminLogin' })
  else next()
})

export default router;