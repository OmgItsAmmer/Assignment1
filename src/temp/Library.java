package temp;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.*;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Library {
//Encapsulation
private static boolean checker = true;
private static boolean userchecker = true;


//////////////////////////////////////////////
///////////////////METHODS////////////////////
//////////////////////////////////////////////






public static void AddBook(book obj,ArrayList<book> arr)
{   
 try
 { 
    RandomAccessFile file = new RandomAccessFile("book_data.txt", "rw");
    if(file.length()==0)
    {
        file.seek(0);

    }
    else{
        file.seek(file.length());
    }
    
    file.writeBytes(obj.bookID + "," +  obj.title + "," + obj.author + ","+obj.genre+","+obj.avalibilty_status+"\n");
     
    System.out.println("book saved successfully");
    file.close();
   
 }
 catch(Exception e) {
    System.out.println("you faced an issue while adding book " + e);
 }
 System.out.println("------------------------------------------------------------");
System.out.println("BOOK DETAIL :\n"+ "Book id: " +obj.bookID+"\n"+ "Book Title: "+obj.title  + "\n"+"Book Author: "+obj.author+"\n"+"Book genre: "+obj.genre+"\n"+"Book Avability status: "+obj.avalibilty_status);
System.out.println("-------------------------------------------------------------");


}


public static void AddUser(user obj,ArrayList<user> arr)
{   
    
   // arr.add(obj);
    try {
        RandomAccessFile userfile = new RandomAccessFile("user_data.txt", "rw");
        //userfile.seek(0);
        if(userfile.length()==0)
    {
        userfile.seek(0);

    }
    else{
        userfile.seek(userfile.length());
    }

        userfile.writeBytes(obj.userID + "," + obj.name + "," + obj.ContactInfo + "," + obj.borrowedBooks+"\n");
        userfile.close();
        //arr.add(obj);
    } catch (Exception e) {
        System.out.println("you faced an issue while adding user " + e);
    }

    
}


public static void CheckingOut(String title ,String user,ArrayList<book> bookarr,ArrayList<user> userarr)
{
    boolean flag4=true,flag5=true;

    for(book Bookvalue : bookarr)
            {   
                if(Bookvalue.title.equalsIgnoreCase(title) && Bookvalue.avalibilty_status.equals(true))
                for(user userValue : userarr)
                {   flag4=false;
                {
                    if(userValue.name.equalsIgnoreCase(user) )
                    {   userValue.borrowedBooks++;  
                        flag5=false;
                        System.out.println(user + " checked out " + title);
                        Bookvalue.avalibilty_status=false;
                        clearBookFile("book_data.txt");
                        UpdateBookFile(bookarr,"book_data.txt");
                        clearUserFile("user_data.txt");
                        UpdateUserFile(userarr,"user_data.txt");
                        System.out.println("New status: " + Bookvalue.avalibilty_status);
                        
                    }       
                }
                }

            }
            if(flag4 == true)
            {
                System.out.println("User doesnt found");
                System.out.println("--------------------------------------");
            }
            else if(flag5 == true)
            {
                System.out.println("Book doesnt found");
                System.out.println("--------------------------------------");
            }
        
}
   
    
public static void ReturningBook (String book, String  user,ArrayList<book> bookarr, ArrayList<user> userarr)
{   
    boolean flag6 = true , flag7 = true;
    for(user value2 : userarr)
    {
        if(value2.name.equalsIgnoreCase(user))
        {   value2.borrowedBooks--;
            flag6 = false;
            for(book value1:bookarr)
            {
                if(value1.avalibilty_status.equals(false))
                {   flag7 = false;
                    System.out.println(user + " returned out " + book);
                    value1.avalibilty_status=true;
                    System.out.println("New status: " + value1.avalibilty_status);
                    clearBookFile("book_data.txt");
                    UpdateBookFile(bookarr, "book_data.txt");
                    clearUserFile("user_data.txt");
                    UpdateUserFile(userarr,"user_data.txt");
                   
                }
            }
        }
      
    }
    if(flag6==true)
    {
        System.out.println("User doesnt found");
        System.out.println("--------------------------------------");
    }
    else if(flag7==true)
    {
        System.out.println("book doesnt found");
        System.out.println("--------------------------------------");
    }
}


public static void SearchBook(String Book_Or_Author,ArrayList<book> bookarr,JLabel id , JLabel title,JLabel author,JLabel genre,JLabel status)
{   boolean flag8=true;
    for(book bookvalue : bookarr)
    {
        if(bookvalue.title.equalsIgnoreCase(Book_Or_Author) || bookvalue.author.equalsIgnoreCase(Book_Or_Author) )
        {   flag8 = false;
            System.out.println("Book details: \n"+"BOOK ID:" + bookvalue.bookID+"\n"+"BOOK TITLE: "+bookvalue.title+"\n"+"BOOK VALUE: "+ bookvalue.author+"\n"+"BOOK GENRE: "+bookvalue.genre+"\n"+"BOOK AVABILITY STATUS: "+bookvalue.avalibilty_status);
            id.setText(String.valueOf(bookvalue.bookID));
            title.setText(bookvalue.title);
            author.setText(bookvalue.author);
            genre.setText(bookvalue.genre);
            status.setText(String.valueOf(bookvalue.avalibilty_status));
            
            
            System.out.println("--------------------------------------------------");
        }
    }
    if(flag8==true)
    {
        System.out.println("Book doesnt found");
        System.out.println("--------------------------------------");
    }
}


//////////////////LIBRARY HANDLING METHODS///////////////////////
public static void UserRestoringDatainArray(ArrayList<user> arr)
{
    try (Scanner scanner = new Scanner(new File("user_data.txt"))){
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
           
            user obj  = new user();
            obj.userID = Integer.parseInt(parts[0]);
            obj.name = parts[1];
            obj.ContactInfo = parts[2];
            obj.borrowedBooks = Integer.parseInt(parts[3]);
            arr.add(obj);
           
            
        }
    }
    catch(Exception e) {
       // System.out.println("you faced an issue while restoring users " + e);
    }
}

