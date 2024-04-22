<template>
  <div class="user-profile">
    <h2>Личный кабинет пользователя</h2>
    <div class="user-info">
      <h3>Информация о пользователе:</h3>
      <p>Имя: {{ user.full_name }}</p>
      <p>Email: {{ user.email }}</p>
    </div>

    <div class="bookings">
      <div class="booking">
        <h3>Забронированные туры:</h3>
        <ul>
          <li v-for="tour in bookedTours" :key="tour.id">
            {{ tour.name }}
            <button @click="cancelBooking('tour', tour.id)">Отменить</button>
          </li>
        </ul>
      </div>

      <div class="booking">
        <h3>Забронированные отели:</h3>
        <ul>
          <li v-for="hotel in bookedHotels" :key="hotel.id">
            {{ hotel.name }}
            <button @click="cancelBooking('hotel', hotel.id)">Отменить</button>
          </li>
        </ul>
      </div>

      <div class="booking">
        <h3>Забронированные полёты:</h3>
        <ul>
          <li v-for="flight in bookedFlights" :key="flight.id">
            {{ flight.name }} - {{flight.from}} - {{flight.to}}
            <button @click="cancelBooking('flight', flight.id)">Отменить</button>
          </li>
        </ul>
      </div>
    </div>

    <button class="logout-btn" @click="logout">Выйти из аккаунта</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: {},
      bookedTours: [],
      bookedHotels: [],
      bookedFlights: [],
      token: null
    };
  },
  mounted() {
    this.fetchUser();
    this.token = localStorage.getItem('token')
  },
  methods: {
    cancelBooking(type, id) {
      switch (type) {
        case 'tour':
          this.bookedTours = this.bookedTours.filter(tour => tour.id !== id);
          axios.delete('http://localhost:8080/api/v1/tour/cancel/' + id, {
            headers: {
              "Authorization": `Bearer ${this.token}`
            }
          });
          break;
        case 'hotel':
          this.bookedHotels = this.bookedHotels.filter(hotel => hotel.id !== id);
          axios.delete('http://localhost:8080/api/v1/hotel/cancel/' + id , {
            headers: {
              "Authorization": `Bearer ${this.token}`
            }
          });
          break;
        case 'flight':
          this.bookedFlights = this.bookedFlights.filter(flight => flight.id !== id);
          axios.delete('http://localhost:8080/api/v1/flight/cancel/' + id , {
            headers: {
              "Authorization": `Bearer ${this.token}`
            }
          });
          break;
        default:
          console.error('Неподдерживаемый тип бронирования');
      }
    },
    logout() {
      localStorage.removeItem('token');
      this.$router.push('/account');
    },
    fetchUser() {
      const token = localStorage.getItem('token');
      if (token) {
        axios
            .get('http://localhost:8080/auth/me', {
              headers: {
                Authorization: `Bearer ${token}`
              }
            })
            .then(response => {
              this.user = response.data;
              this.bookedTours = response.data.tours;
              this.bookedHotels = response.data.hotels;
              this.bookedFlights = response.data.flights;
            })
            .catch(error => {
              console.error(error);
            });
      }
    }
  }
};
</script>


<style scoped>
.user-profile {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.user-info {
  margin-bottom: 20px;
}

.bookings {
  margin-bottom: 20px;
}

.booking {
  margin-bottom: 20px;
}

.logout-btn {
  background-color: #f44336;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.logout-btn:hover {
  background-color: #d32f2f;
}
</style>