/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author u20o90
 */
public class OtherTrasactions 
{
    private int ID;
    private String CustomerName;
    private java.sql.Date ExpireDate;
    private String CardNo;
    private String Method;
    private String Money;
    private java.sql.Timestamp Date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }



 
    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String Method) {
        this.Method = Method;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public OtherTrasactions(int ID, String CustomerName, java.sql.Date ExpireDate, String CardNo, String Method, String Money, Timestamp Date) {
        this.ID = ID;
        this.CustomerName = CustomerName;
        this.ExpireDate = ExpireDate;
        this.CardNo = CardNo;
        this.Method = Method;
        this.Money = Money;
        this.Date = Date;
    }

    public Date getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(Date ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public OtherTrasactions()
    {
        
    }
    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

 
    
}
