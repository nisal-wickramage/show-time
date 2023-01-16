<template>
  <div>
    <img src="../assets/logo.svg">
    <h2>Movie Name</h2>
    <span>
      <p>Description</p>
      <p>Runtime</p>
      <p>Cast</p>
      <p>Trailer</p>
    </span>
    <button>Reserve Seats</button>
    <router-link to="/reserve/1">Reserve Seats</router-link>
  </div>
</template>

<script>
export default {
  name: "MovieCard"
}
</script>

<style scoped>

</style>