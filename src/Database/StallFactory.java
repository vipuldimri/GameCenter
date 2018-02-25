/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author vipul
 */
public class StallFactory
{
    
      public static StallInterface getInstance()
    {
        return new StallImplements();
    }
    
}
