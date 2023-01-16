<template>
<h1>Ticket Reservation</h1>
  <SelectShow></SelectShow>
  <SelectSeat></SelectSeat>
  <router-link data-test-id="movie-payment-link" to="/reserve/payment">Pay and Reserve</router-link>
</template>

<script lang="ts">
import SelectShow from "@/reservation/SelectShow.vue";
import SelectSeat from "@/reservation/SelectSeat.vue";

export default {
  name: "TicketReservation",
  components: {SelectSeat, SelectShow}
}
</script>

<style scoped>

</style>