import java.io.Serializable;
//-------------------------------------------------------
//Assignment {3}
//Written by: {Shanvin Luo Student ID:40248485 } and {Atai Askarov Student ID:40248327}
//For COMP 249 Section {Section QQ} - Winter 2023
//--------------------------------------------------------

/**
 * Written by: {Shanvin Luo Student ID:40248485 } and {Atai Askarov Student ID:40248327}
 * For COMP 249 Section {Section QQ} - Winter 2023 Assignment {2}
 * Due 11:59 PM – Monday, March 27, 2023
 */


public class Book implements Serializable {

    private String title; // title of the entry
    private String author; // the author of the entry's object
    private double price; //  price of the entry
    private String ISBN; // ID of the entry
    private String genre; // genre of the entre
    private int year; // year when it got published

    /**
     * default constructor
     */
    public Book() {

    }

    /**
     *
     * @param title the titile of entry
     * @param author the author of the entry
     * @param price the price of the entry's object
     * @param ISBN the ID code
     * @param genre the genre of the entry
     * @param year the year the entry was published
     */
    public Book(String title, String author, double price, String ISBN, String genre, int year){
        this.title=title;
        this.author=author;
        this.price=price;
        this.ISBN=ISBN;
        this.genre=genre;
        this.year=year;
    }

    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return String
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     *
     * @param ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * return String
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @param book
     * @return boolean
     */
    public Boolean equals(Book book){
        if (book==null||book.getClass()!=this.getClass()){
            return false;
        }
        else {
            Book book2=(Book) book;
            return (this.year==book2.year&&this.genre.equals(book2.genre)&&this.title.equals(book2.title)&&this.price==book2.price&&this.ISBN.equals(book2.ISBN)&&this.author.equals(book2.author));
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", ISBN='" + ISBN + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
