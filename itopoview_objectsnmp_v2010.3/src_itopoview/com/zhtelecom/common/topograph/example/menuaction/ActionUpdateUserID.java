package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.zhtelecom.common.topograph.TopoGraphView;
import com.zhtelecom.common.topograph.TopoObject;
import com.zhtelecom.common.topograph.example.NMSExample;

public class ActionUpdateUserID extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {

        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");
        TopoObject topoObject = topoView.getSelectedObject();
        String rs = JOptionPane.showInputDialog(topoView, "�������µ��û�ID����");
        if (rs == null || rs.equals(""))
        {
            rs = "newName" + NMSExample.getNum();
        }
        StringBuffer userID = (StringBuffer) topoObject.getUserID();
        userID.replace(0, userID.length(), rs);

        //֪ͨ����ͼ���û����ݷ����ı䡣
        topoObject.fireChanged();

    }

    public ActionUpdateUserID()
    {
        super("�޸��û�ID����");
    }
}