public static void BookRestoringDatainArray(ArrayList<book> arr)
{   
    int bookID;
    String title,author,genre;
    boolean availability = false;

    try (Scanner scanner = new Scanner(new File("book_data.txt"))){
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            
            bookID = Integer.parseInt(parts[0]);
            title = parts[1];
            author = parts[2];
            genre = parts[3];
            availability = Boolean.parseBoolean(parts[4]);
            book obj  = new book(bookID,title,author,genre,availability);
            arr.add(obj);
            
        }
    }
    catch(Exception e) {
        
    }
}
public static void UpdateBookFile(ArrayList<book> bookarr, String filename) {

    try {
        RandomAccessFile file = new RandomAccessFile("book_data.txt", "rw");
        //userfile.seek(0);
       
    
        file.seek(0);
        for (book valueBook : bookarr) {
            file.writeBytes(valueBook.bookID + "," + valueBook.title + "," +
                                  valueBook.author + "," + valueBook.genre + "," +
                                  valueBook.avalibilty_status + "\n");
        }
        file.close();
      //arr.add(obj);
    } catch (Exception e) {
        System.out.println("you faced an issue while adding user " + e);
    }
}

public static void clearBookFile(String fileName) {
    try {
        File file = new File(fileName);
        if (file.exists()) {
           // file.delete();
           // System.out.println("File deleted successfully.");
            if(file.delete())
            {
                if(file.createNewFile())
                {
                    System.out.println("new file created");
                    FileWriter writer = new FileWriter(fileName);
                    writer.write(0+","+"null"+","+"null"+","+"null" + false);
                    
                }
            }
        } else {
            System.out.println("File does not exist.");
        }
    } catch (Exception e) {
        System.out.println("Error deleting file: " + e.getMessage());
    }
}


public static void clearUserFile(String fileName) {
    try {
        File file = new File(fileName);
        if (file.exists()) {
           // file.delete();
           // System.out.println("File deleted successfully.");
            if(file.delete())
            {
                if(file.createNewFile())
                {
                    System.out.println("new file created");
                    FileWriter writer = new FileWriter(fileName);
                    writer.write(0+","+"null"+","+"null"+","+0);

                }
            }
        } else {
            System.out.println("File does not exist.");
        }
    } catch (Exception e) {
        System.out.println("Error deleting file: " + e.getMessage());
    }
}

