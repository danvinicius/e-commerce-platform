<template>
  <div class="product-details" v-if="product?.stock">
    <h3>Sizes</h3>
    <ProductSizesDetails :productSizes="product.stock"></ProductSizesDetails>
  </div>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import ProductSizesDetails from "./ProductSizesDetails.vue";
import { Product } from "../../composables/useProduct";

const props = defineProps({
  product: {
    type: Object as PropType<Product>,
    required: true,
  },
});
</script>

<style scoped lang="scss">
.product-details {
  border: 1px solid var(--secondary-text-color);
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 1rem;

  h3 {
    font-weight: bold;
  }
}
</style>