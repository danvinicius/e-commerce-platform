<template>
  <div class="product-image">
    <img :src="getImageUrl(product.imageUrl)" :alt="product.name" :title="product.name" />
  </div>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import useHelpers from "../../composables/useHelpers";
import { Product } from "../../composables/useProduct";
const { getImageUrl } = useHelpers();

defineProps({
  product: Object as PropType<Product>
});
</script>

<style scoped lang="scss">
</style>