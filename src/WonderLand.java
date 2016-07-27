//Handles events such as key press (left,right,up,down)
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WonderLand extends JPanel implements KeyListener,ActionListener{

	Monkey monkey=new Monkey(); 
	Banana banana=new Banana();
	public static int countBanana=0;
	public int maxBanana=22;
	public Timer timer_banana;

	public static Timer timer;
	private int max_x_y=540;
	public int square_size=60;	 
	public boolean gameOn=true;
	JLabel label;
	int timerGame=3;  
	int timerBanana=10; 
	Image wonderLand;
	int resx = 600,resy = 600;
	
	//Constructor for WonderLand
	public WonderLand () {
		wonderLand=new ImageIcon(this.getClass().getResource("/wonderland.png")).getImage();
		
		JFrame frame = new JFrame();	
		frame.setSize(resx,resy);
		frame.setBackground(Color.GREEN);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Monkey & Banana");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monkey.monkey_x=numberGenerator(); 
		monkey.monkey_y=numberGenerator(); 
		banana.banana_x=numberGenerator(); 
		banana.banana_y=numberGenerator();
		monkey.move_x=square_size;

		this.setFocusable(true);
		this.requestFocusInWindow(); 
		setBackground (Color.green);
		setSize(600,600);	
 
		label = new JLabel("<html>Time Remaining: "+String.valueOf(timerGame)+"<br>Bananas: "+countBanana+"</html>");
		this.add(label);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		addKeyListener(this);
		

		timer = new Timer(10000, this);
		timer.start();
		timer_banana=new Timer(1000,this);
		timer_banana.start();
	}

	//Random number generator for movement of the banana
	public int numberGenerator()
	{
		int a = (int) (Math.random()*max_x_y);
		a= (a-square_size)-(a%square_size);
		while(a<0) 
		{
			a = (int) (Math.random()*max_x_y);
			a= (a-square_size)-(a%square_size);
		}
		return a;
	}

	//Function to redraw the game componenents
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(wonderLand, 0, 0, null);
		doDrawing(g);
	}

	//Function to redraw the game componenents
	private void doDrawing(Graphics g) {
		if(gameOn)
		{
			
			g.drawImage(banana.ban, banana.banana_x,banana.banana_y, square_size,square_size,this);
			g.drawImage(monkey.mon, monkey.monkey_x,monkey.monkey_y,square_size,square_size, this);
	
		}    		
		else 
			gameOver(g);
	}

	//Function to check if the monkey and banana are at the same position
	public void checkBanana() {
		// TODO Auto-generated method stub
		
	
		
		if(banana.banana_x==monkey.monkey_x && banana.banana_y==monkey.monkey_y)
		{
			
			countBanana++;
			System.out.println(countBanana);
			moveBanana();
		}
	}

	//Function to move position of banana depending on the timer or monkey's position
	public void moveBanana() {
		// TODO Auto-generated method stub
		
		int old_x = banana.banana_x;
     	int old_y = banana.banana_y;
     	
     	banana.banana_x=numberGenerator();
     	banana.banana_y=numberGenerator();
	
	while(banana.banana_x == old_x && banana.banana_y == old_y)
	{ 
		banana.banana_x=numberGenerator();
		banana.banana_x=numberGenerator();
	}
		
		
		repaint();
		timerBanana=10;
		timer_banana.restart();
	}

	//Function to display the game score and play the appropriate music on game over 
	//condition
	private void gameOver(Graphics g) {
		
	        
			String msg="";
			if(countBanana>=maxBanana)
				msg = "I need 22 Bananas.\nYou win!!!\nYour Score is :"+countBanana;
			else
				msg = "I need 22 Bananas.\nYou Loose!!!\nYour Score is :"+countBanana;
			Font small = new Font("Helvetica", Font.BOLD, 20);
			//FontMetrics metr = getFontMetrics(small);
			g.setColor(Color.black);
			g.setFont(small); 
			//g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 600 / 2);
			drawString(g,msg, 180,250);
		
	}
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
	//Function to check the timer value
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (--timerGame>0)
		{
			label.setText("<html>Time Remaining: "+String.valueOf(timerGame)+"<br>Bananas: "+countBanana+"</html>");
			//(timerGame%10==0)
			//	moveBanana();
		}
		else if(timerGame==0){
			label.setText("");
		timer.stop();
		gameOn=false;
	} 
	else 
	{ 
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("win.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(0);
	        // Calculate totalFrames
	        long totalFrames = (long)(clip.getFrameLength() * clip.getFormat().getFrameRate());
	        Thread.sleep( ( totalFrames* 1000 )); // pause the thread till the sound plays


	        
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		 
	}

		if(--timerBanana==0) 
		{
			
			moveBanana();

		}	

	}

	//Function verifies the key press events and redirects to the appropriate 
	//method from Monkey class
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==37 && monkey.monkey_x>0)
		{
			monkey.goLeft();
		
		}
		if(e.getKeyCode()==38 && monkey.monkey_y>0)
		{
			monkey.goUp();
		}

		if(e.getKeyCode()==39 && monkey.monkey_x<max_x_y)
		{
			monkey.goRight();
		} 
		if(e.getKeyCode()==40 && monkey.monkey_y<max_x_y)
		{
			monkey.goDown();
		}
		
		checkBanana();
		repaint();
	


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		monkey.keyUp();
		checkBanana();
		repaint(); 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}


