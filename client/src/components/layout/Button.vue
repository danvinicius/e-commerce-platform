<template>
  <button
    :style="{ background, color, border, padding }"
    v-html="text"
  ></button>
</template>

<script setup lang="ts">
defineProps({
  text: String,
  background: String,
  color: String,
  border: String,
  padding: String,
});
</script>

<style scoped lang="scss">
button {
  padding: 0.75rem 1.75rem;
  border-radius: 0.5rem;
  transition: 0.2s;
  font-size: 1rem;

  &:hover {
    transform: scale(1.02);
  }
}
</style>