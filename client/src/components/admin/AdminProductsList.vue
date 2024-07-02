<template>
  <div class="products-list">
    <table v-if="products?.length">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Weight</th>
        <th>Discount</th>
        <th>Categories</th>
        <th>Image</th>
      </tr>
      <tr v-for="product in products" :key="product.id">
        <td>
          {{ product.id.slice(0, 4) + "..." + product.id.slice(-4) }}
        </td>
        <td>
          <router-link to="/">
            {{
              product.name.length >= 35
                ? product.name.slice(0, 35) + "..."
                : product.name
            }}
          </router-link>
        </td>
        <td>
          {{ priceFormatter(product.price, true) }}
        </td>
        <td>
          {{
            product.description.length >= 35
              ? product.description.slice(0, 35) + "..."
              : product.description
          }}
        </td>
        <td>
          {{ getProductQuantity(product) }}
        </td>
        <td>{{ product.weight * 100 }}g</td>
        <td>{{ product.discount }}%</td>
        <td>
          {{ getProductCategories(product) }}
        </td>
        <td>
          <img :src="getImageUrl(product.imageUrl)" alt="" />
        </td>
      </tr>
    </table>
  </div>
</template>

<script setup lang="ts">
import useProduct, { Product } from "../../composables/useProduct";
import useHelpers from "../../composables/useHelpers";
const { getAllProducts } = useProduct();
const { priceFormatter, getImageUrl } = useHelpers();
import { onMounted, ref } from "vue";

const products = ref<Product[]>([]);

const getProductQuantity = (product: Product) => {
  const quantities = Object.values(product.stock);
  return quantities.reduce((acc: number, curr: number) => {
    return acc + curr;
  }, 0);
};

const getProductCategories = (product: Product) => {
  const categories = product.categories.map((c) => c.name);
  const joinedCategories = categories.join(", ");
  return joinedCategories.length >= 25
    ? joinedCategories.slice(0, 25) + "..."
    : joinedCategories;
};

onMounted(async () => {
  products.value = await getAllProducts();
});
</script>

<style scoped lang="scss">
.products-list {
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