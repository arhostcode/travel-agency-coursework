<template>
  <div class="hotel-list">
    <div class="hotel-preview">
      <ul>
        <li v-for="(hotel, index) in hotels" :key="index" @click="selectHotel(hotel)">
          {{ hotel.name }}
        </li>
      </ul>
    </div>
    <div class="hotel-details" v-if="selectedHotel">
      <h2>{{ selectedHotel.name }}</h2>
      <p>{{ selectedHotel.description }}</p>
      <p>Город: {{ selectedHotel.city }}</p>
      <p>Адрес: {{ selectedHotel.location }}</p>
      <p>Цена за ночь: {{ selectedHotel.price }}</p>
      <img :src="selectedHotel.image" alt="" height="200px"/>
      <button @click="bookHotel(selectedHotel)">Забронировать</button>
    </div>
    <div v-else class="no-hotel-selected">Выберите отель из списка</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      hotels: [],
      selectedHotel: null,
      token: null
    };
  },
  mounted() {
    this.fetchHotels()
    this.token = localStorage.getItem('token')
  },
  methods: {
    selectHotel(hotel) {
      this.selectedHotel = hotel;
    },
    bookHotel(hotel) {
      if (this.token) {
        axios.post(`http://localhost:8080/api/v1/hotel/book/${hotel.id}`, {}, {
          headers: {
            'Authorization': `Bearer ${this.token}`
          }
        }).then(() => {
          this.selectedHotel = null;
          this.fetchHotels();
        })
      }
    },
    fetchHotels() {
      axios.get('http://localhost:8080/api/v1/hotel/list').then(response => {
        this.hotels = response.data
      })
    }
  }
};
</script>

<style scoped>
/* Стили компонента */
.hotel-list {
  display: flex;
}

.hotel-preview {
  flex: 1;
}

.hotel-preview ul {
  list-style-type: none;
  padding: 0;
}

.hotel-preview li {
  cursor: pointer;
}

.hotel-details {
  flex: 3;
}

.no-hotel-selected {
  text-align: center;
}
img {
  display: block;
}
/* Стили кнопки */
button {
  margin-top: 20px;
  display: block;
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
