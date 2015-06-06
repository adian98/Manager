package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.zhtelecom.common.topograph.TopoGraphView;
import com.zhtelecom.common.topograph.TopoObject;
import com.zhtelecom.common.topograph.example.NMSExample;

public class ActionAddAlarm extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {
        //֮ǰ�ѱ���ֵ�������ȡ�����˶���Ҳ����ͨ��TopoGraphView.getContextGraphView()���ٻ�ȡΨһʵ����
        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");

        String rs = JOptionPane.showInputDialog(topoView,
            "������Ҫ��ӵĸ澯����ļ���(0,1,2,3,4,5,6),0-��ͣ�6-���");
        if (rs == null || rs.equals(""))
        {
            rs = "2";
        }

        int level = Integer.parseInt(rs);
        TopoObject obj = topoView.getSelectedObject();
        obj.addAlarm("�澯" + NMSExample.getNum(), level);
    }

    public ActionAddAlarm()
    {
        super("��Ӹ澯");
    }
}
