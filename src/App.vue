<template>
  <div>
    <component :is="getRouteComponent" />
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router'; 
import HomeComponent from './components/HomeComponent.vue';
import LoginComponent from './components/LoginComponent.vue';
import RegisterComponent from "./components/RegisterComponent.vue"
const route = useRoute(); 


const getRouteComponent = computed(() => {
  switch (route.path) {
    case '/':
      return HomeComponent;
    case '/login':
      return LoginComponent;
    
    case '/register' :
      return RegisterComponent;
    default:
      return NotFoundComponent; 
  }
});
</script>

<style scoped>
body {
  font-family: 'Work Sans', sans-serif;
}

</style>
