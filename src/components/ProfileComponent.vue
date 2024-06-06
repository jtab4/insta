<template>
    <div class="flex flex-col items-center min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
      <!-- Profil użytkownika -->
      <div class="profile-container bg-white shadow-lg rounded-lg p-6 w-full max-w-3xl">
        <div class="flex flex-col md:flex-row items-center md:items-start">
          <img class="w-24 h-24 rounded-full mb-4 md:mb-0" src="https://via.placeholder.com/150" alt="Profile Picture">
          <div class="md:ml-6 text-center md:text-left">
            <h2 class="text-2xl font-bold text-pink-500">Username</h2>
            <p class="text-sm text-gray-600 mb-4">Bio description goes here...</p>
            <button class="bg-orange-300 text-white px-4 py-2 rounded-lg hover:bg-orange-400">Follow</button>
          </div>
        </div>
        <div class="stats mt-6 flex justify-around text-center">
          <div class="followers">
            <h3 class="text-lg font-semibold text-pink-500">Followers</h3>
            <p class="text-gray-600">123</p>
          </div>
          <div class="following">
            <h3 class="text-lg font-semibold text-pink-500">Following</h3>
            <p class="text-gray-600">456</p>
          </div>
        </div>
      </div>
  
      <!-- Posty użytkownika -->
      <div class="posts-container mt-6 w-full max-w-3xl">
        <h3 class="text-lg font-semibold text-pink-500 mb-4 text-center">Posts</h3>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <!-- Pojedynczy post jako kafelek -->
          <div class="post bg-white border border-gray-200 rounded-lg overflow-hidden shadow-sm">
            <p class="text-sm text-gray-600 p-4">This is a text post.</p>
          </div>
          <!-- Dodaj więcej postów w podobny sposób -->
          <div class="post bg-white border border-gray-200 rounded-lg overflow-hidden shadow-sm">
            <p class="text-sm text-gray-600 p-4">Another text post.</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    // Bez danych ani metod
  };
  </script>
  
  <style scoped>
  /* Tailwind CSS używane do stylizacji */
  </style>
  