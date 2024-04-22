import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import {createMemoryHistory, createRouter} from "vue-router";
import TourSearch from "@/components/tour/TourSearch.vue";
import FlightSearch from "@/components/flights/FlightSearch.vue";
import HotelSearch from "@/components/hotel/HotelSearch.vue";
import TourPlaceExplorer from "@/components/tour_place/TourPlaceExplorer.vue";
import AuthComponent from "@/components/user/AuthComponent.vue";
import UserInfo from "@/components/user/UserInfo.vue";

const routes = [
    {path: '/', component: TourSearch},
    {path: '/flight', component: FlightSearch},
    {path: '/hotel', component: HotelSearch},
    {path: '/tour-place', component: TourPlaceExplorer},
    {path: '/account', component: AuthComponent},
    {path: '/me', component: UserInfo},
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

const app = createApp(App)
app.use(router)
app.mount('#app')
