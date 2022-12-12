/*
 * 作者:張棨翔
 * 系級:資管二
 * 學號:108403523
 * 新增功能:1.採用自訂的背景圖片2.新增魚的種類3.提供背景音樂
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import TheMusic.AePlayWave;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "有背景音樂可開聲音\n(音樂僅撥放一次)");
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
