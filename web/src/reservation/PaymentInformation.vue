<template>
Select your payment provider
  <Select data-test-id="movie-card-type">
    <option>Visa</option>
    <option>Master</option>
    <option>Amex</option>
  </Select>

Name on Card <input data-test-id="movie-card-name" type="text"/>
Card Number <input data-test-id="movie-card-number" type="text"/>
CVV Number <input data-test-id="movie-card-cvv" type="text"/>
  <button>Pay</button>

  <router-link data-test-id="movie-pay"  to="/reserve/confirmation">Pay</router-link>
</template>

<script lang="ts">
export default {
  name: "PaymentInformation"
}
</script>

<style scoped>

</style>