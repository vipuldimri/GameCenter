
package gamecenter;

import java.sql.Date;

/*
This class Represent An instance of GameeZone(Stall)
*/
public class Stall 
{
    
   private int ID;
   private String Name; 
   private String OwnerName; 
   private String Address; 
   private String Contact; 
   private String Password; 
   private Date   SubStartDate;
   private Date   SubEndDate;
   private String Email;
   private int Max_Employee;
   private int EmailTime;


    public Stall(int ID, String Name, String OwnerName, String Address, String Contact, String Password, Date SubStartDate, Date SubEndDate,String Email,int Max_Employee,int EmailTime)
    {
        this.ID = ID;
        this.Name = Name;
        this.OwnerName = OwnerName;
        this.Address = Address;
        this.Contact = Contact;
        this.Password = Password;
        this.SubStartDate = SubStartDate;
        this.SubEndDate = SubEndDate;
        this.Email = Email;       
        this.Max_Employee = Max_Employee;
        this.EmailTime = EmailTime;
        
    }

    public int getEmailTime() {
        return EmailTime;
    }

    public void setEmailTime(int EmailTime) {
        this.EmailTime = EmailTime;
    }

    public int getMax_Employee() {
        return Max_Employee;
    }

    public void setMax_Employee(int Max_Employee) {
        this.Max_Employee = Max_Employee;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getOwnerName() {
        return OwnerName;
    }


    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    public String getPassword() {
        return Password;
    }

    public Date getSubStartDate() {
        return SubStartDate;
    }

    public Date getSubEndDate() {
        return SubEndDate;
    }
    
    
    public String  toString()
    {
        
        return getID()+getName()+getOwnerName()+getAddress()+getContact()+getPassword()+getSubStartDate()+getSubEndDate();
    }

    public Stall() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setSubStartDate(Date SubStartDate) {
        this.SubStartDate = SubStartDate;
    }

    public void setSubEndDate(Date SubEndDate) {
        this.SubEndDate = SubEndDate;
    }
    
    
    
    
}
