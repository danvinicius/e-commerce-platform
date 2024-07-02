<template>
  <aside>
    <ul>
      <li
        :class="{ selected: selectedOption == 'products' }"
        @click="updateSelectedOption('products')"
      >
        Products
      </li>
      <li
        :class="{ selected: selectedOption == 'categories' }"
        @click="updateSelectedOption('categories')"
      >
        Categories
      </li>
      <li
        :class="{ selected: selectedOption == 'orders' }"
        @click="updateSelectedOption('orders')"
      >
        Orders
      </li>
      <li
        :class="{ selected: selectedOption == 'users' }"
        @click="updateSelectedOption('users')"
      >
        Users
      </li>
    </ul>
  </aside>
</template>

<script setup lang="ts">
defineProps({
  selectedOption: String,
});

const emit = defineEmits(["update:selectedOption"]);

const updateSelectedOption = (option: string) => {
  emit("update:selectedOption", option);
};
</script>

<style scoped lang="scss">
aside {
  box-shadow: 1px 0 20px -5px #aaa;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 300px;
  min-height: 100%;
  background: #fff;
  ul {
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 2rem 0;

    li {
      padding: 1rem 2rem 1rem 3rem;
      cursor: pointer;
      transition: 0.2s;

      &:hover,
      &.selected {
        background: var(--primary-color);
        color: #fff;
      }
    }
  }
}
</style>