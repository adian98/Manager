package com.zhtelecom.common.snmp.test;

import com.zhtelecom.common.snmp.*;

public class RMISNMPServer
{
    public RMISNMPServer()
    {
    }

    /**
     * ����ObjectSNMP��RMIԶ�̷���
     * @param args String[]
     */
    public static void main(String args[])
    {
        try
        {
            SNMPFactory.init(SNMPFactory.Mode_Server_Local, null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
