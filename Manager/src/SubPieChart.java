
import java.awt.Font;  
import java.awt.GridLayout;
import java.text.DecimalFormat;  
import java.text.NumberFormat;  
  

import javax.swing.JFrame;
import javax.swing.JPanel;  
  

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.general.DefaultPieDataset;  
  
public class SubPieChart { 
	int[][] n;
    ChartPanel frame1;  
    public SubPieChart(int[][] n){  
    	this.n = n;

          DefaultPieDataset data = getDataSet();  
          JFreeChart chart = ChartFactory.createPieChart3D("������ռ����",data,true,false,false);  
        //���ðٷֱ�  
          PiePlot pieplot = (PiePlot) chart.getPlot();  
          DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������  
          NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����  
          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����  
          pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�  
        
      //û�����ݵ�ʱ����ʾ������  
          pieplot.setNoDataMessage("��������ʾ");  
          pieplot.setCircular(false);  
          pieplot.setLabelGap(0.02D);  
        
          pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ  
          pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ  
         frame1=new ChartPanel (chart,true);  
          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
          PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������  
          piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������  
          chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));  
    }  
    private  DefaultPieDataset getDataSet() {  
        DefaultPieDataset dataset = new DefaultPieDataset();  
        for(int j=0;j<n.length;j++) {
        	dataset.setValue("�߼���"+n[j][0],n[j][1]);  
        }
   
        return dataset;  
    }  
    public ChartPanel getChartPanel(){  
        return frame1;  
          
    }
    
   
}  