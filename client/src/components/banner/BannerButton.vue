<template>
  <Button
    background="#fff"
    text="Buy now"
    :border="'1px solid #fff'"
    padding=".75rem 4rem"
    color="var(--primary-text-color)"
  ></Button>
</template>

<script setup lang="ts">
import Button from "../layout/Button.vue";
</script>

<style scoped lang="scss">
button {
  max-width: initial;
}
</style>