<template>
    <div
        class="post bg-pink-100 border border-gray-200 rounded-lg overflow-hidden shadow-sm">

        <div class="post-header flex items-center p-4 bg-pink-200">
            <img
                class="w-10 h-10 rounded-full"
                src="https://via.placeholder.com/100"
                alt="Author Picture">
                <div class="ml-3">
                    <h3 class="text-sm font-semibold text-pink-500">Author Name</h3>
                    <p class="text-xs text-gray-500">2 hours ago</p>
                </div>
            </div>

            <div class="post-content p-4">

                <div class="post-text mb-4">
                    <p class="text-sm">
                        <span class="font-semibold text-pink-500">Author Name</span>
                        Post description goes here...</p>
                </div>

                <div class="post-actions flex items-center">

                    <button class="like-button text-red-500">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            class="h-6 w-6"
                            fill="none"
                            viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="M5 15l7-7 7 7"/>
                        </svg>
                    </button>

                    <span class="like-count ml-2 text-gray-600">123 likes</span>
                </div>
            </div>
        </div>
    </template>

    <script>
        export default {};
    </script>

    <style scoped></style>