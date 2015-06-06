import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.zhtelecom.common.topograph.*;
import com.zhtelecom.common.topograph.example.NMSExample;


public class DisplayInterface {
	JFrame frame;
	
	TopoGraphView topoView;
	
	TextField tfTxt = new TextField();
	
	TextArea ta = new TextArea();
	
	String str = "wait";
	
	int flag = 0;
	
	ManagmentServer ms;
	
	static int[][] contentFlow = null;
	static int contentFlowAll = 0;
	
	static int[][] identityFlow = null;
	static int identityFlowAll = 0;
	
	public void setContentFlow(int[][] r) {           //�������
		int subnetNum = r.length;
		if(contentFlow==null) {
			contentFlow = new int[subnetNum][2];
			for(int i=0;i<subnetNum;i++) {
				contentFlow[i][0] +=r[i][0];
				contentFlow[i][1] +=r[i][1];
				contentFlowAll += contentFlow[i][1];
			}
		}
		else {
			for(int i=0;i<subnetNum;i++) {
				contentFlow[i][1] +=r[i][1];
				contentFlowAll += contentFlow[i][1];
			}
		}
		
	}


			
	
	public void setIdentityFlow(int[][] r) {           //�������
		int subnetNum = r.length;
		if(identityFlow==null) {
			identityFlow = new int[subnetNum][2];
			for(int i=0;i<subnetNum;i++) {
				identityFlow[i][0] =r[i][0];
				identityFlow[i][1] =r[i][1];
				identityFlowAll += identityFlow[i][1];
			}
		}
		else {
			identityFlowAll=0;
			for(int i=0;i<subnetNum;i++) {
				identityFlow[i][1] =r[i][1];
				identityFlowAll += identityFlow[i][1];
			}
		}
		
	}


	public DisplayInterface(ManagmentServer ms) {
		this.ms = ms;
	}


	public void display() {                                      //��ʾ����
		frame = new JFrame("���ع�����");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topoView = new TopoGraphView(new TopoDataSource());  
		frame.getContentPane().add(topoView, BorderLayout.CENTER); 
		frame.getContentPane().add(tfTxt, BorderLayout.SOUTH);
		
		//topoView.setBackground(Color.CYAN);
		
		TFListener tf = new TFListener();
		tfTxt.addActionListener(tf);
		
		frame.getContentPane().add(ta, BorderLayout.EAST);
		ta.setFont(new Font("����",Font.BOLD,13));
		//ta.setBackground(Color.pink);
		frame.setVisible(true);
		
		HelloMouseAction menuHandle = new HelloMouseAction(); //�����¼��������
		ms.di.topoView.setMouseHandle(menuHandle); //��������ͼ���ô������
	}
	
	
	
	
	private class TFListener implements ActionListener {     //�����²����������������ݣ�����OKʱͬ���·�������Change��������ı�ǰ��·����·���������ʱ����1,3,5/1,6,2,5��,·���ı�

		@Override
		public void actionPerformed(ActionEvent e) {
			
			str = tfTxt.getText().trim();
			tfTxt.setText("");
			
			if(str.equals("content OK")) {
				ta.setText(ta.getText()+str+"\n\n");
				str = "wait";
				ms.c.agreeForwarding();
			}
			
			else if(str.equals("identity OK")) {
				ta.setText(ta.getText()+str+"\n\n");
				str = "wait";
				ms.i.agreeForwarding();
			}
			
			else if(str.equals("content Change")) {
				ta.setText(ta.getText()+str+"\n");
				str = "wait";
				flag = 1;
			}
			
			else if(str.equals("identity Change")) {
				ta.setText(ta.getText()+str+"\n");
				str = "wait";
				flag = 2;
			}
			
			else if(flag != 0) {
				
				ta.setText(ta.getText()+str+"\n\n");
				String[] strs=str.split("/");
								

					String[] strArray1 = strs[0].split(",");
					String[] strArray2 = strs[1].split(",");
					
					int r1[] = new int[strArray1.length];
					int r2[] = new int[strArray2.length];
					
					for(int j=0; j<strArray1.length;j++) 
						
						r1[j] = Integer.parseInt(strArray1[j]);

																	
					for(int i=0; i<strArray2.length;i++) 
						
						r2[i] = Integer.parseInt(strArray2[i]);	
										
					
					if(flag == 1) {
						int subNum = 0;
						for(int i=0;i<ms.c.t.subTopology.length;i++) {
							if(r1[0] == ms.c.t.subTopology[i][0]) {
								subNum = i;
							}
						}
						
						ms.c.routingChange (r1,r2,subNum);
					}
					if(flag == 2) {
						int subNum = 0;
						for(int i=0;i<ms.i.t.subTopology.length;i++) {
							if(r1[0] == ms.i.t.subTopology[i][0]) {
								subNum = i;
							}
						}
						ms.i.routingChange (r1,r2,subNum);
					}
					
					//System.out.println(ms.c.arrays.size());               //debug
					
//					for(int i=0;i<ms.c.arrays.size();i++) {
//						for(int j=0;j<ms.c.arrays.get(i).length;j++) {
//								System.out.print(ms.c.arrays.get(i)[j]);    //debug
//						}
//						System.out.print("\n");
//					}	
					flag = 0;
					str = "wait";
			}
			
			else if(str.equals("Barchart")) {
				ta.setText(ta.getText()+str+"\n\n");				
				new FlowInterface().bar(contentFlowAll, identityFlowAll, 0, 0);
			}
			
			else if(str.equals("Barchart2")) {
				ta.setText(ta.getText()+str+"\n\n");
				new FlowInterface().subBar(contentFlow, identityFlow);
			}
			
			else if(str.equals("Piechart")) {
				ta.setText(ta.getText()+str+"\n\n");
				new FlowInterface().pie(contentFlowAll, identityFlowAll, 0, 0);
			}
			
			else if(str.equals("Timechart")) {
				ta.setText(ta.getText()+str+"\n\n");
				new TimeSeriesChart().start();
			}
			
			
			
		}		
	}

	
	class HelloMouseAction implements TopoMouseHandle                     //����������
	{
	    
