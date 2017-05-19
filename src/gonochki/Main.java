package gonochki;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("ВАЗ 2104 Racing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1200, 838);
		f.add(new Road());
		f.setVisible(true);
		
		
		
		

	}

}