import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

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
                        System.out.println("Ai ales: rezervare");
                        break;
                    case 2:
                        System.out.println("Ai ales: afisareRezervari");
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