package repository;

import config.DBConnection;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Reservation> getReservationsByCustomerName(String customerName) {
        ArrayList<Reservation> reservationArrayList= new ArrayList<Reservation>();

        try {
            Connection connection = dbConnection.getConnection();
            String sql = "SELECT * FROM reservations WHERE customer_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);

            var resultSet = statement.executeQuery();

            // if there is a line underneath, read it. if not => stop
            while(resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("id"),
                        resultSet.getString("movie"),
                        resultSet.getInt("room_number"),
                        resultSet.getString("customer_name"),
                        resultSet.getInt("number_of_seats"),
                        resultSet.getDate("reservation_date").toLocalDate()
                );
                reservationArrayList.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservationArrayList;
    }
}
