import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import FeedComponent from "./components/FeedComponent.vue";
import HomeComponent from './components/HomeComponent.vue';
import LoginComponent from './components/LoginComponent.vue';
import RegisterComponent from "./components/RegisterComponent.vue";

import 'tailwindcss/base.css'; // Import Tailwind CSS styles (optional)
import 'tailwindcss/components.css';
import 'tailwindcss/utilities.css';
import ProfileComponent from './components/ProfileComponent.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: HomeComponent },
    { path: '/login', component: LoginComponent },
    {path : '/register', component : RegisterComponent},
    {path : '/feed',component : FeedComponent},
    {path : '/profile',component : ProfileComponent}
  ],
});

const app = createApp(App);

app.use(router);

app.mount('#app');
