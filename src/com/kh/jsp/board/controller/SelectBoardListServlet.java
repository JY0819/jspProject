package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.PageInfo;

@WebServlet("/selectList.bo")
public class SelectBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ArrayList<Board> list = new BoardService().selectList();
	
		String page = "";
		if(list != null) {
			request.setAttribute("list", list);
			
			page = "views/board/boardList.jsp";
		} else {
			request.setAttribute("msg", "게시판 조회 실패 ㅠㅅㅜ");
			
			page = "views/common/errorPage.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);*/
		
		
		// -----------------페이징 처리 추가---------------------- //
		int currentPage;	// 현재 보고있는 페이지를 표시할 변수
		int limit;			// 한 페이지에 게시글이 몇 개가 보여질 것인지 표시
		int maxPage;		// 전체 페이지에서 가장 마지막 페이지
		int startPage;		// 한번에 표시될 페이지가 시작할 페이지
		int endPage;		// 한번에 표시될 페이지가 끝나는 페이지
		
		
		/* 현재 페이지 처리 */
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		
		/* 한 페이지에 보여질 목록 갯수 */
		limit = 10;
		
		BoardService bs = new BoardService();
		
		// 전체 게시글 수 조회
		int listCount = bs.getListCount();
		
		
		/* 총 페이지 수 계산 */
		// 예를 들어, 목록 수가 123개이면 페이지는 13페이지가 필요하다.
		maxPage = (int)((double)listCount / limit + 0.9);
		
		
		/* 현재 페이지에 보여줄 시작페이지 수 */
		// (10페이지 기준) 1, 11, 21, 31, ..
		startPage = (((int)((double)currentPage / limit + 0.9)) - 1 ) * limit + 1;
		
		
		/* 목록 아래쪽에 보여질 마지막 페이지 수(10, 20, 30, ...) */
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
	
		ArrayList<Board> list = new BoardService().selectList(currentPage, limit);
		
//		System.out.println(list);
		
		String page = "";
		if(list != null) {
			page = "views/board/boardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회 실패 8ㅅ8");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
