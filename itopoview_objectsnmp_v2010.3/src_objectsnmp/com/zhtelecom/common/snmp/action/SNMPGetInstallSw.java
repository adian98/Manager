package com.zhtelecom.common.snmp.action;

import java.awt.event.*;

import com.l2fprod.common.beans.editor.*;
import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor.*;
import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
import com.zhtelecom.common.snmp.view.*;
import com.zhtelecom.common.table.*;

public class SNMPGetInstallSw extends SNMPBaseAction
{
    public SNMPGetInstallSw()
    {
        super("�鿴�����װ״̬");
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
                        {"������", "�������","�������", "��װID"};
                    return cols;
                }

                public Object[] getRowDatas(Object obj)
                {
                    MibSoftwareInstallEntry table = (MibSoftwareInstallEntry) obj;


                    Object[] viDatas =
                        {
                        new Integer(table.getHrSWInstalledIndex()), table.getHrSWInstalledName(),
                        new Integer(table.getHrSWInstalledType()),table.getHrSWInstalledID()
                    };

                    return viDatas;
                }

                public Class getTableObjectClass()
                {
                    return MibSoftwareInstallEntry.class;
                }
            };

            SNMPTarget target = getTarget();
            SNMPTableDialog dialog = new SNMPTableDialog(getFrame(), desc, false, false, false, true);
            dialog.setSize(600, 500);
            Value[] vs = new Value[4];
            vs[0] = new Value(new Integer(1), "δ֪");
            vs[1] = new Value(new Integer(2), "����ϵͳ���");
            vs[2] = new Value(new Integer(3), "�豸�������");
            vs[3] = new Value(new Integer(4), "Ӧ�����");
            dialog.table.setRendererKVType(2, vs);

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
            SNMPGetInstallSw action = new SNMPGetInstallSw();
            action.actionPerformed(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
