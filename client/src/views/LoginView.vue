<template>
  <Header></Header>
  <div class="login">
    <div class="container flex">
      <LoginBanner></LoginBanner>
      <LoginForm
        v-if="currentView == 'login'"
        :currentView="currentView"
        @update:currentView="(view) => (currentView = view)"
      ></LoginForm>
      <SignupForm
        v-else-if="currentView == 'signup'"
        :currentView="currentView"
        @update:currentView="(view) => (currentView = view)"
      ></SignupForm>
      <ForgotPassword
        v-else-if="currentView == 'forgot'"
        :currentView="currentView"
        @update:currentView="(view) => (currentView = view)"
      ></ForgotPassword>
    </div>
  </div>
</template>
  
  <script setup lang="ts">
import Header from "../components/header/Header.vue";
import LoginForm from "../components/login/LoginForm.vue";
import SignupForm from "../components/login/SignupForm.vue";
import LoginBanner from "../components/login/LoginBanner.vue";
import { Ref, ref } from "vue";
import ForgotPassword from "../components/login/ForgotPassword.vue";

type view = Ref<"login" | "signup" | "forgot" | "reset">;

const currentView: view = ref("login");
</script>
  
  <style scoped lang="scss">
.login {
  display: flex;
  gap: 2rem;
  background-image: linear-gradient(
    to right,
    var(--primary-color) 50%,
    #fff 50%
  );
}
</style>
  