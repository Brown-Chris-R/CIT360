/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.gameOfLife.control;

import byui.cit260.gameOfLife.exceptions.RepentanceControlException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RMLOY_000
 */
public class RepentanceControlTest {
    
    public RepentanceControlTest() {
    }

    /**
     * Test of repentance method, of class RepentanceControl.
     */
    @Test
    public void testRepentance() {
        //Test Case 1
        System.out.println("Test Case 1");
        double faith = 5.0;
        double sorrowForSin = 5.0;
        double confession = 5.0;
        double abandonmentOfSin = 5.0;
        double restitution = 5.0;
        double righteousLiving = 5.0;
        RepentanceControl instance = new RepentanceControl();
        double result;
        double expResult =30.0;
        try {
            result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving);
            assertEquals(expResult, result, 0.0);
        }
        catch (Exception e){
            fail(e.getMessage());
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
          //Test Case 2  
          System.out.println("Test Case 2");  
           faith = 0.0;  
           sorrowForSin = 5.0;  
           confession = 5.0;  
           abandonmentOfSin = 5.0;  
          restitution = 5.0;  
           righteousLiving = 5.0;  
            expResult = -1.0;
        try {
           result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving );
           fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }
      //Test Case 3
         System.out.println("Test Case 3");  
           faith = 5.0;  
           sorrowForSin = 0.0;  
           confession = 5.0;  
         abandonmentOfSin = 5.0;  
           restitution = 5.0;  
           righteousLiving = 5.0;  
             
   
           expResult = -1.0;
        try {
            result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving );
            fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }
     //Test Case 4
         System.out.println("Test Case 4");  
          faith = 5.0;  
           sorrowForSin = 5.0;  
           confession = 0.0;  
          abandonmentOfSin = 5.0;  
          restitution = 5.0;  
         righteousLiving = 5.0;  
           
           expResult = -1.0;
        try {
           result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving );
            fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }
      //Test Case 5
          System.out.println("Test Case 5");  
          faith = 5.0;  
          sorrowForSin = 5.0;  
          confession = 5.0;  
         abandonmentOfSin = 0.0;  
          restitution = 5.0;  
           righteousLiving = 5.0;  
            
   
           expResult = -1.0;
        try {
          result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving);
            fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }
     //Test Case 6
        System.out.println("Test Case 6");  
           faith = 5.0;  
           sorrowForSin = 5.0;  
          confession = 5.0;  
          abandonmentOfSin = 5.0;  
          restitution = 0.0;  
           righteousLiving = 5.0;  
            
   
          expResult = -1.0;
        try {
          result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving);
            fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }

      //Test Case 7  
          System.out.println("Test Case 7");  
           faith = 5.0;  
          sorrowForSin = 5.0;  
           confession = 5.0;  
           abandonmentOfSin = 5.0;  
          restitution = 5.0;  
           righteousLiving = 9.0;  
            
             expResult = -1.0;
        try {
           result = instance.repentance(faith, sorrowForSin, confession, abandonmentOfSin, restitution, righteousLiving);
            fail();
        }
        catch (RepentanceControlException e){
            assertEquals("Invalid input because input is range between 1 to 5", e.getMessage());
        }
    }
}
    
