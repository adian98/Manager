package com.zhtelecom.common.topograph.example;

import java.awt.*;
import javax.swing.*;

import com.zhtelecom.common.topograph.*;

//��ʾ����ͼ����Ҫ���ܡ���ͬʱ��Applet��Ӧ�ó��������С�

//network��չ�������أ�û�����Ƶļ���network�¿��ٹ�network,node��link
//����ͼ����ʾ
//ͼ�θı������ͼ�Զ�����
//��ɫ�Զ����ϸı�
//�Ŵ���С ת���ϲ�
//Link ·��ģʽ��ֱ��ģʽ
//��ӡ�ɾ���澯���Զ�������ɫ�仯
//��ꡢ�˵�����
//��ѡ��������ͼѡ��ͬ��
//��ӡ�ɾ�� �ڵ㡢���硢����
//<p>Copyright: www.zhtelecom.com Copyright (c) 2007</p>
public class NMSExample extends JApplet
{
    static int i = 1;

    public static int getNum()
    {
        return i++;
    }

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
            topoView.getStatusLabel().setText("״̬��");
            //��ʼ��ͼ�ν��档Swing������
            JScrollPane sGraph = new JScrollPane(topoView);

            //��������ͼ��ͼ���¼��������
            TopoMouseAction menuHandle = new TopoMouseAction();
            topoView.setMouseHandle(menuHandle);

            //��������Դ������
            TopoTreeView tree = new TopoTreeView(source);
            //�������û�ѡ���������ͼ���û�ѡ�����ͬ��������
            tree.syncTopoview(topoView);

