<template>
  <router-link to="/login">
    <Button
      text="Sign in / Sign up"
      background="#fff"
      color="var(--primary-text-color)"
      :border="'2px solid var(--primary-color)'"
    ></Button>
  </router-link>
</template>

<script setup lang="ts">
import Button from "../layout/Button.vue";
</script>

<style scoped lang="scss">
</style>