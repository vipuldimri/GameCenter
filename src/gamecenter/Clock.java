/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import java.util.Date;
import java.util.TimerTask;
import javax.swing.JLabel;


/**
 *
 * @author vipul
 */
public class Clock extends TimerTask
{
        JLabel clocklabel;
	int c=0;
	public Clock(JLabel clocklabel)
	{
	this.clocklabel=clocklabel;	
	}
        @Override
	public void run()
	{
		
	        Date date=new Date();
		String dip=date.toString();
		clocklabel.setText(dip);
		
		c++;
		
	}
}
