<template>
  <section class="signup-form flex column gap-125">
    <div class="title">
      <h1>Sign Up</h1>
      <p class="signup">
        Already have an account?
        <span @click="changeToLogin" class="pointer">Login</span>
      </p>
    </div>
    <Form>
      <Input
        type="name"
        name="name"
        placeholder="your name"
        label="Name"
        :value="name"
        @update:value="(value) => (name = value)"
      />
      <Input
        type="email"
        name="email"
        placeholder="your@email.com"
        label="E-mail"
        :value="email"
        @update:value="(value) => (email = value)"
      />
      <Input
        type="phone"
        name="phone"
        placeholder="(xx) xxxxx-xxxx"
        label="Phone"
        :value="phone"
        @update:value="(value) => (phone = value)"
      />
      <PasswordInput
        placeholder="*********"
        label="Password"
        :value="password"
        @update:value="(value) => (password = value)"
        :enableForgotPassword="false"
      />
      <Button
        text="Login"
        background="var(--primary-color)"
        :border="'none'"
        @click.prevent="handleSignup"
      ></Button>
    </Form>
  </section>
</template>
  
  <script setup lang="ts">
import Button from "../layout/Button.vue";
import Input from "../form/Input.vue";
import Form from "../form/Form.vue";
import { ref } from "vue";
import useLogin from "../../composables/useLogin";
import PasswordInput from "../form/PasswordInput.vue";
const { create } = useLogin();

const name = ref("");
const phone = ref("");
const email = ref("");
const password = ref("");

defineProps({
  currentView: String,
});

const emit = defineEmits(["update:currentView"]);

const changeToLogin = () => {
  emit("update:currentView", "login");
};

const handleSignup = async () => {
  if (
    email.value.length &&
    password.value.length &&
    phone.value.length &&
    name.value.length
  ) {
    try {
      const data = await create({
        email: email.value,
        password: password.value,
        phone: phone.value,
        name: name.value,
      });
      return data;
    } catch (error) {
      return null;
    }
  }
};
</script>
  
  <style scoped lang="scss">
.signup-form {
  width: 80%;
  background: #fff;
  padding: 2rem;
  padding-right: 0;
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