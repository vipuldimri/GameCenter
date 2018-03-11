/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

/**
 *
 * @author vipul
 */
import javax.swing.JTable;
import javax.swing.JTextField;
import Gui.LoginScreen;
import java.util.ArrayList;
import java.awt.List;
public class BackgroundAutocomplete extends Thread
{
    JTextField textfield;
    java.awt.List list_empsearch;
    
    public BackgroundAutocomplete(JTextField textField,java.awt.List list_empsearch) 
    {
    this.list_empsearch = list_empsearch;
    this.textfield = textField;
    }
    
    
    @Override
    public void run()
    {
        String nametobesearch = textfield.getText();
        list_empsearch.removeAll();
        ArrayList<String> names=new ArrayList<>();
        LoginScreen.trienames.AutoComplete(nametobesearch,names);
        try{     
            names.forEach((item) -> 
            {
                list_empsearch.add(item);
            });
            
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            
        }
        
    }
    
}
