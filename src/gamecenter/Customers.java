/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

/**
 *
 * @author u20o90
 */
public class Customers 
{
    
    private int Id;
    private String Name;
    private String Contact;
    private String EmailId;
    
    public Customers()
    {
        
    }

    public Customers(int Id, String Name, String Contact, String EmailId) {
        this.Id = Id;
        this.Name = Name;
        this.Contact = Contact;
        this.EmailId = EmailId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }
    
    
    
}
