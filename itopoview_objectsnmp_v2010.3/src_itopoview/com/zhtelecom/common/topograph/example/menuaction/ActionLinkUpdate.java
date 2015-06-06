package com.zhtelecom.common.topograph.example.menuaction;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import com.zhtelecom.common.topograph.TopoGraphView;
import com.zhtelecom.common.topograph.TopoLink;


public class ActionLinkUpdate extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {

        TopoGraphView topoView = (TopoGraphView) getValue("topoGraphView");
        TopoLink obj = topoView.getSelectedLink();

        //���ÿ��
        obj.setLinkWidth(3);

        //����ת����
        obj.setStraight(false);

        //��������
        obj.setBrokenLink(true);

        //���ü�ͷ
        obj.setShowArrow(false);

    }



    public ActionLinkUpdate()
    {
        super("�ı�Link���");
    }
}
