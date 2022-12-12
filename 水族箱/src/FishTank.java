import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
public class FishTank extends JFrame{
	private JPanel toolPanel;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private ImagePanel pool;
    private static int mod = 0;
	private static JLabel statusBar;
	private int option = 0;
	ExecutorService exe;
	Image background = Toolkit.getDefaultToolkit().createImage("src/780762.png");
    private JLabel test = new JLabel();
    private static int Tcount = 0;
    private static int Fcount = 0;
	public FishTank(){
		super("FishBowl");
		
		//
		
		//set thread executor
		exe = Executors.newCachedThreadPool();
		
		//set button
		btn1 = new JButton("新增魚");
		btn2 = new JButton("移除選取");
		btn3 = new JButton("新增烏龜ˋ");
		btn4 = new JButton("移除全部");
		
		//set pool
		pool = new ImagePanel(background);
	    pool.setOpaque(true);
	    //pool.setLayout(null);
	    Btn4 b4 = new Btn4();
	    Btn3 b3 = new Btn3();
	    Btn2 b2 = new Btn2();
	    Btn1 b1 = new Btn1();
        btn4.addActionListener(b4);
        btn3.addActionListener(b3);
	    btn2.addActionListener(b2);
	    btn1.addActionListener(b1);
	    //
	    pool.setPreferredSize(new Dimension(970,623));
	    pool.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    mouseHandleT m = new mouseHandleT();
	    mouseHandleF n = new mouseHandleF();
	    pool.addMouseListener(n);
	    pool.addMouseListener(m);
	    //set status bar
	    statusBar = new JLabel();
	    Font font = new Font("Serif",Font.PLAIN,14);
	    statusBar.setText(String.format("目前功能:未選擇功能    %10s  %10s","魚數量: 0","烏龜數量: 0"));
	    statusBar.setFont(font);
	    statusBar.setForeground(Color.BLUE);
		//set toolPanel
		toolPanel = new JPanel();
		toolPanel.setLayout(new GridLayout(2,2,0,0));
		toolPanel.add(btn1);
		toolPanel.add(btn2);
		toolPanel.add(btn3);
		toolPanel.add(btn4);
		//re layout
		JPanel up = new JPanel();
		up.setLayout(new BorderLayout());
		up.add(toolPanel,BorderLayout.CENTER);
		up.add(statusBar,BorderLayout.SOUTH);
		//set basic layout
		add(up,BorderLayout.NORTH);
		add(pool,BorderLayout.CENTER);
		//handle event
		//test h = new test();
		//pool.addMouseMotionListener(h);
		Modhandler handler1 = new Modhandler();
		btn1.addActionListener(handler1);
		btn2.addActionListener(handler1);
		btn3.addActionListener(handler1);
		btn4.addActionListener(handler1);
		
		
	}
	
	public class ImagePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private Image image = null;
        
		public ImagePanel(Image image){
			this.image = image;
			this.setLayout(null);
		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if (image != null){
				g.drawImage(image,0,0,this);
			}
		}
	}
	public class Modhandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn1) {
				mod = 1;
			}
			else if(e.getSource() == btn3) {
				mod = 2;
			}
			changeBar();
		}
	}
	public static void changeBar() {
		if(mod == 1) {
			statusBar.setText(String.format("目前功能:新增魚    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
		}
		else if(mod == 2) {
			statusBar.setText(String.format("目前功能:新增烏龜    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
		}
		else {
			statusBar.setText(String.format("目前功能:未選擇功能    %10s  %10s","魚數量: "+Fcount,"烏龜數量: 0"));
		}
	}
	
	public class test extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("x:"+e.getX()+" y:"+e.getY());
		}
		
	}
	
	public class mouseHandleT extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(option == 3) {
				Point p = new Point(e.getX(),e.getY());
				Turtle turtle = new Turtle(p);
				mouseT nm = new mouseT();
				turtle.addMouseListener(nm);
				Tcount += 1;
				statusBar.setText(String.format("目前功能:新增烏龜    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
				pool.add(turtle);
				exe.execute(turtle);
				pool.validate();
			}
		}
	}
	
	public class mouseHandleF extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(option == 1) {
				Point p = new Point(e.getX(),e.getY());
				Fish fish = new Fish(p);
				mouseF nm = new mouseF();
				fish.addMouseListener(nm);
				Fcount += 1;
				statusBar.setText(String.format("目前功能:新增魚   %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
				pool.add(fish);
				exe.execute(fish);
				pool.validate();
			}
		}
	}
	
	public class mouseT extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(option == 2) {
				((Turtle)e.getSource()).setV();
					try {
						pool.remove((Turtle)e.getSource());
						Tcount -= 1;
						statusBar.setText(String.format("目前功能:移除選取    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
						pool.validate();
						((Turtle)e.getSource()).Halt();
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
	
					
				}
			}
	}
	
	public class mouseF extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(option == 2) {
				((Fish)e.getSource()).setV();
					try {
						pool.remove((Fish)e.getSource());
						Fcount -= 1;
						statusBar.setText(String.format("目前功能:移除選取    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
						pool.validate();
						((Fish)e.getSource()).Halt();
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
	
					
				}
			}
			
	}
	
	public class Btn4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pool.removeAll();
			pool.repaint();
			pool.validate();
			Tcount = 0;
			Fcount = 0;
			statusBar.setText(String.format("目前功能:移除全部    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
			try {
				exe.wait();
			}catch(InterruptedException e1) {
				System.out.println("Error happened!! Please ignore it.");
			}
		}
	}
	
	public class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			option = 3;
			statusBar.setText(String.format("目前功能:新增烏龜   %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
		}
	}
	
	public class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			option = 2;
			statusBar.setText(String.format("目前功能:移除選取    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
		}
	}
	
	public class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			option = 1;
			statusBar.setText(String.format("目前功能:新增魚    %10s  %10s","魚數量: "+Fcount,"烏龜數量: "+Tcount));
		}
	}
	
}
