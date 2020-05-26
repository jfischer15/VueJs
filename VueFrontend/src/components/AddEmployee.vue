<template>
  <div class="add-employee">
    <div class="container-contact100">

      <div class="wrap-contact100">
        <form class="contact100-form validate-form">
				<span class="contact100-form-title">
					Mitarbeiter hinzufügen
				</span>

          <div class="wrap-input100 validate-input">
            <input class="input100" type="text" v-model="firstname" placeholder="Vorname">
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input">
            <input class="input100" type="text" v-model="lastname" placeholder="Nachname">
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input">
            <input class="input100" type="text" v-model="address" placeholder="Adresse">
            <span class="focus-input100"></span>
          </div>

          <p class="error-message">{{error}}</p>


          <div class="container-contact100-form-btn">
            <button class="contact100-form-btn" v-on:click="addEmployee">
						<span>
							<i class="fa fa-paper-plane-o m-r-6" aria-hidden="true"></i>
							Hinzufügen
						</span>
            </button>
          </div>
        </form>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: "AddEmployee",

    data() {
      return {
        firstname: '',
        lastname: '',
        address: '',
        error: ''
      }
    },
    methods: {
      addEmployee: function () {
        console.log('ADD EMPLOYEE CLICKED');

        const requestOptions = {
          method: "POST",
          headers: {"Content-Type": "application/json"},
          body: JSON.stringify({
            forename: this.firstname,
            surname: this.lastname,
            address: this.address
          })
        };

          fetch('http://localhost:1871/employees', requestOptions)
            .then(respones => respones.json())
            .then(data => {
              if(data.status === 400) {
                console.log('Ein Fehler ist aufgetreten');
                this.error = data.message;
                return;
              }
              console.log('EMPLOYEE ADDED');
              console.log(data);
              this.clearInputFields();
              this.$router.push('/employees');
            });

      },

      clearInputFields: function () {
        this.firstname = '';
        this.lastname = '';
        this.address = '';
      }

    }
  }
</script>

