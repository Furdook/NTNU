<script setup lang="ts">
import HeaderNavigation from '@/components/header-navigation.vue'
import LoginForm from '@/components/login-form.vue'
</script>

<template>
  <div class="flex h-screen w-screen items-center justify-center">
    <header-navigation />
    <main class="w-full max-w-[540px] pb-16">
      <h2 class="mx-auto w-fit text-[1.75rem] font-medium text-heading">
        Login
      </h2>
      <a
        href=""
        class="mb-4 w-full text-center text-link hover:text-link-hover hover:underline"
        >Don't have an account? Sign up</a
      >
      <login-form />
      <a
        href=""
        class="mt-2 w-full text-center text-link hover:text-link-hover hover:underline"
        >Forgot password?</a
      >
    </main>
  </div>
</template>
