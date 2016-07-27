//This Concrete state class implements transitions going out of the still state
//It returns Moving state.
public class Still extends MonkeyState {
 
	@Override 
	public MonkeyState goLeft(Monkey monkey){
		
		monkey.monkey_x-=monkey.move_x;
		return new Moving(); 
	}
	public MonkeyState goRight(Monkey monkey){
		monkey.monkey_x+=monkey.move_x;
		return new Moving();
	}
	public MonkeyState goUp(Monkey monkey){
		monkey.monkey_y-=monkey.move_x;
		return new Moving();
	}
	public MonkeyState goDown(Monkey monkey){
		monkey.monkey_y+=monkey.move_x;
		return new Moving();
	}
	
}
