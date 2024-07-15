import java.time.LocalDate;

public class Book {
    String title;
    Genres genre;
    double price;
    LocalDate publicationDate;
    public Book(String title, Genres genre, double price, LocalDate publicationDate) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publicationDate = publicationDate;
    }
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    public String toString(){
        return "Title: " + title + ", Genre: " + genre + ", Price: " + price+ ", Publication Date: " + publicationDate;
    }
}
