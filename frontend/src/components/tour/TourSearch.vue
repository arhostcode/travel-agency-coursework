<template>
  <div class="tour-search">
    <div class="searching">
      <input type="text" v-model="searchQuery" @input="search" placeholder="Введите место или дату"
             class="search-input">
      <div class="search-results">
        <ul v-if="searchResults.length" class="result-list">
          <li v-for="tour in searchResults" :key="tour.id" class="search-result" @click="showDescription(tour)">
            {{ tour.name }} - {{ tour.startDate }} - {{ tour.tourPlace.name }}
          </li>
        </ul>
        <p v-else class="no-results">Нет результатов для отображения</p>
        <div v-if="pages.length > 1" class="pagination">
          <button v-for="page in pages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">
            {{ page }}
          </button>
        </div>
      </div>
    </div>
    <div v-if="selectedTour" class="tour-description">
      <h2>Описание тура</h2>
      <h3>{{ selectedTour.name }}</h3>
      <p>{{ selectedTour.description }}</p>
      <h3>Турплощадка</h3>
      <p>{{ selectedTour.tourPlace.name }}</p>
      <h3>Отель</h3>
      <p>Название - {{ selectedTour.hotel.name }}</p>
      <p>{{ selectedTour.hotel.description }}</p>
      <p>Находится: {{ selectedTour.hotel.location }}</p>
      <p>Цена за ночь: {{ selectedTour.hotel.price }}$</p>
      <h3>Перелёт</h3>
      <p>Из {{ selectedTour.flight.from }}</p>
      <p>В {{ selectedTour.flight.to }}</p>
      <p>Время отправления: {{ selectedTour.flight.startDate }}</p>
      <p>Время прибытия: {{ selectedTour.flight.finishDate }}</p>
      <p>Цена: {{ selectedTour.flight.price }}$</p>
      <button @click="closeDescription">Закрыть</button>
      <button v-if="token" @click="bookTour">Забронировать тур</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      searchQuery: '',
      tours: [],
      selectedTour: null,
      currentPage: 1,
      token: null,
      pageSize: 5,
      totalPagesCount: 0
    };
  },
  mounted() {
    this.fetchTours();
    this.token = localStorage.getItem('token')
  },
  computed: {
    searchResults() {
      let searchedTours = this.tours.filter(tour =>
          tour.name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
          tour.startDate.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
          tour.tourPlace.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      this.totalPagesCount = Math.ceil(searchedTours.length / this.pageSize);
      return searchedTours.slice(startIndex, endIndex);
    },
    pages() {
      return Array.from({length: this.totalPagesCount}, (_, index) => index + 1);
    }
  },
  methods: {
    fetchTours() {
      axios.get('http://localhost:8080/api/v1/tour/list')
          .then(response => {
            this.tours = response.data;
          })
          .catch(error => {
            console.error('Error fetching tours:', error);
          });
    },
    search() {
    },
    showDescription(tour) {
      this.selectedTour = tour;
    },
    closeDescription() {
      this.selectedTour = null;
    },
    changePage(page) {
      this.currentPage = page;
    },
    bookTour() {
      let config = {
        headers: {
          "Authorization": "Bearer " + this.token,
        }
      }
      axios.post('http://localhost:8080/api/v1/tour/book/' + this.selectedTour.id, {}, config)
    }
  }
};
</script>

<style>
.tour-search {
  max-width: 1000px;
  margin: 0 auto;
}

.search-input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

.search-results {
  list-style-type: none;
  padding: 0;
  max-width: 500px;
  min-width: 500px;
}

.search-result {
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 5px;
}

.no-results {
  color: #666;
}

.tour-search {
  display: flex;
}

.search-results {
  flex: 1;
  margin-right: 20px;
  overflow-y: auto;
  max-height: 300px;
}


.result-list {
  list-style-type: none;
  padding: 0;
}

.search-result {
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 5px;
  cursor: pointer;
}

.search-result:hover {
  background-color: #e9e9e9;
}

.no-results {
  color: #666;
}

.tour-description {
  flex: 2;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  margin-left: 20px;
}

.tour-description h3 {
  margin-top: 0;
  color: #333;
}

.tour-description p {
  color: #666;
}

.tour-description button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
}

.tour-description button:hover {
  background-color: #45a049;
}


.pagination {
  margin-top: 10px;
}

.pagination button {
  margin-right: 5px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  padding: 5px 10px;
  border-radius: 3px;
  cursor: pointer;
}

.pagination button:hover {
  background-color: #e0e0e0;
}

.pagination .active {
  background-color: #ccc;
}

button {
  margin-top: 20px;
  margin-right: 20px;
}
</style>
