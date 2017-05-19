package gonochki;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	
	int x;
	int y;
	int v;
	Image img = new ImageIcon("res/enemy.png").getImage(); //Привязка модели машины врага
	Road road;
	
	public Rectangle getRect(){ //Задание физических параметров объекта врага
		return new Rectangle(x, y, 180, 60);
		
	}
	
	public Enemy(int x, int y, int v, Road road){ //Обработчик врага как объекта относительно дороги
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}
	
	public void move(){ //Обработчик движения врага
		x = x - road.p.v + v;
	}

}
