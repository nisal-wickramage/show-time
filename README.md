# show-time
This repo host vue frontend and spring boot backend for imaginary show time movie ticket booking web site.

## Features
* View now showing movies
* View up coming movies
* View show times
* Allow select seat for show time
* Allow book seat for show time
* See already booked seats for show time

## Deployment Architecture

## API

REST endpoints

* `/movies` 
    * `/movies?nowshowing=true` GET
    * `/movies?nowshowing=false` GET

* `/movies/{movie-id}/showtimes` GET
* `/movies/{movie-id}/showtimes/{showtime-id}/seats` GET
* `/movies/{movie-id}/showtimes/{showtime-id}/reservations` POST
* `/movies/{movie-id}/showtimes/{showtime-id}/reservations/{reservation-id}` POST

