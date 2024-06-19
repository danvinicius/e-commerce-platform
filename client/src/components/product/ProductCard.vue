<template>
  <router-link
    :to="productLink"
    class="product-card primary-text-color decoration-none"
  >
    <div
      class="image-container flex justify-center"
      :style="{ background: imageBackground, height }"
    >
      <img :src="getimageUrl(imageUrl)" :alt="name" :title="name" />
    </div>
    <div
      class="content-container flex column gap-5"
      :style="{ background: textBackground, color }"
    >
      <p v-html="name"></p>
      <p class="price">${{ price }}</p>
    </div>
  </router-link>
</template>
  
  <script setup lang="ts">
import { useHelpers } from "../../composables/useHelpers";
const { getimageUrl, slugify } = useHelpers();
const props = defineProps({
  id: {
    required: true,
    type: String,
  },
  imageUrl: {
    required: true,
    type: String,
  },
  name: {
    required: true,
    type: String,
  },
  price: Number,
  height: Number,
  textBackground: String,
  imageBackground: String,
  color: String,
});

const productLink = `/product/${slugify(props.name + "-" + props.id)}`;
</script>
  
  <style scoped lang="scss">
.product-card {
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