<template>
  <div class="flex flex-col md:flex-row min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
   
    <aside class="sidebar w-full md:w-1/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      <div class="profile flex flex-col items-center bg-pink-100 p-4 rounded-lg">
        <img class="w-24 h-24 rounded-full mb-4" src="https://via.placeholder.com/150" alt="Profile Picture">
        <h2 class="text-lg font-semibold text-pink-500">Username</h2>
        <p class="text-sm text-gray-600 mb-4">Bio description goes here...</p>
        <button class="bg-orange-300 text-white px-4 py-2 rounded-lg hover:bg-orange-400">View profile</button>
      </div>
      <div class="suggestions mt-6 bg-pink-100 p-4 rounded-lg">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Suggestions for you</h3>
        <div class="suggestion flex items-center mb-3">
          <img class="w-10 h-10 rounded-full" src="https://via.placeholder.com/100" alt="Suggestion Picture">
          <div class="ml-3">
            <h4 class="text-sm font-semibold text-pink-500">Suggested User</h4>
            <button class="text-orange-300 text-xs hover:text-orange-400">Follow</button>
          </div>
        </div>
        
      </div>
    </aside>

    
    <main class="feed w-full md:w-2/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      
      <div class="stories flex space-x-4 overflow-x-auto bg-white p-4 rounded-lg shadow-sm mb-4">
        <div class="story flex flex-col items-center">
          <img class="w-16 h-16 rounded-full border-2 border-red-500 p-1" src="https://via.placeholder.com/100" alt="Story Picture">
          <span class="text-xs mt-1 text-pink-500">User 1</span>
        </div>
        
      </div>

      
      <div class="post-list space-y-6">
        
        <PostComponent />
        <PostComponent />
        
        <div class="no-posts-message text-center text-gray-500">
          <p>No posts available.</p>
        </div>
      </div>
    </main>

    
    <aside class="w-full md:w-1/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      <div class="explore mb-4 bg-pink-100 p-4 rounded-lg">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Explore</h3>
        
        <div class="space-y-2">
          <p class="text-sm">Explore content here...</p>
          
        </div>
      </div>

      <div class="notifications bg-pink-100 p-4 rounded-lg">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Notifications</h3>
        <ul class="space-y-2">
          <li class="text-sm">User1 liked your post</li>
          <li class="text-sm">User2 started following you</li>
          
        </ul>
      </div>
    </aside>
  </div>
</template>

<script>
import PostComponent from './PostComponent.vue'; 

export default {
  components: {
    PostComponent, 
  },

  props: ['userEmail'], 
  
  created() {
    console.log('Logged in as:', this.userEmail);
  }
};
</script>

<style scoped>

</style>
