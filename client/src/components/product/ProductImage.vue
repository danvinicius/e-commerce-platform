<template>
  <div>
    <img :src="getImageUrl(imageUrl)" :alt="name" :title="name" />
  </div>
</template>

<script setup lang="ts">
import useHelpers from "../../composables/useHelpers";
const { getImageUrl, slugify } = useHelpers();

const props = defineProps({
  name: {
    required: true,
    type: String,
  },
  imageUrl: {
    required: true,
    type: String,
  },
});

const productLink = `/product/${slugify(props.name + "-" + props.id)}`;
</script>

<style scoped lang="scss">
</style>