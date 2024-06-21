<template>
  <div class="product-price" v-if="price">
    <span class="product-price-type">R$</span>
    <span class="product-price-value">{{ priceFormatter(price, false) }}</span>
  </div>
</template>

<script setup lang="ts">
import useHelpers from "../../composables/useHelpers";
const { priceFormatter } = useHelpers();
defineProps({
  price: Number,
});
</script>

<style scoped lang="scss">
.product-price {
  display: flex;
  span {
    font-size: 1.75rem;

    &.product-price-type {
      font-size: 0.85rem;
    }
  }
}
</style>