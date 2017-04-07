
import java.sql.Connection;
import java.sql.DriverManager;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author perky
 */
public class Connect {
    
   public static void main(String[] arg) {
    
    Connection conn = null;
        try{
        
         
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","system");
        if(conn!= null){
        System.out.println("Connection Successful");
        }
        }catch(Exception e)
                {
                System.out.println("Unsuccesful");
   
}
    
}
}
