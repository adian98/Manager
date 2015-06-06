package com.zhtelecom.common.topograph.example;

import javax.swing.JPopupMenu;

import com.zhtelecom.common.topograph.TopoGraphView;
import com.zhtelecom.common.topograph.TopoLink;
import com.zhtelecom.common.topograph.TopoMouseHandle;
import com.zhtelecom.common.topograph.TopoNetwork;
import com.zhtelecom.common.topograph.TopoNode;
import com.zhtelecom.common.topograph.TopoObject;
import com.zhtelecom.common.topograph.TopoTreeView;
import com.zhtelecom.common.topograph.example.menuaction.ActionAddAlarm;
import com.zhtelecom.common.topograph.impl.ActionAlarmThreadTest;
import com.zhtelecom.common.topograph.example.menuaction.ActionAlarmDel;
import com.zhtelecom.common.topograph.example.menuaction.ActionAlarmView;
import com.zhtelecom.common.topograph.example.menuaction.ActionBatchAddNodeTest;
import com.zhtelecom.common.topograph.example.menuaction.ActionLinkUpdate;
import com.zhtelecom.common.topograph.example.menuaction.ActionNetworkAdd;
import com.zhtelecom.common.topograph.example.menuaction.ActionNetworkNodeLinkDel;
import com.zhtelecom.common.topograph.example.menuaction.ActionNodeAdd;
import com.zhtelecom.common.topograph.example.menuaction.ActionNodeImageUpdate;
import com.zhtelecom.common.topograph.example.menuaction.ActionPrintXY;
import com.zhtelecom.common.topograph.example.menuaction.ActionSetLinkEnd;
import com.zhtelecom.common.topograph.example.menuaction.ActionSetLinkStart;
import com.zhtelecom.common.topograph.example.menuaction.ActionUpdateUserID;
import com.zhtelecom.common.topograph.example.menuaction.ActionClearAll;
import com.zhtelecom.common.topograph.example.menuaction.ActionDelAllAlarm;
import com.zhtelecom.common.topograph.example.menuaction.ActionAlarmAddAll;

/**
 * ���ֲ˵����¼��Ĵ�����ڡ�������¼�������ÿһ��Action���档

 * @version 1.0
 */
public class TopoMouseAction implements TopoMouseHandle
{
    /**
     * ��node�������Ҽ��Ĳ˵�
     * @param topoView TopoGraphView
     * @return JPopupMenu
     */
    public JPopupMenu createRightClickMenu(TopoGraphView topoView)
    {
        JPopupMenu menu = new JPopupMenu();

        menu.add("�Ҽ�����:" + topoView.getSelectedObject());
        menu.addSeparator();

        //����ɾ���˵�
        ActionNetworkNodeLinkDel actionNetworkNodeLinkDel = new
            ActionNetworkNodeLinkDel();
        //����topoView����
        actionNetworkNodeLinkDel.putValue("topoGraphView", topoView);
        menu.add(actionNetworkNodeLinkDel);

        //�ڵ������������в˵�
        if (topoView.getSelectedObject() instanceof TopoNode)
        {
            menu.addSeparator();
            //�����������˵�
            ActionSetLinkStart actionSetLinkStart = new ActionSetLinkStart();
            actionSetLinkStart.putValue("topoGraphView", topoView);
            menu.add(actionSetLinkStart);

            //���������յ�˵�
            ActionSetLinkEnd actionSetLinkEnd = new ActionSetLinkEnd();
            actionSetLinkEnd.putValue("topoGraphView", topoView);
            menu.add(actionSetLinkEnd);

            menu.addSeparator();
            //�����޸�ͼƬ�˵�
            ActionNodeImageUpdate actionNodeImageUpdate = new
                ActionNodeImageUpdate();
            actionNodeImageUpdate.putValue("topoGraphView", topoView);
            menu.add(actionNodeImageUpdate);

        }

        //Link�����в˵�
        if (topoView.getSelectedObject() instanceof TopoLink)
        {
            menu.addSeparator();
            ActionLinkUpdate actionLinkUpdate = new ActionLinkUpdate();
            actionLinkUpdate.putValue("topoGraphView", topoView);
            menu.add(actionLinkUpdate);

        }

        menu.addSeparator();
        //���ø澯��Ӳ˵�
        ActionAddAlarm actionAddAlarm = new ActionAddAlarm();
        actionAddAlarm.putValue("topoGraphView", topoView);
        menu.add(actionAddAlarm);

        //���ø澯����˵�
        ActionAlarmDel actionAlarmDel = new ActionAlarmDel();
        actionAlarmDel.putValue("topoGraphView", topoView);
        menu.add(actionAlarmDel);

        //���ø澯��ʾ�˵�
        ActionAlarmView actionAlarmView = new ActionAlarmView();
        actionAlarmView.putValue("topoGraphView", topoView);
        menu.add(actionAlarmView);

        //�����޸��û�ID�˵�
        menu.addSeparator();
        ActionUpdateUserID actionUpdateUserID = new ActionUpdateUserID();
        actionUpdateUserID.putValue("topoGraphView", topoView);
        menu.add(actionUpdateUserID);

        return menu;

    }

