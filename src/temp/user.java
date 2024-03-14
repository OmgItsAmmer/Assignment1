package temp;


public class user {
    // cant make them private because of extensive file handling
    int userID;
    String name;
    String ContactInfo;
    int borrowedBooks=0;

    public user(int userId_,String name_,String ContactInfo_,int borrowedBooks_)
    {
       this.setBook(userId_, name_, ContactInfo_, borrowedBooks_);
    }
    public user()
    {
       
    }
    /////////SETTER & GETTER /////////////////
    private void setBook(int userId_,String name_,String ContactInfo_,int borrowedBooks_)
    {
        this.userID = userId_;
        this.name = name_;
        this.ContactInfo = ContactInfo_;
        this.borrowedBooks = borrowedBooks_;
    }
    
}

