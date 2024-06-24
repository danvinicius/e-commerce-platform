<template>
  <ul class="product-sizes">
    <li
      class="pointer"
      v-for="size in orderProductSizes(productSizes)"
      :key="size"
    >
      <p>{{ size }}</p>
    </li>
  </ul>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import { ProductStock } from "../../composables/useProduct";

defineProps({
  productSizes: {
    type: Object as PropType<ProductStock>,
    required: true,
  },
});

function orderProductSizes(productSizes: ProductStock) {
  const order = ["PP", "P", "M", "G", "GG", "XGG"];

  return Object.keys(productSizes).sort((a, b) => {
    return order.indexOf(a) - order.indexOf(b);
  });
}
</script>

<style scoped lang="scss">
.product-sizes {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 2rem;

  li {
    width: 3rem;
    height: 3rem;
    border-radius: 50%;
    background: transparent;
    border: 1px solid var(--secondary-text-color);
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>