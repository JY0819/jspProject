package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;

@WebServlet("/insertReply.bo")
public class InsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertReplyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		int bid = Integer.parseInt(request.getParameter("bid"));
		String content = request.getParameter("content");
		
		System.out.println(writer);
		System.out.println(bid);
		System.out.println(content);
		
		Board b = new Board();
		b.setBid(bid);
		b.setbWriter(writer);
		b.setbContent(content);
		
		ArrayList<Board> replyList = new BoardService().insertReply(b);
		
		response.setContentType("application/json");
		new Gson().toJson(replyList, response.getWriter());
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}









