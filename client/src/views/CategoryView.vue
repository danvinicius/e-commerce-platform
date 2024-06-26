<template>
  <Header></Header>
  <div class="category-view">
    <div class="container">
      <h1>{{ title }}</h1>
      <ProductsList v-if="products?.length" :products="products"></ProductsList>
    </div>
  </div>
</template>
      
  <script setup lang="ts">
import Header from "../components/header/Header.vue";
import ProductsList from "../components/product/ProductsList.vue";

import useCategory from "../composables/useCategory";
import useProduct from "../composables/useProduct";
import { useRoute } from "vue-router";
const { getCategoryBySlug, getBestSellingProducts } = useCategory();
const { getAllProducts } = useProduct();
import { onMounted, ref, watch } from "vue";

const category = ref();
const products = ref();
const title = ref("");

const router = useRoute();

onMounted(async () => {
  const slug = router.params?.category;
  if (slug) await getCategoryProducts(slug);
});

watch(
  () => router.params?.category,
  async () => await getCategoryProducts(router.params?.category)
);

const getCategoryProducts = async (slug) => {
  if (slug == "new-arrival") {
    title.value = "Our New Arrival";
    products.value = await getAllProducts();
    return;
  }
  if (slug == "best-selling") {
    title.value = "Our Best Selling";
    products.value = await getBestSellingProducts();
    return;
  }
  category.value = await getCategoryBySlug(slug as string);
  products.value = category.value.products;
  title.value = category.value.name;
};
</script>
      
  <style scoped lang="scss">
.category-view {
  padding-top: 2rem;

  h1 {
    color: var(--primary-text-color);
    margin-bottom: 2rem;
    font-size: 2rem;
  }
}
</style>
      