		int x,y;
		
		public int objectEqual(double[][] m) {
			if(m != null) {
				for(int i=1;i<m.length+1;i++) {
					if(topoView.getSelectedObject().equals(ms.c.t.source.getTopoObject("contentRouter"+i))) {
						return 1;
					}
					else if(topoView.getSelectedObject().equals(ms.i.t.source.getTopoObject("identityRouter"+i))) {
						return 2;
					}
					
					else {
						for(int j=1;j<m.length+1;j++) {
							if(topoView.getSelectedObject().equals(ms.c.t.source.getTopoObject("cline"+i+j))) {
								x=i-1;
								y=j-1;
								return 3;
							}
							else if(topoView.getSelectedObject().equals(ms.i.t.source.getTopoObject("iline"+i+j))) {
								x=i-1;
								y=j-1;
								return 4;
							}
						}
					}
				}
				
				return 0;
			}
			else
				return 0;
		}
		
		
		//������ͼ�����������Ҽ��Ĳ˵�
	    public JPopupMenu createRightClickMenu(TopoGraphView topoView)
	    {
	        JPopupMenu menu = new JPopupMenu();
	        menu.add("�Ҽ�����:" + topoView.getSelectedObject());
	        return menu;
	    }
	    //������ͼ�հ��������Ҽ��Ĳ˵�
	    public JPopupMenu createRightClickBlankMenu(TopoGraphView topoView)
	    {
	        JPopupMenu menu = new JPopupMenu();
	        menu.add("�Ҽ��հ����򣬵�ǰ���磺" + topoView.getCurrentNetwork());
	        return menu;
	    }
	    //˫���¼�
	    
	    public void doubleClickAction(TopoGraphView topoView)
	    {
	    //   int f = objectEqual(ms.c.t.topologyArray);
	    //	System.out.println(f);                              //debug
	    	try {
		    	if(objectEqual(ms.c.t.topologyArray)==1)
		    	JOptionPane.showMessageDialog(topoView, "���ݣ�" + "����", "˫������",JOptionPane.INFORMATION_MESSAGE);
		       else if(objectEqual(ms.c.t.topologyArray)==3)
			    	JOptionPane.showMessageDialog(topoView, "��·Ȩֵ��" + ms.c.t.topologyArray[x][y], "˫������",JOptionPane.INFORMATION_MESSAGE);
		       
		       	       	       
		       else if(objectEqual(ms.i.t.topologyArray)==2)
		    	JOptionPane.showMessageDialog(topoView, "���ݣ�" + "����", "˫������",JOptionPane.INFORMATION_MESSAGE);
		       else if(objectEqual(ms.i.t.topologyArray)==4)
			    	JOptionPane.showMessageDialog(topoView, "��·Ȩֵ��" + ms.i.t.topologyArray[x][y], "˫������",JOptionPane.INFORMATION_MESSAGE);
		       
		       else JOptionPane.showMessageDialog(topoView, "����" + topoView.getSelectedObject(), "˫������", JOptionPane.INFORMATION_MESSAGE);
	       
	    	} catch(NullPointerException e) {
	    		System.out.println("brfore you doubleclick, you should add all the client!");
	    	}
	    }
	    
		public void actionClick(TopoGraphView arg0, TopoObject arg1) {
			
		}

	}
	

	
}
