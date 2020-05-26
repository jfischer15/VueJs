<template>
  <div class="service">

    <table class="table-fill">
      <thead>
      <tr>
        <th class="text-center">ID</th>
        <th class="text-center">Servicename</th>
        <th class="text-center">Mitarbeiter</th>
        <th class="text-center">Datum</th>
        <th class="text-center">Adresse</th>
        <th class="text-center">Bearbeiten</th>
        <th class="text-center">Löschen</th>
      </tr>
      </thead>
      <tbody class="table-hover">
      <tr v-for="service in services" :key="service.id">
        <td class="text-center">{{service.id}}</td>
        <td class="text-center">{{service.name}}</td>
        <td class="text-center">{{service.employee.name}}</td>
        <td class="text-center">{{service.date}}</td>
        <td class="text-center">{{service.address}}</td>
        <td class="text-center">
          <router-link to="/editService"><img class="icon" v-on:click="editService(service.id)"
                                              src="../assets/baseline_edit_black_48dp.png"></router-link>
        </td>
        <td class="text-center">
          <img v-on:click="deleteService(service.id)" class="icon" src="../assets/baseline_delete_black_48dp.png">
        </td>
      </tr>

      </tbody>
    </table>

  </div>

</template>

<script>
  export default {
    name: "Services",

    data() {
      return {
        services: []
      }
    },

    created() {
      this.loadServices();
    },
    //-----------------------------------METHODEN
    methods: {
      editService: function (id) {
        console.log("SERVICE-ID --> " + id);
        this.$router.replace({name: "EditService", params: {id: id}});
      },

      deleteService: function (id) {
        console.log('DELETE SERVICE --> ' + id);

        const requestOptions = {
          method: "DELETE",
          headers: {"Content-Type": "application/json"}
        };

        fetch('http://localhost:6969/services/' + id, requestOptions)
          .then(respones => respones.json())
          .then(data => {
            console.log('SERVICE DELETED');
            console.log(data);
            this.loadServices();
          })
      },

      loadServices: function () {
        fetch('http://localhost:6969/services')
          .then(response => response.json())
          .then(data => {
              console.log('SERVICES-LIST')
              this.services = data.sort((x,y) => x.id - y.id);
              console.log(this.services)
            }
          )
      }


    }
  }
</script>

<style scoped>

  @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);

  .icon {
    width: 36px;
  }

  body {
    background-color: #3e94ec;
    font-family: "Roboto", helvetica, arial, sans-serif;
    font-size: 14px;
    text-rendering: optimizeLegibility;
  }

  div.table-title {
    display: block;
    margin: auto;

    padding: 5px;
  }

  .table-title h3 {
    color: #fafafa;
    font-size: 10px;
    font-style: normal;
    font-family: "Roboto", helvetica, arial, sans-serif;
    text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
    text-transform: uppercase;
  }


  /*** Table Styles **/

  .table-fill {
    background: white;
    border-radius: 3px;
    border-collapse: collapse;
    max-width: 100%;
    width: 100%;
    /*margin: 1% 5%;*/
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
    animation: float 5s infinite;
  }

  th {
    color: #D5DDE5;;
    background: #1b1e24;
    border-right: 1px solid #343a45;
    font-size: 20px;
    font-weight: 100;
    padding: 14px;
    text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
    vertical-align: middle;
  }

  th:first-child {
    border-top-left-radius: 3px;
  }

  th:last-child {
    border-top-right-radius: 3px;
    border-right: none;
  }

  tr {
    border-top: 1px solid #C1C3D1;
    border-bottom-: 1px solid #C1C3D1;
    color: #666B85;
    font-size: 16px;
    font-weight: normal;
    text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
  }

  tr:hover td {
    background: #4E5066;
    color: #FFFFFF;
    border-top: 1px solid #22262e;
  }

  tr:first-child {
    border-top: none;
  }

  tr:last-child {
    border-bottom: none;
  }

  tr:nth-child(odd) td {
    background: #EBEBEB;
  }

  tr:nth-child(odd):hover td {
    background: #4E5066;
  }

  tr:last-child td:first-child {
    border-bottom-left-radius: 3px;
  }

  tr:last-child td:last-child {
    border-bottom-right-radius: 3px;
  }

  td {
    background: #FFFFFF;
    padding: 20px;
    text-align: left;
    vertical-align: middle;
    font-weight: 300;
    font-size: 18px;
    text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
    border-right: 1px solid #C1C3D1;
  }

  td:last-child {
    border-right: 0px;
  }

  th.text-left {
    text-align: left;
  }

  th.text-center {
    text-align: center;
  }

  th.text-right {
    text-align: right;
  }

  td.text-left {
    text-align: left;
  }

  td.text-center {
    text-align: center;
  }

  td.text-right {
    text-align: right;
  }


</style>
