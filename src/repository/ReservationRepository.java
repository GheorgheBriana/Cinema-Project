package repository;

import config.DBConnection;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private DBConnection dbConnection;

    public ReservationRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addReservation(Reservation reservation) {
        if (reservation.getReservationDate().isBefore(LocalDate.now())) {
            System.out.println("Data rezervării nu poate fi mai mică decât data curentă!");
            return;
        }

        if (reservation.getRoomNumber() < 1 || reservation.getRoomNumber() > 9) {
            System.out.println("Număr de sală invalid! Cinematograful are doar săli de la 1 la 9.");
            return;
        }

        int reservedSeats = getReservedSeatsForRoomAndDate(
                reservation.getRoomNumber(),
                reservation.getReservationDate()
        );

        if (reservedSeats + reservation.getNumberOfSeats() > 20) {
            System.out.println("Sală plină!");
            return;
        }

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
            System.out.println(e.getMessage());
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

    public int getReservedSeatsForRoomAndDate(int roomNumber, LocalDate reservationDate) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "SELECT SUM(number_of_seats) FROM reservations WHERE room_number = ? AND reservation_date = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomNumber);
            statement.setDate(2, java.sql.Date.valueOf(reservationDate));

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAvailableSeats(int roomNumber, LocalDate reservationDate) {
        int reservedSeats = getReservedSeatsForRoomAndDate(roomNumber, reservationDate);
        return 20 - reservedSeats;
    }

    public void deleteReservationById(int id) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "DELETE FROM reservations WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rezervarea a fost ștearsă cu succes!");
            } else {
                System.out.println("Nu există nicio rezervare cu acest id.");
            }

        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea rezervării.");
            System.out.println(e.getMessage());
        }
    }

}
