package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

@WebServlet("/update.no")
public class UpdateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateNoticeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("nno");
		String date = request.getParameter("date");
		String content = request.getParameter("content");

		java.sql.Date day = null;

		if (date != "") {
			String[] dateArr = date.split("-");
			int[] drr = new int[dateArr.length];

			for (int i = 0; i < dateArr.length; i++) {
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

		int result = new NoticeService().updateNotice(n);

		String page = "";

		if (result > 0) {
			page = "/jsp/selectOne.no?num="+n.getnWriter();
			response.sendRedirect(page);
//			request.setAttribute("n", n);
		} else {
			request.setAttribute("msg", "공지사항 수정 실패 8ㅅ8");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
