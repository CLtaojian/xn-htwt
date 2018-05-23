package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Node;

public interface INodeBO extends IPaginableBO<Node> {

    public String saveNode(Node data);

    public int removeNode(String code);

    public int refreshNode(Node data);

    public List<Node> queryNodeList(Node condition);

    public Node getNode(String code);

}
