<template>
  Select Date
<select data-test-id="movie-date">
  <option value="2023-01-20">2023-01-20</option>
  <option value="2023-01-21">2023-01-21</option>
</select>

  Select Time
  <select data-test-id="movie-time">
    <option value="2:00PM">2:00 PM</option>
    <option value="6:00PM">6:00 PM</option>
  </select>
</template>

<script lang="ts">
export default {
  name: "SelectShow"
}
</script>

<style scoped>

</style>