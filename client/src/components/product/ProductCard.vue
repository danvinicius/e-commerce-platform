<template>
  <router-link
    :to="productLink"
    class="product-card primary-text-color decoration-none"
  >
  <ProductDiscountTag :product="product" class="product-discount-tag"/>
    <div
      class="image-container flex justify-center"
      :style="{ background: imageBackground, height }"
    >
      <img
        :src="getImageUrl(product.imageUrl)"
        :alt="product.name"
        :title="product.name"
      />
    </div>
    <div
      class="content-container flex column gap-5"
      :style="{ background: textBackground, color }"
    >
      <p v-html="product.name"></p>
      <ProductPrice :product="product"/>
    </div>
  </router-link>
</template>
  
  <script setup lang="ts">
import { PropType } from "vue";
import useHelpers from "../../composables/useHelpers";
import { Product } from "../../composables/useProduct";
import ProductPrice from "./ProductPrice.vue";
import ProductDiscountTag from "./ProductDiscountTag.vue";
const { getImageUrl, slugify } = useHelpers();
const props = defineProps({
  product: { type: Object as PropType<Product>, required: true },
  price: Number,
  height: Number,
  textBackground: String,
  imageBackground: String,
  color: String,
});

const productLink = `/product/${slugify(
  props.product.name + "-" + props.product.id
)}`;
</script>
  
  <style scoped lang="scss">
.product-card {
  display: flex;
  flex-direction: column;
  flex-basis: 300px;
  position: relative;

  .product-discount-tag {
    border-radius: 0 0 5px 0;
    position: absolute;
    top: 0;
    left: 0;
  }

  .image-container {
    height: 300px;
    background: var(--quaternary-color);
    img {
      max-width: 100%;
      max-height: 100%;
      object-fit: cover;
    }
  }
  .content-container {
    background: #fff;
    padding: 1rem 0;

    .price {
      font-size: 1.5rem;
    }
  }
}
</style>