<template>
  <div class="product-info">
    <h1 v-html="product.name"></h1>
    <ProductPrice class="price" :price="product.price"></ProductPrice>
    <p class="description" v-html="product?.description"></p>
    <ProductBenefits></ProductBenefits>
    <ProductSizes
      v-if="productSizes"
      :productSizes="productSizes"
    ></ProductSizes>
    <AddToCartButton></AddToCartButton>
    <ProductDelivery></ProductDelivery>
  </div>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import { Product } from "../../composables/useProduct";
import ProductPrice from "./ProductPrice.vue";
import AddToCartButton from "./AddToCartButton.vue";
import ProductBenefits from "./ProductBenefits.vue";
import ProductSizes from "./ProductSizes.vue";
import ProductDelivery from "./ProductDelivery.vue";
const props = defineProps({
  product: {
    type: Object as PropType<Product>,
    required: true,
  },
});

const productSizes = props.product.productSizes.map((ps) => ps.size);
</script>

<style scoped lang="scss">
.price {
  margin-bottom: 2rem;
}
.description {
  margin-bottom: 2rem;
  font-size: 1rem;
  max-width: 70%;
}
</style>