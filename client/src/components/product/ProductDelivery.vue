<template>
  <div class="shipping-calculator">
    <h3>Calculate delivery time and value</h3>
    <div class="flex gap-5 align-center">
      <Input
        :value="cep"
        type="text"
        name="cep"
        @update:value="(value) => (cep = value)"
      ></Input>
      <Button
        text="Calculate"
        background="var(--primary-color)"
        color="#fff"
        padding="0.85rem 1.75rem"
        @click.prevent="handleGetDeliveryInfo"
      ></Button>
    </div>
    <a
      href="https://buscacepinter.correios.com.br/app/endereco/"
      target="_blank"
      >Don't know my CEP</a
    >
    <table v-if="deliveryInfo">
      <tr>
        <td>
          {{ deliveryInfo.city }} - {{ deliveryInfo.state }} ({{
            deliveryInfo.time
          }}
          working days)
        </td>
        <td>
          {{ priceFormatter(deliveryInfo.value, true) }}
        </td>
      </tr>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Input from "../form/Input.vue";
import Button from "../layout/Button.vue";

import useDelivery from "../../composables/useDelivery";
import useHelpers from "../../composables/useHelpers";
const { getDeliveryInfo } = useDelivery();
const { priceFormatter } = useHelpers();
const cep = ref("");
const deliveryInfo = ref();

const handleGetDeliveryInfo = async () => {
  if (cep.value) {
    try {
      deliveryInfo.value = await getDeliveryInfo(cep.value);
    } catch (error) {
      console.log(error);
    }
  }
};
</script>

<style scoped lang="scss">
h3 {
  font-size: 1rem;
  font-weight: bold;
}

.flex {
  margin-bottom: 0.5rem;

  a {
    font-size: 14px;
    display: block;
    margin-bottom: 0.5rem;
    color: (var(--primary-color));
  }

  table {
    margin-bottom: 0.5rem;
  }

  table,
  tr,
  td {
    border: 1px solid var(--secondary-text-color);
  }

  td {
    padding: 0.75rem;
  }
}
</style>