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
                             "可重构网络流量统计", // 图表标题  
                            "多态类别", // 目录轴的显示标签  
                            "流量", // 数值轴的显示标签  
                            dataset, // 数据集  
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                            true,           // 是否显示图例(对于简单的柱状图必须是false)  
                            false,          // 是否生成工具  
                            false           // 是否生成URL链接  
                            );  
          
        //从这里开始  
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
          chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
          chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
            
          //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题  
            
         frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame  
           
    }  


	public CategoryDataset getDataSet() {  
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	    if(c!=null) {
	    	for(int j=0;j<c.length;j++) {
			     dataset.addValue(c[j][1], "内容逻辑网"+c[j][0], "内容网络"); 
			   }
	    }
	   if(i!=null) { 
		   for(int j=0;j<i.length;j++) {
		     dataset.addValue(i[j][1], "身份逻辑网"+i[j][0], "身份网络"); 
		   }
  
	   }
	    
//	    dataset.addValue(0, "逻辑网1", "地址网络"); 
//	    dataset.addValue(0, "逻辑网2", "地址网络");  
//	    dataset.addValue(0, "逻辑网3", "地址网络");  
//	    dataset.addValue(0, "逻辑网4", "地址网络"); 
//	    
//	    dataset.addValue(0, "逻辑网1", "服务网络"); 
//	    dataset.addValue(0, "逻辑网2", "服务网络");  
//	    dataset.addValue(0, "逻辑网3", "服务网络");  
//	    dataset.addValue(0, "逻辑网4", "服务网络"); 
  
	        return dataset;  
	}  
    
    
    public ChartPanel getChartPanel(){  
        return frame1;  
          
    } 
    
    
    
    
   
}  
