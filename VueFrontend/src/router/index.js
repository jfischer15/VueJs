import Vue from 'vue'
import Router from 'vue-router'
import Services from '../components/Services'
import Employees from '../components/Employees'
import AddService from '../components/AddService'
import AddEmployee from '../components/AddEmployee'
import EditEmployee from '../components/EditEmployee'
import EditService from '../components/EditService'
import ServicesMap from '../components/ServicesMap'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Services',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: Services
    },
    {
      path: '/employees',
      name: 'Employees',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: Employees
    },
    {
      path: '/addService',
      name: 'AddService',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: AddService
    },
    {
      path: '/addEmployee',
      name: 'AddEmployee',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: AddEmployee
    },
    {
      path: '/editEmployee/:id',
      name: 'EditEmployee',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: EditEmployee
    },
    {
      path: '/editService',
      name: 'EditService',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: EditService
    },
    {
      path: '/showMap',
      name: 'ShowMap',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: ServicesMap
    }
  ]
})
