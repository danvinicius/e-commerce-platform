<template>
  <section class="admin-login-form flex column gap-125">
    <div class="title">
      <h1>Admin login</h1>
      <p class="authorized">Only for <span>authorized</span> people.</p>
    </div>
    <Form>
      <Input
        type="email"
        name="email"
        placeholder="your@email.com"
        label="E-mail"
        :value="email"
        @update:value="(value) => (email = value)"
      />
      <PasswordInput
        type="password"
        name="password"
        placeholder="*********"
        label="Password"
        :value="password"
        @changeToForgotPassword="changeToForgotPassword"
        @update:value="(value) => (password = value)"
        :enableForgotPassword="false"
      />
      <Button
        text="Login"
        background="var(--secondary-text-color)"
        :border="'none'"
        @click.prevent="handleLogin"
      ></Button>
    </Form>
  </section>
</template>
  
  <script setup lang="ts">
import Button from "../layout/Button.vue";
import Input from "../form/Input.vue";
import Form from "../form/Form.vue";
import useLogin from "../../composables/useLogin";
const { authenticate } = useLogin();
import { ref } from "vue";
import PasswordInput from "../form/PasswordInput.vue";

const email = ref("");
const password = ref("");

const handleLogin = async (event: any) => {
  if (email.value.length && password.value.length) {
    try {
      const data = await authenticate({
        email: email.value,
        password: password.value,
      });
      return data;
    } catch (error) {
      return null;
    }
  }
};
</script>
  
  <style scoped lang="scss">
.admin-login-form {
  background: #fff;
  margin: auto;
  width: 100%;

  h1 {
    font-size: 2rem;
    font-weight: bold;
    color: var(--primary-text-color);
  }

  p.authorized {
    color: var(--secondary-text-color);

    span {
      color: var(--primary-color);
    }
  }
}
</style>