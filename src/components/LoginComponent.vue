<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-gradient-to-r from-orange-300 to-pink-300">
    <div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-sm">
      <h2 class="text-2xl font-bold text-pink-300 text-center mb-4">Sign up</h2>

      <form @submit.prevent="onSubmit">
        <div class="mb-4">
          <label for="email" class="block text-pink-500 text-sm mb-2">Email adress</label>
          <input type="email" id="email" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="email" />
        </div>

        <div class="mb-4">
          <label for="password" class="block text-pink-500 text-sm mb-2">Password</label>
          <input type="password" id="password" class="form-input w-full bg-white border border-gray-300 rounded-md py-2 px-4" v-model="password" />
        </div>

        <div class="flex items-center justify-between mb-4">
          

          <a href="#" class="text-sm text-pink-500 font-medium">Did you forget password?</a>
        </div>

        <button type="submit" class="w-full bg-orange-300 text-white font-bold py-2 px-4 rounded-md hover:bg-orange-400">Sign up</button>

        <p class="mt-4 text-center text-sm">Don't have an account? <a href="#" class="text-pink-500 font-medium">Sign in</a></p>
      </form>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      
      console.log('Próba logowania...', this.username, this.password);
    },
  },
};
</script>

<style scoped>
.instagram-orange {
  background-color: #ff9900;
}

.instagram-pink {
  background-color: #f74e60;
}

body {
  font-family: 'Inter', sans-serif;
}

</style>
