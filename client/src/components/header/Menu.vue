<template>
  <nav class="primary-text-color">
    <ul class="flex align-center justify-center">
      <li>
        <router-link to="/categories" class="primary-text-color decoration-none"
          >Our Categories</router-link
        >
      </li>
      <li>
        <router-link to="/products" class="primary-text-color decoration-none"
          >Our New Arrival</router-link
        >
      </li>
      <li>
        <router-link
          to="/category/anime"
          class="primary-text-color decoration-none"
          >Anime</router-link
        >
      </li>
    </ul>
  </nav>
</template>

<script setup lang="ts">
</script>

<style scoped lang="scss">
nav {
  ul {
    gap: 1.8rem;
  }
}
</style>