public static void UpdateUserFile(ArrayList<user> userarr, String filename) {



try {
    RandomAccessFile userfile = new RandomAccessFile("user_data.txt", "rw");
    
    userfile.seek(0);
    for (user valueUser : userarr) {
        userfile.writeBytes(valueUser.userID + "," + valueUser.name + "," +
        valueUser.ContactInfo + "," + valueUser.borrowedBooks + "\n");
        }
        userfile.close();
    


}
catch (Exception e) {
    System.out.println("you faced an issue while adding user " + e);
    }
}


 public static void displayLibrary(ArrayList<book> Book_array,ArrayList<user> User_array)
 {
    ///lOCAL VARIABLES///
    String title , author, genre,username,contactInfo_,input="0";
    String book_id,user_id;
    
    Scanner sc = new Scanner(System.in);
    
    boolean checker = true;
    while(checker)
    {
    System.out.println("|1.Add the Book");
    
    System.out.println("|2.Add the User");
    
    System.out.println("|3.Checking out");

    System.out.println("|4.Returning book");
    
    System.out.println("|5.Search book details");

    System.out.println("|6.Exit");
    
    
    System.out.print("=> ");
    input = sc.nextLine();
    try {
        int temp;
        temp = Integer.parseInt(input);

        if(temp<7&&temp>0)
        {

            checker =true;

        switch (Integer.parseInt(input)) {
        case 1:
        
        System.out.println("Enter your Book ID:");
        book_id = sc.nextLine();
        try {
            int flag2;
            flag2 = Integer.parseInt(book_id);

        } catch (Exception e) {
            System.out.println("Invalid book id");
            System.out.println("--------------------------------------");
        }

        System.out.println("Enter your Book title:");
        title = sc.nextLine();

        System.out.println("Enter book author name:");
        author = sc.nextLine();

        System.out.println("Enter book genre:");
        genre = sc.nextLine();
      
            
            if(!genre.matches("[a-zA-Z]+"))
            {
                System.out.println("invalid input!");
                System.out.println("--------------------------------------");
            }
            else {
                book bk = new book(Integer.parseInt(book_id),title,author,genre,true);
                Library.AddBook(bk,Book_array);
            }
            
            break;


        case 2:
        System.out.println("Enter User ID:");
        user_id = sc.nextLine();
        try {
            int flag3;
            flag3 = Integer.parseInt(user_id);

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Enter User name:");
        username = sc.nextLine();

        System.out.println("Enter User Contact info:");
        contactInfo_ = sc.nextLine(); //Contact Info 

        user us = new user(Integer.parseInt(user_id),username,contactInfo_,0);
        Library.AddUser(us, User_array);
        break;


        case 3:
        Library.BookRestoringDatainArray(Book_array);
        Library.UserRestoringDatainArray(User_array);
        System.out.println("Enter your Book title:");
        title = sc.nextLine();

        System.out.println("Enter User name:");
        username = sc.nextLine();

        Library.CheckingOut(title, username, Book_array, User_array);
        break;

        case 4:
        Library.BookRestoringDatainArray(Book_array);
        Library.UserRestoringDatainArray(User_array);

        System.out.println("Enter your Book title:");
        title = sc.nextLine();

        System.out.println("Enter User name:");
        username = sc.nextLine();

        Library.ReturningBook(title, username, Book_array, User_array);
        
        break;

        case 5:

        Library.BookRestoringDatainArray(Book_array);
        Library.UserRestoringDatainArray(User_array);
        System.out.println("Enter Book name: ");
        title = sc.nextLine();
        //Library.SearchBook(title,Book_array);
        break;
        
         case 6:
         System.exit(0);
    }
    }
    else {
        System.out.println("invalid input");
        System.out.println("--------------------------------------");
    }

    } 
    
    catch (Exception e) {
        System.out.println("Invalid Input");
        System.out.println("--------------------------------------");
    }
   
    
    }
 }

}
