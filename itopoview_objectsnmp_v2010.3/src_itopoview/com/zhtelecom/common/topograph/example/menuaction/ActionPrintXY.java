package com.zhtelecom.common.topograph.example.menuaction;

import javax.swing.AbstractAction;
import com.zhtelecom.common.topograph.TopoGraphView;
import java.awt.event.ActionEvent;

import com.zhtelecom.common.topograph.TopoNode;

public class ActionPrintXY extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {
        //֮ǰ�ѱ���ֵ�������ȡ�����˶���Ҳ����ͨ��TopoGraphView.getContextGraphView()���ٻ�ȡΨһʵ����
        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");
        java.util.Vector rs= topoView.getTopoDataSource().getAllTopoNode();
        for(int i=0;i<rs.size();i++)
        {
            TopoNode node=(TopoNode) rs.get(i);
            System.out.println(""+node+":X="+node.getX()+", Y="+node.getY());
        }
    }

    public ActionPrintXY()
    {
        super("��ӡ���нڵ�����");
    }
}
