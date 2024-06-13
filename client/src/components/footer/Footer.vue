<template>
    <footer>
        <div class="container">
            <div class="main">
                <Logo></Logo>
                <div class="about">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
                        Minus natus asperiores aliquam ex quia,
                        nobis nisi in id dignissimos quasi.</p>
                    </div>
                </div>
                <div class="sitemap">
                    <div class="title">
                        <h3>Sitemap</h3>
                        <ul>
                            <li>About Us</li>
                            <li>FAQ's</li>
                            <li>Customers Services</li>
                        </ul>
                    </div>
                </div>
                <div class="products">
                    <h3>Products</h3>
                    <ul>
                        <li>Tops</li>
                        <li>Bottons</li>
                        <li>Accessories</li>
                </ul>
            </div>
            <div class="social-media-links">
                <h3>Contact Us</h3>
                <ul class="social-media-icons">
                    <li><img src="../../assets/icon/Twitter.svg" alt=""></li>
                    <li><img src="../../assets/icon/Instagram.svg" alt=""></li>
                    <li><img src="../../assets/icon/Mail.svg" alt=""></li>
                </ul>
            </div>
        </div>
        <div class="container">
            <p>{{ year }} - Neo6 &copy; All rights reserved</p>
            <p>Developed by <a href="https://www.linkedin.com/in/danvinicius/">Daniel Vinícius</a></p>
        </div>
    </footer>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Logo from '../../assets/logo.svg';

const year = ref(new Date().getFullYear())
</script>

<style scoped lang="scss">
footer {
    background: #f2f5ff;
    padding: 1.5rem 0;

    a {
        color: var(--primary-color);
        text-decoration: unset;
    }
    .container {
        display: flex;
        justify-content: space-between;
        gap: 2rem;
        
        &:first-child {
            padding-bottom: 2rem;
            border-bottom: 1px solid #d2d2d2;
        }

        &:nth-child(2) {
            padding-top: 1rem;
        }
    }
    
    .main {
        display: flex;
        flex-direction: column;
        flex-basis: 30%;
        align-items: flex-start;
    }

    .sitemap, .products, .social-media-links {
        display: flex;
        flex-direction: column;

        ul {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        h3 {
            margin-bottom: 1.5rem;
        }

        .social-media-icons {
            flex-direction: row;
            align-items: center;
            gap: 1.5rem;
        }
    }
    
}
</style>