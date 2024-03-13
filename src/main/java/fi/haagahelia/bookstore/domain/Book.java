package fi.haagahelia.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private int publicationYear;
    private long isbn;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Book() {
    }

    public Book(String title, String author, int year, long isbn) {
        super();
        this.title = title;
        this.author = author;
        this.publicationYear = year;
        this.isbn = isbn;
    }

    public Book(String title, String author, int publicationYear, long isbn, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return publicationYear;
    }

    public long getIsbn() {
        return isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.publicationYear = year;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (this.category != null)
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + publicationYear
                    + ", isbn=" + isbn + ", category=" + this.getCategory() + "]";
        else
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + publicationYear + ", isbn"
                    + isbn + "]";
    }
}
