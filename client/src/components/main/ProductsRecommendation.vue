<template>
  <section class="products-recomendation flex gap-2">
    <ProductAd
      v-for="product in recommendedProducts"
      :title="product.title"
      :imageUrl="product.imageUrl"
      :id="product.id"
      :name="product.name"
      background="var(--primary-color)"
      color="#fff"
      :key="product.id"
    />
  </section>
</template>
<script setup lang="ts">
import ProductAd from "../product/ProductAd.vue";
import { useProduct } from "../../composables/useProduct";

const { getRecommendedProduct } = useProduct();
import { onMounted, ref } from "vue";

const recommendedProducts = ref([]);

onMounted(async () => {
  recommendedProducts.value = await getRecommendedProduct();
});
</script>

<style scoped lang="scss">
section.products-recomendation {
  margin: 0 0 4rem;
  flex-wrap: wrap;
}
</style>