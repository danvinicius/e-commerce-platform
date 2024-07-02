<template>
  <Header :isAdmin="true"></Header>
  <div class="admin">
    <AdminSidebar
      v-model:selectedOption="selectedOption"
      @update:selectedOption="(option) => (selectedOption = option)"
    ></AdminSidebar>
    <AdminProductsList v-if="selectedOption == 'products'"></AdminProductsList>
    <AdminCategoriesList
      v-if="selectedOption == 'categories'"
    ></AdminCategoriesList>
  </div>
</template>
        
  <script setup lang="ts">
import Header from "../components/header/Header.vue";
import AdminSidebar from "../components/admin/AdminSidebar.vue";
import AdminProductsList from "../components/admin/AdminProductsList.vue";
import AdminCategoriesList from "../components/admin/AdminCategoriesList.vue";
import { ref } from "vue";

const selectedOption = ref("products");
</script>
        
  <style scoped lang="scss">
.admin {
  color: var(--primary-text-color);
  height: 90vh;
  display: flex;
  overflow: hidden;
}
</style>
        