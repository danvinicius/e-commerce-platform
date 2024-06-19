<template>
  <label :for="slugify(name)">
    <p v-html="label"></p>
    <input
      :type="type"
      :label="label"
      :name="name"
      :id="slugify(name)"
      :placeholder="placeholder"
      @input="updateValue"
    />
  </label>
</template>

<script setup lang="ts">
import { useHelpers } from "../../composables/useHelpers";
const { slugify } = useHelpers();

const emit = defineEmits(["update:value"]);

const updateValue = (evt: any) => {
  emit("update:value", evt.target.value);
};

defineProps({
  value: String,
  label: String,
  name: {
    type: String,
    required: true,
  },
  type: String,
  placeholder: String,
});
</script>

<style scoped lang="scss">
label {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;

  input {
    width: 100%;
    border-radius: 0.5rem;
    outline: none;
    border: 1px solid var(--secondary-text-color);
    padding: 0.75rem 1rem;

    &:focus {
      border: 1px solid var(--primary-color);
    }
  }
}
</style>