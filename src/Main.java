import kuzLib.kuz;

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author("Станислав", "Лем");
        Author author2 = new Author("Стугатский", "Аркадий");
        Book book1 = new Book(author1, kuz.getRandomIntInRange(1955, 2005));
        Book book2 = new Book(author2, kuz.getRandomIntInRange(1964, 1986));

        System.out.println(book2.getYear());

        book2.setYear(kuz.getRandomIntInRange(1964, 1986));
        printBookInfo(book1);
        printBookInfo(book2);
    }

    public static void printBookInfo(Book book)
    {
        System.out.println(book.getAuthor().getFistName() + " " + book.getAuthor().getLastName());
        System.out.println(book.getYear() + "г.");
    }
}