<template>
  <div id="app">

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <router-link class="navbar-brand" to="/" >Manager</router-link>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item active">
            <router-link class="nav-link" to="/">Services</router-link>
          </li>

          <li class="nav-item active">
            <router-link class="nav-link" to="/employees">Mitarbeiter</router-link>
          </li>

          <li class="nav-item active">
            <router-link class="nav-link" to="/addService">Neuer Service</router-link>
          </li>

          <li class="nav-item active">
            <router-link class="nav-link" to="/addEmployee">Neuer Mitarbeiter</router-link>
          </li>
          <li class="nav-item active">
            <router-link class="nav-link" to="/showMap">Service Karte</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>

</style>
