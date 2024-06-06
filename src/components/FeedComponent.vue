<template>
  <div class="flex flex-col md:flex-row min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
   
    <aside class="sidebar w-full md:w-1/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      <div class="profile flex flex-col items-center bg-pink-100 p-4 rounded-lg">
        <img class="w-24 h-24 rounded-full mb-4" src="https://via.placeholder.com/150" alt="Profile Picture">
        <h2 class="text-lg font-semibold text-pink-500">{{ userData.name }}</h2>
        
        <!-- Dodano obsługę kliknięcia przycisku "View profile" -->
        <button @click="viewProfile" class="bg-orange-300 text-white px-4 py-2 rounded-lg hover:bg-orange-400">View profile</button>
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
      
      <!-- Usunięto sekcję stories -->
      
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
          <li class="text-sm" v-for="notification in userData.notifications" :key="notification.id">{{ notification.content }}</li>
        </ul>
      </div>
    </aside>
  </div>
</template>

<script>
import PostComponent from './PostComponent.vue'; 
import { useRouter } from 'vue-router'; // Importujemy useRouter z Vue Router

export default {
  components: {
    PostComponent, 
  },

  props: ['userEmail'], 
  data() {
    return {
      userData: null
    };
  },
  methods: {
    // Metoda do obsługi kliknięcia przycisku "View profile"
    viewProfile() {
      // Pobierz identyfikator użytkownika z danych użytkownika
      const userId = this.userData.id;
      
      this.$router.push(`/profile/${userId}`);
    }
  },
  created() {
    const userEmail = localStorage.getItem('userEmail');
    console.log('Logged in as:', userEmail);

    
    fetch(`http://localhost:8080/users/${userEmail}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('User data:', data);
        this.userData = data; 
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  }
};
</script>

<style scoped>

</style>
