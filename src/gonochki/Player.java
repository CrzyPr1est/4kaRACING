package gonochki;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	
	//Задаем параметры, ограничивающие перемещение игрока
	public static final int MAX_V = 30; 
	public static final int MAX_TOP = 520;
	public static final int MAX_BOTTOM = 710;
	
	//Привязка модели машины игрока (при движении прямо, вверх, вниз)
	Image img_c = new ImageIcon("res/straight.png").getImage();
	Image img_l = new ImageIcon("res/up.png").getImage();
	Image img_r = new ImageIcon("res/down.png").getImage();
	
	Image img = img_c;
	
	
	public Rectangle getRect(){ //Задание физических параметров объектра игрока
		return new Rectangle(x, y, 150, 53);
		
	}
	
	int v = 0; //Переменная, отвечающая за скорость
	int dv = 0; //Переменная, отвечающая за ускорение
	int s = 0;  //Переменная, отвечающая за путь
	
	int x = 10;  //Переменная, отвечающая за положение по оси x
	int y = 10;  //Переменная, отвечающая за положение по оси y
	int dy = 0;  //Переменная, отвечающая за скорость изменения движения вверх/вниз
	
	int layer1 = 0;
	int layer2 = 1100;
	
	//Обработчик движения игрока
	public void move(){ 
		s += v;
		v += dv;
		if (v <= 0) v = 0;
		if (v >= MAX_V) v = MAX_V;
		y -= dy;
		if (y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
		if (layer2 - v <= 0){
			layer1 = 0;
			layer2 = 1100;
			
		} else{
			
		
		
		 layer1 -= v;
		layer2 -= v;
		
		}
	}
	
	//Обработчик управления
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			dv = 1;
			dy = 0;
			img = img_c;
		}
		if (key == KeyEvent.VK_LEFT){
			dv = -1;
			img = img_c;
		}
		if (key == KeyEvent.VK_UP) {
			dy = 5;
			img = img_l;
		
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = -5;
			img = img_r;
		}
	}
		
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
			dv = 0;
	}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			dy = 0;
			img = img_c;
			
		}
	}
	}