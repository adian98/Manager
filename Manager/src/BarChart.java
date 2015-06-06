
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
  
public class BarChart { 	
	
	CategoryDataset dataset;
	
	int n1,n2,n3,n4;

	ChartPanel frame1;
    
    public  BarChart(int n1,int n2,int n3,int n4){ 
    	
    	this.n1 = n1;
    	this.n2 = n2;
    	this.n3 = n3;
    	this.n4 = n4;

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
	    dataset.addValue(n1, "��������", "��������");  
	    dataset.addValue(n2, "�������", "�������");  
	   // dataset.addValue(n3, "��ַ����", "��ַ����");  
	   // dataset.addValue(n4, "��������", "��������");  
	        return dataset;  
	}  
    
    
    public ChartPanel getChartPanel(){  
        return frame1;  
          
    } 
    
    
    
    
   
}  
