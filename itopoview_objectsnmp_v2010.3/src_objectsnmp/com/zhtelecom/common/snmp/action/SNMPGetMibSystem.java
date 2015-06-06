package com.zhtelecom.common.snmp.action;

import java.awt.event.*;
import javax.swing.table.*;

import com.l2fprod.common.beans.*;
import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
import com.zhtelecom.common.snmp.view.*;

public class SNMPGetMibSystem extends SNMPBaseAction
{

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            SNMPTarget target = getTarget();
            MibSystemBeanInfo beaninfo = new MibSystemBeanInfo();
            SNMPSheetDialog dialog = new SNMPSheetDialog(getFrame(), false, false, true, beaninfo);
            dialog.setTitle(getTitle());

            MibSystem mib = new MibSystem();
            dialog.initObject(target, mib);
            dialog.processFresh();
            dialog.setSize(500, 300);

            DialogTools.dialogPack(getFrame(), dialog);
        } catch (Exception ex)
        {
            DialogTools.showErroMsgFromEx(getFrame(), getErroTitle(), ex);
        }

    }

    public SNMPGetMibSystem()
    {
        super("�鿴SNMPϵͳ״̬");
    }

    public static class MibSystemBeanInfo extends BaseBeanInfo
    {

        public MibSystemBeanInfo()
        {
            super(MibSystem.class);
            addProperty("sysDescr").setReadOnly().setDisplayName("ϵͳ������");
            addProperty("sysUpTime").setReadOnly().
                setPropertyTableRendererClass(TimeRender.class).setDisplayName("ϵͳ����ʱ�䣺");
            addProperty("sysObjectID").setReadOnly().setDisplayName("ϵͳOIDֵ��");
            addProperty("sysName").setDisplayName("Ӳ������");
            addProperty("sysContact").setDisplayName("��ϵ�ˣ�");
            addProperty("sysLocation").setDisplayName("����λ�ã�");
        }
    }

    public static class TimeRender extends DefaultTableCellRenderer
    {
        public void setValue(Object value)
        {
            if (value == null)
            {
                super.setValue("");
            } else
            {
                long time = ( (Long) value).longValue();
                long day = time / (24 * 3600 * 100);
                long hour = (time % (24 * 3600 * 100)) / (3600 * 100);
                long munite = (time % (3600 * 100)) / (60 * 100);

                super.setValue("" + day + "�� " + hour + "Сʱ " + munite +
                               "����");

            }
        }

    }

    public static void main(String args[])
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
            SNMPGetMibSystem action = new SNMPGetMibSystem();
            action.actionPerformed(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
