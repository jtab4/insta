<template>
    <div class="post bg-pink-100 border border-gray-200 rounded-lg overflow-hidden shadow-sm mb-6">
      <div class="post-header flex items-center p-4 bg-pink-200">
        <img class="w-12 h-12 rounded-full" src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="Author Picture">
        <div class="ml-3">
          <h3 class="text-md font-semibold text-pink-500">{{ post.user.name }}</h3>
          
        </div>
      </div>
      <div class="post-content p-4">
        <h4 class="font-semibold text-lg text-pink-500 mb-2">{{ post.head }}</h4>
        <div class="post-text mb-4">
          <p class="text-sm">{{ post.text }}</p>
        </div>
        <div class="post-actions flex items-center">
          <button class="like-button text-red-500" @click="addLike(post.id)">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 15l7-7 7 7"/>
            </svg>
          </button>
          <span class="like-count ml-2 text-gray-600">{{ post.likes }} likes</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  export default {
    props: {
      post: {
        type: Object,
        required: true
      },
      
      addLike: {
      type: Function,
      required: true
    }
    },
    methods: {
      
      
    }
  };
  </script>
  
  <style scoped>
  /* Additional styles to enhance the component's appearance */
  .post-header {
    background-color: #f7c6d2;
  }
  .like-button {
    transition: transform 0.2s;
  }
  .like-button:hover {
    transform: scale(1.1);
  }
  </style>
  