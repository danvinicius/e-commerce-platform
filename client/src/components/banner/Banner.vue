<template>
  <div class="banner flex">
    <div class="content-container flex column align-start">
      <h1 v-html="bannerProduct.title"></h1>
      <p v-html="bannerProduct.subtitle"></p>
      <BannerButton></BannerButton>
    </div>
    <div class="image-container relative">
      <router-link :to="productLink">
        <img
          :src="getimageUrl(bannerProduct.imageUrl)"
          :alt="bannerProduct.name"
          :title="bannerProduct.name"
        />
      </router-link>
    </div>
  </div>
</template>

<script setup lang="ts">
import BannerButton from "./BannerButton.vue";
import { useHelpers } from "../../composables/useHelpers";
const { getimageUrl, slugify } = useHelpers();

import { useProduct } from "../../composables/useProduct";

const { getBannerProduct } = useProduct();
import { onMounted, ref } from "vue";

const bannerProduct = ref([]);

onMounted(async () => {
  bannerProduct.value = await getBannerProduct();
});

const productLink = `/product/${slugify(
  bannerProduct.name + "-" + bannerProduct.id
)}`;
</script>

<style scoped lang="scss">
.banner {
  background-image: linear-gradient(
    20deg,
    var(--tertiary-color),
    var(--primary-color) 80%
  );
  padding: 3rem 2rem 2rem;
  border-radius: 0.5rem 0.5rem 9rem 0.5rem;
  margin: 0 0 4rem;

  .content-container {
    max-width: 60%;
    gap: 2.5rem;

    p,
    h1 {
      color: #fff;
    }

    p {
      line-height: 1.4;
      max-width: 70%;
    }

    h1 {
      line-height: 1.15;
      max-width: 90%;
    }
  }

  .image-container {
    img {
      transform: rotate(10deg);
      max-width: 350px;
    }
  }
}
</style>