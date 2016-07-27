//Represents parameters for banana
import java.awt.Image;

import javax.swing.ImageIcon;

public class Banana {
	int banana_x;
	int banana_y;
	int timerBanana=5;
	Image ban;
	 
	public Banana(){
		//this.monkeyState=new LeftAction();
		banana_x=0;
		banana_y=0;
		ban=new ImageIcon(this.getClass().getResource("/ban_m.png")).getImage(); 
		
	}
}
