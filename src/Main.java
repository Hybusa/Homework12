import kuzLib.kuz;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Author author1 = new Author("Stanisław", "Lem");
        Author author2 = new Author("Strugatsky", "Arkady");

        Book book1 = new Book(author1, getPublishYearByBookName(getTopBookNameByAuthor(author1)),
                getTopBookNameByAuthor(author1));
        Book book2 = new Book(author2, getPublishYearByBookName(getTopBookNameByAuthor(author2)),
                getTopBookNameByAuthor(author2));

        System.out.println(book2.getYear());

        book2.setYear(getPublishYearByBookName(getTopBookNameByAuthor(author2)));
        printBookInfo(book1);
        printBookInfo(book2);
    }

    public static void printBookInfo(Book book) {
        System.out.println(book.getAuthor().getFistName() + " " + book.getAuthor().getLastName());
        System.out.println(book.getBookName());
        System.out.println(book.getYear() + "г.");
        System.out.println();
    }

    public static String getTopBookNameByAuthor(Author author) throws URISyntaxException, RuntimeException {

        URI uri = new URI("https://openlibrary.org/search/authors.json?q="
                + author.getLastName() + "+" + author.getFistName());

        JSONObject answer = formAndSendHTTPRequest(uri);
        JSONArray docs = answer.getJSONArray("docs");
        JSONObject first = docs.getJSONObject(0);

        return (String) first.get("top_work");
    }

    public static int getPublishYearByBookName(String bookName) throws URISyntaxException {
        String uriBookName = bookName.replace(" ", "+");
        URI uri = new URI("https://openlibrary.org/search.json?title="
                + uriBookName);

        JSONObject answer = formAndSendHTTPRequest(uri);
        JSONArray docs = answer.getJSONArray("docs");
        JSONObject first = docs.getJSONObject(0);
        JSONArray arrayPublishYear = first.getJSONArray("publish_year");

        return arrayPublishYear.getInt(kuz.getRandomIntInRange(0, arrayPublishYear.length()));
    }

    private static JSONObject formAndSendHTTPRequest(URI uri) {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri).GET().build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse;
        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException timeOut) {
            throw new RuntimeException(timeOut);
        }
        return new JSONObject(getResponse.body());
    }
}

