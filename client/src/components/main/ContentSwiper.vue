<template>
  <div class="content-swiper">
    <h2>{{ title }}</h2>
    <Swiper :slides="slides">
    </Swiper>
  </div>
</template>

<script setup lang="ts">
import Swiper, { Slide } from '../layout/Swiper.vue';

defineProps({
  title: String,
  slides: Array<Slide>,
})

</script>

<style scoped lang="scss">
.content-swiper {
  margin: 0 0 4rem;
  // if add flex column swiper.js crashes (?)

  h2 {
    margin-bottom: 1rem;
  }
}
</style>