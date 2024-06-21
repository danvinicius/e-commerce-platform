<template>
  <Button
    background="var(--primary-color)"
    text="Add to cart"
    :border="'1px solid #fff'"
    padding="1rem 4rem"
    color="#fff"
    icon="White_Shopping_Cart.svg"
  ></Button>
</template>
  
  <script setup lang="ts">
import Button from "../layout/Button.vue";
</script>
  
  <style scoped lang="scss">
button {
  max-width: initial;
  text-transform: uppercase;
  margin-bottom: 3rem;
}
</style>