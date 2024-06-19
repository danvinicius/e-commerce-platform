<template>
  <footer>
    <div class="container flex space-between gap-2">
      <div class="main flex column align-start">
        <router-link to="/">
          <Logo></Logo>
        </router-link>
        <div class="about">
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus natus
            asperiores aliquam ex quia, nobis nisi in id dignissimos quasi.
          </p>
        </div>
      </div>
      <div class="sitemap flex column">
        <div class="title">
          <h3>Sitemap</h3>
          <ul class="flex column gap-1">
            <li>About Us</li>
            <li>FAQ's</li>
            <li>Customers Services</li>
          </ul>
        </div>
      </div>
      <div class="products flex column">
        <h3>Products</h3>
        <ul class="flex column gap-1">
          <li>Tops</li>
          <li>Bottons</li>
          <li>Accessories</li>
        </ul>
      </div>
      <div class="social-media-links flex column">
        <h3>Contact Us</h3>
        <ul class="flex row align-center gap-15">
          <li>
            <img
              src="../../assets/icon/Twitter.svg"
              alt="Twitter"
              title="Twitter"
            />
          </li>
          <li>
            <img
              src="../../assets/icon/Instagram.svg"
              alt="Instagram"
              title="Instagram"
            />
          </li>
          <li>
            <img src="../../assets/icon/Mail.svg" alt="E-mail" title="E-mail" />
          </li>
        </ul>
      </div>
    </div>
    <div class="container flex space-between">
      <p>{{ year }} - Neo6 &copy; All rights reserved</p>
      <p>
        Developed by
        <a href="https://www.linkedin.com/in/danvinicius/">Daniel Vinícius</a>
      </p>
    </div>
  </footer>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Logo from "../../assets/logo.svg";

const year = ref(new Date().getFullYear());
</script>

<style scoped lang="scss">
footer {
  background: var(--quaternary-color);
  padding: 1.5rem 0;

  a {
    color: var(--primary-color);
    text-decoration: unset;
  }
  .container {
    &:first-child {
      padding-bottom: 2rem;
      border-bottom: 1px solid #d2d2d2;
    }

    &:nth-child(2) {
      padding-top: 1rem;
    }
  }

  .main {
    flex-basis: 30%;
  }

  .sitemap,
  .products,
  .social-media-links {
    display: flex;
    flex-direction: column;

    h3 {
      margin-bottom: 1.5rem;
    }
  }
}
</style>