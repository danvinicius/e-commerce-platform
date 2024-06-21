<template>
  <section class="content-swiper">
    <h2 v-html="title"></h2>
    <Swiper
      :slides="slides"
      :slidesPerView="slidesPerView"
      :spaceBetween="spaceBetween"
    >
    </Swiper>
  </section>
</template>

<script setup lang="ts">
import Swiper from "../layout/Swiper.vue";
import { Product } from "../../composables/useProduct";
import { onMounted, ref, shallowRef } from "vue";
import ProductCard from "../product/ProductCard.vue";

const slides = ref([]);

const props = defineProps({
  title: String,
  products: {
    required: true,
    type: Array<Product>,
  },
  slidesPerView: Number,
  spaceBetween: Number,
  height: Number,
  textBackground: String,
  imageBackground: String,
  color: String,
});

onMounted(() => {
  if (props.products?.length) {
    slides.value = props.products.map((product: Product) => ({
      component: shallowRef(ProductCard),
      props: {
        id: product.id,
        imageUrl: product.imageUrl,
        name: product.name,
        price: product.price,
        height: props.height,
        textBackground: props.textBackground,
        imageBackground: props.imageBackground,
        color: props.color,
      },
    }));
  }
});
</script>

<style scoped lang="scss">
section.content-swiper {
  margin: 0 0 4rem;
  // if add flex column swiper.js crashes (?)

  h2 {
    margin-bottom: 1rem;
  }
}
</style>