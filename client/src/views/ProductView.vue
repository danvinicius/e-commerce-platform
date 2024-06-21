<template>
  <Header></Header>
  <div class="product">
    <div class="container">
      <div class="main flex" v-if="product">
        <div class="product-image-wrapper">
          <ProductImage :imageUrl="product.imageUrl"></ProductImage>
        </div>
        <div class="product-info-wrapper">
          <ProductInfo :product="product"></ProductInfo>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>
      
<script setup lang="ts">
import Header from "../components/header/Header.vue";
import Footer from "../components/footer/Footer.vue";
import ProductImage from "../components/product/ProductImage.vue";
import ProductInfo from "../components/product/ProductInfo.vue";

import useProduct from "../composables/useProduct";
import { useRoute } from "vue-router";
const { getProductBySlug } = useProduct();
import { onMounted, ref } from "vue";

const product = ref();

const router = useRoute();

onMounted(async () => {
  const slug = router.path;

  product.value = await getProductBySlug(slug);
});
</script>
      
<style scoped lang="scss">
.product {
  color: var(--primary-text-color);
  padding: 2rem 0 4rem;
  .product-image-wrapper {
    min-width: 50%;
  }
}
</style>
      