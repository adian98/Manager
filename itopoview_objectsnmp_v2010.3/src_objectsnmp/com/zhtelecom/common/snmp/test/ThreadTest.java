package com.zhtelecom.common.snmp.test;

import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.mib.*;
/**
 * ѭ������SNMP���ݶ�ȡ��
 * 6�������̡߳�ÿ���߳�ѭ��ִ��3��������2���������ݣ�һ�����糬ʱ���ݡ�
 */
public class ThreadTest extends Thread
{
    public static long num = 0;

    public static long err = 0;

    public ThreadTest()
    {
    }

    public static void main(String argsp[])
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Local, null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();

    }

    public void run()
    {
        SNMPTarget target127 = new SNMPTarget();

        SNMPTarget target166 = new SNMPTarget();
        target166.port = 166;

        SNMPTarget notarget = new SNMPTarget();
        notarget.nodeIP = "192.168.2.199";

        MibSystem mibsys = new MibSystem();
        while (true)
        {
            try
            {
                SNMPAPI snmpapi = SNMPFactory.getSNMPAPI();

                try
                {
                    num++;
                    MibSystem newsys = (MibSystem) snmpapi.getMibObject(mibsys, target127);

                    if (newsys.getSysServices() != 76)
                    {
                        err++;
                    }
                } catch (Exception ex)
                {
                    err++;
                }

                try
                {
                    num++;
                    MibSystem newsys = (MibSystem) snmpapi.getMibObject(mibsys, target166);

                    if (newsys.getSysServices() != 99)
                    {
                        err++;
                    }
                } catch (Exception ex)
                {
                    err++;
                }

                try
                {
                    num++;
                    MibSystem newsys = (MibSystem) snmpapi.getMibObject(mibsys, notarget);
                    err++;
                } catch (Exception ex)
                {

                }

                System.out.println("process:" + num + "   err:" + err);

            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
