package com.zhtelecom.common.snmp.action;

import java.awt.event.*;
import javax.swing.*;

import com.zhtelecom.common.topograph.*;

public class UpdateIPAddress extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {

        TopoGraphView topoView = TopoGraphView.getContextGraphView();
        TopoObject topoObject = topoView.getSelectedObject();
        String rs = JOptionPane.showInputDialog(topoView, "�������µ�IP��ַ");
        if (rs == null || rs.equals(""))
        {
            rs = "127.0.0.1";
        }
        StringBuffer userID = (StringBuffer) topoObject.getUserID();
        userID.replace(0, userID.length(), rs);

        //֪ͨ����ͼ���û����ݷ����ı䡣
        topoObject.fireChanged();

    }

    public UpdateIPAddress()
    {
        super("�޸�IP��ַ");
    }
}
