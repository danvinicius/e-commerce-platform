<template>
    <div class="product-ad" :style="{background, color}">
        <div class="content-container">
            <h3 v-html="title"></h3>
            <Button text="Buy now" background="#fff" color="var(--text-color)" :border="'1px solid #fff'"></Button>
        </div>
        <div class="image-container">
            <img :src="getImageUrl(image)" />
        </div>
    </div>
</template>

<script setup lang="ts">
import { useHelpers } from '../../composables/useHelpers';
const {getImageUrl} = useHelpers();
import Button from '../layout/Button.vue';

defineProps({
    title: String,
    image: String,
    background: String,
    color: String,
})
</script>

<style scoped lang="scss">
.product-ad {
    display: flex;
    justify-content: space-between;
    border-radius: 5rem .5rem 5rem .5rem;
    padding: 1.5rem 2rem;
    flex-grow: 1;
    flex-basis: 400px;
    
    .content-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        gap: 3rem;
    }

    .image-container {
        flex-basis: 400px;        

        img {
            max-width: 100%;
            max-height: 250px;
        }
    }
}
</style>