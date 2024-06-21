<template>
  <button :style="{ background, color, border, padding }">
    <img :src="iconPath" alt="" v-if="icon" />
    {{ text }}
  </button>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import useHelpers from "../../composables/useHelpers";
const { getIconUrl } = useHelpers();
const props = defineProps({
  text: String,
  background: String,
  color: {
    type: String,
    default: "#fff",
  },
  border: String,
  padding: {
    type: String,
    default: "0.75rem 1.75rem",
  },
  icon: {
    required: false,
    type: String,
  },
});

const iconPath = ref("");
onMounted(() => {
  if (props?.icon) {
    iconPath.value = getIconUrl(props.icon);
  }
});
</script>

<style scoped lang="scss">
button {
  border-radius: 0.5rem;
  transition: 0.2s;
  font-size: 1rem;
  display: flex;
  align-items: center;
  gap: 1rem;

  &:hover {
    transform: scale(1.02);
  }
}
</style>