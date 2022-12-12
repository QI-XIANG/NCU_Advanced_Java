/*
 * 作者: 張棨翔
 * 學號: 108403523
 * 系級: 資管2A
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import java.util.Date;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class EditableFrame extends JFrame{
	private int counter = 1;
	private JLabel title;
	private static JLabel weatherTitle;
	private JPanel upup;	
	private JPanel up;
	private JPanel down;
    private String [] wheatherIcon = {"sunny.png","rainy.png","cloudy.png"};
    private String [] like_unlike = {"like.png","unlike.png"};
    private Icon [] img = {
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
    private static JLabel area;
    private static JPanel areaContainer;
    private static JPanel editTable;
    private static JComboBox<String> weatherBox;
    private static JPanel weatherPanel;
    private static JPanel outerWeatherPanel;
    private static JPanel container1;
    private static JPanel inup;
    private static JLabel time = new JLabel();;
    //extra area
    private static JTextArea editArea;
    private static JButton edit;
    private static JButton renew;
    private static JPanel container3;
    //variable from object
    private static ObjectInputStream intputObj;
    private static ObjectOutputStream outputObj;
    private static String content1 = "";
	private static boolean isLike = false;
	private static Date editTime = new Date();
	private static int weather = 0;
    //import file variables
	private Path filePath;
	@SuppressWarnings("deprecation")
	public EditableFrame() {
		super("聯絡簿小程式");
		
		// bg color
		Color color1 = new Color(0,117,0);
		//red default information
		openObjFile();
		readObj();
		
		
		//get date
		Date date = new Date();
		
		//set up panel 
		upup = new JPanel(new BorderLayout());
		upup.setBackground(color1);
		up = new JPanel();
		up.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		up.setBackground(color1);
		
		//set editTable
		editTable = new JPanel();
		editTable.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton save = new JButton("儲存");
		JButton otherSave = new JButton("另存內容");
		JButton inFile = new JButton("匯入內容");
		JButton cancel = new JButton("取消");
		HandleCancel handleCancel = new HandleCancel();
		HandleSave handleSave = new HandleSave();
		HandleInFile handleInFile = new HandleInFile();
		HandleSaveFile handleSaveFile = new HandleSaveFile();
		cancel.addActionListener(handleCancel);
		save.addActionListener(handleSave);
		inFile.addActionListener(handleInFile);
		otherSave.addActionListener(handleSaveFile);
		save.setPreferredSize(new Dimension(60,40));
		inFile.setPreferredSize(new Dimension(100,40));
		cancel.setPreferredSize(new Dimension(60,40));
		otherSave.setPreferredSize(new Dimension(100,40));
		editTable.add(save);
		editTable.add(otherSave);
		editTable.add(inFile);
		editTable.add(cancel);
		editTable.setPreferredSize(new Dimension(300,55));
		editTable.setBackground(new Color(133,66,0));
		//inner up
		inup = new JPanel(new GridLayout(2,1,0,0));
		
		//set down panel 
		down = new JPanel(new BorderLayout());
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
		down.add(likeOrNot,BorderLayout.WEST);
		
		
		
		//set editable button
		JPanel storeBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		edit = new JButton("編輯");
		renew = new JButton("全新貼文");
		edit.setPreferredSize(new Dimension(60,40));
		renew.setPreferredSize(new Dimension(100,40));
		HandleEdit handleEdit = new HandleEdit();
		HandleNew handleNew = new HandleNew();
		renew.addActionListener(handleNew);
		edit.addActionListener(handleEdit);
		storeBtn.add(edit);
		storeBtn.add(renew);
		storeBtn.setBackground(color2);
		down.add(storeBtn,BorderLayout.EAST);
		
		
		//set title
		title = new JLabel("");
		title.setText("聯絡簿");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Serif",Font.PLAIN,40));
		
		//set weather
		weatherTitle = new JLabel("");
		weatherTitle.setText("天氣");
		weatherTitle.setForeground(Color.WHITE);
		weatherTitle.setFont(new Font("Serif",Font.PLAIN,28));
		weatherTitle.setIcon(img[weather]);
		weatherTitle.setHorizontalTextPosition(SwingConstants.LEFT);
		
		// show time label
		String Date = editTime.toString();
		time.setText(Date);
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Serif",Font.PLAIN,20));

		container1 = new JPanel(new GridLayout(2,1,0,0));
		container1.add(weatherTitle);
		container1.add(time);
	
		//weather box
		weatherPanel = new JPanel();
		weatherPanel.setLayout(new GridLayout(2,1,0,0));
		
		
		String []  choiceForWeather = {"晴天","陰天","雨天"};
		weatherBox = new JComboBox<String>(choiceForWeather);
		weatherBox.setPreferredSize(new Dimension(15,20));
		weatherBox.setSize(30,30);
		weatherPanel.add(weatherBox);
		weatherPanel.add(time);
		weatherPanel.setBackground(color1);
		
		
		
		
		
		
		//text area
		/*openFile();
		setDefaultText();
		String content = readRecords();*/
		//editable area
		editArea = new JTextArea();
	
		
		//show message
		openFile();
		closeFile();
		area = new JLabel();
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
		container1.add(time);
		inup.add(container1);
		up.add(inup);
		container1.setBackground(color1);
		inup.setPreferredSize(new Dimension(600,150));
		inup.setBackground(color1);
		upup.add(up,BorderLayout.NORTH);
	
		upup.add(editArea,BorderLayout.CENTER);
		editArea.setVisible(false);
		upup.add(areaContainer,BorderLayout.CENTER);
		
		//upup.add(editArea,BorderLayout.CENTER);
		upup.setBackground(color1);
		add(upup,BorderLayout.CENTER);
		
		closeObj();
	}
	
	public static void openFile() {
		try { // use scanner to read in file
			input = new Scanner(Paths.get("post.txt"));
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
	
	public static void closeFile() {
		if(input != null) {
			input.close();
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
	
	private class HandleEdit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(areaContainer.isVisible()) {
				editArea.setText(initTextShow());
				editArea.setFont(new Font("Serif",Font.PLAIN,16));
				editArea.setVisible(true);
				down.setVisible(false);
				areaContainer.setVisible(false);
				editTable.setVisible(true);
				weatherPanel.setVisible(true);
				weatherPanel.add(time);
				inup.add(weatherPanel);
				container1.setVisible(false);
				add(editTable,BorderLayout.SOUTH);
				upup.add(editArea,BorderLayout.CENTER);
			}
			
		}
	}
	
	private class HandleCancel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			editArea.setVisible(false);
			editTable.setVisible(false);
			down.setVisible(true);
			weatherPanel.setVisible(false);
			container1.setVisible(true);
			container1.add(time);
			inup.add(container1);
			add(down,BorderLayout.SOUTH);
			areaContainer.setVisible(true);
		}
	}
	
	private class HandleSave implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent e) {
			
			//return to initial status 
			editArea.setVisible(false);
			editTable.setVisible(false);
			down.setVisible(true);
			weatherPanel.setVisible(false);
			container1.setVisible(true);
			container1.add(time);
			inup.add(container1);
			add(down,BorderLayout.SOUTH);
			areaContainer.setVisible(true);
			editContent();
			openObjFile();
			readObj();
			writeObj();
			finWriteObj();
			resetLayout();
			closeObj();
		}
	}
	
	public static void writeObj() {
		try {
			outputObj = new ObjectOutputStream(Files.newOutputStream(Paths.get("post")));
			PostSerializable obj = new PostSerializable();
			obj.setEditTime(new Date());
			obj.setIsLike(isLike);
			obj.setWeather(weatherBox.getSelectedIndex());
			obj.setContent(content);
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
	
	public void resetLayout() {
		openObjFile();
		readObj();
		closeObj();
		weatherTitle.setIcon(img[weather]);
		time.setText(editTime.toString());
		content = "<html>";
		openFile();
		area.setVisible(false);
		content1 = content;
		area = null;
		area = new JLabel();
		area.setBackground(new Color(0,117,0));
		area.setForeground(Color.YELLOW);
		area.setFont(new Font("Serif",Font.PLAIN,20));
		area.setText(content1);
		areaContainer.add(area);
		closeFile();
	}
	
	public static String initTextShow() {
		String initContent = "";
		try {
			Scanner initReader = new Scanner(Paths.get("post.txt"));
			while(initReader.hasNext()) {
				initContent += initReader.nextLine();
				initContent += "\n";
			}
			initReader.close();
		}
		catch(IOException e) {
			
		}
		return initContent;
	}
	
	public static void editContent() {
		try {
			FileWriter fw = new FileWriter("post.txt");
			Scanner reader = new Scanner(editArea.getText());
			while(reader.hasNext()) {
				String str = reader.nextLine();
				fw.write(str,0,str.length());
				fw.write("\n");
			}
			
			fw.flush();
			fw.close();
			reader.close();
		}
		catch(FileNotFoundException e) {
			
		}
		catch(SecurityException e) {
			
		}
		catch(NoSuchElementException e) {
			
		}
		catch(IOException e){
			
		}
		
	}
	
	
	//import new text file
	public void importFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().toPath();
			readImportFile();
		}
	}
	
	public void readImportFile() {
		try {
			if(Files.exists(filePath)) {
				Scanner readInFile = new Scanner(filePath);
				String str = "";
				while(readInFile.hasNext()) {
					str += readInFile.nextLine();
					str += "\n";
				}
				readInFile.close();
				editArea.setText(str);
			}
			else{
				JOptionPane.showMessageDialog(this, "This file does not exist.");
			}
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	public class HandleInFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			importFile();
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
	
	
	public void saveFile() {
		int result = 0;
		JFileChooser saveFile = new JFileChooser();
		result = saveFile.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter writer = new FileWriter(saveFile.getSelectedFile()+".txt");
				Scanner input = new Scanner(editArea.getText());
				while(input.hasNext()) {
					writer.write(input.nextLine());
					writer.write("\n");
				}
				input.close();
				writer.close();
			}
			catch(IOException e) {
				
			}
		}
	}
	
	public class HandleSaveFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	}
	
	public class HandleNew implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			editArea.setText(initTextShow());
			editArea.setFont(new Font("Serif",Font.PLAIN,16));
			editArea.setVisible(true);
			down.setVisible(false);
			areaContainer.setVisible(false);
			editTable.setVisible(true);
			weatherPanel.setVisible(true);
			weatherPanel.add(time);
			inup.add(weatherPanel);
			container1.setVisible(false);
			add(editTable,BorderLayout.SOUTH);
			upup.add(editArea,BorderLayout.CENTER);
			editArea.setText("");
			isLike = false;
			writeObj();
			finWriteObj();
			likeOrNot.setIcon(new ImageIcon(getClass().getResource(like_unlike[1])));
		}
	}
}
