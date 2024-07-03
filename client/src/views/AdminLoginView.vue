<template>
  <Header :isAdmin="true"></Header>
  <div class="admin-login">
    <div class="container flex">
      <AdminLoginForm></AdminLoginForm>
    </div>
  </div>
</template>
    
<script setup lang="ts">
import Header from "../components/header/Header.vue";
import AdminLoginForm from "../components/admin/AdminLoginForm.vue";
</script>
    
<style scoped lang="scss">
.admin-login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60vh;
  gap: 2rem;
}
</style>
    