<template>
  <Header></Header>
  <div class="home container">
    <Banner></Banner>
    <ContentSwiper
      v-if="bestSellingProducts.length"
      title="Our Best Selling"
      :products="bestSellingProducts"
    ></ContentSwiper>
    <ProductsRecommendation></ProductsRecommendation>
    <OnSale title="15% off anime<br>clothing promotion"></OnSale>
    <ContentSwiper
      v-if="newArrivalProducts.length"
      title="New Arrival"
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
import ContentSwiper from "../components/main/ContentSwiper.vue";
import ProductsRecommendation from "../components/main/ProductsRecommendation.vue";
import OnSale from "../components/main/OnSale.vue";

import { useProduct } from "../composables/useProduct";
const { getAllProducts, getBestSellingProducts } = useProduct();
import { onMounted, ref } from "vue";

const newArrivalProducts = ref([]);
const bestSellingProducts = ref([]);

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
