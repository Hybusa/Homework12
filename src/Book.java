public class Book {
    private final Author author;
    private int year;


    Book(Author author, int year){
        this.author = author;
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}