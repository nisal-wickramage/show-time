<template>
Select your payment provider
  <Select>
    <option>Visa</option>
    <option>Master</option>
    <option>Amex</option>
  </Select>

Name on Card <input type="text"/>
Card Number <input type="text"/>
CVV Number <input type="text"/>
  <button>Pay</button>

  <router-link to="/reserve/confirmation">Pay</router-link>
</template>

<script lang="ts">
export default {
  name: "PaymentInformation"
}
</script>

<style scoped>

</style>