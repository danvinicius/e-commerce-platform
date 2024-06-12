<script setup lang="ts">
import Banner from './components/banner/Banner.vue';
import Header from './components/header/Header.vue'
import ContentSwiper from './components/main/ContentSwiper.vue'
import ProductCard from './components/product/ProductCard.vue';
import ProductsRecommendation from './components/main/ProductsRecommendation.vue';
import OnSale from './components/main/OnSale.vue';

const slides = [
  { component: ProductCard, props: { src: 'modelo1.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo2.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo3.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo4.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo1.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo2.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo3.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo4.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
];

</script>

<template>
    <Header></Header>
    <div class="container">
      <Banner></Banner>
      <ContentSwiper title="Our Best Selling" :slides="slides"></ContentSwiper>
      <ProductsRecommendation></ProductsRecommendation>
      <OnSale title="15% off anime<br>clothing promotion"></OnSale>
      <ContentSwiper title="New Arrival" :slides="slides"></ContentSwiper>
    </div>
        
</template>

<style scoped lang="scss">
h1 {
    color: #1f1f1f;
  }
</style>
