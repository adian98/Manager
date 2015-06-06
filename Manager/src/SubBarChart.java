import java.awt.Font;  
  


import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  

import java.awt.GridLayout;  

import javax.swing.JFrame; 
  
public class SubBarChart { 	
	
	CategoryDataset dataset;
	
	int[][] c;
	int[][] i;


	ChartPanel frame1;
    
    public  SubBarChart(int[][] c, int[][] i){ 
    	
    	this.c = c;

      	this.i = i;

    	
        dataset = getDataSet();  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                             "���ع���������ͳ��", // ͼ�����  
                            "��̬���", // Ŀ¼�����ʾ��ǩ  
                            "����", // ��ֵ�����ʾ��ǩ  
                            dataset, // ���ݼ�  
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ  
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
                            false,          // �Ƿ����ɹ���  
                            false           // �Ƿ�����URL����  
                            );  
          
        //�����￪ʼ  
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
            
          //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������  
            
         frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame  
           
    }  


	public CategoryDataset getDataSet() {  
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	    if(c!=null) {
	    	for(int j=0;j<c.length;j++) {
			     dataset.addValue(c[j][1], "�����߼���"+c[j][0], "��������"); 
			   }
	    }
	   if(i!=null) { 
		   for(int j=0;j<i.length;j++) {
		     dataset.addValue(i[j][1], "����߼���"+i[j][0], "�������"); 
		   }
  
	   }
	    
//	    dataset.addValue(0, "�߼���1", "��ַ����"); 
//	    dataset.addValue(0, "�߼���2", "��ַ����");  
//	    dataset.addValue(0, "�߼���3", "��ַ����");  
//	    dataset.addValue(0, "�߼���4", "��ַ����"); 
//	    
//	    dataset.addValue(0, "�߼���1", "��������"); 
//	    dataset.addValue(0, "�߼���2", "��������");  
//	    dataset.addValue(0, "�߼���3", "��������");  
//	    dataset.addValue(0, "�߼���4", "��������"); 
  
	        return dataset;  
	}  
    
    
    public ChartPanel getChartPanel(){  
        return frame1;  
          
    } 
    
    
    
    
   
}  
