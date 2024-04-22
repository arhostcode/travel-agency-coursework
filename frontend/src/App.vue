<script setup>
import MenuTravelAgency from "@/components/MenuTravelAgency.vue";
</script>

<template>
  <header>
    <MenuTravelAgency/>
  </header>
  <main>
    <router-view></router-view>
  </main>
</template>

<style scoped>
header {
  display: flex;
  flex-direction: row;
  align-content: center;
  justify-items: center;
  justify-content: center;
  vertical-align: center;
}

main {
  margin-top: 20px;
}
</style>
