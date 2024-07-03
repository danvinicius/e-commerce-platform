<template>
  <section class="login-form flex column gap-125">
    <div class="title">
      <h1>Login</h1>
      <p class="signup">
        Doesn't have an account yet?
        <span @click="changeToSignup" class="pointer">Sign Up</span>
      </p>
    </div>
    <Form>
      <Input
        type="email"
        name="email"
        placeholder="your@email.com"
        label="E-mail"
        :value="email"
        @update:value="(value) => updateEmail(value)"
      />
      <PasswordInput
        type="password"
        name="password"
        placeholder="*********"
        label="Password"
        :value="password"
        @changeToForgotPassword="changeToForgotPassword"
        @update:value="(value) => updatePassword(value)"
        :enableForgotPassword="true"
      />
      <span class="error">{{ error }}</span>
      <Button
        text="Login"
        background="var(--primary-color)"
        :border="'none'"
        @click.prevent="handleLogin"
        :loading="loading"
      ></Button>
    </Form>
  </section>
</template>

<script setup lang="ts">
import Button from "../layout/Button.vue";
import Input from "../form/Input.vue";
import PasswordInput from "../form/PasswordInput.vue";
import Form from "../form/Form.vue";
import useLogin from "../../composables/useLogin";
import useLocalStorage from "../../composables/useLocalStorage";
const { authenticate, loading, data, error } = useLogin();
const { setItem } = useLocalStorage();
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const email = ref("");
const password = ref("");

defineProps({
  currentView: String,
});

const emit = defineEmits(["update:currentView"]);

const changeToSignup = () => {
  emit("update:currentView", "signup");
};

const changeToForgotPassword = () => {
  emit("update:currentView", "forgot");
};

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

    if (data.value) {
      setItem("token", data.value.token);
      setItem("name", data.value.name);
      setItem("email", data.value.email);
      emptyData();
      router.push("/");
    }
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
.login-form {
  width: 80%;
  background: #fff;
  padding: 2rem 0 2rem 10rem;
  margin: auto;
  position: relative;

  h1 {
    font-size: 2rem;
    font-weight: bold;
    color: var(--primary-text-color);
  }

  p.signup {
    color: var(--secondary-text-color);

    span {
      color: var(--primary-color);
    }
  }
}
</style>