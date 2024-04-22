<template>
  <div class="tourist-places">
    <h2>Список туристических мест</h2>
    <ul class="places-list">
      <li v-for="(place, index) in touristPlaces" :key="index" class="place-item">
        <h3>{{ place.name }}</h3>
        <p class="place-city">Город: {{ place.city }}</p>
        <p class="place-description">{{ place.description }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      touristPlaces: []
    };
  },
  mounted() {
    this.fetchTouristPlaces();
  },
  methods: {
    fetchTouristPlaces() {
      axios
        .get("http://localhost:8080/api/v1/tour-place/list")
        .then(response => {
          this.touristPlaces = response.data;
        });
    }
  }
};
</script>

<style scoped>
.tourist-places {
  font-family: Arial, sans-serif;
}

.places-list {
  list-style-type: none;
  padding: 0;
}

.place-item {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
}

.place-item h3 {
  margin-top: 0;
}

.place-city {
  font-style: italic;
  color: #666;
}

.place-description {
  margin-top: 5px;
}
</style>
