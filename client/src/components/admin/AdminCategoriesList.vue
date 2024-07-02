<template>
  <div class="categories-list">
    <table v-if="categories?.length">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Discount</th>
        <th>Quantity of products</th>
      </tr>
      <tr v-for="category in categories" :key="category.id">
        <td>
          {{ category.id }}
        </td>
        <td>
          <router-link to="/">
            {{
              category.name.length >= 40
                ? category.name.slice(0, 40) + "..."
                : category.name
            }}
          </router-link>
        </td>
        <td>{{ category.discount }}%</td>
        <td>
          {{ getQuantityOfProducts(category) }}
        </td>
      </tr>
    </table>
  </div>
</template>
  
  <script setup lang="ts">
import useCategory, { Category } from "../../composables/useCategory";
import useHelpers from "../../composables/useHelpers";
const { getAllCategories } = useCategory();
import { onMounted, ref } from "vue";

const categories = ref<Category[]>([]);

const getQuantityOfProducts = (category: Category) => {
  return category.products.length;
};

onMounted(async () => {
  categories.value = await getAllCategories();
});
</script>
  
  <style scoped lang="scss">
.categories-list {
  padding: 2rem;
  width: 100%;
  overflow-y: scroll;
}
table {
  margin-bottom: 0.5rem;
  width: 100%;

  td,
  th {
    padding: 0.75rem;
    vertical-align: middle;

    img {
      max-width: 2rem;
    }
  }

  tr:nth-child(1) {
    background-color: var(--primary-color);
    color: #fff;
  }

  tr:nth-child(even) {
    background-color: var(--helper-color);
  }
}

table,
tr,
td,
th {
  text-align: left;
}
</style>