package com.zhtelecom.common.snmp.test;

import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;

/**
 * ����RFC1213-MIB��HostResoure�����ݻ�ȡ��
 * ��Windows XP������SNMP Agent����󣬿�ִ�д˲��ԡ�
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class GetObjectTest
{
    SNMPAPI snmpapi;

    SNMPTarget nodeParam;

    public GetObjectTest()
    {
        try
        {
            /**
             * ��ʼ��ϵͳ��־�ļ���
             */
            MyLog.initLogParam("SNMPTest");
            //ʹ�ÿͻ���ģʽ����Ҫ������ObjectSNMP��RMI�������磺����RMISNMPServer.java����
            //SNMPFactory.init(SNMPFactory.Mode_Client, "127.0.0.1");

            //ʹ�ñ���ģʽ��
            SNMPFactory.init(SNMPFactory.Mode_Local, null);

            //Ĭ���Ѽ���
            //SNMPFactory.loadMib("RFC1213-MIB");
            //SNMPFactory.loadMib("HOST-RESOURCES-MIB");

            // ��ȡSNMP API
            snmpapi = SNMPFactory.getSNMPAPI();

            //����SNMP Agent���Ӳ���
            nodeParam = new SNMPTarget();
            nodeParam.nodeIP = "127.0.0.1";
            nodeParam.port = 161;

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * ���Դ�MIB���ȡ����
     */
    public void testGetMibGroup(Object mib)
    {
        try
        {

            Object mibobj = snmpapi.getMibObject(mib, nodeParam);
            System.out.println(mib.getClass());
            System.out.println(mibobj);
            System.out.println("--------------");
        } catch (Exception ex)
        {
            ex.printStackTrace();

        }
    }

    /**
     * ���Դ�MIB���ȡ����
     */
    public void testGetMibTable(Class tableclass)
    {
        try
        {

            java.util.List list = snmpapi.getAllTableData(tableclass,
                nodeParam);

            System.out.println(tableclass + " �����ݸ�����" + list.size());
            for (int i = 0; i < list.size(); i++)
            {
                Object mibobj = list.get(i);
                System.out.println(mibobj);
            }
            System.out.println("--------------");

        } catch (Exception ex)
        {
            ex.printStackTrace();

        }
    }

    /**
     * �ڱ������Դ�windows xp��SNMP Agent��ѯ���ݡ����μ��������xp��SNMP Agent����
     * @param args String[]
     */
    public static void main(String args[])
    {
        try
        {

            GetObjectTest test = new GetObjectTest();

            //��ȡRFC1213-MIB������
            test.testGetMibGroup(new MibSystem());
            test.testGetMibTable(MibIfEntry.class);
            test.testGetMibGroup(new MibIP());
            test.testGetMibTable(MibIPAddrEntry.class);
            test.testGetMibTable(MibIPRouterEntry.class);
            test.testGetMibTable(MibTCPConnEntry.class);
            test.testGetMibTable(MibUDPEntry.class);
            test.testGetMibGroup(new MibSNMP());

            //��ȡResource-Host�е�����
            test.testGetMibTable(MibDiskAndMemoEntry.class);
            test.testGetMibTable(MibDeviceEntry.class);
            test.testGetMibTable(MibProcessorEntry.class);
            test.testGetMibTable(MibSoftwareRunEntry.class);
            test.testGetMibTable(MibSoftwareInstallEntry.class);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
