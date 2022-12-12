/*
 * 作者: 張棨翔
 * 學號: 108403523
 * 系級: 資管2A
 * 加分功能 : 1.設定畫布背景色，按鈕顏色隨著設定的背景色改變
 * 		   2.滑鼠在使用橡皮擦時，會將鼠標形狀變更為橡皮擦
 * 		   3.使用滑鼠在畫布上作畫時，滑鼠自動變為畫筆的形狀
 * 		   4.選擇筆刷顏色的按鈕會隨著選擇的顏色而改變按鈕顏色
 *         5.其他鼠標形狀設定
 *         6.下方黑色提示欄會提示滑鼠是否位於工具列中
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
		JOptionPane.showMessageDialog(frame,"Wellcome","訊息",1,icon.img1);
		JOptionPane.showMessageDialog(frame,"選擇筆刷作畫時，禁用填滿","溫馨小叮嚀1",1,icon.img2);
		JOptionPane.showMessageDialog(frame,"設定畫布顏色時，請記得先清除畫面\n(此視窗只出現一次，請多加留意)","溫馨小叮嚀2",1,icon.img2);
	}
}

