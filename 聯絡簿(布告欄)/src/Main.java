/*
 * �@��: �i�ɵ�
 * �Ǹ�: 108403523
 * �t��: ���2A
*/
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Main implements icon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int result = JOptionPane.showConfirmDialog(null,"�O�_���o�G��?","�n�J",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icon.img1);
		if(result == 1) {
			DefaultFrame frame1 = new DefaultFrame();
			//DefaultFrame.openFile();
			//DefaultFrame.setDefaultText();
			//DefaultFrame.readRecords();
			frame1.setSize(1000,800);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setVisible(true);
		}
		if(result == 0) {
			EditableFrame frame2 = new EditableFrame();
			frame2.setSize(1000,800);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setVisible(true);
		}
		
	}

}
