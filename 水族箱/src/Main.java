/*
 * �@��:�i�ɵ�
 * �t��:��ޤG
 * �Ǹ�:108403523
 * �s�W�\��:1.�ĥΦۭq���I���Ϥ�2.�s�W��������3.���ѭI������
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import TheMusic.AePlayWave;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "���I�����֥i�}�n��\n(���ֶȼ���@��)");
		AePlayWave apw=new AePlayWave("src/Literature.wav");
		apw.start();
		FishTank fishTank = new FishTank();
		fishTank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fishTank.setSize(1075,770);
		fishTank.setVisible(true);
		fishTank.setLocationRelativeTo(null);
		fishTank.setResizable(false);
	}

}
