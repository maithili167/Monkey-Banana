//Defines a method for each transition and implements it as 'return self'
public class MonkeyState {
	
	//Move monkey to the left
	public MonkeyState goLeft(Monkey monkey){
		
		return this;
	} 
	//Move monkey to the right 
	public MonkeyState goRight(Monkey monkey){
		return  this;
	}
	//Move monkey to the up
	public MonkeyState goUp(Monkey monkey){
		return this;
	}
	//Move monkey to the down
	public MonkeyState goDown(Monkey monkey){
		return this;
	}
	public MonkeyState keyUp(Monkey monkey){
		return this;
	}
	
}
