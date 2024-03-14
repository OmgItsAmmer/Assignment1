


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package temp;
import java.util.ArrayList;
/**
 *
 * @author ammer
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           ArrayList <book> Book_array = new ArrayList<>();
           ArrayList <user> User_array = new ArrayList<>();
           HomeForm form1 = new HomeForm(User_array,Book_array);
           form1.setVisible(true);
           
           
    }
    
}
