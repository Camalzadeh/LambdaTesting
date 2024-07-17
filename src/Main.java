import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Book> books = new HashMap<>();
        books.put("1", new Book("Math", Genres.SCIENCE, 10.0, LocalDate.of(2010, 1, 1)));
        books.put("2", new Book("History of Art", Genres.HISTORY, 15.0, LocalDate.of(2015, 6, 12)));
        books.put("3", new Book("Fantasy Land", Genres.FANTASY, 20.0, LocalDate.of(2020, 3, 25)));
        books.put("4", new Book("Biography of a Scientist", Genres.BIOGRAPHY, 12.5, LocalDate.of(2018, 8, 30)));
        books.put("5", new Book("Romantic Tales", Genres.ROMANCE, 18.0, LocalDate.of(2016, 2, 14)));

        books.forEach((key, book) -> System.out.println(key + ": " + book));

        Filter<Book, LocalDate> filterTime = (book,time) -> book.publicationDate.isAfter(time);//filter implementations
        Filter<Book, Genres> filterGenre = (book, genre) -> genre.equals(book.genre);
        Filter<Book, Double> filterValue = (book, value) -> book.price<=value;

        LocalDate time=LocalDate.of(2016, 1, 1);
        System.out.println("After this date : " + time);
        int counter=1;
        for(Book book: books.values()) {
            if(filterTime.filter(book, time)){
                System.out.println(counter+"." + book);
                counter++;
            }
        }

        Genres genre=Genres.SCIENCE;
        System.out.println("Books with this genre : " + genre);
        counter =1;
        for(Book book: books.values()) {
            if(filterGenre.filter(book, genre)){
                System.out.println(counter+"." + book);
                counter++;
            }
        }

        double value=15;
        System.out.println("Cheaper than : " + value);
        counter =1;
        for(Book book: books.values()) {
            if(filterValue.filter(book, value)){
                System.out.println(counter+"." + book);
                counter++;
            }
        }

    }
}
