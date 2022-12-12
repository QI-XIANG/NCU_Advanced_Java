import java.awt.Dimension;
import java.awt.Font;
import java.lang.Runnable;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Turtle extends JLabel implements Runnable{
	private int x;
	private int y;
	private int race1;
	private int race2;
	private int mod = 0;
	int counter = 0;
	ImageIcon img1 = new ImageIcon(getClass().getResource("turtle3.png")); 
	ImageIcon img2 = new ImageIcon(getClass().getResource("turtle4.png"));
	ImageIcon img3 = new ImageIcon(getClass().getResource("turtle.png")); 
	ImageIcon img4 = new ImageIcon(getClass().getResource("turtle2.png"));
	ImageIcon img55 = new ImageIcon(getClass().getResource("turtle5.png"));
	ImageIcon img66 = new ImageIcon(getClass().getResource("turtle6.png"));
	ImageIcon[] img5 = {img3,img1,img66};
	ImageIcon[] img6 = {img4,img2,img55};
	
	Random r = new Random();
	int i = r.nextInt(3);
	Random generator = new Random();
	
	public Turtle(Point p) {
		super("");
		this.setPreferredSize(new Dimension(150,150));
		this.setFont(new Font("Serif",Font.BOLD,250));
	    this.setBounds(p.x, p.y, 150, 150);
	    this.setHorizontalAlignment(SwingConstants.RIGHT);
	    this.setIcon(img5[i]);
	    this.paintAll(this.getGraphics());
	    x = p.x;
		y = p.y;
		race1 = generator.nextInt(2)+2;
		race2 = generator.nextInt(1)+2;
	}
	@Override
	public void run() {
		x += race1;
		y += race2;
		while(true) {
			if(y<525) {
				mod = 1;
			}
			if(y>=525) {
				if(x>850) {
					mod = 4;
				}
				if(x<=0) {
					mod = 3;
				}
			}
			if(mod == 1) {
				try {
					Thread.sleep(10);
					this.setBounds(x,y, 150, 150);
					
					race1 = generator.nextInt(2)+1;
					race2 = generator.nextInt(1)+1;
					x+=0;//
					this.setIcon(img5[i]);
					if(y>=525) {
						mod = 4;
					}
					else {
						y += race2;
					}
					
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(mod == 2) {
				try {
					Thread.sleep(10);
					this.setBounds(x,y, 150, 150);
					
					race1 = generator.nextInt(2)+1;
					race2 = generator.nextInt(1)+1;
					x-=race1;//
					this.setIcon(img6[i]);
					y += 0;
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(mod == 3) {
				try {
					Thread.sleep(10);
					this.setBounds(x,y, 150, 150);
					
					race1 = generator.nextInt(2)+1;
					race2 = generator.nextInt(1)+1;
					x+=race1;//
					this.setIcon(img5[i]);
					y += 0;
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(mod == 4) {
				try {
					Thread.sleep(10);
					this.setBounds(x,y, 150, 150);
					
					race1 = generator.nextInt(2)+1;
					race2 = generator.nextInt(1)+1;
					x-=race1;//
					this.setIcon(img6[i]);
					y += 0;
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setV() {
		this.setVisible(false);
	}
	
	public void Halt() {
		Thread.interrupted();
	}
}
