<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-gradient-to-r from-orange-300 to-pink-300">
    <div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-sm">
      <h2 class="text-2xl font-bold text-pink-300 text-center mb-4">Log in</h2>

      <!-- Komunikat o błędzie -->
      <div v-if="error" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
        <strong class="font-bold">Error! </strong>
        <span class="block sm:inline">{{ error }}</span>
      </div>

      <form @submit.prevent="onSubmit">
        <div class="mb-4">
          <label for="email" class="block text-pink-500 text-sm mb-2">Email address</label>
          <input type="email" id="email" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="email" />
        </div>

        <div class="mb-4">
          <label for="password" class="block text-pink-500 text-sm mb-2">Password</label>
          <input type="password" id="password" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="password" />
        </div>

        <div class="flex items-center justify-between mb-4">
          <a href="#" class="text-sm text-pink-500 font-medium">Did you forget password?</a>
        </div>

        <button type="submit" class="w-full bg-orange-300 text-white font-bold py-2 px-4 rounded-md hover:bg-orange-400">Log in</button>

        <p class="mt-4 text-center text-sm">Don't have an account? <router-link to="/register" class="text-pink-500 font-medium">Sign up</router-link></p>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      email: '',
      password: '',
      error: null, 
    };
  },
  methods: {
    async onSubmit() {
      try {
        const response = await axios.post('http://localhost:8080/users/login', {
          email: this.email,
          password: this.password,
        });
        console.log('Login successful:', response.data);
        
        this.$router.push('/feed');
        localStorage.setItem('userEmail', this.email);
      } catch (error) {
        console.error('Login failed:', error.response.data);
        
        this.error = error.response.data;
      }
    },
  },
};
</script>

<style scoped>

</style>
