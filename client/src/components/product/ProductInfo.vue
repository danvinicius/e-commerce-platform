<template>
  <div class="product-info">
    <h1 v-html="product.name"></h1>
    <ProductDiscountTag :product="product" class="product-discount-tag"/>
    <ProductPrice class="price" :product="product"/>
    <p class="description" v-html="product?.description"></p>
    <ProductBenefits></ProductBenefits>
    <ProductSizes
      v-if="product?.stock"
      :productSizes="product.stock"
      v-model:selectedSize="selectedSize"
      @update:selectedSize="(size) => (selectedSize = size)"
    ></ProductSizes>
    <AddToCartButton></AddToCartButton>
    <ProductDelivery></ProductDelivery>
  </div>
</template>

<script setup lang="ts">
import { PropType, ref } from "vue";
import { Product } from "../../composables/useProduct";
import ProductPrice from "./ProductPrice.vue";
import AddToCartButton from "./AddToCartButton.vue";
import ProductBenefits from "./ProductBenefits.vue";
import ProductSizes from "./ProductSizes.vue";
import ProductDelivery from "./ProductDelivery.vue";
import ProductDiscountTag from "./ProductDiscountTag.vue";

defineProps({
  product: {
    type: Object as PropType<Product>,
    required: true,
  },
});

const selectedSize = ref("");
</script>

<style scoped lang="scss">
.product-discount-tag {
  width: max-content;
  margin-top: 1rem;
  border-radius: 5px;
  margin-bottom: 1rem;
}
.price {
  margin-top: .5rem;
  margin-bottom: 2rem;
}
.description {
  margin-bottom: 2rem;
  font-size: 1rem;
  max-width: 70%;
}
</style>