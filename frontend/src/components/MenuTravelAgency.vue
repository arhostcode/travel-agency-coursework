<template>
  <div class="menu">
    <img alt="Logo" class="logo" src="../assets/travel-agency.png" width="50" height="50"/>
    <ul>
      <li v-for="(item, index) in menuItems" :key="index">
        <router-link :to="item.link">{{ item.title }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuItems: [
        { title: 'Поиск туров', link: '/' },
        { title: 'Полёты', link: '/flight' },
        { title: 'Отели', link: '/hotel' },
        { title: 'Места для туров', link: '/tour-place' },
        { title: 'Аккаунт', link: '/account' }
      ]
    };
  }
};
</script>

<style scoped>
.menu {
  font-family: Arial, sans-serif;
  display: flex;
  vertical-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: row;
}

.menu ul {
  list-style-type: none;
  padding: 0;
  display: flex;
  flex-direction: row;
}

.menu li {
  margin-right: 10px;
}

.menu a {
  text-decoration: none;
  font-size: large;
  color: var(--color-text);
}

.menu a:hover {
  color: #007bff;
}

.logo {
  margin-right: 20px;
}
</style>
