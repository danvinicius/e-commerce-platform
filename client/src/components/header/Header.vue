<template>
  <header>
    <div class="container flex align-center space-between">
      <Logo></Logo>
      <Menu></Menu>
      <LoginButton></LoginButton>
    </div>
  </header>
</template>

<script setup lang="ts">
import Logo from "../../assets/logo.svg";
import Menu from "./Menu.vue";
import LoginButton from "./LoginButton.vue";
</script>

<style scoped lang="scss">
header {
  padding: 0.5rem 0;
}
</style>