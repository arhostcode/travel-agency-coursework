<template>
  <div class="auth-form">
    <h2 v-if="isLoginMode">Вход</h2>
    <h2 v-else>Регистрация</h2>

    <form @submit.prevent="handleSubmit" class="form">
      <div v-if="!isLoginMode" class="form-group">
        <label for="username">Полное имя:</label>
        <input type="text" id="fullName" v-model="fullName" required>
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>

      <div class="form-group">
        <label for="password">Пароль:</label>
        <input type="password" id="password" v-model="password" required>
      </div>

      <button type="submit" class="btn">{{ isLoginMode ? 'Войти' : 'Зарегистрироваться' }}</button>
    </form>

    <button @click="toggleAuthMode" class="btn">{{ isLoginMode ? 'Перейти к регистрации' : 'Перейти к входу' }}</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      fullName: '',
      email: '',
      password: '',
      isLoginMode: true
    };
  },
  mounted() {
    this.checkToken();
  },
  methods: {
    async checkToken() {
      const token = localStorage.getItem('token');
      if (token) {
        this.$router.push('/me');
      }
    },
    async handleSubmit() {
      const authData = {
        email: this.email,
        password: this.password
      };

      if (!this.isLoginMode) {
        authData.full_name = this.fullName;
      }

      try {
        let response;
        if (this.isLoginMode) {
          response = await axios.post('http://localhost:8080/auth/login', authData);
        } else {
          response = await axios.post('http://localhost:8080/auth/signup', authData);
        }

        const token = response.data.jwt_token;
        localStorage.setItem('token', token);
        this.$router.push('/me');
      } catch (error) {
        console.error('Ошибка аутентификации:', error);
      }
    },
    toggleAuthMode() {
      this.isLoginMode = !this.isLoginMode;
      this.clearForm();
    },
    clearForm() {
      this.fullName = '';
      this.email = '';
      this.password = '';
    }
  }
};
</script>
<style scoped>
.auth-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button.btn {
  margin-top: 20px;
  display: block;
  width: 100%;
  padding: 10px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button.btn:hover {
  background-color: #0056b3;
}
</style>