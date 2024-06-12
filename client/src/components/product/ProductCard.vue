<template>
    <div class="product-card">
      <div class="image-container">
        <img :src="getImageUrl(src)" />
      </div>
      <div class="content-container">
        <p>{{ name }}</p>
        <p class="price">${{ price }}</p>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { useHelpers } from '../../composables/useHelpers';
  const {getImageUrl} = useHelpers();
  defineProps({
    src: {
      type: String,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
    price: {
      type: Number,
      required: true,
    },
  });
  </script>
  
  <style scoped lang="scss">
  .product-card {
    .image-container {
      width: 300px;
      background: #f2f5ff;
      img {
        max-width: 100%;
        max-height: 300px;
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