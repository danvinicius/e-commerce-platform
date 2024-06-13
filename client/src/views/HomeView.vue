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
    props: {
      id: "sdshfghgfhd-asd",
      imageSrc: "modelo1.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sdsaghffghfhd-asd",
      productName: "Camisa Cyberpunk Roxa",
      imageSrc: "modelo2.png",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sdscfr324d-asd",
      imageSrc: "modelo3.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sdujuj7ud-asd",
      imageSrc: "modelo4.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "svnmfgasd-asd",
      imageSrc: "modelo1.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sd893vgdasd-asd",
      imageSrc: "modelo2.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sdsdsdfsaad-asd",
      imageSrc: "modelo3.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
  {
    component: ProductCard,
    props: {
      id: "sdnxcei5asd-asd",
      imageSrc: "modelo4.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
    },
  },
];
</script>

<style scoped>
</style>