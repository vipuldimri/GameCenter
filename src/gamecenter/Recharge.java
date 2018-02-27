package gamecenter;

import java.sql.Date;


/*
Class for Reacharges and all the transaction done .
Object of this class is used for storing transaction details into the database
*/
public class Recharge 
{
    
    private int ID;
    private String CardNo;
    private String EmpName;
    private int Amount;
    private java.sql.Timestamp Date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public java.sql.Timestamp getDate() {
        return Date;
    }

    public void setDate(java.sql.Timestamp Date) {
        this.Date = Date;
    }
    
        
}