            JScrollPane sTree = new JScrollPane(tree);
            JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sTree,
                                             sGraph);
            pane.setDividerLocation(200);

            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(pane, BorderLayout.CENTER);
            //this.setVisible(true);

            //���һ�������������������ݡ�(Ҳ����ѡ���ȼ����������ݣ��ٹ���������ͼ)
            processData(topoView);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

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

        //�����ҵ��������
        TopoNetwork serviceNet = new TopoNetwork(new StringBuffer("��ҵ������")); // ʹ��StringBuffer,���ں�����޸�
        serviceNet.setXY(163, 131);

        //�����ҵ���������豸
        TopoNode nodePC = new TopoNode(new StringBuffer("PC"));
        nodePC.setXY(15, 273); // ����x/y����
        TopoNode nodeTV = new TopoNode(new StringBuffer("TV������"));
        nodeTV.setXY(15, 166);
        nodeTV.setImageIconFromTopoFile("backRecovery.png"); //����ͼƬ����
        TopoNode nodePhone = new TopoNode(new StringBuffer("IP�绰"));
        nodePhone.setXY(15, 57);
        nodePhone.setImageIconFromTopoFile("digiphone.png");
        TopoNode nodeGW = new TopoNode(new StringBuffer("��ͥ����"));
        nodeGW.setXY(151, 156);
        nodeGW.setImageIconFromTopoFile("dm.png");
        TopoNode nodeAcess = new TopoNode(new StringBuffer("�����豸"));
        nodeAcess.setXY(281, 160);
        TopoNode serverManager = new TopoNode(new StringBuffer("ҵ�������"));
        serverManager.setXY(301, 459);
        serverManager.setImageIconFromTopoFile("server_db.png");

        nodeAcess.setImageIconFromTopoFile("cartridge_system.png");
        TopoNode serverDB = new TopoNode(new StringBuffer("���ݿ�"));
        serverDB.setXY(431, 452);
        serverDB.setImageIconFromTopoFile("single_database.png");
        TopoNode serverWeb = new TopoNode(new StringBuffer("��վ������"));
        serverWeb.setXY(538, 459);
        serverWeb.setImageIconFromTopoFile("server.png");

        TopoNode routerAccess = new TopoNode(new StringBuffer("����·����"));
        routerAccess.setImageIconFromTopoFile("tw130.png");
        routerAccess.setXY(415, 150);
        TopoNode routerData = new TopoNode(new StringBuffer("����·����"));
        routerData.setXY(584, 48);
        routerData.setImageIconFromTopoFile("tw130.png");
        TopoNode routerPhone = new TopoNode(new StringBuffer("����·����"));
        routerPhone.setXY(702, 150);
        routerPhone.setImageIconFromTopoFile("tw130.png");
        TopoNode routerTV = new TopoNode(new StringBuffer("��Ƶ·����"));
        routerTV.setXY(584, 235);
        routerTV.setImageIconFromTopoFile("tw130.png");

        //�����ҵ������������
        TopoLink linkPCGW = new TopoLink(new StringBuffer("100M"), nodePC, nodeGW); //ͨ��������㡢�յ㣬����Link
        linkPCGW.setStraight(false); //����ת����
        linkPCGW.setBrokenLink(true); //��������
        linkPCGW.setLinkWidth(1); //���ÿ��
        TopoLink linkTVGW = new TopoLink(new StringBuffer("Cable"), nodeTV, nodeGW);
        linkTVGW.setStraight(false);
        linkTVGW.setBrokenLink(true);
        linkTVGW.setLinkWidth(1);
        TopoLink linkPhoneGW = new TopoLink(new StringBuffer("RJ45"), nodePhone, nodeGW);
        linkPhoneGW.setStraight(false);
        linkPhoneGW.setBrokenLink(true);
        linkPhoneGW.setLinkWidth(1);
        TopoLink linkGWAcess = new TopoLink(new StringBuffer("FE"), nodeGW, nodeAcess);
        TopoLink linkAcessRouterAC = new TopoLink(new StringBuffer("GE"), nodeAcess, routerAccess);

        TopoLink linkAcessManager = new TopoLink(new StringBuffer("L1"), routerAccess, serverManager);
        linkAcessManager.setStraight(false);
        TopoLink linkAcessDB = new TopoLink(new StringBuffer("L2"), routerAccess, serverDB);
        linkAcessDB.setStraight(false);
        TopoLink linkAcessWeb = new TopoLink(new StringBuffer("L3"), routerAccess, serverWeb);
        linkAcessWeb.setStraight(false);

        TopoLink linkRouterACData = new TopoLink(new StringBuffer("G1"), routerAccess, routerData);
        linkRouterACData.setShowArrow(false);
        linkRouterACData.setLinkWidth(5);
        TopoLink linkRouterACTV = new TopoLink(new StringBuffer("G2"), routerAccess, routerTV);
        linkRouterACTV.setShowArrow(false);
        linkRouterACTV.setLinkWidth(5);
        TopoLink linkRouterACPhone = new TopoLink(new StringBuffer("G3"), routerAccess, routerPhone);
        linkRouterACPhone.setShowArrow(false);
        linkRouterACPhone.setLinkWidth(5);
        TopoLink linkRouterTVData = new TopoLink(new StringBuffer("����1"), routerTV, routerData);
        linkRouterTVData.setShowArrow(false);
        linkRouterTVData.setBrokenLink(true);
        TopoLink linkRouterTVPhone = new TopoLink(new StringBuffer("����2"), routerTV, routerPhone);
        linkRouterTVPhone.setShowArrow(false);
        linkRouterTVPhone.setBrokenLink(true);
        TopoLink linkRouterPhoneData = new TopoLink(new StringBuffer("����3"), routerPhone, routerData);
        linkRouterPhoneData.setShowArrow(false);
        linkRouterPhoneData.setBrokenLink(true);

        //��Ӹ澯
        nodePC.addAlarm("alarm1", TopoSysConfig.AlarmSeverity_Critical); //����
        nodeTV.addAlarm("alarm2", TopoSysConfig.AlarmSeverity_Major); //��Ҫ
        nodePhone.addAlarm("alarm3", TopoSysConfig.AlarmSeverity_Minor); //��Ҫ;
        nodeGW.addAlarm("alarm4", TopoSysConfig.AlarmSeverity_Warning); //����;
        linkGWAcess.addAlarm("alarm5", TopoSysConfig.AlarmSeverity_Indeterminate); //δ֪
        nodeAcess.addAlarm("alarm6", TopoSysConfig.AlarmSeverity_Indeterminate); //δ֪

        ////����ҵ������������ݷ��뵽����Դ������......................

        source.addTopoData(serviceNet); //serviceNet�ڸ���ͼ��
        source.addTopoData(nodePC, serviceNet); //nodePC��serviceNet��
        source.addTopoData(nodeTV, serviceNet);
        source.addTopoData(nodePhone, serviceNet);
        source.addTopoData(nodeGW, serviceNet);
        source.addTopoData(nodeAcess, serviceNet);
        source.addTopoData(routerAccess, serviceNet);

        source.addTopoData(serverManager, serviceNet);
        source.addTopoData(serverWeb, serviceNet);
        source.addTopoData(serverDB, serviceNet);

        source.addTopoData(routerTV, serviceNet);
        source.addTopoData(routerData, serviceNet);
        source.addTopoData(routerPhone, serviceNet);

        source.addTopoData(linkPCGW, serviceNet);
        source.addTopoData(linkTVGW, serviceNet);
        source.addTopoData(linkPhoneGW, serviceNet);
        source.addTopoData(linkGWAcess, serviceNet);
        source.addTopoData(linkAcessRouterAC, serviceNet);

        source.addTopoData(linkAcessDB, serviceNet);
        source.addTopoData(linkAcessManager, serviceNet);
        source.addTopoData(linkAcessWeb, serviceNet);

        source.addTopoData(linkRouterACData, serviceNet);
        source.addTopoData(linkRouterACTV, serviceNet);
        source.addTopoData(linkRouterACPhone, serviceNet);
        source.addTopoData(linkRouterTVData, serviceNet);
        source.addTopoData(linkRouterTVPhone, serviceNet);
        source.addTopoData(linkRouterPhoneData, serviceNet);

