<script setup lang="ts">
import FlowLogList from './FlowLogList.vue'
import WebSocketProvider from '@/providers/WebSocketProvider.vue'

import { ref } from 'vue'

defineProps<{
  show: boolean
  flowId: string
}>()

const currentDevices = ref([{ key: 0, item: 0 }] as { key: number; item: number }[])

const modifyCurrentDevices = (id: number) => {
  const device = currentDevices.value.find((item) => item.item === id)
  if (device) {
    currentDevices.value = currentDevices.value.filter((item) => item.item !== id)
  } else {
    currentDevices.value.push({ key: Math.random(), item: id })
  }
}
</script>

<template>
  <div v-if="show">
    <nav class="mt-2 flex" role="tabpanel">
      <ul class="flex h-full flex-grow gap-2 overflow-x-scroll" role="tablist">
        <li
          @click="modifyCurrentDevices(0)"
          :aria-expanded="currentDevices.some((device) => device.item === 0)"
          tabindex="1"
          role="tab"
          class="h-9 w-fit flex-shrink-0 rounded-md bg-primary-100 px-4 py-1.5 text-sm leading-6 shadow-md transition-colors duration-200 hover:cursor-pointer hover:bg-accent-500 aria-expanded:bg-accent-400 dark:hover:bg-accent-600 dark:aria-expanded:bg-accent-500"
        >
          All Devices
        </li>

        <!-- <li
          role="tab"
          :key="item.id"
          v-for="item in logItems"
          @click="modifyCurrentDevices(item.id)"
          :aria-expanded="currentDevices.some((device) => device.item === item.id)"
          tabindex="1"
          class="h-9 w-fit flex-shrink-0 rounded-md bg-primary-100 px-4 py-1.5 text-sm leading-6 shadow-md transition-colors duration-200 hover:cursor-pointer hover:bg-accent-500 aria-expanded:bg-accent-400 dark:hover:bg-accent-600 dark:aria-expanded:bg-accent-500"
        >
          {{ item.name }}
        </li> -->
      </ul>
    </nav>
    <div
      class="grid h-[calc(100vh-9.75rem)] grid-cols-2 gap-2"
      :class="{ 'grid-cols-1': currentDevices.length === 1 }"
    >
      <web-socket-provider>
        <flow-log-list
          v-for="currentDevice in currentDevices"
          :key="currentDevice.item"
          :device-id="currentDevice.item"
          :devices="currentDevices"
          :flow-id="flowId"
        />
      </web-socket-provider>
    </div>
  </div>
</template>
