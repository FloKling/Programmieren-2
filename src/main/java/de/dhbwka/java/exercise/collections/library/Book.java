package de.dhbwka.java.exercise.collections.library;

public class Book {
    private String title;
    private String author;
    private String year;
    private String publisher;

    public Book(String title, String author, String year, String publisher) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!getTitle().equals(book.getTitle())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getYear().equals(book.getYear())) return false;
        return getPublisher().equals(book.getPublisher());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getYear().hashCode();
        result = 31 * result + getPublisher().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\"" + this.title + "\"" + " von: " + this.author + " (" + this.year + ") \n");
        builder.append("Verlag: " + this.publisher + "\n");

        return builder.toString();
    }
}