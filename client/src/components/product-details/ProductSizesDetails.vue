<template>
  <table>
    <tr>
      <th>Size</th>
      <th v-for="size in orderProductSizes(productSizes)" :key="size">
        {{ size }}
      </th>
    </tr>
    <tr>
      <td>Width</td>
      <td v-for="size in orderProductSizes(productSizes)" :key="size">
        {{ productSizesWidths[size] }}
      </td>
    </tr>
    <tr>
      <td>Height</td>
      <td v-for="size in orderProductSizes(productSizes)" :key="size">
        {{ productSizesHeights[size] }}
      </td>
    </tr>
  </table>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import { ProductStock } from "../../composables/useProduct";

defineProps({
  productSizes: {
    type: Object as PropType<ProductStock>,
    required: true,
  },
});

function orderProductSizes(productSizes: ProductStock) {
  const order = ["PP", "P", "M", "G", "GG", "XGG"];

  return Object.keys(productSizes).sort((a, b) => {
    return order.indexOf(a) - order.indexOf(b);
  });
}

const productSizesHeights = {
  PP: 63,
  P: 65,
  M: 67,
  G: 69,
  GG: 72,
  XGG: 73,
};

const productSizesWidths = {
  PP: 44,
  P: 46,
  M: 49,
  G: 55,
  GG: 58,
  XGG: 62,
};
</script>

<style scoped lang="scss">
</style>