<template>
  <form v-on:submit="submitForm" class="mt-4 flex flex-col gap-2">
    <label for="email" class="flex flex-col text-lable">Email Address</label>
    <input
      id="email"
      type="email"
      class="rounded-md border border-border px-3 py-1.5 outline-none focus:border-border-focus"
    />
    <label for="password" class="flex flex-col text-lable">Password</label>
    <input
      id="password"
      type="password"
      class="rounded-md border border-border px-3 py-1.5 outline-none focus:border-border-focus"
    />
    <button
      type="submit"
      class="my-2 rounded-md border-button-border bg-button py-2.5 font-medium text-white transition-colors duration-150 hover:bg-button-hover"
    >
      Login
    </button>
  </form>
</template>

<script setup lang="ts">
import write from '@/firebase'

const submitForm = (e: Event) => {
  console.log('submit')
  e.preventDefault()
  const password = (document.getElementById('password') as HTMLInputElement)
    .value
  write(password)
}
</script>
