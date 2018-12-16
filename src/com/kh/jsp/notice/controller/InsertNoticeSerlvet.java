package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;

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
      
      
      
      Date nDate = Date.valueOf(date);
      if(date == "") {
         java.util.Date utilDate = new java.util.Date();
         nDate = new java.sql.Date(utilDate.getTime());
      }
      System.out.println("title : " + title );
      System.out.println("write : " + writer );
//      System.out.println("nno : " + nno );
      System.out.println("date : " + nDate );
      System.out.println("content : " + content );
      
      Notice reqNotice = new Notice();
      reqNotice.setnTitle(title);
      reqNotice.setnWriter(writer);
//      reqNotice.setNno(nno);
      reqNotice.setnDate(nDate);
      reqNotice.setnContent(content);
      
      
      
      int result = new NoticeService().insertNotice(reqNotice);
      if(result>0) {
            request.getSession().setAttribute("msg", "공지사항 등록 완료");
            //response.sendRedirect("views/common/successPage.jsp");
            //response.sendRedirect("views/notice/noticeList.jsp");
            System.out.println("test!");
            System.out.println(request);
            System.out.println("test!");
            System.out.println(response);
            
            
            request.getRequestDispatcher("/selectList.no").forward(request, response);
    		
      }else {
         request.setAttribute("msg", "공지사항 등록 실패");
         request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}