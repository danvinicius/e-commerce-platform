<template>
  <label :for="slugify(name)" :class="{ error }">
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
import useHelpers from "../../composables/useHelpers";
const { slugify } = useHelpers();

const emit = defineEmits(["update:value"]);

const updateValue = (evt: any) => {
  emit("update:value", evt.target.value);
};

defineProps({
  value: String,
  label: String,
  error: String,
  name: {
    type: String,
    required: true,
  },
  type: String,
  placeholder: String,
  border: {
    type: String,
    default: "1px solid var(--primary-color)",
  },
});
</script>

<style scoped lang="scss">
label {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;

  &.error {
    color: var(--error-color);

    input {
      border: 1px solid var(--error-color);
    }
  }

  input {
    width: 100%;
    border-radius: 0.5rem;
    outline: none;
    border: 1px solid var(--secondary-text-color);
    padding: 0.75rem 1rem;

    &:focus {
      border: v-bind("border");
    }
  }
}
</style>