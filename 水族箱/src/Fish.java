import java.awt.Dimension;
import java.awt.Font;
import java.lang.Runnable;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Fish extends JLabel implements Runnable {
	private int x;
	private int y;
	private int race1;
	private int race2;
	private int mod = 2;
	int counter = 0;
	ImageIcon img1 = new ImageIcon(getClass().getResource("1.png")); 
	ImageIcon img2 = new ImageIcon(getClass().getResource("1-1.png"));
	ImageIcon img3 = new ImageIcon(getClass().getResource("1-2.png")); 
	ImageIcon img4 = new ImageIcon(getClass().getResource("2.png"));
	ImageIcon img55 = new ImageIcon(getClass().getResource("2-1.png"));
	ImageIcon img66 = new ImageIcon(getClass().getResource("2-2.png"));
	ImageIcon img7 = new ImageIcon(getClass().getResource("3.png"));
	ImageIcon img8 = new ImageIcon(getClass().getResource("3-1.png"));
	ImageIcon img9 = new ImageIcon(getClass().getResource("3-2.png"));
	ImageIcon img10 = new ImageIcon(getClass().getResource("4.png"));
	ImageIcon img11 = new ImageIcon(getClass().getResource("4-1.png"));
	ImageIcon img12 = new ImageIcon(getClass().getResource("4-2.png"));
	ImageIcon img13 = new ImageIcon(getClass().getResource("5.png"));
	ImageIcon img14 = new ImageIcon(getClass().getResource("5-1.png"));
	ImageIcon img15 = new ImageIcon(getClass().getResource("5-2.png"));
	ImageIcon img16 = new ImageIcon(getClass().getResource("6.png"));
	ImageIcon img17 = new ImageIcon(getClass().getResource("6-1.png"));
	ImageIcon img18 = new ImageIcon(getClass().getResource("6-2.png"));
	ImageIcon img19 = new ImageIcon(getClass().getResource("megumi.png"));
	ImageIcon img20 = new ImageIcon(getClass().getResource("megumi.png"));
	ImageIcon[] img5 = {img1,img2,img3,img7,img8,img9,img13,img14,img15,img19};
	ImageIcon[] img6 = {img4,img55,img66,img10,img11,img12,img16,img17,img18,img20};
	
	Random r = new Random();
	int i = r.nextInt(10);
	Random generator = new Random();
	public Fish(Point p) {
		super("");
		this.setPreferredSize(new Dimension(175,175));
		this.setFont(new Font("Serif",Font.BOLD,250));
	    this.setBounds(p.x, p.y, 250, 286);
	    this.setHorizontalAlignment(SwingConstants.RIGHT);
	    this.setIcon(img5[i]);
	    this.paintAll(this.getGraphics());
	    x = p.x;
		y = p.y;
		race1 = generator.nextInt(5)+2;
		race2 = generator.nextInt(5)+2;
	}
	@Override
	public void run() {
		x += race1;
		y += race2;
		while(true) {
			if(x>=875) {//先碰到右邊界923
				mod = 1;
			}
			else if(y>=480) {//先碰到下邊界623
				mod = 2;
			}
			else if(y<=-20) {//先碰到上邊界
				mod = 3;
			}
			else if(x<=0) {//先碰到左邊界
				mod = 4;
			}
			if(mod == 1){//先碰到右邊界
			
				try {
					Thread.sleep(9);
					this.setBounds(x,y, 250, 286);
					race1 = generator.nextInt(3)+1;
					race2 = generator.nextInt(2)+1;
					this.setIcon(img6[i]);
					x -= race1;//
					y += race2;
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(mod == 2) {
				try {
					Thread.sleep(9);
					this.setBounds(x,y, 250, 286);
					
					race1 = generator.nextInt(3)+1;
					race2 = generator.nextInt(2)+1;
					
					
					this.setIcon(img5[i]);
					x += race2;
					y -= race2;//
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(mod == 3) {
				try {
					Thread.sleep(9);
					this.setBounds(x,y, 250, 286);
					
					race1 = generator.nextInt(3)+1;
					race2 = generator.nextInt(2)+1;
				    this.setIcon(img6[i]);
					x -= race2;
					
					y += race2;//
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(mod == 4) {
				try {
					Thread.sleep(9);
					this.setBounds(x,y, 250, 286);
					
					race1 = generator.nextInt(3)+1;
					race2 = generator.nextInt(2)+1;
					x+=race1;//
					this.setIcon(img5[i]);
					y += race2;
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					Thread.sleep(9);
					this.setBounds(x,y, 250,286);
					//System.out.println("Test");
					race1 = generator.nextInt(3)+1;
					race2 = generator.nextInt(2)+1;
					this.setIcon(img5[i]);
					x+=race1;
					y += race2;
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
