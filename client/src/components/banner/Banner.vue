<template>
  <div class="banner flex">
    <div class="content-container flex column align-start">
      <h1>Discover the Most Stylish Cyberpunk Tops in Market</h1>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. xcepturi
        delectus optio inventore eaque ipsum sit. Maxime aliquid dolorum minima
        modi.
      </p>
      <BannerButton></BannerButton>
    </div>
    <div class="image-container relative">
      <router-link :to="productLink">
        <img
          :src="getImageUrl(product.imageSrc)"
          :alt="product.name"
          :title="product.name"
        />
      </router-link>
    </div>
  </div>
</template>

<script setup lang="ts">
import BannerButton from "./BannerButton.vue";
import { useHelpers } from "../../composables/useHelpers";
const { getImageUrl, slugify } = useHelpers();

const product = {
  id: "qweqweqwe-asd",
  imageSrc: "modelo-banner.png",
  name: "Camisa Cyberpunk Roxa",
  price: 39.9,
};

const productLink = `/product/${slugify(product.name + "-" + product.id)}`;
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