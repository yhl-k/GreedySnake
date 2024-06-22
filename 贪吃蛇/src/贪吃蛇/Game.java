package 贪吃蛇;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Game {
	public static void main(String[] args) {
		new Win();
	}
}

class Win extends JFrame{
	public Win(){
		add(new GamePanel());
		setTitle("贪吃蛇");
		setBounds(10,0,1000,700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}

class GamePanel extends JPanel implements KeyListener,ActionListener{
	int len,socre;
	Icon snake,food,food1,food2;
	int[] snakeX,snakeY;
	int foodX,foodY,food1X,food2Y,food2X,food1Y;
	boolean isS=false;
	boolean isO=false;
	javax.swing.Timer timer;
	String dir;
	
	public GamePanel() {
		init();		
		setFocusable(true);
		addKeyListener(this);
		timer=new Timer(200,this);
		timer.start();
	}
	public void init() {
		setBackground(Color.gray);
		snake=new ImageIcon("images/蛇.png");
		food=new ImageIcon("images/食物.png");
		food1=new ImageIcon("images/无标题.png");
		food2=new ImageIcon("images/食物2.png");
		
		len=3;
		socre=0;
		dir="右";
		
		snakeX=new int[10000];
		snakeY=new int[10000];
		
		snakeX[0]=200;
		snakeY[0]=200;
		snakeX[1]=190;
		snakeY[1]=200;
		snakeX[2]=180;
		snakeY[2]=200;
		
		foodX=20+10*(new Random().nextInt(93));
		foodY=100+10*(new Random().nextInt(54));
		
		food1X=20+10*(new Random().nextInt(93));
		food1Y=100+10*(new Random().nextInt(54));
		
		food2X=20+10*(new Random().nextInt(93));
		food2Y=100+10*(new Random().nextInt(54));		
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(20,100,940,550);//设置边框的距离
		
		g.setColor(new Color(20,20,20));
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString("长度："+len,50,30);
		g.drawString("分数："+socre,50,60);
		
		for(int i=0;i<len;i++)
			snake.paintIcon(this, g,snakeX[i],snakeY[i]);
		
		food.paintIcon(this, g,foodX,foodY);
		food1.paintIcon(this, g, food1X, food1Y);
		food2.paintIcon(this, g, food2X, food2Y);
		
		if(isS==false) {
			g.setColor(Color.white);
			g.drawString("按空格开始",500,200);
		}
		if(isO==true) {
			g.setColor(Color.red);
			g.drawString("死掉了，按空格重新开始",500,200);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {

		int k=e.getKeyCode();
		
		if(k==KeyEvent.VK_SPACE) {
			if(isO) {
				isO=false;
				init();
			}else {
				isS=!isS;
			}
			repaint();	
		}
		
		if(k==KeyEvent.VK_RIGHT) {
			if(!dir.equals("左"))
				dir="右";
		}else if (k==KeyEvent.VK_LEFT) {
			if(!dir.equals("右"))
				dir="左";
		}else if (k==KeyEvent.VK_DOWN) {
			if(!dir.equals("上"))
				dir="下";
		}else if (k==KeyEvent.VK_UP) {
			if(!dir.equals("下"))
				dir="上";
		}
	}
	public void keyReleased(KeyEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isS==true&&isO==false) {
			for(int i=len-1;i>0;i--) {
				snakeX[i]=snakeX[i-1];
				snakeY[i]=snakeY[i-1];
			}
			
			if(dir.equals("右")) {
				snakeX[0]=snakeX[0]+10;
				if(snakeX[0]>950) {
					isO=true;
				}
			}else if(dir.equals("下")) {
				snakeY[0]=snakeY[0]+10;
				if(snakeY[0]>640) {
					isO=true;
				}
			}else if(dir.equals("上")) {
				snakeY[0]=snakeY[0]-10;
				if(snakeY[0]<100) {
					isO=true;
				}
			}else if(dir.equals("左")) {
				snakeX[0]=snakeX[0]-10;
				if(snakeX[0]<20) {
					isO=true;
				}
			}
			
			if(snakeX[0]==foodX&&snakeY[0]==foodY) {
				len+=1;
				socre+=10;
				foodX=20+10*(new Random().nextInt(93));
				foodY=100+10*(new Random().nextInt(54));
			}
			
			if(snakeX[0]==food1X&&snakeY[0]==food1Y) {
				len+=3;
				socre+=30;
				food1X=20+10*(new Random().nextInt(93));
				food1Y=100+10*(new Random().nextInt(54));
			}
			
			if(snakeX[0]==food2X&&snakeY[0]==food2Y) {
				len+=10;
				socre+=100;
				food2X=20+10*(new Random().nextInt(93));
				food2Y=100+10*(new Random().nextInt(54));
			}
			for(int i=1;i<len;i++) {
				if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]) {
					isO=true;
				}
			}
			repaint();
			timer.start();
		}
	}
}




