//................................................//
        //�������������������豸������
        TopoNetwork mutiNet = new TopoNetwork(new StringBuffer("����������"));
        mutiNet.setXY(392, 242);
        TopoNetwork internet = new TopoNetwork(new StringBuffer("Internet"));
        internet.setXY(331, 303);
        TopoNetwork pstn = new TopoNetwork(new StringBuffer("�绰����"));
        pstn.setXY(330, 105);
        TopoNetwork vide = new TopoNetwork(new StringBuffer("��Ƶ����"));
        vide.setXY(140, 192);
        TopoNode nodesw = new TopoNode(new StringBuffer("������"));
        nodesw.setXY(158, 393);
        TopoNode nodeVod1 = new TopoNode(new StringBuffer("��Ƶ������1"));
        nodeVod1.setXY(100, 100);
        TopoNode nodeVod2 = new TopoNode(new StringBuffer("��Ƶ������2"));
        nodeVod2.setXY(300, 100);
        TopoLink link1 = new TopoLink(new StringBuffer("Link1"), internet, pstn);
        TopoLink link2 = new TopoLink(new StringBuffer("Link2"), pstn, vide);
        TopoLink link3 = new TopoLink(new StringBuffer("Link3"), vide, internet);
        TopoLink link = new TopoLink(new StringBuffer("video"), nodeVod1, nodeVod2);

        //��Ӹ澯
        pstn.addAlarm("a1", TopoSysConfig.AlarmSeverity_Major);
        nodeVod1.addAlarm("a2", TopoSysConfig.AlarmSeverity_Critical);
        nodesw.addAlarm("a3", TopoSysConfig.AlarmSeverity_Warning);

        //�����������������ݼ��뵽����Դ
        source.addTopoData(mutiNet);
        source.addTopoData(internet, mutiNet);
        source.addTopoData(pstn, mutiNet);
        source.addTopoData(vide, mutiNet);
        source.addTopoData(nodesw, mutiNet);
        source.addTopoData(link1, mutiNet);
        source.addTopoData(link2, mutiNet);
        source.addTopoData(link3, mutiNet);
        source.addTopoData(nodeVod1, vide);
        source.addTopoData(nodeVod2, vide);
        source.addTopoData(link, vide);

    }

    //Appliction������ڣ�����Appletģʽ��
    public static void main(String[] args)
    {
        //��ʼ�����ݺ�ͼ��
        NMSExample helloApplet = new NMSExample();
        helloApplet.init();

        //����Application ����
        JFrame frame = new JFrame("��������ͼ��ʾ ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(helloApplet.getContentPane());
        frame.setVisible(true);

    }

}
