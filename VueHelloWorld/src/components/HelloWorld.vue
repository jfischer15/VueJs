<template>
  <div class="hello-world">
    <p>{{title}}</p>
    <p>{{msg}}</p>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to VueJS',
      title: 'VueJS'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
