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
  color: {
    type: String,
    default: "#fff",
  },
  border: String,
  padding: {
    type: String,
    default: "0.75rem 1.75rem",
  },
});
</script>

<style scoped lang="scss">
button {
  border-radius: 0.5rem;
  transition: 0.2s;
  font-size: 1rem;

  &:hover {
    transform: scale(1.02);
  }
}
</style>