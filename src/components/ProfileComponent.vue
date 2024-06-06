<template>
    <div class="flex flex-col items-center min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
      <!-- Profil użytkownika -->
      <div class="profile-container bg-white shadow-lg rounded-lg p-6 w-full max-w-3xl">
        <div class="flex flex-col md:flex-row items-center md:items-start">
          <img class="w-24 h-24 rounded-full mb-4 md:mb-0" src="https://via.placeholder.com/150" alt="Profile Picture">
          <div class="md:ml-6 text-center md:text-left">
            <h2 class="text-2xl font-bold text-pink-500">{{ name }}</h2>
            <button class="bg-orange-300 text-white px-4 py-2 rounded-lg hover:bg-orange-400">Follow</button>
          </div>
        </div>
        <div class="stats mt-6 flex justify-around text-center">
          <div class="followers">
            <h3 class="text-lg font-semibold text-pink-500">Followers</h3>
            <p class="text-gray-600">{{ followers.length }}</p>
          </div>
          <div class="following">
            <h3 class="text-lg font-semibold text-pink-500">Following</h3>
            <p class="text-gray-600">{{ following.length }}</p>
          </div>
        </div>
      </div>
      
      <div class="posts-container mt-6 w-full max-w-3xl">
        <h3 class="text-lg font-semibold text-pink-500 mb-4 text-center">Posts</h3>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div class="post bg-white border border-gray-200 rounded-lg overflow-hidden shadow-sm" v-for="(post, index) in posts" :key="index">
            <p class="text-sm text-gray-600 p-4">{{ post.content }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: "",
        followers: [],
        following: [],
        posts: []
      };
    },
    created() {
      const userId = parseInt(this.$route.params.userId);
      
      axios.get(`http://localhost:8080/findProfile/${userId}`)
        .then(response => {
          const data = response.data;
          this.name = data.name;
          this.followers = data.followers;
          this.following = data.following;
          this.posts = data.posts;
          console.log("User data:", data);
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    }
  };
  </script>
  
  <style scoped>
  
  </style>
  