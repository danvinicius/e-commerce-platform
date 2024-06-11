<template>
    <header>
        <div class="container">
            <Logo></Logo>
            <Menu></Menu>
            <LoginButton></LoginButton>
        </div>
    </header>
</template>

<script setup lang="ts">
import Logo from '../../assets/logo.svg';
import Menu from './Menu.vue';
import LoginButton from './LoginButton.vue';
</script>

<style scoped>
header {
    padding: .5rem 0; 
    .container {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>