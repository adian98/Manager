package com.zhtelecom.common.snmp.action;

import java.awt.event.*;

import com.l2fprod.common.beans.*;
import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
import com.zhtelecom.common.snmp.view.*;

public class SNMPGetIP extends SNMPBaseAction
{

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            SNMPTarget target = getTarget();
            MibBeanInfo beaninfo = new MibBeanInfo();
            SNMPSheetDialog dialog = new SNMPSheetDialog(getFrame(), false, false, true, beaninfo);
            dialog.setTitle(getTitle());

            MibIP mib = new MibIP();
            dialog.initObject(target, mib);
            dialog.processFresh();
            dialog.setSize(400, 460);

            DialogTools.dialogPack(getFrame(), dialog);
        } catch (Exception ex)
        {
            DialogTools.showErroMsgFromEx(getFrame(), getErroTitle(), ex);
        }

    }

    public SNMPGetIP()
    {
        super("�鿴IP���ݰ�״̬");
    }

    public static class MibBeanInfo extends BaseBeanInfo
    {

        public MibBeanInfo()
        {
            super(MibIP.class);
            addProperty("ipInReceives").setReadOnly().setDisplayName("���н��յ���IP��������");
            addProperty("ipInDelivers").setReadOnly().setDisplayName("�ɹ�����Ľ��հ�������");
            addProperty("ipInHdrErrors").setReadOnly().setDisplayName("�յ�IPͷ����İ�������");
            addProperty("ipInAddrErrors").setReadOnly().setDisplayName("�յ���ַ����İ�������");
            addProperty("ipForwDatagrams").setReadOnly().setDisplayName("���ղ�·��ת���İ�������");
            addProperty("ipInDiscards").setReadOnly().setDisplayName("ֱ�Ӷ����Ľ��հ�������");

            addProperty("ipOutRequests").setReadOnly().setDisplayName("ȫ�����͵�IP��������");
            addProperty("ipOutDiscards").setReadOnly().setDisplayName("ֱ�Ӷ����ķ��Ͱ�������");
            addProperty("ipOutNoRoutes").setReadOnly().setDisplayName("��ʧ·�ɵķ��Ͱ�������");

            addProperty("ipReasmReqds").setReadOnly().setDisplayName("���յ��ķ�ƬIP��������");
            addProperty("ipFragOKs").setReadOnly().setDisplayName("�ɹ���Ƭ��IP��������");

        }
    }

    public static void main(String args[])
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
            SNMPGetIP action = new SNMPGetIP();
            action.actionPerformed(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
