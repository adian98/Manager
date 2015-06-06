package com.zhtelecom.common.snmp.test;

import java.awt.*;
import javax.swing.*;

import com.zhtelecom.common.base.*;
import com.zhtelecom.common.snmp.*;
import com.zhtelecom.common.snmp.action.*;
import com.zhtelecom.common.topograph.*;

//���д�ʾ������Ҫ��Windows XP������SNMP Agent������֧��SNMP���豸�ϻ�ȡ��
//��GUI������Ի�ȡSNMP���ݵ����ӡ����ȡ���硢IP��UDP��TCP��SNMPϵͳ��IP��ַ��CPU���ڴ桢���̡��������Ϣ��
//<p>Copyright: www.zhtelecom.com Copyright (c) 2007</p>
public class GetObjectGuiTest extends JApplet
{
    /**
     * WEB AppletС�����ʼ���ӿڡ�Ҳ��ApplicationӦ�ó���ĳ�ʼ���ӿڡ�
     * ��������ͼ��ܡ�
     */
    public void init()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //��������Դ
            TopoDataSource source = new TopoDataSource();

            //ͨ������Դ����������ͼ��
            TopoGraphView topoView = new TopoGraphView(source);

            //��ʼ��ͼ�ν��档Swing������
            JScrollPane sGraph = new JScrollPane(topoView);

            //��������ͼ��ͼ���¼��������
            HelloMouseAction menuHandle = new HelloMouseAction();
            topoView.setMouseHandle(menuHandle);

            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(sGraph, BorderLayout.CENTER);
            //this.setVisible(true);
            SNMPBaseAction.setFrame(getAppletFrame());
            this.getParent();
            //���һ�������������������ݡ�(Ҳ����ѡ���ȼ����������ݣ��ٹ���������ͼ)
            processData(topoView);
            SNMPFactory.init(SNMPFactory.Mode_Local, null);

        } catch (Exception ex)
        {
            DialogTools.showErroMsgFromEx(this, "��ʼ��ϵͳ����", ex);
            ex.printStackTrace();
        }
    }

    private Frame getAppletFrame()
    {
        Container c = getParent();
        while (c != null)
        {
            if (c instanceof Frame)
            {
                return (Frame) c;
            }
            c = c.getParent();
        }
        return null;
    }

    /**
     * �������д����������ݡ�
     * ������ͼ�����ݴ����������ʾ�ֿ�����
     *
     */
    private void processData(TopoGraphView topoView)
    {
        //��ȡ����Դ
        TopoDataSource source = topoView.getTopoDataSource();
        TopoNode nodePC = new TopoNode(new StringBuffer("127.0.0.1")); //����PC�豸
        nodePC.setX(188);
        nodePC.setY(188);
        source.addTopoData(nodePC);
    }

    public static SNMPTarget getTarget()
    {
        SNMPTarget target = new SNMPTarget();
        TopoGraphView topoview = TopoGraphView.getContextGraphView();
        if (topoview == null)
        {
            return target;
        }
        Object userid = topoview.getSelectedObject();
        if (userid != null)
        {
            String ip = userid.toString();
            target.nodeIP = ip;
        }
        return target;
    }

    //Appliction������ڣ�����Appletģʽ��
    public static void main(String[] args)
    {
        //��ʼ�����ݺ�ͼ��
        GetObjectGuiTest helloApplet = new GetObjectGuiTest();
        helloApplet.init();

        //����Application ����
        JFrame frame = new JFrame("ObjectSNMP��ȡSNMP������ʾ");
        SNMPBaseAction.setFrame(frame);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(helloApplet.getContentPane());
        DialogTools.setWindowPCCenter(frame);

    }



//ʵ���¼�����ӿ�
class HelloMouseAction implements TopoMouseHandle
{
    //������ͼ�����������Ҽ��Ĳ˵�
    public JPopupMenu createRightClickMenu(TopoGraphView topoView)
    {
        JPopupMenu menu = new JPopupMenu();
        {
            Action action = new UpdateIPAddress();
            menu.add(action);
        }
        menu.addSeparator();

        {
            JMenu menunet = new JMenu("�鿴����״̬");
            {
                SNMPBaseAction action = new SNMPGetMibSystem();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetMibIfEntry();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetIfNetStatus();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetIP();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetIPAddr();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetTCPConn();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetMibUdpEntry();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetSnmp();
                action.setTarget(getTarget());
                menunet.add(action);
            }
            menu.add(menunet);

        }
        menu.addSeparator();
        {
            JMenu menuhost = new JMenu("�鿴������Դ״̬");
            {
                SNMPBaseAction action = new SNMPGetDiskMemo();
                action.setTarget(getTarget());
                menuhost.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetMibDevice();
                action.setTarget(getTarget());
                menuhost.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetCpu();
                action.setTarget(getTarget());
                menuhost.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetInstallSw();
                action.setTarget(getTarget());
                menuhost.add(action);
            }
            {
                SNMPBaseAction action = new SNMPGetRunSW();
                action.setTarget(getTarget());
                menuhost.add(action);
            }
            menu.add(menuhost);

        }

        menu.addSeparator();
        {
            SNMPBaseAction action = new MibBrowserAction();
            action.setTarget(getTarget());
            menu.add(action);
        }
        return menu;
    }

    //������ͼ�հ��������Ҽ��Ĳ˵�
    public JPopupMenu createRightClickBlankMenu(TopoGraphView topoView)
    {
        return null;
    }

    //˫���¼�
    public void doubleClickAction(TopoGraphView topoView)
    {

    }
}
}