<style scoped>


  * {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
  }

  body, html {
    height: 100%;
    font-family: Ubuntu-Bold, sans-serif;
  }

  .error-message{
    width: 100%;
    text-align: center;
    color: #fff;
  }

  /*---------------------------------------------*/
  a {
    font-family: Ubuntu-Bold;
    font-size: 14px;
    line-height: 1.7;
    color: #666666;
    margin: 0px;
    transition: all 0.4s;
    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
  }

  a:focus {
    outline: none !important;
  }

  a:hover {
    text-decoration: none;
  }

  /*---------------------------------------------*/
  h1, h2, h3, h4, h5, h6 {
    margin: 0px;
  }

  p {
    font-family: Ubuntu-Bold;
    font-size: 14px;
    line-height: 1.7;
    color: #666666;
    margin: 0px;
  }

  ul, li {
    margin: 0px;
    list-style-type: none;
  }


  /*---------------------------------------------*/
  input {
    outline: none;
    border: none;
  }

  input[type="number"] {
    -moz-appearance: textfield;
    appearance: none;
    -webkit-appearance: none;
  }

  input[type="number"]::-webkit-outer-spin-button,
  input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
  }

  textarea {
    outline: none;
    border: none;
  }

  textarea:focus, input:focus {
    border-color: transparent !important;
  }

  input::-webkit-input-placeholder {
    color: #000000;
  }

  input:-moz-placeholder {
    color: #000000;
  }

  input::-moz-placeholder {
    color: #000000;
  }

  input:-ms-input-placeholder {
    color: #000000;
  }

  textarea::-webkit-input-placeholder {
    color: #000000;
  }

  textarea:-moz-placeholder {
    color: #000000;
  }

  textarea::-moz-placeholder {
    color: #000000;
  }

  textarea:-ms-input-placeholder {
    color: #000000;
  }

  /*---------------------------------------------*/
  button {
    outline: none !important;
    border: none;
    background: transparent;
  }

  button:hover {
    cursor: pointer;
  }

  iframe {
    border: none !important;
  }


  /*---------------------------------------------*/
  .container {
    max-width: 1200px;
  }


  /*//////////////////////////////////////////////////////////////////
  [ Contact ]*/

  .container-contact100 {
    width: 100%;
    min-height: 100vh;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 15px;
    position: relative;
    background-color: #2e073e;
  }

  .wrap-contact100 {
    width: 550px;
    background: transparent;
    padding: 50px 0px 160px 0px;
  }


  /*==================================================================
  [ Form ]*/

  .contact100-form {
    width: 100%;
  }

  .contact100-form-title {
    display: block;
    font-family: Ubuntu-Bold;
    font-size: 30px;
    color: #ffffff;
    line-height: 1.2;
    text-transform: uppercase;
    text-align: center;
    padding-bottom: 49px;
  }

  /*------------------------------------------------------------------
  [ Input ]*/

  .wrap-input100 {
    width: 100%;
    background-color: #fff;
    border-radius: 31px;
    margin-bottom: 16px;
    position: relative;
    z-index: 1;
  }

  .input100 {
    position: relative;
    display: block;
    width: 100%;
    background: #fff;
    border-radius: 31px;
    font-family: Ubuntu-Bold;
    font-size: 18px;
    color: #000000;
    line-height: 1.2;
  }


  /*---------------------------------------------*/
  input.input100 {
    height: 62px;
    padding: 0 35px 0 35px;
  }


  textarea.input100 {
    min-height: 169px;
    padding: 19px 35px 0 35px;
  }

  /*------------------------------------------------------------------
  [ Focus Input ]*/

  .focus-input100 {
    display: block;
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
    top: 0;
    left: 50%;
    -webkit-transform: translateX(-50%);
    -moz-transform: translateX(-50%);
    -ms-transform: translateX(-50%);
    -o-transform: translateX(-50%);
    transform: translateX(-50%);
    border-radius: 31px;
    background-color: #fff;
    pointer-events: none;

    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
    transition: all 0.4s;
  }

  .input100:focus + .focus-input100 {
    width: calc(100% + 20px);
  }

  /*------------------------------------------------------------------
  [ Button ]*/
  .container-contact100-form-btn {
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    padding-top: 10px;
  }

  .contact100-form-btn {
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 20px;
    min-width: 150px;
    height: 62px;
    background-color: transparent;
    border-radius: 31px;

    font-family: Ubuntu-Bold;
    font-size: 16px;
    color: #fff;
    line-height: 1.2;
    text-transform: uppercase;

    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
    transition: all 0.4s;
    position: relative;
    z-index: 1;
  }

  .contact100-form-btn::before {
    content: "";
    display: block;
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
    top: 0;
    left: 50%;
    -webkit-transform: translateX(-50%);
    -moz-transform: translateX(-50%);
    -ms-transform: translateX(-50%);
    -o-transform: translateX(-50%);
    transform: translateX(-50%);
    border-radius: 31px;
    background-color: #827ffe;
    pointer-events: none;

    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
    transition: all 0.4s;
  }

  .contact100-form-btn:hover:before {
    background-color: #403866;
    width: calc(100% + 20px);
  }


  /*------------------------------------------------------------------
  [ Alert validate ]*/

  .validate-input {
    position: relative;
  }

  .alert-validate::before {
    content: attr(data-validate);
    position: absolute;
    z-index: 1000;
    max-width: 70%;
    background-color: #fff;
    border: 1px solid #c80000;
    border-radius: 14px;
    padding: 4px 25px 4px 10px;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    transform: translateY(-50%);
    right: 10px;
    pointer-events: none;

    font-family: Ubuntu-Bold;
    color: #c80000;
    font-size: 13px;
    line-height: 1.4;
    text-align: left;

    visibility: hidden;
    opacity: 0;

    -webkit-transition: opacity 0.4s;
    -o-transition: opacity 0.4s;
    -moz-transition: opacity 0.4s;
    transition: opacity 0.4s;
  }

  .alert-validate::after {
    content: "\f06a";
    font-family: FontAwesome;
    display: block;
    position: absolute;
    z-index: 1100;
    color: #c80000;
    font-size: 16px;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    transform: translateY(-50%);
    right: 16px;
  }

  .alert-validate:hover:before {
    visibility: visible;
    opacity: 1;
  }

  @media (max-width: 992px) {
    .alert-validate::before {
      visibility: visible;
      opacity: 1;
    }
  }
</style>
