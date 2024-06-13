<template>
  <section class="on-sale">
    <div class="content-container flex space-between align-center">
      <h2 v-html="title"></h2>
      <Timer :time="saleDeadline"></Timer>
    </div>
    <div class="products-container">
      <ContentSwiper :slides="slides" :slidesPerView="3" />
    </div>
  </section>
</template>

<script setup lang="ts">
import Timer from "../layout/Timer.vue";
import ContentSwiper from "../main/ContentSwiper.vue";
import ProductCard from "../product/ProductCard.vue";

defineProps({
  title: String,
});

const saleDeadline = new Date("2024-06-20T12:00:00");

const slides = [
  {
    component: ProductCard,
    props: {
      id: "qweqweqwe-asd",
      imageSrc: "modelo1.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
      height: 200,
      textBackground: "transparent",
      color: "#fff",
    },
  },
  {
    component: ProductCard,
    props: {
      id: "fdsfsdf-asd",
      imageSrc: "modelo2.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
      height: 200,
      textBackground: "transparent",
      color: "#fff",
    },
  },
  {
    component: ProductCard,
    props: {
      id: "vbcvbbvc-asd",
      imageSrc: "modelo3.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
      height: 200,
      textBackground: "transparent",
      color: "#fff",
    },
  },
  {
    component: ProductCard,
    props: {
      id: "jkujhsd-asd",
      imageSrc: "modelo4.png",
      productName: "Camisa Cyberpunk Roxa",
      price: 39.9,
      height: 200,
      textBackground: "transparent",
      color: "#fff",
    },
  },
];
</script>

<style scoped lang="scss">
.on-sale {
  padding: 2rem 2rem 1rem;
  background: #111;
  margin-bottom: 4rem;
  .content-container {
    margin-bottom: 2rem;
    h2 {
      font-size: 2rem;
      color: #fff;
    }
  }
}
</style>