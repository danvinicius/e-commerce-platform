<template>
  <section class="products-list">
    <ProductCard
      v-for="product in products"
      :key="product.id"
      :product="product"
    ></ProductCard>
  </section>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import { Product } from "../../composables/useProduct";
import ProductCard from "./ProductCard.vue";

defineProps({
  products: Object as PropType<Product>,
});
</script>

<style scoped lang="scss">
.products-list {
  display: flex;
  flex-wrap: wrap;
  gap: 3rem;
  row-gap: 3rem;
}
</style>