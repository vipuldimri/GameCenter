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
public class Games {
    private int ID;
    private String  GameName;
    private String Amount;

    public Games(int ID, String GameName, String Amount) {
        this.ID = ID;
        this.GameName = GameName;
        this.Amount = Amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String GameName) {
        this.GameName = GameName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }
    
}
