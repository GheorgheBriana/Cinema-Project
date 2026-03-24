import config.DBConnection;
import model.Reservation;
import repository.ReservationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        DBConnection dbConnection = new DBConnection();
        ReservationRepository reservationRepository = new ReservationRepository(dbConnection);

        try {
            Connection connection = dbConnection.getConnection();
            System.out.println("Database connected successfully! " + connection);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Write your username: ");
        String username = read.nextLine();

        System.out.println("Write your password: ");
        String password = read.nextLine();

        //Username has to be: personalCinematograf
        //Password has to be: Cinem@123
        if(!username.equals("personalCinematograf") || !password.equals("Cinem@123")) {
            System.out.println("Username sau parolă greșită!");
        } else {
            System.out.println("Logare reușită!");

            boolean running = true;
            while (running) {
                System.out.println("1. rezervare");
                System.out.println("2. afisareRezervari");
                System.out.println("3. verificareCapacitate");
                System.out.println("4. stergeRezervare");
                System.out.println("5. salvareTxt");
                System.out.println("0. iesire");

                System.out.println("Alege o optiune: ");
                int option = read.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Ai ales: adaugaRezervare");
                        read.nextLine();

                        System.out.println("Scrie numele filmului: ");
                        String movie = read.nextLine();

                        System.out.println("Alege numărul sălii: ");
                        int roomNumber = read.nextInt();
                        read.nextLine();

                        System.out.println("Scrie numele clientului: ");
                        String customerName = read.nextLine();

                        System.out.println("Scrie numărul de locuri pe care dorești să-l rezervi.");
                        int numberOfSeats = read.nextInt();
                        read.nextLine();

                        System.out.println("Scrie data rezervării (aaaa-ll-zz): ");
                        String dateText = read.nextLine();
                        LocalDate reservationDate = LocalDate.parse(dateText);

                        // create object + call addReservation method
                        Reservation reservation = new Reservation(movie, roomNumber, customerName, numberOfSeats, reservationDate);
                        reservationRepository.addReservation(reservation);
                        break;

                    case 2:
                        System.out.println("Ai ales: afisareRezervari");
                        read.nextLine();

                        System.out.println("Scrie numele clientului: ");
                        String searchedName = read.nextLine();

                        List<Reservation> reservationList = reservationRepository.getReservationsByCustomerName(searchedName);
                        for(Reservation reservationItem : reservationList) {
                            System.out.println(reservationItem);
                        }
                        break;
                    case 3:
                        System.out.println("Ai ales: verificareCapacitate");
                        break;
                    case 4:
                        System.out.println("Ai ales: stergeRezervare");
                        break;
                    case 5:
                        System.out.println("Ai ales: salvareText");
                        break;
                    case 0:
                        System.out.println("Aplicația se oprește!");
                        running = false;
                        break;
                    default:
                        System.out.println("Opțiune invalidă");
                }
            }
        }
    }
}