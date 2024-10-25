<template>
  <header class="fixed top-0 flex w-full max-w-[1072px] pt-7">
    <ctfd-logo class="ml-4" />
    <nav class="my-auto ml-auto">
      <ul
        class="flex items-center gap-6 text-[13px] font-medium tracking-[.15em] text-paragraph/50"
      >
        <li class="cursor-pointer hover:text-paragraph">FEATURES</li>
        <li class="cursor-pointer hover:text-paragraph">PRICING</li>
        <li class="cursor-pointer hover:text-paragraph">STORE</li>
        <li class="cursor-pointer hover:text-paragraph">CONTACT</li>
        <li class="cursor-pointer hover:text-paragraph">LOGIN</li>
        <li
          class="cursor-pointer rounded-md border border-paragraph/50 px-2 py-1.5 text-[15px] font-normal tracking-wide hover:text-paragraph"
        >
          Sign Up
        </li>
      </ul>
    </nav>
  </header>
</template>

<script setup lang="ts">
import CtfdLogo from '@/ctfd-logo.vue'
</script>
