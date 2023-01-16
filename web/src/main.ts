import { createApp } from "vue";
import * as VueRouter from "vue-router";
import App from "./App.vue"
import TicketReservation from "@/reservation/TicketReservation.vue"
import MovieCarousel from "@/movie/MovieCarousel.vue";

import "./assets/main.css";
import PaymentInformation from "@/reservation/PaymentInformation.vue";
import ReservationConfirmation from "@/reservation/ReservationConfirmation.vue";

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
    components,
    directives,
})

const routes = [
    { path: '/', component: MovieCarousel },
    { path: '/reserve/:movieId', component: TicketReservation },
    { path: '/reserve/payment', component: PaymentInformation },
    { path: '/reserve/confirmation', component: ReservationConfirmation },
];
const router = VueRouter.createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: VueRouter.createWebHashHistory(),
    routes, // short for `routes: routes`
})

const app = createApp(App);
app.use(router)
app.use(vuetify)
app.mount("#app");
