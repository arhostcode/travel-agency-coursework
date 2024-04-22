<template>
  <div class="flight-search">
    <div class="input-group">
      <label for="departure">Откуда:</label>
      <input type="text" id="departure" v-model="departure" @input="searchFlights"
             placeholder="Введите место отправления">
    </div>
    <div class="input-group">
      <label for="destination">Куда:</label>
      <input type="text" id="destination" v-model="destination" @input="searchFlights"
             placeholder="Введите место назначения">
    </div>
    <div class="input-group">
      <label for="date">Дата:</label>
      <input type="date" id="date" v-model="selectedDate" @input="searchFlights">
    </div>
    <ul v-if="filteredFlights.length" class="flight-list">
      <li v-for="(flight, index) in filteredFlights" :key="index" class="flight-item"
          @click="showFlightDetails(flight)">
        <p><strong>Откуда:</strong> {{ flight.from }}</p>
        <p><strong>Куда:</strong> {{ flight.to }}</p>
        <p><strong>Дата:</strong> {{ flight.startDate }}</p>
        <p><strong>Цена:</strong> {{ flight.price }}</p>
      </li>
    </ul>
    <p v-else class="no-flights">Нет доступных рейсов</p>
    <flight-details-modal v-if="selectedFlight" :flight="selectedFlight"
                          @close="selectedFlight = null"></flight-details-modal>
  </div>
</template>

<script>
import FlightDetailsModal from './FlightDetailsModal.vue';
import axios from "axios";

export default {
  components: {
    FlightDetailsModal
  },
  data() {
    return {
      departure: '',
      destination: '',
      selectedDate: '',
      flights: [],
      selectedFlight: null
    };
  },
  mounted() {
    this.fetchFlights();
  },
  computed: {
    filteredFlights() {
      return this.flights.filter(flight => {
        const departureMatch = !this.departure || flight.from.toLowerCase().includes(this.departure.toLowerCase());
        const destinationMatch = !this.destination || flight.to.toLowerCase().includes(this.destination.toLowerCase());
        const timeMatch = !this.selectedDate || flight.startDate > this.selectedDate;
        return departureMatch && destinationMatch && timeMatch;
      });
    }
  },
  methods: {
    searchFlights() {
    },
    fetchFlights() {
      axios.get('http://localhost:8080/api/v1/flight/list')
          .then(response => {
            this.flights = response.data;
            this.searchFlights()
          })
          .catch(error => {
            console.error('Error fetching tours:', error);
          });
    },
    showFlightDetails(flight) {
      this.selectedFlight = flight;
    }
  }
};
</script>

<style scoped>
.flight-search {
  max-width: 400px;
  margin: 0 auto;
}

.input-group {
  margin-bottom: 10px;
}

/* Стили для поля ввода места отправления и назначения */
.input-group label {
  display: block;
  font-weight: bold;
}

.input-group input[type="text"],
.input-group input[type="date"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Стили для списка рейсов */
.flight-list {
  list-style-type: none;
  padding: 0;
}

/* Стили для элемента списка рейсов */
.flight-item {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  cursor: pointer;
}

/* Стили для текста, отображаемого при отсутствии рейсов */
.no-flights {
  font-style: italic;
  color: #888;
}
</style>
