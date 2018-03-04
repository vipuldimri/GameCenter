
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
   private int    Basket_Ball;  
   private int    Speed_Ball ;
   private int    Air_Hockey   ;
   private int    Dance    ;
   private int    Catch_Light ;

    public Stall(int ID, String Name, String OwnerName, String Address, String Contact, String Password, Date SubStartDate, Date SubEndDate, int Basket_Ball, int Speed_Ball, int Air_Hockey, int Dance, int Catch_Light) {
        this.ID = ID;
        this.Name = Name;
        this.OwnerName = OwnerName;
        this.Address = Address;
        this.Contact = Contact;
        this.Password = Password;
        this.SubStartDate = SubStartDate;
        this.SubEndDate = SubEndDate;
        this.Basket_Ball = Basket_Ball;
        this.Speed_Ball = Speed_Ball;
        this.Air_Hockey = Air_Hockey;
        this.Dance = Dance;
        this.Catch_Light = Catch_Light;
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

    public int getSpeed_Ball() {
        return Speed_Ball;
    }

    public void setSpeed_Ball(int Speed_Ball) {
        this.Speed_Ball = Speed_Ball;
    }

    public int getCatch_Light() {
        return Catch_Light;
    }

    public void setCatch_Light(int Catch_Light) {
        this.Catch_Light = Catch_Light;
    }

    public int getDance() {
        return Dance;
    }

    public void setDance(int Dance) {
        this.Dance = Dance;
    }

    public int getAir_Hockey() {
        return Air_Hockey;
    }

    public void setAir_Hockey(int Air_Hockey) {
        this.Air_Hockey = Air_Hockey;
    }

    public int getBasket_Ball() {
        return Basket_Ball;
    }

    public void setBasket_Ball(int Basket_Ball) {
        this.Basket_Ball = Basket_Ball;
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
