<template>
  <header>
    <div class="container flex align-center space-between">
      <router-link to="/">
        <Logo></Logo>
      </router-link>
      <Menu v-if="!isAdmin"></Menu>
      <LogoutButton v-if="isAuth"></LogoutButton>
      <LoginButton v-else></LoginButton>
    </div>
  </header>
</template>

<script setup lang="ts">
import Logo from "../../assets/logo.svg";
import Menu from "./Menu.vue";
import LoginButton from "../login/LoginButton.vue";
import LogoutButton from "../login/LogoutButton.vue";
import useLogin from "../../composables/useLogin";
import { onMounted, ref } from "vue";

const { isAuthenticated } = useLogin();

const isAuth = ref(false);

onMounted(async () => {
  isAuth.value = await isAuthenticated();
})


defineProps({
  isAdmin: Boolean,
});
</script>


<style scoped lang="scss">
header {
  position: relative;
  padding: 0.5rem 0;
  box-shadow: 1px 1px 10px #aaa;
}
</style>