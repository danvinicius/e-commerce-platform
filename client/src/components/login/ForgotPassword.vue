<template>
  <section class="forgot-password-form flex column gap-125">
    <div class="title">
      <h1>Forgot password?</h1>
      <p class="reset-instructions">
        No worries, we'll send you
        <span>reset instructions</span>. Or
        <span @click="changeToLogin" class="pointer">go back to login.</span>
      </p>
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
      <Button
        text="Send e-mail"
        background="var(--primary-color)"
        :border="'none'"
        @click.prevent="handleForgotPassword"
      ></Button>
    </Form>
  </section>
</template>
  
  <script setup lang="ts">
import Button from "../layout/Button.vue";
import Input from "../form/Input.vue";
import Form from "../form/Form.vue";
import useLogin from "../../composables/useLogin";
const { forgotPassword } = useLogin();
import { ref } from "vue";

defineProps({
  currentView: String,
});

const emit = defineEmits(["update:currentView"]);

const changeToLogin = () => {
  emit("update:currentView", "login");
};

const email = ref("");

const handleForgotPassword = async (event: any) => {
  if (email.value.length) {
    try {
      const data = await forgotPassword({
        email: email.value,
      });
      return data;
    } catch (error) {
      return null;
    }
  }
};
</script>
  
  <style scoped lang="scss">
.forgot-password-form {
  width: 80%;
  background: #fff;
  padding: 2rem 0 2rem 10rem;
  margin: auto;
  position: relative;

  p.reset-instructions {
    color: var(--secondary-text-color);

    span:first-child {
      font-weight: bold;
      color: inherit;
    }
    span {
      color: var(--primary-color);
    }
  }

  h1 {
    font-size: 2rem;
    font-weight: bold;
    color: var(--primary-text-color);
  }
}
</style>