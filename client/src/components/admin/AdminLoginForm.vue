<template>
  <section class="admin-login-form flex column gap-125">
    <div class="title">
      <h1>Admin login</h1>
      <p class="authorized">Only for <span>authorized</span> people.</p>
    </div>
    <Form>
      <Input type="email" name="email" placeholder="your@email.com" label="E-mail" :value="email"
        @update:value="(value) => updateEmail(value)" />
      <PasswordInput type="password" name="password" placeholder="*********" label="Password" :value="password"
        @update:value="(value) => updatePassword(value)" :enableForgotPassword="false" />
      <span class="error">{{ error }}</span>
      <Button text="Login" background="var(--secondary-text-color)" :border="'none'"
        @click.prevent="handleLogin"></Button>
    </Form>
  </section>
</template>

<script setup lang="ts">
import Button from "../layout/Button.vue";
import Input from "../form/Input.vue";
import PasswordInput from "../form/PasswordInput.vue";
import Form from "../form/Form.vue";
import useLogin from "../../composables/useLogin";
const { authenticate, loading, data, error } = useLogin();
import useLocalStorage from "../../composables/useLocalStorage";
const { setItem } = useLocalStorage();
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const email = ref("");
const password = ref("");

const updateEmail = (value: string) => {
  emptyError();
  email.value = value;
};

const updatePassword = (value: string) => {
  emptyError();
  password.value = value;
};

const handleLogin = async () => {
  if (email.value.length && password.value.length) {
    await authenticate({
      email: email.value,
      password: password.value,
    });

    if (data.value.role !== "admin") {
        router.push("/");
        return;
      }
      setItem("token", data.value.token);
      setItem("name", data.value.name);
      setItem("email", data.value.email);
      emptyData();
      router.push("/admin");
  }
};

const emptyError = () => (error.value = "");

const emptyData = () => {
  email.value = "";
  password.value = "";
  emptyError();
};

onMounted(() => {
  emptyData();
});
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