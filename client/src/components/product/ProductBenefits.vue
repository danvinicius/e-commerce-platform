<template>
  <div class="product-benefits">
    <div class="shipping-benefit">
      <img src="../../assets/icon/Delivery.svg" alt="" />
      <p>Delivery nationwide</p>
    </div>
    <div class="category-benefit">
      <img src="../../assets/icon/Discount.svg" alt="" />
      <p>20% OFF</p>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped lang="scss">
.product-benefits {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 2rem;

  .shipping-benefit,
  .category-benefit {
    display: flex;
    align-items: center;
    gap: 1rem;

    p {
      font-weight: bold;
      text-transform: uppercase;
    }
  }
}
</style>