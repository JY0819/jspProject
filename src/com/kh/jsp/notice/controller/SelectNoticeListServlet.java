package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;
import com.kh.jsp.notice.model.vo.PageInfo;

@WebServlet("/selectList.no")
public class SelectNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectNoticeListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ArrayList<Notice> list = new NoticeService().selectList();
		
		System.out.println("controller : " + list);
		
		String page = "";
		if(list != null) {
			page = "views/notice/noticeList.jsp";
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 조회 실패 ㅜㅅㅠ");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		*/
		
		
		// -----------------페이징 처리 추가---------------------- //
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		
		/* 현재 페이지 처리 */
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		
		/* 한 페이지에 보여질 목록 갯수 */
		limit = 10;
		
		NoticeService ns = new NoticeService();
		int listCount = ns.getListCount();	
		
		
		/* 총 페이지 수 계산 */
		maxPage = (int)((double)listCount / limit + 0.9);
		
		
		/* 현재 페이지에 보여줄 시작페이지 수 */
		startPage = (((int)((double)currentPage / limit + 0.9)) - 1 ) * limit + 1;
		
		
		/* 목록 아래쪽에 보여질 마지막 페이지 수 */
		endPage = startPage + 10 - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		ArrayList<Notice> list = new NoticeService().selectList(currentPage, limit);
		
		
		String page = "";
		if(list != null) {
			page = "views/notice/noticeList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 조회 실패 ㅜㅅㅠ");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
