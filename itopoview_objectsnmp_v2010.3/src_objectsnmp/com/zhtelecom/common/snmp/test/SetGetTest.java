package com.zhtelecom.common.snmp.test;

import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;

/**
 * �˲�����Ҫ·������SNMP Agent֧�ֻ�֧��OSPF-MIB���豸��
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class SetGetTest
{
    SNMPAPI snmpapi;

    SNMPTarget nodeParam;

    public SetGetTest()
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

            // ��ȡSNMP API
            snmpapi = SNMPFactory.getSNMPAPI();

            SNMPFactory.loadMib("OSPF-MIB");

            //����SNMP Agent���Ӳ���
            nodeParam = new SNMPTarget();
            nodeParam.nodeIP = "127.0.0.1";
            nodeParam.port = 166;
            nodeParam.writeCommunity = "private";

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void testGetOID()
    {
        try
        {
            String v = snmpapi.getOIDValue("1.3.6.1.2.1.2.2.1.2.1", nodeParam);
            System.out.println(v);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testGetNextOID()
    {
        try
        {
            String v = snmpapi.getNextOIDValue("1.3.6.1.2.1.1.2.0", nodeParam);
            System.out.println(v);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testUpdateSystem()
    {
        try
        {
            MibSystem mib = new MibSystem();
            mib.setSysName("objectsnmp");
            mib.setSysUpTime(111);
            snmpapi.update(mib, nodeParam);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testIf()
    {
        try
        {

            MibIfEntry entry = new MibIfEntry();
            entry.setIfIndex(1);
            Object obj = snmpapi.getMibObject(entry, nodeParam);
            System.out.println(obj);
            entry = (MibIfEntry) obj;
            entry.setIfAdminStatus(1);
            snmpapi.update(entry, nodeParam);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testAddTableOspf()
    {
        try
        {
            MibOspfStubAreaEntry ospf = new MibOspfStubAreaEntry();
            ospf.setOspfStubAreaId("192.168.9.0");
            ospf.setOspfStubTOS(3);
            ospf.setOspfStubStatus(SNMPAPI.RowStatusEntryAdd); //����snmp rowstatus��ֵΪ������塣
            ospf.setOspfStubMetric(1);
            ospf.setOspfStubMetricType(2);

            snmpapi.addTableRow(ospf, nodeParam);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testUpdateTableOspf()
    {
        try
        {
            MibOspfStubAreaEntry ospf = new MibOspfStubAreaEntry();
            ospf.setOspfStubAreaId("192.168.9.0");
            ospf.setOspfStubTOS(3);
            ospf.setOspfStubStatus(SNMPAPI.RowStatusEntryActive); //����snmp rowstatus��ֵΪ�������塣
            ospf.setOspfStubMetric(3);
            ospf.setOspfStubMetricType(3);

            snmpapi.update(ospf, nodeParam);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void testdelTableOspf()
    {
        try
        {
            MibOspfStubAreaEntry ospf = new MibOspfStubAreaEntry();
            ospf.setOspfStubAreaId("192.168.9.0");
            ospf.setOspfStubTOS(3);
            ospf.setOspfStubStatus(SNMPAPI.RowStatusEntryDel); //����snmp rowstatus��ֵΪɾ�����塣
            ospf.setOspfStubMetric(3);
            ospf.setOspfStubMetricType(3);

            snmpapi.delTableRow(ospf, nodeParam);

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

            SetGetTest test = new SetGetTest();
            test.testGetOID();
            test.testGetNextOID();
            test.testUpdateSystem();
            test.testIf();
            test.testAddTableOspf();
            test.testUpdateTableOspf();
            test.testdelTableOspf();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
