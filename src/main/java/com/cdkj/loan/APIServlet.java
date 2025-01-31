/**
 * @Title APIServlet.java 
 * @Package com.hsnet.pz.api 
 * @Description 
 * @author miyb  
 * @date 2015-3-24 下午4:23:53 
 * @version V1.0   
 */
package com.cdkj.loan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdkj.loan.proxy.DispatcherImpl;
import com.cdkj.loan.proxy.IDispatcher;

/** 
 * @author: miyb 
 * @since: 2015-3-24 下午4:23:53 
 * @history:
 */
public class APIServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(APIServlet.class);

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6175432226630152841L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("Successful Deployment");
        PrintWriter writer = response.getWriter();
        writer.append("Version:1.5.0/ \n");
        writer.append("Description:htwt_meet_1227 \n");
        writer.flush();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String operator = request.getParameter("operator");
        String json = request.getParameter("json");
        IDispatcher dispatcher = new DispatcherImpl();
        String result = dispatcher.doDispatcher(code, json, operator);
        PrintWriter writer = response.getWriter();
        writer.append(result);
        writer.flush();
    }
}
