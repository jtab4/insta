<template>
  <div class="flex flex-col items-center min-h-screen w-full bg-gradient-to-r from-orange-300 to-pink-300 p-4">
    <!-- Profil użytkownika -->
    <div class="profile-container bg-white shadow-lg rounded-lg p-8 w-full max-w-4xl">
      <div class="flex flex-col md:flex-row items-center md:items-start md:space-x-6">
        <img class="w-32 h-32 rounded-full mb-4 md:mb-0" src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="Profile Picture">
        <div class="flex flex-col items-center md:items-start text-center md:text-left">
          <h2 class="text-3xl font-bold text-pink-500">{{ name || 'N/A' }}</h2>
          <div v-if="visitorId == visitedUserId" class="text-gray-500 mt-4 md:mt-2 md:self-start">Your profile</div>
          <button v-if="visitorId != visitedUserId && !isFollowing" @click="followUser" class="bg-orange-300 text-white px-6 py-2 rounded-lg hover:bg-orange-400 mt-4 md:mt-2 md:self-start">Follow</button>
          <button v-if="visitorId != visitedUserId && isFollowing" @click="unfollowUser" class="bg-gray-400 text-white px-6 py-2 rounded-lg hover:bg-gray-500 mt-4 md:mt-2 md:self-start">Unfollow</button>
        </div>
      </div>
      <div class="stats mt-8 flex justify-around text-center">
        <div class="followers cursor-pointer" @click="openFollowersPopup">
          <h3 class="text-xl font-semibold text-pink-500">Followers</h3>
          <p class="text-gray-600">{{ followersCount }}</p>
        </div>
        <div class="following cursor-pointer" @click="openFollowingPopup">
          <h3 class="text-xl font-semibold text-pink-500">Following</h3>
          <p class="text-gray-600">{{ followingCount }}</p>
        </div>
      </div>
    </div>

    <!-- Posty użytkownika -->
    <div class="posts-container mt-8 w-full max-w-4xl">
      <h3 class="text-xl font-semibold text-pink-500 mb-6 text-center">Posts</h3>
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Add Post Button -->
        <div v-if="visitorId == visitedUserId" class="flex justify-center items-center bg-white border border-gray-200 rounded-lg overflow-hidden shadow-sm cursor-pointer hover:bg-gray-100" @click="openAddPostPopup">
          <div class="flex flex-col justify-center items-center p-6">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-pink-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
            </svg>
            <span class="mt-2 text-pink-500">Add Post</span>
          </div>
        </div>

        <div class="post bg-white border border-gray-200 rounded-lg overflow-hidden shadow-sm" v-for="(post, index) in posts" :key="index">
          <div class="p-4">
              <h4 class="font-semibold text-lg text-pink-500">{{ post.head }}</h4>
              <p class="text-sm text-gray-600 mt-2">{{ post.text }}</p>
              <div class="flex items-center mt-4">
                <button 
                    class="like-button text-red-500" 
                    :disabled="visitedUserId === visitorId"
                    @click="addLike(post.id)">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                        <path fill-rule="evenodd" d="M10 18l-8-5V5a2 2 0 012-2h12a2 2 0 012 2v8l-8 5z" clip-rule="evenodd" />
                    </svg>
                </button>
                <span class="like-count ml-2 text-gray-600">{{ post.likes }}</span>
            </div>
          </div>
      </div>
      
      </div>
    </div>

    <!-- Popup z listą followers -->
    <div v-if="showFollowersPopup" class="popup fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="popup-content bg-white p-6 rounded-lg shadow-lg w-11/12 md:w-2/3 lg:w-1/2 xl:w-1/3">
        <h3 class="text-xl font-semibold text-pink-500 mb-4 text-center">Followers</h3>
        <ul class="grid grid-cols-1 sm:grid-cols-2 gap-4">
          <li v-for="follower in followersList" :key="follower.id" class="flex items-center space-x-4 p-4 bg-gray-100 rounded-lg cursor-pointer hover:bg-gray-200" @click="goToProfile(follower.id)">
            <img class="w-10 h-10 rounded-full" :src="follower.profilePicture || 'https://cdn-icons-png.flaticon.com/512/149/149071.png'" alt="Follower Picture">
            <span class="text-gray-600">{{ follower.name }}</span>
          </li>
        </ul>
        <button @click="showFollowersPopup = false" class="mt-4 bg-pink-500 text-white px-4 py-2 rounded-lg block mx-auto">Close</button>
      </div>
    </div>

    <!-- Popup z listą following -->
    <div v-if="showFollowingPopup" class="popup fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="popup-content bg-white p-6 rounded-lg shadow-lg w-11/12 md:w-2/3 lg:w-1/2 xl:w-1/3">
        <h3 class="text-xl font-semibold text-pink-500 mb-4 text-center">Following</h3>
        <ul class="grid grid-cols-1 sm:grid-cols-2 gap-4">
          <li v-for="follow in followingList" :key="follow.id" class="flex items-center space-x-4 p-4 bg-gray-100 rounded-lg cursor-pointer hover:bg-gray-200" @click="goToProfile(follow.id)">
            <img class="w-10 h-10 rounded-full" :src="follow.profilePicture || 'https://cdn-icons-png.flaticon.com/512/149/149071.png'" alt="Follow Picture">
            <span class="text-gray-600">{{ follow.name }}</span>
          </li>
        </ul>
        <button @click="showFollowingPopup = false" class="mt-4 bg-pink-500 text-white px-4 py-2 rounded-lg block mx-auto">Close</button>
      </div>
    </div>

    <!-- Popup do dodawania posta -->
    <div v-if="showAddPostPopup" class="popup fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="popup-content bg-white p-6 rounded-lg shadow-lg w-11/12 md:w-2/3 lg:w-1/2 xl:w-1/3">
        <h3 class="text-xl font-semibold text-pink-500 mb-4 text-center">Add New Post</h3>
        <form @submit.prevent="submitNewPost">
          <div class="mb-4">
            <label class="block text-gray-700">Headline</label>
            <input v-model="newPostHeadline" type="text" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring focus:border-pink-500" required>
          </div>
          <div class="mb-4">
            <label class="block text-gray-700">Content</label>
            <textarea v-model="newPostContent" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring focus:border-pink-500" required></textarea>
          </div>
          <div class="flex justify-end">
            <button type="button" @click="showAddPostPopup = false" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2">Cancel</button>
            <button type="submit" class="bg-pink-500 text-white px-4 py-2 rounded-lg">Submit</button>
          </div>
        </form>
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
      posts: [],
      visitorId: "",
      visitedUserId : "",
      followersCount: 0,
      followingCount: 0,
      showFollowersPopup: false,
      showFollowingPopup: false,
      showAddPostPopup: false,
      followersList: [],
      followingList: [],
      isFollowing: false,
      newPostHeadline: "",
      newPostContent: "",
      posts : []
    };
  },

  methods: {
    followUser() {
      const newFollower = {
        user: { id: this.visitedUserId },
        follower: { id: this.visitorId }
      };

      axios.post('http://localhost:8080/followers/add', newFollower)
        .then(response => {
          console.log(response.data); 
          this.getFollowersCount();
          this.isFollowing = true;
          this.loadFollowersList(); 
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },


    unfollowUser() {
      axios.post('http://localhost:8080/followers/remove', {
        user: { id: this.visitedUserId },
        follower: { id: this.visitorId }
      })
      .then(response => {
        console.log(response.data); 
        this.getFollowersCount();
        this.loadFollowersList();
      
        this.isFollowing = false; 
      })
      .catch(error => {
        console.error("There was a problem with the Axios request:", error);
      });
},


    checkIfLoggedIn() {
      const userId = localStorage.getItem("userId");
      if (!userId) {
        
        window.location.href = '/login';
      }
    },

    getFollowersCount() {
      axios.get(`http://localhost:8080/followers/count/${this.visitedUserId}`)
        .then(response => {
          this.followersCount = response.data; 
          console.log(response.data)
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },

    getFollowingCount() {
      axios.get(`http://localhost:8080/followers/count-following/${this.visitedUserId}`)
        .then(response => {
          this.followingCount = response.data; 
          console.log(response.data)
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },

    openFollowersPopup() {
      this.showFollowersPopup = true;
    },

    openFollowingPopup() {
      this.showFollowingPopup = true;
    },

    openAddPostPopup() {
      this.showAddPostPopup = true;
    },

    goToProfile(userId) {
      window.location.href = `/profile/${userId}`;
    },

    fetchData() {
      const visitedUserId = parseInt(this.$route.params.userId);
      this.visitedUserId = visitedUserId;

      axios.get(`http://localhost:8080/findProfile/${visitedUserId}`)
        .then(response => {
          const data = response.data;
          this.name = data.name;
          this.followers = data.followers || [];
          this.following = data.following || [];
          this.posts = data.posts || [];
          console.log("User data:", data);

          this.getFollowersCount();
          this.getFollowingCount();
          this.isFollowing = this.followers.some(follower => follower.id === this.visitorId);

          // Pobierz listy followers i following
          this.loadFollowersList();
          this.loadFollowingList();
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });

        axios.get(`http://localhost:8080/posts/user-posts/${visitedUserId}`)
            .then(response => {
              this.posts = response.data;
              console.log("User posts:", this.posts);
            })
            .catch(error => {
              console.error("There was a problem fetching user posts:", error);
            });

    },

    loadFollowersList() {
      axios.get(`http://localhost:8080/followers/followers-list/${this.visitedUserId}`)
        .then(response => {
          this.followersList = response.data;
          console.log(response.data);
          this.isFollowing = this.followersList.some(follower => follower.id === this.visitorId);
          console.log(this.isFollowing);
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },

    loadFollowingList() {
      axios.get(`http://localhost:8080/followers/following-list/${this.visitedUserId}`)
        .then(response => {
          this.followingList = response.data;
          
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    },

    addLike(postId) {
    axios.post(`http://localhost:8080/posts/add-like/${postId}`)
      .then(response => {
        
        const updatedPost = response.data;
        
        const index = this.posts.findIndex(post => post.id === updatedPost.id);
        
        if (index !== -1) {
          this.posts[index] = updatedPost;
        }
      })
      .catch(error => {
        console.error("There was a problem with the Axios request:", error);
      });
  },


    submitNewPost() {
      const newPost = {
        head: this.newPostHeadline,
        text: this.newPostContent,
        user_id: this.visitorId,
        likes : 0
      };

      axios.post(`http://localhost:8080/posts/add-post/${this.visitorId}`, newPost)
        .then(response => {
          console.log(response.data); 
          this.posts.push(response.data); 
          this.newPostHeadline = "";
          this.newPostContent = "";
          this.showAddPostPopup = false;
        })
        .catch(error => {
          console.error("There was a problem with the Axios request:", error);
        });
    }
  },

  created() {
    this.checkIfLoggedIn();
    const visitorId = parseInt(localStorage.getItem("userId"));   
    this.visitorId = visitorId;
    
    this.fetchData();
  }
};
</script>

<style scoped>
.popup {
  z-index: 1000;
}
.popup-content {
  max-height: 80vh;
  overflow-y: auto;
}
</style>
