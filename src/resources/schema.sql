    CREATE DATABASE IF NOT EXISTS cinemaDB;
    USE cinemaDB;
    CREATE TABLE IF NOT EXISTS reservations (
        id INT PRIMARY KEY AUTO_INCREMENT,
        movie VARCHAR(100) NOT NULL,
        room_number INT NOT NULL,
        customer_name VARCHAR(100) NOT NULL,
        number_of_seat INT NOT NULL,
        reservation_date DATE NOT NULL
    );