<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-gradient-to-r from-orange-300 to-pink-300 font-sans">
    <div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-sm">
      <h2 class="text-2xl font-bold text-pink-300 text-center mb-4">Sign in</h2>

      <div v-if="error" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
        <strong class="font-bold">Error! </strong>
        <span class="block sm:inline"> {{ error }}</span>
      </div>

      <div v-if="success" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
        <strong class="font-bold">Success!</strong>
        <span class="block sm:inline">Registration successful. Redirecting to login...</span>
      </div>

      <form @submit.prevent="register">
        <div class="mb-4">
          <label for="name" class="block text-pink-500 text-sm mb-2">Name and surname</label>
          <input type="text" id="name" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="name" />
        </div>

        <div class="mb-4">
          <label for="email" class="block text-pink-500 text-sm mb-2">Email address</label>
          <input type="email" id="email" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="email" />
        </div>

        <div class="mb-4">
          <label for="password" class="block text-pink-500 text-sm mb-2">Password</label>
          <input type="password" id="password" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="password" />
        </div>

        <div class="mb-4">
          <label for="passwordConfirmation" class="block text-pink-500 text-sm mb-2">Repeat password</label>
          <input type="password" id="passwordConfirmation" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="passwordConfirmation" />
          <span v-if="password !== passwordConfirmation" class="text-red-500 text-xs mt-1">Passwords don't match</span>
        </div>

        <button type="submit" class="w-full bg-orange-300 text-white font-bold py-2 px-4 rounded-md hover:bg-orange-400">Sign in</button>

        <p class="mt-4 text-center text-sm">Do you have an account? <router-link to="/login" class="text-pink-500 font-medium">Sign up</router-link></p>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      email: '',
      password: '',
      passwordConfirmation: '',
      error: null,
      success: false
    };
  },
  methods: {
    async register() {
      
      if (!this.name || !this.email || !this.password || !this.passwordConfirmation) {
        this.error = 'All fields are required';
        return;
      }

      
      if (this.password !== this.passwordConfirmation) {
        this.error = 'Passwords don\'t match';
        return;
      }

      try {
        const response = await axios.post('http://localhost:8080/users/add', {
          name: this.name,
          email: this.email,
          password: this.password,
        });
        console.log('Rejestracja udana:', response.data);
        this.success = true;
        setTimeout(() => {
          this.$router.push('/login');
        }, 3000); 
      } catch (error) {
        console.error('Błąd podczas rejestracji:', error);
        this.error = error.response.data;
      }
    },
  },
};
</script>

<style scoped>

</style>
