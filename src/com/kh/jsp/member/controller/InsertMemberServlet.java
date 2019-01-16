package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/insertMember.me")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String nickName = request.getParameter("nickName");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String phone = tel1 + "-" + tel2 + "-" + tel3;
		String email = request.getParameter("email");
		String zip = request.getParameter("zipCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address = zip + '@' + address1 + "@" + address2;
		String[] irr = request.getParameterValues("interest");
		String interest = "";

		if (irr != null) {
			for (int i = 0; i < irr.length; i++) {
				if (i == 0) {
					interest += irr[i];
				} else {
					interest += ", " + irr[i];
				}
			}
		}
		
		System.out.println("userId : " + userId);
		System.out.println("userPwd : " + userPwd);
		System.out.println("nickName : " + nickName);
		System.out.println("phone : " + phone);
		System.out.println("email : " + email);
		System.out.println("address : " + address);
		System.out.println("interest : " + interest);

		Member reqMember = new Member();
		reqMember.setUserId(userId);
		reqMember.setUserPwd(userPwd);
		reqMember.setNickName(nickName);
		reqMember.setPhone(phone);
		reqMember.setEmail(email);
		reqMember.setAddress(address);
		reqMember.setInterest(interest);

		int result = new MemberService().insertMember(reqMember);

		if (result > 0) {
			request.getSession().setAttribute("msg", "회원가입 성공 ^ㅁ^!");
			response.sendRedirect("views/common/successPage.jsp");
		} else {
			request.setAttribute("msg", "회원가입 실패 ㅠㅅㅜ");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
