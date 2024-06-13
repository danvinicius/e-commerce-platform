<template>
  <section class="products-recomendation flex gap-2">
    <ProductAd
      title="Lorem ipsum dolor sit amet consectetur adipisicing elit."
      imageSrc="modelo1.png"
      id="sdsaghffghxxx-xxx"
      productName="Camisa Cyberpunk Roxa"
      background="var(--primary-color)"
      color="#fff"
    />
    <ProductAd
      title="Lorem ipsum dolor sit amet consectetur adipisicing elit."
      imageSrc="modelo2.png"
      id="sdsaghffghfhd-asd"
      productName="Camisa Cyberpunk Amarela"
      background="var(--secondary-color)"
      color="#fff"
    />
  </section>
</template>
<script setup lang="ts">
import ProductAd from "../product/ProductAd.vue";
</script>

<style scoped lang="scss">
section.products-recomendation {
  margin: 0 0 4rem;
}
</style>