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
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Dimension;
public class Main implements icon{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PainterFrame frame = new PainterFrame();
		frame.setSize(750,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame,"Wellcome","�T��",1,icon.img1);
		JOptionPane.showMessageDialog(frame,"��ܵ���@�e�ɡA�T�ζ�","���ɤp�m�{1",1,icon.img2);
		JOptionPane.showMessageDialog(frame,"�]�w�e���C��ɡA�аO�o���M���e��\n(�������u�X�{�@���A�Цh�[�d�N)","���ɤp�m�{2",1,icon.img2);
	}
}

