<template>
  <Button
    text="Logout"
    background="#fff"
    color="var(--primary-text-color)"
    :border="'2px solid var(--primary-color)'"
    @click="handleLogout"
  ></Button>
</template>
  
  <script setup lang="ts">
import Button from "../layout/Button.vue";
import useLogin from "../../composables/useLogin";
import { useRouter } from "vue-router";

const router = useRouter();

const { logout } = useLogin();

const handleLogout = () => {
  logout();
  router.push("/login");
};
</script>
  
  <style scoped lang="scss">
</style>