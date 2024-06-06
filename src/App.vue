<template>
  <div>
    <component :is="getRouteComponent" />
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router'; 
import HomeComponent from './components/HomeComponent.vue';
import LoginComponent from './components/LoginComponent.vue';
import RegisterComponent from './components/RegisterComponent.vue';
import FeedComponent from './components/FeedComponent.vue';
import ProfileComponent from './components/ProfileComponent.vue';

const route = useRoute(); 

const getRouteComponent = computed(() => {
  if (route.path === '/') {
    return HomeComponent;
  } else if (route.path === '/login') {
    return LoginComponent;
  } else if (route.path === '/register') {
    return RegisterComponent;
  } else if (route.path === '/feed') {
    return FeedComponent;
  } else if (route.path.startsWith('/profile')) {
    return ProfileComponent;
  }
});
</script>

<style>
body {
  font-family: 'Inter', sans-serif;
}
</style>
