<script setup lang="ts">
import Banner from './components/banner/Banner.vue';
import Header from './components/header/Header.vue'
import ContentSwiper from './components/main/ContentSwiper.vue'

</script>

<template>
    <Header></Header>
    <div class="container">
      <Banner></Banner>
      <ContentSwiper title="Our Best Selling"></ContentSwiper>
    </div>
        
</template>

<style scoped lang="scss">
h1 {
    color: #1f1f1f;
  }
</style>