    /**
     * �ڿհ��������Ҽ��Ĳ˵���
     * @param topoView TopoGraphView
     * @return JPopupMenu
     */
    public JPopupMenu createRightClickBlankMenu(TopoGraphView topoView)
    {
        JPopupMenu menu = new JPopupMenu();

        TopoNetwork net = topoView.getCurrentNetwork();
        String netName = "";
        if (net == null)
        {
            netName = TopoTreeView.topoTreeRootObject;
        } else
        {
            netName = net.toString();
        }

        menu.add("�Ҽ��հ����򣬵�ǰ���磺" + netName);

        menu.addSeparator();

        //  ����������Ӳ˵�
        ActionNetworkAdd actionNetworkAdd = new ActionNetworkAdd();
        actionNetworkAdd.putValue("topoGraphView", topoView);
        menu.add(actionNetworkAdd);

        //���ýڵ���Ӳ˵�
        ActionNodeAdd actionNodeAdd = new ActionNodeAdd();
        actionNodeAdd.putValue("topoGraphView", topoView);
        menu.add(actionNodeAdd);

        menu.addSeparator();

        //��������ͼ������Ӳ˵�
        ActionBatchAddNodeTest actionBatchAddTest = new ActionBatchAddNodeTest();
        actionBatchAddTest.putValue("topoGraphView", topoView);
        menu.add(actionBatchAddTest);


        ActionClearAll actionclearall = new ActionClearAll();
        actionclearall.putValue("topoGraphView", topoView);
        menu.add(actionclearall);


        ActionAlarmAddAll actionAlarmAddAll = new ActionAlarmAddAll();
        actionAlarmAddAll.putValue("topoGraphView", topoView);
        menu.add(actionAlarmAddAll);


        ActionDelAllAlarm actionDelAllAlarm = new ActionDelAllAlarm();
        actionDelAllAlarm.putValue("topoGraphView", topoView);
        menu.add(actionDelAllAlarm);

        menu.addSeparator();
        //���ô�ӡ����˵�
        ActionPrintXY actionPrintXY = new ActionPrintXY();
        actionPrintXY.putValue("topoGraphView", topoView);
        menu.add(actionPrintXY);
        return menu;


    }

    //˫���¼�
    public void doubleClickAction(TopoGraphView topoView)
    {
        TopoObject obj = topoView.getSelectedObject();

        if (null == obj)
        {
            String name = (topoView.getCurrentNetwork() != null) ? topoView.getCurrentNetwork().toString() :
                TopoTreeView.topoTreeRootObject;
            javax.swing.JOptionPane.showMessageDialog(topoView, "��ǰ��ʾ����:" + name, "˫���հ�����", javax.swing.JOptionPane.
                INFORMATION_MESSAGE);
            return;
        }

        String parentName = (obj.getParent() != null) ? obj.getParent().toString() : TopoTreeView.topoTreeRootObject;

        if (obj instanceof TopoNetwork)
        {
            TopoNetwork net = (TopoNetwork) obj;
            javax.swing.JOptionPane.showMessageDialog(topoView,
                "��������" +
                net.toString() +
                "   ���¼�����" + net.getChildren().size() +
                "��  ���ϼ�����:" + parentName +
                "    ;�������и澯����:" + net.getAllAlarms().size() + "��" +
                "(���У�����澯" + net.getThisNetworkAlarms().size() +
                "���¼��澯" + net.getAllChildAlarms().size() + "��)",
                "˫������",
                javax.swing.JOptionPane.
                INFORMATION_MESSAGE);

        } else
        {
            javax.swing.JOptionPane.showMessageDialog(topoView,
                "����" +
                obj.toString() +
                "  ���ϼ�����:" + parentName +
                "    ������澯������" + obj.getAllAlarms().size(),
                "˫������",
                javax.swing.JOptionPane.
                INFORMATION_MESSAGE);

        }

    }

}
