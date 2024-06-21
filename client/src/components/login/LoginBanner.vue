<template>
  <section class="login-banner">
    <img
      src="../../assets/img/online-shopping.svg"
      alt="Login banner image: Woman accessing e-commerce on mobile"
    />
    <div class="content">
      <h2>Welcome!</h2>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic debitis
        repudiandae labore quasi sit veniam obcaecati facere!
      </p>
    </div>
  </section>
</template>

<script setup lang="ts">
</script>

<style scoped lang="scss">
.login-banner {
  height: 90vh;
  background: var(--primary-color);
  display: flex;
  padding-right: 3rem;
  gap: 2rem;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;

  img {
    max-width: 100%;
  }

  .content {
    display: flex;
    flex-direction: column;
    color: #fff;
    gap: 1rem;

    h2 {
      font-size: 3rem;
    }

    p {
      font-size: 20px;
    }
  }
}
</style>