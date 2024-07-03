<template>
  <div class="product-price" v-if="product.price">
    <div class="old-price" v-if="discount">{{ priceFormatter(product.price, true) }}</div>
    <span class="product-price-value" v-if="discount">{{ priceFormatter(product.price - ((discount / 100) * product.price), true) }}</span>
    <span class="product-price-value" v-else>{{ priceFormatter(product.price, true) }}</span>
  </div>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import useHelpers from "../../composables/useHelpers";
import { Product } from "../../composables/useProduct";
const { priceFormatter } = useHelpers();
const props = defineProps({
  product: {
    required: true,
    type: Object as PropType<Product>
  }
});

const discount = props.product.discount ? props.product.discount : Math.max.apply(null, props.product.categories.map(c => c.discount))
</script>

<style scoped lang="scss">
.product-price {
  display: flex;
  span {
    font-size: 1.75rem;
  }

  .old-price {
    text-decoration: line-through;
    font-size: 1.1rem;
    margin-right: .5rem;
  }
}
</style>