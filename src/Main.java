import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Book> books = new HashMap<>();
        System.out.println("All books: ");
        books.put("1", new Book("Math", Genres.SCIENCE, 10.0, LocalDate.of(2010, 1, 1)));
        books.put("2", new Book("History of Art", Genres.HISTORY, 15.0, LocalDate.of(2015, 6, 12)));
        books.put("3", new Book("Fantasy Land", Genres.FANTASY, 20.0, LocalDate.of(2020, 3, 25)));
        books.put("4", new Book("Biography of a Scientist", Genres.BIOGRAPHY, 12.5, LocalDate.of(2018, 8, 30)));
        books.put("5", new Book("Romantic Tales", Genres.ROMANCE, 18.0, LocalDate.of(2016, 2, 14)));

        books.forEach((key, book) -> System.out.println(key + ": " + book));

        System.out.println("searching... ");
        Search<Book> searchBook = (book, parameter) ->  //search implementations
                book.title.contains(parameter)
                || book.genre.toString().contains(parameter)
                || book.publicationDate.toString().contains(parameter)
                || ((Double) book.price).toString().contains(parameter);


        String paramatr="GENRE";

        for (Book book : books.values()) {
            if(searchBook.search(book, paramatr)){
                System.out.println(book);
            }
        }

        System.out.println("filtering...");
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

        System.out.println("adding taxes...");

        Consumer<Book> taxFunc = (book)->book.price*=1.1;// consumer implementations

        for (Book book : books.values()) {
            taxFunc.accept(book);
        }

        books.forEach((key, book) -> System.out.println(key + ": " + book));

        System.out.println("taking random book...");//supplier implementations
        Supplier<Book> takeRandomBook = ()->{
            Random rand = new Random();
            ArrayList<Book> bookList;
            bookList =(ArrayList<Book>) books.values();
            int random = rand.nextInt(bookList.size());
            return bookList.get(random);
        };

        System.out.println(takeRandomBook.get());


    }
}
