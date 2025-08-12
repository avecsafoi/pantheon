<script setup></script>

<template>
  <div>
    {{ title }}
    <p v-for="(item, i) in foods" :key="i">{{ item }}</p>

    <p>{{ data[0].name }}</p>
    <p>{{ data[1].name }}</p>

    <a href="https://vite.dev" target="_blank">
      <img alt="Vite logo" class="logo" src="/vite.svg"/>
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img alt="Vue logo" class="logo vue" src="./assets/vue.svg"/>
    </a>
  </div>
  <HelloWorld msg="Vite + Vue"/>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>

<script>
export default {
  name: 'App',
  data() {
    return {
      title: "노랑", year: 2022, foods: [1, 2, 3, 4, 5, 6, 7, 8],
      data: [{name: "selani"}, {name: "orange"}]
    }
  }
}
</script>
