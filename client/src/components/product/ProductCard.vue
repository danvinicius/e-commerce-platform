<template>
    <div class="product-card">
      <div class="image-container" :style="{background: imageBackground, height}">
        <img :src="getImageUrl(src)" />
      </div>
      <div class="content-container" :style="{background: textBackground, color}">
        <p>{{ name }}</p>
        <p class="price">${{ price }}</p>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { useHelpers } from '../../composables/useHelpers';
  const {getImageUrl} = useHelpers();
  defineProps({
    src: String,
    name: String,
    price: Number,
    height: Number,
    textBackground: String,
    imageBackground: String,
    color: String,
  });
  </script>
  
  <style scoped lang="scss">
  .product-card {
    .image-container {
      height: 300px;
      background: #f2f5ff;
      display: flex;
      justify-content: center;
      img {
        max-width: 100%;
        max-height: 100%;
        object-fit: cover;
      }
    }
    .content-container {
      background: #fff;
      display: flex;
      flex-direction: column;
      gap: .5rem;
      padding: 1rem 0;

      .price {
        font-size: 1.5rem;
      }
    }
}
  </style>