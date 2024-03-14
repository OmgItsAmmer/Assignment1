package temp;

public class book {
int bookID;
String title;
String author;
String genre;
Boolean avalibilty_status;

public book(int bookID ,String title_ , String author_ , String genre_, Boolean avalibilty_status_ )
{
    this.setBook(bookID, title_, author_, genre_, avalibilty_status_);
}

public book()
{
   
}
/////////SETTER & GETTER /////////////////
private void setBook(int bookID ,String title_ , String author_ , String genre_, Boolean avalibilty_status_ )
{
    this.bookID = bookID;
    this.title = title_;
    this.author = author_;
    this.genre = genre_;
    this.avalibilty_status = avalibilty_status_;
}
}

