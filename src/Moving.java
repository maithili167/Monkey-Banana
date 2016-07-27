//This Concrete state class implements transitions going out of the moving state
//It returns Still state.
public class Moving extends MonkeyState{
 
	//After moving left, monkey goes to Still state
	public MonkeyState goLeft(Monkey monkey){
		monkey.monkey_x-=monkey.move_x;
		
		return this;
	}
	//After moving right, monkey goes to Still state
	public MonkeyState goRight(Monkey monkey){
		monkey.monkey_x+=monkey.move_x;
		return this; 
	}
	//After moving up, monkey goes to Still state
	public MonkeyState goUp(Monkey monkey){
		monkey.monkey_y-=monkey.move_x;
		return this;
	}
	//After moving down, monkey goes to Still state
	public MonkeyState goDown(Monkey monkey){
		monkey.monkey_y+=monkey.move_x;
		return this;
	}
	
	public MonkeyState keyUp(Monkey monkey){
		return new Still();
	}
}
