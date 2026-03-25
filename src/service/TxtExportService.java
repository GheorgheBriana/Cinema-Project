package service;

import model.Reservation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TxtExportService {

    public void saveReservationsToTxt(String customerName, List<Reservation> reservations) {
        DateTimeFormatter headerFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String saveTime = LocalDateTime.now().format(headerFormatter);

        try (FileWriter writer = new FileWriter("reservations.txt")) {
            writer.write("Salvare: " + saveTime + "\n\n");

            for (Reservation reservation : reservations) {
                writer.write("Nume: " + reservation.getCustomerName() + "\n");
                writer.write("Data rezervare: " + reservation.getReservationDate() + "\n");
                writer.write("Film: " + reservation.getMovie() + "\n");
                writer.write("Sala: " + reservation.getRoomNumber() + "\n");
                writer.write("Locuri rezervate: " + reservation.getNumberOfSeats() + "\n");
                writer.write("\n");
            }

            System.out.println("Rezervările au fost salvate în reservations.txt");

        } catch (IOException e) {
            System.out.println("Eroare la salvarea în fișier txt.");
            System.out.println(e.getMessage());
        }
    }
}