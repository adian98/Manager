package com.zhtelecom.common.snmp.action;

import java.awt.event.*;

import com.l2fprod.common.beans.editor.*;
import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor.*;
import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
import com.zhtelecom.common.snmp.view.*;
import com.zhtelecom.common.table.*;

public class SNMPGetIfNetStatus extends SNMPBaseAction
{
    public SNMPGetIfNetStatus()
    {
        super("�鿴��������״̬");
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            ObjectTableDesc desc = new ObjectTableDesc()
            {
                public Object[] getcolumnNames()
                {
                    String[] cols =
                        {"����ӿ����", "�ӿ�����",
                        "���յ�����", "���͵�����", "���յĵ���������", "���յĹ㲥���鲥������", "���͵ĵ���������", "���͵Ĺ㲥���鲥������"};
                    return cols;
                }

                public Object[] getRowDatas(Object obj)
                {
                    MibIfEntry table = (MibIfEntry) obj;
                    Object[] viDatas =
                        {new Integer(table.getIfIndex()), table.getIfDescr(),
                        "" + table.getIfInOctets() / 1000000 + "MB" + (table.getIfInOctets() % 1000000) / 1000 + "KB",
                        "" + table.getIfOutOctets() / 1000000 + "MB" + (table.getIfOutOctets() % 1000000) / 1000 + "KB",
                        new Long(table.getIfInUcastPkts()),
                        new Long(table.getIfInNUcastPkts()),
                        new Long(table.getIfOutUcastPkts()),
                        new Long(table.getIfOutNUcastPkts()),

                    };

                    return viDatas;
                }

                public Class getTableObjectClass()
                {
                    return MibIfEntry.class;
                }
            };

            SNMPTarget target = getTarget();
            SNMPTableDialog dialog = new SNMPTableDialog(getFrame(), desc, false, false, false, true);
            dialog.setSize(700, 300);

            Value[] vs = new Value[3];
            vs[0] = new Value(new Integer(1), "����");
            vs[1] = new Value(new Integer(2), "ֹͣ");
            vs[2] = new Value(new Integer(3), "����");
            dialog.table.setRendererKVType(2, vs);
            dialog.table.setRendererKVType(5, vs);
            dialog.setTitle(getTitle());
            dialog.myInit(target, null, null);
            dialog.processQueryall();
            DialogTools.dialogPack(getFrame(), dialog);
        } catch (Exception ex)
        {
            DialogTools.showErroMsgFromEx(getFrame(), getErroTitle(), ex);
        }

    }

    public static void main(String[] ag)
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
            SNMPGetIfNetStatus action = new SNMPGetIfNetStatus();
            action.actionPerformed(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
