package com.zhtelecom.common.topograph.example;

import javax.swing.*;

import com.zhtelecom.common.topograph.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: www.zhtelecom.com Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class HelloWorld
{
    public static void main(String[] args)
    {
        //......����1��������ʾ���......
        TopoGraphView topoView = new TopoGraphView(new TopoDataSource()); //ͨ������Դ����������ͼ
        TopoTreeView tree = new TopoTreeView(topoView.getTopoDataSource()); //��������Դ������
        tree.syncTopoview(topoView); //����������ͼ��ѡ���¼�ͬ������

        //......����2�����ò˵������ �¼��������......
        HelloMouseAction menuHandle = new HelloMouseAction(); //�����¼��������
        topoView.setMouseHandle(menuHandle); //��������ͼ���ô������

        //......����3����ͨjava swing��������������ͼ��������ͼ��ӵ�������......
        JFrame frame = new JFrame("��������ͼHelloWorld");
        JScrollPane graphPane = new JScrollPane(topoView);
        JScrollPane treePane = new JScrollPane(tree);
        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, graphPane);
        pane.setDividerLocation(200);
        frame.setContentPane(pane);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //......����4����������Ԫ��......

        TopoNetwork net = new TopoNetwork("����"); //��������
        TopoNode nodePC = new TopoNode("PC"); //����PC�豸
        TopoNode nodeGW = new TopoNode("����"); //�����ͥ�����豸
        nodeGW.setXY(300, 98); // ����x y����
        nodeGW.setImageIconFromTopoFile("dm.png"); //����ͼƬ
        TopoLink linkPCGW = new TopoLink("100M", nodePC, nodeGW); //ͨ������յ㣬��������
        nodePC.addAlarm("alarm1", TopoSysConfig.AlarmSeverity_Critical); //��ӽ����澯
        linkPCGW.addAlarm("alarm2", TopoSysConfig.AlarmSeverity_Major); //�����Ҫ�澯

        //......����5����������ӵ�����Դ......
        TopoDataSource source = topoView.getTopoDataSource(); //��ȡ����Դ
        source.addTopoData(net); //������1���뵽����ͼ��
        source.addTopoData(nodePC, net); //��PC���뵽����1��
        source.addTopoData(nodeGW, net); //�����ؼ��뵽����1��
        source.addTopoData(linkPCGW, net); //�����Ӽ��뵽����1��

    }
}

//ʵ���¼�����ӿ�
class HelloMouseAction implements TopoMouseHandle
{
    //������ͼ�����������Ҽ��Ĳ˵�
    public JPopupMenu createRightClickMenu(TopoGraphView topoView)
    {
        JPopupMenu menu = new JPopupMenu();
        menu.add("�Ҽ�����:" + topoView.getSelectedObject());
        return menu;
    }

    //������ͼ�հ��������Ҽ��Ĳ˵�
    public JPopupMenu createRightClickBlankMenu(TopoGraphView topoView)
    {
        JPopupMenu menu = new JPopupMenu();
        menu.add("�Ҽ��հ����򣬵�ǰ���磺" + topoView.getCurrentNetwork());
        return menu;
    }

    //˫���¼�
    public void doubleClickAction(TopoGraphView topoView)
    {
        JOptionPane.showMessageDialog(topoView, "����" + topoView.getSelectedObject(), "˫������",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
}
