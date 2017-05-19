package gonochki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer = new Timer(20, this);
	
	Image img_s = new ImageIcon ("res/road_s.png").getImage(); //Привязка модели фона дороги
	Image img = new ImageIcon("res/road1.png").getImage(); //Привязка модели дороги
	
	Player p = new Player();
	
	Thread enemiesFactory = new Thread(this);
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public Road(){
		mainTimer.start();
		enemiesFactory.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);	
		}	
	}
	
	//Прорисовка объектов на поле
	public void paint(Graphics g){
		g.drawImage(img_s, -(p.s/80), 0, null);
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null);
		g.drawImage(img, p.layer2, 0, null);
		g.drawImage(p.img, p.x, p.y, null);
		
		double v = (30/Player.MAX_V) * p.v;
		g.setColor(Color.RED);
		Font font = new Font("Calibri", Font.ITALIC, 20);
		g.setFont(font);
		g.drawString("Текущая скорость " + Math.round(v*6) +" км/ч.", 50,490); //Датчик скорости
		Iterator<Enemy> i = enemies.iterator(); 
		
		//Удаление врагов с поля, если они уехали достаточно далеко
		while(i.hasNext()){ 
			Enemy e = i.next();
			if (e.x >= 2400 || e.x <= - 2400){
				i.remove();
				
			} else {
				e.move();
			g.drawImage(e.img, e.x, e.y, null);
		}
		
		}
		
		
		
	}
	
	public void actionPerformed(ActionEvent e){ //Обрабочик событий, происходящих с игроком

		p.move();
		repaint();
		testCilligenWithEnemies();
		testWin();
	}
	
	//Обработчик события выигрыша
	private void testWin() { 
		if (p.s> 35000){
			JOptionPane.showMessageDialog(null, "Я всех обогнал!");
			System.exit(0);
		}
		
	}

	 //Обработчик события столкновения
	private void testCilligenWithEnemies() {
		
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()){
			Enemy e = i.next();
			if (p.getRect().intersects(e.getRect())){
				JOptionPane.showMessageDialog(null, "Пацаны, в аварию попал...");
				System.exit(1);
			}
		}
		
		
	}
	
	public void run() {
		
		while(true){
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				
				//Добавление врагов на поле
				enemies.add(new Enemy(1200, (520), rand.nextInt(200), this));
				enemies.add(new Enemy(1200, (620), rand.nextInt(90), this));
				enemies.add(new Enemy(1200, (720), rand.nextInt(150), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
