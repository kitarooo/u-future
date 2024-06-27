package backend.microservices;


import backend.microservices.exception.IncorrectCommandException;

import java.util.Scanner;

public class Main {
    private static final Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showCommands();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> searchBooks();
                case 4 -> getAllBooks();
                case 5 -> running = false;
                default -> throw new IncorrectCommandException("Команды введена неверно, повторите попытку!");
            }
        }
    }

    private static void showCommands() {
        System.out.println("Система для управления библиотекой!");
        System.out.println("Команда:" + "1. " + "Добавить книгу");
        System.out.println("Команда:" + "2. " + "Удалить книгу");
        System.out.println("Команда:" + "3. " + "Найти книгу");
        System.out.println("Команда:" + "4. " + "Вывести все книги");
        System.out.println("Команда:" + "5. " + "Выход");
        System.out.print("Команда: ");
    }

    private static void addBook() {
        System.out.print("Название: ");
        String title = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        System.out.print("Год издания: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, year, isbn);
        library.addBook(book);
        System.out.println("Книга успешно добавлена!");
    }

    private static void removeBook() {
        System.out.print("Укажите ISBN для удаления: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("Книга успешно удалена!");
    }

    private static void searchBooks() {
        System.out.println("Найти книгу");
        System.out.println("Команда: "+"1. По названию");
        System.out.println("Команда: "+"2. По автору");
        System.out.println("Команда: "+"1. По году издания");
        System.out.print("Команда: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.print("Название: ");
                String title = scanner.nextLine();
                library.searchByTitle(title).forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Автор: ");
                String author = scanner.nextLine();
                library.searchByAuthor(author).forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Год издания: ");
                int year = scanner.nextInt();
                library.searchByYear(year).forEach(System.out::println);
            }
            default -> throw new IncorrectCommandException("Команды введена неверно, повторите попытку!");
        }
    }

    private static void getAllBooks() {
        library.getAllBooks().forEach(System.out::println);
    }
}