<template>
  <section class="on-sale">
    <div class="content-container flex space-between align-center">
      <h2 v-html="title"></h2>
      <Timer :time="saleDeadline"></Timer>
    </div>
    <div class="products-container">
      <ContentSwiper
        v-if="onSaleProducts.length"
        :products="onSaleProducts"
        :slidesPerView="3"
        textBackground="transparent"
        color="#fff"
      />
    </div>
  </section>
</template>

<script setup lang="ts">
import Timer from "../layout/Timer.vue";
import ContentSwiper from "../home/ContentSwiper.vue";

import useCategory from "../../composables/useCategory";

const { getOnSaleProducts } = useCategory();
import { onMounted, ref } from "vue";

const onSaleProducts = ref([]);

onMounted(async () => {
  onSaleProducts.value = await getOnSaleProducts();
});

defineProps({
  title: String,
  saleDeadline: Date,
});
</script>

<style scoped lang="scss">
.on-sale {
  padding: 2rem 2rem 1rem;
  background: #111;
  margin-bottom: 4rem;
  .content-container {
    margin-bottom: 2rem;
    h2 {
      font-size: 2rem;
      color: #fff;
    }
  }
}
</style>