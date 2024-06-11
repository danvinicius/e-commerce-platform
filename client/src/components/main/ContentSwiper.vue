<template>
  <div class="content-swiper">
    <h2>{{ title }}</h2>
    <Swiper :slides="slides">
    </Swiper>
  </div>
</template>

<script setup lang="ts">
import Swiper from '../layout/Swiper.vue';
import ProductCard from '../main/ProductCard.vue';

defineProps({
  title: String
})

const slides = [
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
  { component: ProductCard, props: { src: 'modelo-banner.png', name: 'Camisa Cyberpunk Roxa', price: 39.90 } },
];
</script>

<style scoped lang="scss">
.content-swiper {
  padding: 3rem 0 1rem;
  // if add flex column swiper.js crashes (?)

  h2 {
    margin-bottom: 1rem;
  }
}
</style>