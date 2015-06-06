import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class FlowInterface {
	
	TextField tfTxt;
	
	public void bar(int n1,int n2,int n3,int n4) {
  	  JFrame frame=new JFrame("����ͳ����״ͼ");  
  	    frame.setLayout(new GridLayout(1,1,10,10));  
  	    frame.add(new BarChart(n1,n2,n3,n4).getChartPanel());           //�������ͼ  
  	    frame.setBounds(50, 50, 800, 600); 
  	    frame.pack();
  	    frame.setVisible(true);  
  }
	
	
	 public void pie(int n1,int n2,int n3,int n4) {
	  	  JFrame frame=new JFrame("����ͳ�Ʊ�״ͼ");  
	  	    frame.setLayout(new BorderLayout());  
	  	    frame.add(new PieChart(n1,n2,n3,n4).getChartPanel(),BorderLayout.CENTER);         
	  	    tfTxt = new TextField();
	  	    frame.add(tfTxt,BorderLayout.SOUTH);
	  	    TFListener tf = new TFListener();
			tfTxt.addActionListener(tf);
	  	    frame.setBounds(50, 50, 800, 600); 
	  	    frame.pack();
	  	    frame.setVisible(true);  
	  }


	public void subBar(int[][] c, int[][] i) {
		JFrame frame=new JFrame("����ͳ����״ͼ");  
  	    frame.setLayout(new GridLayout(1,1,10,10));  
  	    frame.add(new SubBarChart(c, i).getChartPanel());           //�������ͼ  
  	    frame.setBounds(50, 50, 800, 600); 
  	    frame.pack();
  	    frame.setVisible(true);  
	}
	
	private class TFListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			tfTxt.setText("");
			
			if(str.equals("��������")) {
				JFrame frame=new JFrame("����ͳ�Ʊ�״ͼ");  
		  	    frame.setLayout(new GridLayout(1,1,10,10));  
		  	    frame.add(new SubPieChart(DisplayInterface.contentFlow).getChartPanel());           //�������ͼ  
		  	    frame.setBounds(50, 50, 800, 600); 
		  	    frame.pack();
		  	    frame.setVisible(true);  
			}
			if(str.equals("�������")) {
				JFrame frame=new JFrame("����ͳ�Ʊ�״ͼ");  
		  	    frame.setLayout(new GridLayout(1,1,10,10));  
		  	    frame.add(new SubPieChart(DisplayInterface.identityFlow).getChartPanel());           //�������ͼ  
		  	    frame.setBounds(50, 50, 800, 600); 
		  	    frame.pack();
		  	    frame.setVisible(true);  
			}
		}
		
	}
}
