
package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.*;
import javax.swing.*;

import com.zhtelecom.common.topograph.*;

public class ActionDelAllAlarm extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {
        //֮ǰ�ѱ���ֵ�������ȡ�����˶���Ҳ����ͨ��TopoGraphView.getContextGraphView()���ٻ�ȡΨһʵ����
        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");

        TopoDataSource tds = topoView.getTopoDataSource();
       tds.cleareAllAlarm();
    }

    public ActionDelAllAlarm()
    {
        super("���ȫ���澯");
    }
}
