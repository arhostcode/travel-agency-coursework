<template>
  <div class="modal-overlay">
    <div class="modal">
      <div class="modal-header">
        <h2>Детали рейса</h2>
        <button class="close-button" @click="closeModal">&times;</button>
      </div>
      <div class="modal-body">
        <p><strong>Номер рейса:</strong> {{ flight.name }}</p>
        <p><strong>Место отправления:</strong> {{ flight.from }}</p>
        <p><strong>Место назначения:</strong> {{ flight.to }}</p>
        <p><strong>Дата отправления:</strong> {{ flight.startDate }}</p>
        <p><strong>Дата назначения:</strong> {{ flight.finishDate }}</p>
        <p><strong>Цена:</strong> {{ flight.price }}$</p>
      </div>
      <button class="book-button" @click="book">Забронировать</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    flight: {
      type: Object,
      required: true
    }
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
    book() {
      const token = localStorage.getItem('token');
      if (token) {
        axios.post(import.meta.env.VITE_API_URL + "/api/v1/flight/book/" + this.flight.id, {}, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })
      }
    }
  }
};
</script>

<style scoped>
/* Стили для модального окна */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background-color: #fff;
  padding: 20px;
  border-radius: 4px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.modal-header h2 {
  margin: 0;
}

.close-button {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
}

.modal-body {
  padding: 10px 0;
}

/* Скрываем модальное окно по умолчанию */
.modal-overlay {
}

/* Отображаем модальное окно, если props flight не пустой */
.modal-overlay.active {
  display: flex;
}
</style>
