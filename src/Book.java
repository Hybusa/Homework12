public class Book {
    private final Author author;
    private final String bookName;
    private int year;


    Book(Author author, int year, String bookName){
        this.author = author;
        this.year = year;
        this.bookName = bookName;
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

    public String getBookName() {
        return bookName;
    }
}