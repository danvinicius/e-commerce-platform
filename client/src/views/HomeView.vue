<template>
  <Header></Header>
  <div class="container">
    <Banner></Banner>
    <ContentSwiper title="Our Best Selling" :slides="slides"></ContentSwiper>
    <ProductsRecommendation></ProductsRecommendation>
    <OnSale title="15% off anime<br>clothing promotion"></OnSale>
    <ContentSwiper title="New Arrival" :slides="slides"></ContentSwiper>
  </div>
  <Footer></Footer>
</template>

<script setup lang="ts">
import Banner from "../components/banner/Banner.vue";
import Header from "../components/header/Header.vue";
import Footer from "../components/footer/Footer.vue";
import ContentSwiper from "../components/main/ContentSwiper.vue";
import ProductsRecommendation from "../components/main/ProductsRecommendation.vue";
import OnSale from "../components/main/OnSale.vue";
import ProductCard from "../components/product/ProductCard.vue";

const slides = [
  {
    component: ProductCard,
    props: { src: "modelo1.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo2.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo3.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo4.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo1.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo2.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo3.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
  {
    component: ProductCard,
    props: { src: "modelo4.png", name: "Camisa Cyberpunk Roxa", price: 39.9 },
  },
];
</script>

<style scoped>
</style>