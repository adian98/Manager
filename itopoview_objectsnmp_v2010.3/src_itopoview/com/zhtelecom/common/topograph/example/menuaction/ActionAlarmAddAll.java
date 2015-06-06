package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.*;
import javax.swing.*;

import com.zhtelecom.common.topograph.*;
import com.zhtelecom.common.topograph.example.*;

public class ActionAlarmAddAll extends AbstractAction
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

        TopoDataSource tds = topoView.getTopoDataSource();
        java.util.Vector vec = tds.getAllTopoObject();

        for (int i = 0; i < vec.size(); i++)
        {
            TopoObject obj = (TopoObject) vec.get(i);
            obj.addAlarm("�澯" + NMSExample.getNum(), level);
        }
    }

    public ActionAlarmAddAll()
    {
        super("������Ӹ澯");
    }
}
