package codealpha;



import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean available;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.available = true;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String category;

    Booking(String customerName, int roomNumber, String category) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
    }
}

public class codealpha_task2Room {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        initializeRooms();

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    saveBookings();
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void initializeRooms() {

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));

        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));

        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));
    }

    static void searchRooms() {

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {
            if (room.available) {
                System.out.println(
                        "Room No: " + room.roomNumber +
                        " | Category: " + room.category);
            }
        }
    }

    static void bookRoom() {

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo && room.available) {

                System.out.println("Payment Successful!");

                room.available = false;

                bookings.add(
                        new Booking(name,
                                room.roomNumber,
                                room.category));

                System.out.println("Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelBooking() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {

            Booking booking = iterator.next();

            if (booking.roomNumber == roomNo) {

                iterator.remove();

                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.available = true;
                    }
                }

                System.out.println("Booking Cancelled!");
                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No Bookings Available");
            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    "\nCustomer : " + booking.customerName +
                    "\nRoom No  : " + booking.roomNumber +
                    "\nCategory : " + booking.category);
        }
    }

    static void saveBookings() {

        try {

            FileWriter writer =
                    new FileWriter("bookings.txt");

            for (Booking booking : bookings) {

                writer.write(
                        booking.customerName + "," +
                        booking.roomNumber + "," +
                        booking.category + "\n");
            }

            writer.close();

            System.out.println("Bookings Saved!");
        }

        catch (IOException e) {
            System.out.println("Error Saving File");
        }
    }
}