package com.zhtelecom.common.snmp.test;

import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;

/**
 * �����������ܣ�1���MIBSystem(7���OID��ȡ)ʱ��<=5�롣(PC������)
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: zhtelecom.com</p>
 * @author lisl
 * @version 1.0
 */
public class PerformanceTest
{
    public static void main(String args[])
    {
        try
        {
            SNMPTarget target = new SNMPTarget();
            target.nodeIP = "127.0.0.1";
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
            SNMPAPI snmpapi = SNMPFactory.getSNMPAPI();
            MibSystem mibsystem = new MibSystem();
            System.out.println("��ʼ��ȡ����");
            long timeStart = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++)
            {
                Object obj = snmpapi.getMibObject(mibsystem, target);

            }
            long timeEnd = System.currentTimeMillis();
            System.out.println("��ȡ1���MIBSystem��ʱ��:" + (timeEnd - timeStart) / 1000 + "��");
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
