package model;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private String movie;
    private int roomNumber;
    private String customerName;
    private int numberOfSeats;
    private LocalDate reservationDate;

    public Reservation() {
    }

    public Reservation(int id, String movie, int roomNumber, String customerName, int numberOfSeats, LocalDate reservationDate) {
        this.id = id;
        this.movie = movie;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.numberOfSeats = numberOfSeats;
        this.reservationDate = reservationDate;
    }

    public Reservation(String movie, int roomNumber, String customerName, int numberOfSeats, LocalDate reservationDate) {
        this.movie = movie;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.numberOfSeats = numberOfSeats;
        this.reservationDate = reservationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", roomNumber=" + roomNumber +
                ", customerName='" + customerName + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", reservationDate=" + reservationDate +
                '}';
    }
}