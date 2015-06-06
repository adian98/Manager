package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.zhtelecom.common.topograph.TopoGraphView;
import com.zhtelecom.common.topograph.TopoObject;
import com.zhtelecom.common.topograph.TopoNode;
import com.zhtelecom.common.topograph.TopoNetwork;

public class ActionAlarmView extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {

        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");
        TopoObject obj = topoView.getSelectedObject();
        String size = "��ǰ�澯������" + obj.getAllAlarms().size();
        java.util.Iterator iter = obj.getAllAlarmSeverity().iterator();
        String msg = "��ǰ���ڵĸ澯����:";
        while (iter.hasNext())
        {
            msg = msg + iter.next() + "��";
        }
        JOptionPane.showMessageDialog(topoView, msg, size,
                                      JOptionPane.INFORMATION_MESSAGE);


    }

    public ActionAlarmView()
    {
        super("��ʾ�澯");
    }
}
