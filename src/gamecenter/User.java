
package gamecenter;


public class User 
{
   private int ID;
   private String Name;
   private String Address;
   private String Contact;
   private String Email;
   private String  Type;
   private int GameZoneID;
   private String Password;

    public User(int ID, String Name, String Address, String Contact, String Email, String Type, int GameZoneID, String Password) {
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.Contact = Contact;
        this.Email = Email;
        this.Type = Type;
        this.GameZoneID = GameZoneID;
        this.Password = Password;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getType() {
        return Type;
    }

    public int getGameZoneID() {
        return GameZoneID;
    }

    public String getPassword() {
        return Password;
    }

    
   @Override
    public String toString()
    {
        return getID()+getName()+getAddress()+getContact()+getEmail()+getType()+getGameZoneID()+getPassword();
    }


    
}
