<template>
  <div class="password-input-wrapper">
    <small
      v-if="enableForgotPassword"
      class="secondary-text-color pointer"
      @click="changeToForgotPassword"
      >Forgot password?</small
    >
    <Input
      :error="error"
      :type="type"
      name="password"
      :placeholder="placeholder"
      :label="label"
      :value="value"
      @update:value="updateValue"
    />
    <img
      v-if="type == 'password'"
      class="pointer"
      src="../../assets/icon/Eye_Off.svg"
      alt="Turn password visible"
      @click="type = 'text'"
    />
    <img
      v-if="type == 'text'"
      class="pointer"
      src="../../assets/icon/Eye.svg"
      alt="Turn password invisible"
      @click="type = 'password'"
    />
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Input from "./Input.vue";

const type = ref("password");

const emit = defineEmits(["update:value", "changeToForgotPassword"]);

const updateValue = (value: string) => {
  emit("update:value", value);
};

const changeToForgotPassword = () => {
  emit("changeToForgotPassword");
};

defineProps({
  error: String,
  value: String,
  label: String,
  placeholder: String,
  enableForgotPassword: Boolean,
});
</script>

<style scoped lang="scss">
.password-input-wrapper {
  position: relative;

  img {
    position: absolute;
    top: 50%;
    right: 1rem;
  }

  small {
    font-size: 14px;
    position: absolute;
    right: 0;
    top: 0.25rem;
  }
}
</style>