<template>
  <div class="product-discount-tag">
    <p>{{ product.discount ? product.discount : Math.max.apply(null, product.categories.map(c => c.discount)) }}% OFF</p>
  </div>
</template>

<script setup lang="ts">
import { PropType } from 'vue';
import { Product } from '../../composables/useProduct';

defineProps({
    product: Object as PropType<Product>
})
</script>

<style scoped lang=scss>
  .product-discount-tag {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: .15rem 1.5rem;
    background: var(--tertiary-color);
    font-weight: bold;
    color: #fff;
  }
</style>