<template>
  <Header></Header>
  <div class="home container">
    <Banner></Banner>
    <ContentSwiper
      v-if="bestSellingProducts.length"
      title="Our Best Selling"
      categoryLink="best-selling"
      :products="bestSellingProducts"
    ></ContentSwiper>
    <ProductsRecommendation></ProductsRecommendation>
    <OnSale
      v-if="saleDeadline.getTime() > new Date().getTime()"
      title="20% off anime<br>clothing promotion"
      :saleDeadline="saleDeadline"
    ></OnSale>
    <ContentSwiper
      v-if="newArrivalProducts.length"
      title="New Arrival"
      categoryLink="new-arrival"
      :products="newArrivalProducts"
    >
    </ContentSwiper>
  </div>
  <Footer></Footer>
</template>

<script setup lang="ts">
import Banner from "../components/banner/Banner.vue";
import Header from "../components/header/Header.vue";
import Footer from "../components/footer/Footer.vue";
import ContentSwiper from "../components/home/ContentSwiper.vue";
import ProductsRecommendation from "../components/home/ProductsRecommendation.vue";
import OnSale from "../components/home/OnSale.vue";

import useProduct from "../composables/useProduct";
import useCategory from "../composables/useCategory";
const { getAllProducts } = useProduct();
const { getBestSellingProducts } = useCategory();
import { onMounted, ref } from "vue";

const newArrivalProducts = ref([]);
const bestSellingProducts = ref([]);
const saleDeadline = new Date("2024-07-31T12:00:00");

onMounted(async () => {
  newArrivalProducts.value = await getAllProducts();
  bestSellingProducts.value = await getBestSellingProducts();
});
</script>

<style scoped lang="scss">
.home {
  padding-top: 1rem;
}
</style>
