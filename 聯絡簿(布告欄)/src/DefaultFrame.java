/*
 * 作者: 張棨翔
 * 學號: 108403523
 * 系級: 資管2A
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Icon;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultFrame extends JFrame{
	private int counter = 1;
	private JLabel title;
	private JLabel wheatherTitle;
	private JPanel up;
	private JPanel down;
    private String [] wheatherIcon = {"sunny.png","rainy.png","cloudy.png"};
    private String [] like_unlike = {"like.png","unlike.png"};
    private Icon [] img2 = {
    		new ImageIcon(getClass().getResource(wheatherIcon[0])),
    		new ImageIcon(getClass().getResource(wheatherIcon[2])),
    		new ImageIcon(getClass().getResource(wheatherIcon[1]))
    };
    private Icon Like; 
    public static Scanner input;
    private static FileWriter writer;
    private static FileReader reader;
    public static String content = "<html>";
    private static  JLabel likeOrNot;
    private static JPanel areaContainer;
	private JPanel upup;
    //variable from object
    private static ObjectInputStream intputObj;
    private static ObjectOutputStream outputObj;
    private static String content1 = "";
	private static boolean isLike = false;
	private static Date editTime = new Date();
	private static int weather = 0;
    
	public DefaultFrame() {
super("聯絡簿小程式");
		
		// bg color
		Color color1 = new Color(0,117,0);
		//red default information
		openObjFile();
		readObj();
		closeObj();
		
		//get date
		Date date = new Date();
		
		//set up panel 
		upup = new JPanel(new BorderLayout());
		upup.setBackground(color1);
		up = new JPanel();
		up.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		up.setBackground(color1);
		
		//inner up
		JPanel inup = new JPanel(new GridLayout(2,1,0,0));
		
		//set down panel 
		down = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Color color2 = new Color(133,66,0);
		down.setBackground(color2);
		down.setPreferredSize(new Dimension(100, 55));
		add(down,BorderLayout.SOUTH);
		
		//like label
		Handler handler = new Handler();
	    likeOrNot = new JLabel();
	    if(isLike == true) {
	    	likeOrNot.setIcon(new ImageIcon(getClass().getResource(like_unlike[0])));
	    }
	    else if(isLike == false) {
	    	likeOrNot.setIcon(new ImageIcon(getClass().getResource(like_unlike[1])));
	    }
		likeOrNot.addMouseListener(handler);
		down.add(likeOrNot);
		
		
		
		
		//set title
	    title = new JLabel("");
		title.setText("聯絡簿");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Serif",Font.PLAIN,40));
				
				
		//set weather
		wheatherTitle = new JLabel("");
		wheatherTitle.setText("天氣");
		wheatherTitle.setForeground(Color.WHITE);
		wheatherTitle.setFont(new Font("Serif",Font.PLAIN,28));
		wheatherTitle.setIcon(img2[weather]);
		wheatherTitle.setHorizontalTextPosition(SwingConstants.LEFT);
				
				
				
				
		// show time label
		String Date = editTime.toString();
		JLabel time = new JLabel();
		time.setText(Date);
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Serif",Font.PLAIN,20));

		JPanel container1 = new JPanel(new GridLayout(2,1,0,0));
		container1.add(wheatherTitle);
		container1.add(time);

		
		
		//text area
		/*openFile();
		setDefaultText();
		String content = readRecords();*/
		openFile();
		closeFile();
		JLabel area = new JLabel();
		area.setText(content);
		area.setBackground(new Color(0,117,0));
		area.setForeground(Color.YELLOW);
		area.setFont(new Font("Serif",Font.PLAIN,20));
		areaContainer = new JPanel();
		areaContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
		areaContainer.setBackground(color1);
		areaContainer.add(area);
		//put component in
		inup.add(title);
		inup.add(container1);
		up.add(inup);
		container1.setBackground(color1);
		inup.setPreferredSize(new Dimension(300,150));
		inup.setBackground(color1);
		upup.add(up,BorderLayout.NORTH);
	
		
		upup.add(areaContainer,BorderLayout.CENTER);
		
		//upup.add(editArea,BorderLayout.CENTER);
		upup.setBackground(color1);
		add(upup,BorderLayout.CENTER);
		
		
	}
	
	public static void openFile() {
		/*try { // use scanner to read in file
			writer = new FileWriter("post.txt");
			reader = new FileReader("post.txt");
			
		}*/
		try { // use scanner to read in file
			input = new Scanner(Paths.get("post.txt"),"UTF-8");
			while(input.hasNext()) {
					
				content += input.nextLine();
				content += "<br>";
			}
			
			content += "</html>";
		}
		catch(IOException e) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
		
	}
	public static void setDefaultText() {
		try {
			writer.write("1. 明考國文第五課註釋");
			writer.write("\n");
			writer.write("2. 週四考生物小考");
			writer.write("\n");
			writer.write("3. 國文乙本第六課詞語");
			writer.write("\n");
			writer.write("4. 數習第四章：分數與小數");
			writer.write("\n");
			writer.write("5. 訂簽輔導學習單");
			writer.flush();
			writer.close();
		}
		catch(IOException e) {
			
		}
	}
	public static void closeFile() {
		if(input != null) {
			input.close();
		}
	}

	private class Handler extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isLike == true) {
				likeOrNot.setIcon(new ImageIcon(getClass().getResource(like_unlike[1])));
				isLike = false;
				writeObj();
				finWriteObj();
				counter++;
			}
			else {
				likeOrNot.setIcon(new ImageIcon(getClass().getResource(like_unlike[0])));
				isLike = true;
				writeObj();
				finWriteObj();
				counter++;
			}
		}
	}
	
	public static void openObjFile() {
		try {
			intputObj = new ObjectInputStream(Files.newInputStream(Paths.get("post")));
		}
		catch(IOException e) {
			System.err.println("Error opening file. Terminating.");
		}
	}
	
	public static  void readObj() {
		try {
			PostSerializable obj = (PostSerializable)intputObj.readObject();
			content1 = obj.getContent();
			isLike = obj.getIsLike();
			editTime = obj.getEditTime();
			weather = obj.getWeather();
		}
		catch(IOException e) {
			
		}
		catch(ClassNotFoundException e) {
			
		}
	}
	
	public static void closeObj() {
		try{
			if(intputObj!=null) {
				intputObj.close();
			}
		}
		catch(IOException e) {
			
		}
	}
	
	public static void writeObj() {
		try {
			outputObj = new ObjectOutputStream(Files.newOutputStream(Paths.get("post")));
			PostSerializable obj = new PostSerializable();
			obj.setIsLike(isLike);
			outputObj.writeObject(obj);
		}
		catch(IOException e) {
			
		}
	}
	public static void finWriteObj() {
		try {
			if(outputObj != null) {
				outputObj.close();
			}
		}
		catch(IOException e) {
			
		}
	}
}
