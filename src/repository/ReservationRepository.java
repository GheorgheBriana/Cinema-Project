package repository;

import config.DBConnection;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationRepository {
    private DBConnection dbConnection;

    public ReservationRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addReservation(Reservation reservation) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "INSERT INTO reservations (movie, room_number, customer_name, number_of_seats, reservation_date) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reservation.getMovie());
            statement.setInt(2, reservation.getRoomNumber());
            statement.setString(3, reservation.getCustomerName());
            statement.setInt(4, reservation.getNumberOfSeats());
            statement.setDate(5, java.sql.Date.valueOf(reservation.getReservationDate()));

            statement.executeUpdate();
            System.out.println("Rezervare adăugată cu succes!");
        } catch (SQLException e) {
            System.out.println("Eroare la adăugarea rezervării.");
        }

    }
}
