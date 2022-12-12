/*
 * �@��: �i�ɵ�
 * �Ǹ�: 108403523
 * �t��: ���2A
 * �[���\�� : 1.�]�w�e���I����A���s�C���H�۳]�w���I�������
 * 		   2.�ƹ��b�ϥξ�����ɡA�|�N���ЧΪ��ܧ󬰾����
 * 		   3.�ϥηƹ��b�e���W�@�e�ɡA�ƹ��۰��ܬ��e�����Ϊ�
 * 		   4.��ܵ����C�⪺���s�|�H�ۿ�ܪ��C��ӧ��ܫ��s�C��
 *         5.��L���ЧΪ��]�w
 *         6.�U��¦ⴣ����|���ܷƹ��O�_���u��C��
*/
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class PainterFrame extends JFrame implements icon{
	private final JLabel StatusBar;
	private final JPanel MousePanel;
	private final JPanel ToolBox;
	private final JCheckBox fillOption = new JCheckBox(); 
	private final JLabel Tool1 = new JLabel("ø�Ϥu��");
	private final JLabel Tool2 = new JLabel("����j�p");
	private final JLabel Tool3 = new JLabel("��");
	private final ButtonGroup size = new ButtonGroup();
	private final JRadioButton btn1;
	private final JRadioButton btn2;
	private final JRadioButton btn3;
	private final JButton painterColor = new JButton("�����C��");
	private final JButton eraser = new JButton("�����");
	private final JButton CanvasColor = new JButton("�e���I���C��");
	private final JButton clearPanel = new JButton("�M���e��");
	
	//use ArrayList to store different shape's status
	private final ArrayList<paintPoint> points = new ArrayList<paintPoint>();
	private final ArrayList<paintRectangle> rectangles = new ArrayList<paintRectangle>();
	private final ArrayList<paintOval> ovals = new ArrayList<paintOval>();
	private final ArrayList<paintLine> lines = new ArrayList<paintLine>();
	
	//two points to draw a line
	private Point firstPoint = new Point();
	private Point secondPoint = new Point();
	//counter for drawing a line & count for eraser
	private int countEraser = 1;
	//paint size & color
	private int paintSize = 13;
	private Color color = Color.BLACK;
	
	//ToolBox's Panel
	private final JPanel btnGroup = new JPanel();
	private final JPanel btnGroup2 = new JPanel();
	private final Canvas canvas = new Canvas();
	private final JPanel jp1 = new JPanel();
	private final JPanel jp2 = new JPanel();
	private final JPanel jp3 = new JPanel();
	private final JPanel jp4 = new JPanel();
	private final JComboBox<String> paintTool;
	private final String [] names1 = {"����","���u","����","�x��","�ꨤ�x��"}; 
	
	public PainterFrame() {
		super("�p�e�a");
		// set up tool box
		//Canvas.setBackground(Color.WHITE);
		ToolBox = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		jp2.setLayout(new GridLayout(2,1));
		jp3.setLayout(new GridLayout(2,1));
		jp4.setLayout(new GridLayout(1,1));
		paintTool = new JComboBox<String>(names1);
		paintTool.setMaximumRowCount(3);
		btn1 = new JRadioButton("�p",true);
		btn2 = new JRadioButton("��",false);
		btn3 = new JRadioButton("�j",false);
		size.add(btn1);
		size.add(btn2);
		size.add(btn3);
		btnGroup.add(btn1);
		btnGroup.add(btn2);
		btnGroup.add(btn3);
		btnGroup2.add(painterColor);
		btnGroup2.add(CanvasColor);
		btnGroup2.add(eraser);
		btnGroup2.add(clearPanel);
		
		//setCursor Shape
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		jp1.setCursor(cursor);
		jp2.setCursor(cursor);
		jp3.setCursor(cursor);
		jp4.setCursor(cursor);
		
		final Cursor cursorC;
		final Cursor cursorB;
		Toolkit toolkit = getToolkit();
		cursorC = toolkit.createCustomCursor(((ImageIcon) img5).getImage(),new Point(8,15), "stick");
		cursorB = toolkit.createCustomCursor(((ImageIcon) img6).getImage(),new Point(8,15), "stick");
		canvas.setCursor(cursorC);
		//Handler
		paintToolHandler PaintToolHandler = new paintToolHandler();
		paintTool.addItemListener(PaintToolHandler);
		RadioBtnHandler radioBtnHandler = new RadioBtnHandler();
		btn1.addItemListener(radioBtnHandler);
		btn2.addItemListener(radioBtnHandler);
		btn3.addItemListener(radioBtnHandler);
		fillOptionHandler FillOptionHandler = new fillOptionHandler();
		fillOption.addActionListener(FillOptionHandler);
		BtnHandler btnHandler = new BtnHandler();
		painterColor.addActionListener(btnHandler);
		clearPanel.addActionListener(btnHandler);
		CanvasColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Color canvasColor = Color.WHITE;
				canvasColor = JColorChooser.showDialog(jp4, "�п�ܵe���I���C��", Color.WHITE);
				canvas.setBackground(canvasColor);
				CanvasColor.setBackground(canvasColor);
			}
		});
		eraser.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				if(countEraser %2 != 0) {
					color = Color.WHITE;
					painterColor.setEnabled(false);
					JOptionPane.showMessageDialog(canvas, "�w��w\"�����C��\"�A�ñN�C����w���զ�C\n(�A���I���i�H�Ѱ���w)","�}�Ҿ�����\��",1,icon.img3);
					canvas.setCursor(cursorB);
					countEraser++;
				}
				else if(countEraser %2 == 0) {
					color = Color.BLACK;
					painterColor.setEnabled(true);
					JOptionPane.showMessageDialog(canvas, "�w�Ѱ���w\"�����C��\"�A�ñN�C���l�Ƭ��¦�C","����������\��",1,icon.img4);
					canvas.setCursor(cursorC);
					countEraser++;
				}
			}
		});
		//initial fillOption
		fillOption.setEnabled(false);
		//settingLayout
		jp1.add(Tool1);
		jp1.add(paintTool);
		jp2.add(Tool2);
		jp2.add(btnGroup);
		jp3.add(Tool3);
		jp3.add(fillOption);
		jp4.add(btnGroup2);
		
		//add four panel in one panel
		ToolBox.add(jp1);
		ToolBox.add(jp2);
		ToolBox.add(jp3);
		ToolBox.add(jp4);
		ToolBox.validate();
		
		
		//set up mouse status
		MousePanel = new JPanel();
		MousePanel.setBackground(Color.black);
		MousePanel.setLayout(new BorderLayout());
		StatusBar = new JLabel("���Цb�����~");//initial status
		MouseHandler handler = new MouseHandler();
		StatusBar.setForeground(Color.white);
		MousePanel.add(StatusBar,BorderLayout.WEST);
		
		
		//add component to frame
		MouseHandler2 mouseHandler2 = new MouseHandler2();
		MouseHandler3 mouseHandler3 = new MouseHandler3();
		ToolBox.addMouseMotionListener(mouseHandler2);
		ToolBox.addMouseMotionListener(mouseHandler3);
		add(MousePanel,BorderLayout.SOUTH);
		add(ToolBox,BorderLayout.NORTH);
		canvas.addMouseListener(handler);
		canvas.addMouseMotionListener(handler);
		canvas.setBackground(Color.WHITE);
		add(canvas,BorderLayout.CENTER);
		
		
	}
	private class MouseHandler2 extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			StatusBar.setText("���Цb�u��C��");
		}
	}
	
	private class MouseHandler3 extends MouseAdapter{
		@Override
		public void mouseExited(MouseEvent e) {
			StatusBar.setText("���Цb�e���~");
		}
	}
	private class MouseHandler implements MouseListener,MouseMotionListener{
		@Override
		public void mouseExited(MouseEvent e) {
			StatusBar.setText("���Цb�e���~");
		}
		@Override
		public void mousePressed(MouseEvent e) {
			StatusBar.setText("���Ц�m(Pressed): ("+e.getX()+","+e.getY()+")");
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			StatusBar.setText("���Ц�m(Clicked): ("+e.getX()+","+e.getY()+")");
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			StatusBar.setText("���Ц�m(Released): ("+e.getX()+","+e.getY()+")");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			StatusBar.setText("���Ц�m(Entered): ("+e.getX()+","+e.getY()+")");
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			StatusBar.setText("���Ц�m(Dragged): ("+e.getX()+","+e.getY()+")");
		}
		@Override
		public void mouseMoved(MouseEvent e){
			StatusBar.setText("���Ц�m(Moved): ("+e.getX()+","+e.getY()+")");
		}
	}
	private class paintToolHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(paintTool.getSelectedIndex() == 0) {
					System.out.println("��ܵ���");
					JOptionPane.showMessageDialog(canvas,"��ܵ���@�e�ɡA�T�ζ�","���ɤp�m�{",1,icon.img2);
					fillOption.setEnabled(false);
				}
				else if(paintTool.getSelectedIndex() == 1) {
					System.out.println("��ܪ��u");
					fillOption.setEnabled(true);
				}
				else if(paintTool.getSelectedIndex() == 2) {
					System.out.println("��ܾ���");
					fillOption.setEnabled(true);
				}
				else if(paintTool.getSelectedIndex() == 3) {
					System.out.println("��ܯx��");
					fillOption.setEnabled(true);
				}
				else if(paintTool.getSelectedIndex() == 4) {
					System.out.println("��ܶꨤ�x��");
					fillOption.setEnabled(true);
				}
			}
		}
	}
	private class RadioBtnHandler implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == btn1) {
				System.out.println("��� �p ����");
				paintSize = 13;
			}
			else if(e.getSource() == btn2) {
				System.out.println("��� �� ����");
				paintSize = 26;
			}
			else if(e.getSource() == btn3) {
				System.out.println("��� �j ����");
				paintSize = 39;
			}
		}
	}
	private class fillOptionHandler implements ActionListener{
		int counter = 1;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == fillOption) {
				if(counter%2 != 0) {
					System.out.println("��ܶ�");
				}
				else if(counter %2 == 0) {
					System.out.println("������");
				}
				counter++;
			}
		}
	}
	private class BtnHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == painterColor) {
				System.out.println("�I�� �����C��");
				color = JColorChooser.showDialog(jp4, "��ܦr���C��", color);
				painterColor.setBackground(color);
			}
			else if(e.getSource() == clearPanel) {
				System.out.println("�I�� �M���e��");
				points.removeAll(points);
				rectangles.removeAll(rectangles);
				ovals.removeAll(ovals);
				lines.removeAll(lines);
				canvas.repaint();
			}
		}
	}
	public class Canvas extends JPanel{
		ArrayList<Graphics> arr = new ArrayList<Graphics>(); 
		public Canvas() {
			addMouseListener(new MouseAdapter() {
		    	@Override 
		    	public void mousePressed(MouseEvent event) {
		    		firstPoint = event.getPoint();
		    	}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent event) {
					if(paintTool.getSelectedIndex() == 0) {
			 			firstPoint = event.getPoint();
			 			points.add(new paintPoint(firstPoint,color,paintSize));
			 			repaint();
			 		}
			 		secondPoint = event.getPoint();
			 		repaint();
			    }
		    });
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent event) {
			 		secondPoint = event.getPoint();
			 		Point p = new Point();
			 		if(firstPoint.x>secondPoint.x) {
			 			p.x = secondPoint.x;
			 			if(firstPoint.y>secondPoint.y) {
			 				p.y = secondPoint.y;
			 			}
			 			else {
			 				p.y = firstPoint.y;
			 			}
			 		}
			 		else if(firstPoint.x<secondPoint.x) {
			 			p.x = firstPoint.x;
			 			if(firstPoint.y>secondPoint.y) {
			 				p.y = secondPoint.y;
			 			}
			 			else {
			 				p.y = firstPoint.y;
			 			}
			 		}
			 		if(paintTool.getSelectedIndex() == 0) {
			 			secondPoint = event.getPoint();
			 			points.add(new paintPoint(secondPoint,color,paintSize));
			 			repaint();
			 		}
			 		else if(paintTool.getSelectedIndex() == 1) {
			 			lines.add(new paintLine(firstPoint,secondPoint,paintSize,color,fillOption.isSelected()));
				 		repaint();
			 		}
			 		else if(paintTool.getSelectedIndex() == 2) {
			 			ovals.add(new paintOval(p,color, Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y),paintSize,fillOption.isSelected()));
				 		repaint();
			 		}
			 		else if(paintTool.getSelectedIndex() == 3) {
			 			rectangles.add(new paintRectangle(p,color, Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y),paintSize,fillOption.isSelected(),false));
				 		repaint();
			 		}
			 		else if(paintTool.getSelectedIndex() == 4) {
			 			rectangles.add(new paintRectangle(p,color, Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y),paintSize,fillOption.isSelected(),true));
				 		repaint();
			 		}
			    }
		    });
			
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for(paintPoint point:points) {
				g2.setColor(point.pointColor);
				g2.setStroke(new BasicStroke(point.pointSize));
				g2.fillOval(point.x,point.y,point.pointSize,point.pointSize);
			}
			
			for(paintOval oval:ovals) {
				if(oval.fill == true) {
					g2.setColor(oval.pointColor);
					g2.setStroke(new BasicStroke(oval.size));
					g2.fillOval(oval.x, oval.y,oval.width,  oval.height);
				}
				else {
					g2.setColor(oval.pointColor);
					g2.setStroke(new BasicStroke(oval.size));
					g2.drawOval(oval.x, oval.y,oval.width,  oval.height);
				}
			}
			
			if(paintTool.getSelectedIndex() == 2) {
				if(fillOption.isSelected()) {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.fillOval(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y));
				}
				else {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.drawOval(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y));
				}
			}
			
			for(paintRectangle rec: rectangles) {
				if(rec.roundOption == false) {
					if(rec.fillOption == true) {
						g2.setColor(rec.pointColor);
						g2.setStroke(new BasicStroke(rec.pointSize));
						g2.fillRect(rec.x,rec.y,rec.width,rec.length);
					}
					else if(rec.fillOption == false) {
						g2.setColor(rec.pointColor);
						g2.setStroke(new BasicStroke(rec.pointSize));
						g2.drawRect(rec.x,rec.y,rec.width,rec.length);
					}
				}
				else if(rec.roundOption == true) {
					if(rec.fillOption == true) {
						g2.setColor(rec.pointColor);
						g2.setStroke(new BasicStroke(rec.pointSize));
						g2.fillRoundRect(rec.x,rec.y,rec.width,rec.length,rec.pointSize+8,rec.pointSize+8);
					}
					else if(rec.fillOption == false) {
						g2.setColor(rec.pointColor);
						g2.setStroke(new BasicStroke(rec.pointSize));
						g2.drawRoundRect(rec.x,rec.y,rec.width,rec.length,rec.pointSize+8,rec.pointSize+8);
					}
				}
			}
			
			if(paintTool.getSelectedIndex() == 3) {
				if(fillOption.isSelected()) {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.fillRect(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y));
				}
				else if(!fillOption.isSelected()) {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.drawRect(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y));
				}
			}
			
			if(paintTool.getSelectedIndex() == 4) {
				if(fillOption.isSelected()) {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.fillRoundRect(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y),paintSize+8,paintSize+8);
				}
				else if(!fillOption.isSelected()) {
					g2.setColor(color);
					g2.setStroke(new BasicStroke(paintSize));
					g2.drawRoundRect(Math.min(firstPoint.x, secondPoint.x), Math.min(firstPoint.y, secondPoint.y), Math.abs(firstPoint.x-secondPoint.x),  Math.abs(firstPoint.y-secondPoint.y),paintSize+8,paintSize+8);
				}
			}
			for(paintLine line:lines) {
				if(line.fill) {
					g2.setColor(line.color);
					Stroke dashed = new BasicStroke(line.paintSize);
					g2.setStroke(dashed);
					g2.drawLine(line.firstPoint.x,line.firstPoint.y,line.secondPoint.x,line.secondPoint.y);
				}
				else if(!line.fill) {
					g2.setColor(line.color);
					Stroke dashed = new BasicStroke(line.paintSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
					g2.setStroke(dashed);
					g2.drawLine(line.firstPoint.x,line.firstPoint.y,line.secondPoint.x,line.secondPoint.y);
				}
			}
			if(paintTool.getSelectedIndex() == 1) {
				if(fillOption.isSelected()) {
					g2.setColor(color);
					Stroke dashed = new BasicStroke(paintSize);
					g2.setStroke(dashed);
					g2.drawLine(firstPoint.x,firstPoint.y,secondPoint.x,secondPoint.y);
				}
				else if(!fillOption.isSelected()) {
					g2.setColor(color);
					Stroke dashed = new BasicStroke(paintSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
					g2.setStroke(dashed);
					g2.drawLine(firstPoint.x,firstPoint.y,secondPoint.x,secondPoint.y);
				}
			}
		}
		
	}//end Canvas
	
	//four different shape classes
	private class paintPoint extends Point{
		public Color pointColor;
		public int pointSize;
		
		public paintPoint(Point p,Color color,int size) {
			super(p);
			pointColor = color;
			pointSize = size;
		}
	}
	
	private class paintRectangle extends Point{
		public Color pointColor;
		public int pointSize;
		public int width;
		public int length;
		public boolean fillOption;
		public boolean roundOption;
		public paintRectangle(Point p,Color color,int x,int y,int size,boolean fill,boolean round) {
			super(p);
			pointColor = color;
			pointSize = size;
			fillOption = fill;
			width = x;
			length = y;
			roundOption = round;
		}
	}
	
	private class paintOval extends Point{
		public Color pointColor;
		public int width;
		public int height;
		public int size;
		public boolean fill;
		public paintOval(Point p,Color color,int x,int y,int paintSize,boolean fillOption) {
			super(p);
			pointColor = color;
			width = x;
			height = y;
			size = paintSize;
			fill = fillOption;
		}
	}
	
	private class paintLine extends Point {
		public Point firstPoint;
		public Point secondPoint;
	    public boolean fill;
	    public int paintSize;
	    public Color color;
		public paintLine(Point firstP,Point secondP,int paintSize,Color color,boolean fill) {
			firstPoint = firstP;
			secondPoint = secondP;
			this.paintSize = paintSize;
			this.color = color;
			this.fill = fill;
		}
	}
}