<template>
  <form class="form">
    <slot></slot>
  </form>
</template>

<script setup lang="ts">
</script>

<style scoped lang="scss">
form.form {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 1.25rem;
}
</style>