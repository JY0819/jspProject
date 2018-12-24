package com.kh.jsp.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;

@WebServlet("/selectOne.bo")
public class SelectOneBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectOneBoardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));

		System.out.println(num);

		Board b = new BoardService().selectOne(num);

		String page = "";

		if (b != null) {
			page = "views/board/boardDetail.jsp";
			request.setAttribute("b", b);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 상세조회 실패");
		}

		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
