<template>
  <GmapMap
    :center="{lat:47.4, lng:14}"
    :zoom="7.3"
    map-type-id="terrain"
    style="width: 100%; height: 94vh"
  >
    <GmapMarker v-for="service in services" :key="service.key" :title="service.employee.name" :position="google && new google.maps.LatLng(parseFloat(service.latitude), parseFloat(service.longitude))">

    </GmapMarker>
  </GmapMap>
</template>

<script>
  import {gmapApi} from 'vue2-google-maps';
  export default {
    name: 'ServicesMap',
    data() {
      return{
        services : []
      }
    },
    created() {
      fetch('http://localhost:6969/services')
        .then(response => response.json())
        .then(data => {
          console.log('SERVICES-MAP')
            this.services = data;
            console.log(this.services)
          }
        )
    },
    computed: {
      google: gmapApi
    }
  }
</script>

<style scoped>

</style>
