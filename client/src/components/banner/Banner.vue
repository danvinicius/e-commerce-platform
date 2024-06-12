<template>
    <div class="banner">
        <div class="content-container">
            <h1>Discover the Most Stylish Cyberpunk Tops in Market</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. 
                xcepturi delectus optio inventore eaque ipsum sit. Maxime aliquid dolorum minima modi.</p>
            <BannerButton></BannerButton>
        </div>
        <div class="image-container">
           <img src="../../assets/img/modelo-banner.png" alt="">
        </div>
    </div>
</template>

<script setup lang="ts">
import BannerButton from './BannerButton.vue';


</script>

<style scoped lang="scss">
.banner {
    background-image: linear-gradient(20deg, var(--tertiary-color), var(--primary-color) 80%);
    display: flex;
    justify-content: flex-start;
    padding: 3rem 2rem 2rem;
    border-radius: .5rem .5rem 9rem .5rem;
    margin: 0 0 4rem;

    .content-container {
        max-width: 60%;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 2.5rem;

        p, h1 {
            color: #fff;
        }
        
        p {
            line-height: 1.4;
            max-width: 70%;
        }
        
        h1 {
            line-height: 1.15;
            max-width: 90%;
        }
    }

    .image-container {
        position: relative;

        img {
            transform: rotate(10deg);
            max-width: 350px;
        }
    }
}
</style>