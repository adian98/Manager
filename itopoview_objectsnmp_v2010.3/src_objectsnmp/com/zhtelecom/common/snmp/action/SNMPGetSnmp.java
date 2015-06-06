package com.zhtelecom.common.snmp.action;
import java.awt.event.*;

import com.l2fprod.common.beans.*;
import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
import com.zhtelecom.common.snmp.view.*;
import com.zhtelecom.common.snmp.mib.MibSNMP;

public class SNMPGetSnmp extends SNMPBaseAction
{

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            SNMPTarget target = getTarget();
            MibBeanInfo beaninfo = new MibBeanInfo();
            SNMPSheetDialog dialog = new SNMPSheetDialog(getFrame(), false, false, true, beaninfo);
            dialog.setTitle(getTitle());

            MibSNMP mib = new MibSNMP();
            dialog.initObject(target, mib);
            dialog.processFresh();
            dialog.setSize(400, 360);

            DialogTools.dialogPack(getFrame(), dialog);
        } catch (Exception ex)
        {
            DialogTools.showErroMsgFromEx(getFrame(), getErroTitle(), ex);
        }

    }

    public SNMPGetSnmp()
    {
        super("�鿴SNMP����״̬");
    }

    public static class MibBeanInfo extends BaseBeanInfo
    {

        public MibBeanInfo()
        {
            super(MibSNMP.class);
            addProperty("snmpInPkts").setReadOnly().setDisplayName("���յ���SNMP����������");
            addProperty("snmpOutPkts").setReadOnly().setDisplayName("�ظ���SNMPӦ��������");
            addProperty("snmpOutTraps").setReadOnly().setDisplayName("�����Trap��Ϣ֪ͨ������");
            addProperty("snmpInBadCommunityNames").setReadOnly().setDisplayName("���յ�����Ĺ�ͬ������������");
            addProperty("snmpInNoSuchNames").setReadOnly().setDisplayName("���յ�������OID������������");
            addProperty("snmpInASNParseErrs").setReadOnly().setDisplayName("���յ��Ĵ����﷨����������");
            addProperty("snmpInTooBigs").setReadOnly().setDisplayName("���յ�������������������");
            addProperty("snmpInBadValues").setReadOnly().setDisplayName("���յ�����OIDֵ������������");

        }
    }

    public static void main(String args[])
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
            SNMPGetSnmp action = new SNMPGetSnmp();
            action.actionPerformed(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
