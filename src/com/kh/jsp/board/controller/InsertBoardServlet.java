package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/insert.bo")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		/*
		// 로그인 된 유저 정보를 세션에서 받아온다
		HttpSession session = request.getSession();
		
		Member loginUser = (Member) session.getAttribute("loginUser");
		// 문자열로 바꿔준다 String.valueOf
		String writer = String.valueOf(loginUser.getUno());
		*/
		
		
		// **위 코드를 한줄로** 
		// (Member)로 다운 캐스팅 
		String writer = String.valueOf(((Member)(request.getSession().getAttribute("loginUser"))).getUno());
	
		
		Board b = new Board();
		b.setCategory(category);
		b.setbTitle(title);
		b.setbContent(content);
		b.setbWriter(writer);
		
		
		int result = new BoardService().insertBoard(b);
		
		
		String page="";
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/selectList.bo");
		} else {
			request.setAttribute("msg", "게시글 작성 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
