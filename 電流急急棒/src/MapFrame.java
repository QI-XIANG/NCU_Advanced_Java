/*
 * 作者: 張棨翔
 * 學號: 108403523
 * 系級: 資管2A
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
public class MapFrame extends JFrame{
	private String[] img  = {"brickwall.png","ghost.png","heart.png","diamond.png"};
	private Icon [] icon = {
			new ImageIcon(getClass().getResource(img[0])),
			new ImageIcon(getClass().getResource(img[1])),
			new ImageIcon(getClass().getResource(img[2])),
			new ImageIcon(getClass().getResource(img[3]))
			};
	private ArrayList<label> label = new ArrayList<>();
	private static ArrayList<label> pic = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private JPanel mainPanel;
	private Scanner sc;
	private JLabel Hp;
	private JPanel ininhpPanel;
	private JPanel inhpPanel;
	private JPanel outhpPanel;
	private static int hp = 100;
	private int size = 720;
	private int counter = 0;
	private int count = 0;
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//constructor
	public MapFrame() {
		super("Map");
		
		//initial pic
		pic.clear();
		//setLayout(new FlowLayout());
		try {
			sc = new Scanner(Paths.get("map.txt"));
			while(sc.hasNext()) {
				switch(sc.next()) {
				case "0":
					str.add("0");
					break;
				case "1":
					str.add("1");
					break;
				case "2":
					str.add("2");
					break;
				}
			}
			sc.close();	
		}
		catch(IOException e) {
			System.out.println("IOException happened!");
			e.printStackTrace();
		}
		str.stream().forEach(value->{
			label.add(new label(str.get(count)));
			count++;
		});
		Handler1 handler1 = new Handler1();//-0
		Handler2 handler2 = new Handler2();//pass
		Handler3 handler3 = new Handler3();//-5
		Handler4 handler4 = new Handler4();//-20
		Handler5 handler5 = new Handler5();//+30
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(10,10,5,5));
		//use lambda & functional programing
		Random r = new Random();
		label.stream().forEach(label -> {
			if(label.kind == "2") {
				label.setIcon(icon[3]);
				label.addMouseListener(handler2);
			}
			else if(label.kind == "1") {
				int x = r.nextInt(3);
				label.setIcon(icon[x]);
				if(x == 0) {
					label.addMouseListener(handler4);
				}
				else if(x == 1) {
					label.addMouseListener(handler1);
				}
				else if(x == 2) {
					label.addMouseListener(handler5);
				}
			}
			else {
				label.addMouseListener(handler3);
			}
		});
		for(label Label:label) {
			mainPanel.add(Label);
		}
		
		ininhpPanel = new JPanel();
		inhpPanel = new JPanel();
		
		outhpPanel = new JPanel();
		outhpPanel.setLayout(new BorderLayout());
		
		inhpPanel.add(ininhpPanel);
		outhpPanel.add(inhpPanel,BorderLayout.CENTER);
		
		ininhpPanel.setPreferredSize(new Dimension(size,50));
		add(outhpPanel,BorderLayout.SOUTH);
		add(mainPanel,BorderLayout.CENTER);
		mainPanel.setBackground(Color.WHITE);
		ininhpPanel.setBackground(Color.blue);
		
		hp = 100;
		
	}
	
	
	public class Handler1 extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MapFrame.this, "-0");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			hp = 0;
			size = 0;
			ininhpPanel.setPreferredSize(new Dimension(size,50));
			inhpPanel.add(ininhpPanel);
			inhpPanel.validate();
			outhpPanel.add(inhpPanel);
			System.out.println(hp);
			if(hp == 0 & counter == 0) {
				JOptionPane.showMessageDialog(MapFrame.this, "You Lose!!(This message shows only once.)");
				counter++;
			}
		}
	}
	
	public class Handler2 extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MapFrame.this, "pass");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if(getHp() == 0) {
				JOptionPane.showMessageDialog(MapFrame.this, "You Lose!! Do not cheat!");
			}
			else {
				JOptionPane.showMessageDialog(MapFrame.this, "pass");
			}
			System.out.println(hp);
		}
	}
	
	public class Handler3 extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MapFrame.this, "-5");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if(hp > 0) {
				hp -=5;
			}
			else{
				hp = 0;
			}
			
			if(size>36) {
				size -= 36;
			}
			else {
				size = 0;
			}
			//size -= 72;
			ininhpPanel.setPreferredSize(new Dimension(size,50));
			ininhpPanel.setBackground(Color.BLUE);
			inhpPanel.add(ininhpPanel);
			inhpPanel.validate();
			outhpPanel.add(inhpPanel);
			System.out.println(hp);
			if(hp == 0 & counter == 0) {
				JOptionPane.showMessageDialog(MapFrame.this, "You Lose!!(This message shows only once.)");
				counter++;
			}
		}
	}
	
	public class Handler4 extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MapFrame.this, "-20");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		
			if(hp > 0){
				hp -=20;
			}
			else {
				hp =0;
			}
			if(size > 144) {
				size -= 144;
			}
			else {
				size = 0;
			}
			ininhpPanel.setPreferredSize(new Dimension(size,50));
			ininhpPanel.setBackground(Color.BLUE);
			inhpPanel.add(ininhpPanel);
			inhpPanel.validate();
			outhpPanel.add(inhpPanel);
			System.out.println(hp);
			if(hp == 0 & counter == 0) {
				JOptionPane.showMessageDialog(MapFrame.this, "You Lose!!(This message shows only once.)");
				counter++;
			}
		}
	}
	
	public class Handler5 extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MapFrame.this, "+30");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if(hp > 70) {
				hp += (100-hp);
			}
			else if(hp <= 70 & hp>0) {
				hp +=30;
			}
			inhpPanel.remove(ininhpPanel);
			ininhpPanel = new JPanel();
			if(size <= 504 & hp!= 0) {
				size += 216;
			}
			else if(size > 504 & hp != 0){
				size += (720-size);
			}
			ininhpPanel.setPreferredSize(new Dimension(size,50));
			ininhpPanel.setBackground(Color.BLUE);
			inhpPanel.add(ininhpPanel);
			inhpPanel.validate();
			outhpPanel.add(inhpPanel);
			System.out.println(hp);
			if(hp == 0 & counter == 0) {
				JOptionPane.showMessageDialog(MapFrame.this, "You Lose!!(This message shows only once.)");
				counter++;
			}
		}
		
	}
	public static int getHp() {
		return hp;
	}
	private class label extends JLabel{
		String kind;
		public label(String kind) {
			this.kind = kind;
		}
	}
}