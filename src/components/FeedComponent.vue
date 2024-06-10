<template>
  <div class="flex flex-col md:flex-row min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
    
    <aside class="sidebar w-full md:w-1/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      
      <div class="profile flex flex-col items-center bg-pink-100 p-4 rounded-lg mb-4">
        <img class="w-24 h-24 rounded-full mb-4" src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="Profile Picture">
        <h2 class="text-lg font-semibold text-pink-500">{{ userData.name }}</h2>
        <button @click="viewProfile" class="bg-orange-300 text-white px-4 py-2 rounded-lg mt-4 hover:bg-orange-400 transition duration-300 ease-in-out">View profile</button>
      </div>
      
      <div class="suggestions bg-pink-100 p-4 rounded-lg mb-4">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Suggestions for you</h3>
        <div class="suggestion flex items-center mb-3" v-for="user in suggestions" :key="user.id">
          <div class="p-4 bg-white rounded-lg shadow-md flex items-center justify-between w-full">
            <div class="flex items-center">
              <img class="w-10 h-10 rounded-full" src="https://cdn-icons-png.flaticon.com/512/149/149071.png" :alt="user.name">
              <router-link :to="`/profile/${user.id}`" class="text-sm font-semibold text-pink-500 hover:underline ml-2">{{ user.name }}</router-link>
            </div>
          </div>
        </div>
      </div>
      
      <div class="settings bg-pink-100 p-4 rounded-lg mb-4">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Settings</h3>
        
      </div>
    </aside>
    
    
    <main class="feed w-full md:w-1/2 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      <div class="post-list space-y-6">
        <PostComponent v-for="post in posts" :key="post.id" :post="post" />
        <div v-if="posts.length === 0" class="no-posts-message text-center text-gray-500">
          <p>No posts available.</p>
        </div>
      </div>
    </main>

    
    <aside class="sidebar w-full md:w-1/4 bg-white shadow-lg rounded-lg p-4 overflow-y-auto">
      
      <div class="explore bg-pink-100 p-4 rounded-lg mb-4">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Explore</h3>
        
      </div>
     
      <div class="notifications bg-pink-100 p-4 rounded-lg mb-4">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Notifications</h3>
        <div class="grid gap-4">
          <div v-for="notification in notifications" :key="notification.id" class="bg-white shadow-md rounded-lg p-4">
            <p class="text-sm">{{ notification.text }}</p>
            <p class="text-xs text-gray-500">{{ formatDate(notification.data) }}</p>
          </div>
        </div>
      </div>
      <div class="messages bg-pink-100 p-4 rounded-lg mb-4">
        <h3 class="text-lg font-semibold text-pink-500 mb-4">Messages</h3>
        
      </div>
    </aside>
  </div>
</template>


<script>
import PostComponent from './PostComponent.vue'; 
import { useRouter } from 'vue-router';
import axios from 'axios' // Importujemy useRouter z Vue Router

export default {
  components: {
    PostComponent, 
  },

  props: ['userEmail'], 
  data() {
    return {
      userData: null,
      suggestions: [],
      notifications : [],
      followersList: [],
      followingList: [],
      posts : []
    };
  },
  methods: {
    
    viewProfile() {
      
      const userId = this.userData.id;
      
      this.$router.push(`/profile/${userId}`);
    },
    fetchUserNotifications(userId) {
      fetch(`http://localhost:8080/notifications/user/${userId}`)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.notifications = data;
          
          console.log(this.formatDate(this.formatDate(data[0].data)))
        })
        .catch(error => {
          console.error('Error fetching user notifications:', error);
        });
    },
    formatDate(dateString) {
      let date = new Date(dateString); 
      let year = date.getFullYear();
      let month = String(date.getMonth() + 1).padStart(2, '0');
      let day = String(date.getDate()).padStart(2, '0');
      let hours = String(date.getHours()).padStart(2, '0');
      let minutes = String(date.getMinutes()).padStart(2, '0');

      return `${year}-${month}-${day} ${hours}:${minutes}`;
  },
  
    fetchSuggestions() {
      const userEmail = localStorage.getItem('userEmail'); 
      fetch(`http://localhost:8080/api/suggestions/lastFiveExcluding/${userEmail}`) 
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          console.log('Suggestions:', data);
          
          this.suggestions = data;
        })
        .catch(error => {
          console.error('There was a problem with fetching suggestions:', error);
        });
    },
    

    loadFollowingList() {
      const userId = localStorage.getItem("userId")
      axios.get(`http://localhost:8080/followers/following-list/${userId}`)
        .then(response => {
          this.followingList = response.data;
          console.log(response.data)
          this.loadPostsByFollowing();
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },
    loadPostsByFollowing() {
      const userIds = this.followingList.map(user => user.id);
      axios.post('http://localhost:8080/posts/user-posts-by-ids', userIds)
        .then(response => {
          this.posts = response.data;
          
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    }
  },
  created() {
    const userEmail = localStorage.getItem('userEmail');
    console.log('Logged in as:', userEmail);
    if (!userEmail) {
      window.location.href = '/login';
      return;
    }
    this.loadFollowingList();
    

    
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
        localStorage.setItem("userId",data.id)
       
        this.fetchSuggestions();
        this.fetchUserNotifications(data.id)
        
        
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  }
};
</script>
<style scoped>
</style>