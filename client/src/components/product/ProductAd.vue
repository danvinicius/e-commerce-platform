<template>
  <div class="product-ad flex space-between" :style="{ background, color }">
    <div class="content-container flex column justify-center align-start">
      <h3 v-html="title"></h3>
      <router-link :to="productLink">
        <Button
          text="Buy now"
          background="#fff"
          color="var(--primary-text-color)"
          :border="'1px solid #fff'"
        ></Button>
      </router-link>
    </div>
    <router-link class="image-container" :to="productLink">
      <img :src="getimageUrl(imageUrl)" :alt="name" :title="name" />
    </router-link>
  </div>
</template>

<script setup lang="ts">
import { useHelpers } from "../../composables/useHelpers";
const { getimageUrl, slugify } = useHelpers();
import Button from "../layout/Button.vue";

const props = defineProps({
  title: String,
  background: String,
  color: String,
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
});

const productLink = `/product/${slugify(props.name + "-" + props.id)}`;
</script>

<style scoped lang="scss">
.product-ad {
  border-radius: 5rem 0.5rem 5rem 0.5rem;
  padding: 1.5rem 2rem;
  flex-grow: 1;
  flex-basis: 300px;

  .content-container {
    gap: 3rem;

    h3 {
      color: #fff;
    }
  }

  .image-container {
    flex-basis: 400px;

    img {
      max-width: 100%;
      max-height: 250px;
    }
  }
}
</style>