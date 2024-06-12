<template>
    <div class="products-recomendation">
        <ProductAd
            title="Lorem ipsum dolor sit amet<br> consectetur adipisicing elit."
            image="modelo1.png"
            background="var(--primary-color)"
            color="#fff"
        />
        <ProductAd
            title="Lorem ipsum dolor sit amet<br> consectetur adipisicing elit."
            image="modelo2.png"
            background="var(--secondary-color)"
            color="#fff"
        />
    </div>
</template>
<script setup lang="ts">
import ProductAd from '../product/ProductAd.vue';
</script>

<style scoped lang="scss">
.products-recomendation {
    display: flex;
    gap: 2rem;
    justify-content: stretch;
    margin: 0 0 4rem;
}
</style>