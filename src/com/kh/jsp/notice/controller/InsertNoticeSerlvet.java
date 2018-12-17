package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

@WebServlet("/insert.no")
public class InsertNoticeSerlvet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
    public InsertNoticeSerlvet() {
        super();
      
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
      String writer = (String)request.getParameter("uno");
      String date = request.getParameter("date");
      String content = request.getParameter("content");
      
     /* Date nDate = Date.valueOf(date);
      if(date == "") {
         java.util.Date utilDate = new java.util.Date();
         nDate = new java.sql.Date(utilDate.getTime());
      }*/
      
      java.sql.Date day = null;
      
      if(date != "") {
    	  String[] dateArr = date.split("-");
    	  int[] drr = new int[dateArr.length];
    	  
    	  for(int i = 0; i < dateArr.length; i++) {
    		  drr[i] = Integer.parseInt(dateArr[i]);
    	  }
    	  // GregorianCalendar = 0월부터 11월
    	  day = new java.sql.Date(new GregorianCalendar(drr[0], drr[1] - 1, drr[2]).getTimeInMillis());

      } else {
    	  day = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
      }
      
      
      Notice n = new Notice();
      n.setnTitle(title);
      n.setnWriter(writer);
      n.setnDate(day);
      n.setnContent(content);
      
      
      int result = new NoticeService().insertNotice(n);
      
      String page = "";
      
      if(result > 0) {
    	  page = "/jsp/selectList.no";
    	  response.sendRedirect(page);
      } else {
    	  request.setAttribute("msg", "공지사항 등록 실패");
          request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}