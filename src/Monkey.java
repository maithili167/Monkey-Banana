//Represents the object with the state dependent behavior
//Its behavior is implemented by MonkeyState,Still and Moving state classes
import java.awt.Image;

import javax.swing.ImageIcon;

public class Monkey {
	MonkeyState monkeyState;
	int monkey_x;
	int monkey_y;
	Image mon;
	int move_x;
	
	public Monkey(){
		this.monkeyState=new Still();
		monkey_x=0;
		monkey_y=0;  
		move_x=0;
		mon=new ImageIcon(this.getClass().getResource("/mon_m.png")).getImage(); 
	}
	public MonkeyState getMonkeyState() {
		return monkeyState;
	}
	public void setMonkeyState(MonkeyState monkeyState) {
		this.monkeyState = monkeyState;
	}
 
	public void goLeft(){
		this.monkeyState=monkeyState.goLeft(this);
		
	}
	public void goRight(){
		this.monkeyState=monkeyState.goRight(this);
	}
	public void goUp(){
		this.monkeyState=monkeyState.goUp(this);
	}
	public void goDown(){
		this.monkeyState=monkeyState.goDown(this);
	}
	public void keyUp() {
		// TODO Auto-generated method stub
		this.monkeyState=monkeyState.keyUp(this);
	}

}
