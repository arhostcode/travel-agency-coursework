<template>
  <div class="menu">
    <img alt="Logo" class="logo" src="../assets/travel-agency.png" width="50" height="50"/>
    <ul>
      <li v-for="(item, index) in menuItems" :key="index">
        <a :href="item.link">{{ item.title }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuItems: [
        { title: 'Home', link: '/' },
        { title: 'Tours', link: '/tours' },
        { title: 'Destinations', link: '/destinations' },
        { title: 'About Us', link: '/about' },
        { title: 'Contact Us', link: '/contact' }
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
