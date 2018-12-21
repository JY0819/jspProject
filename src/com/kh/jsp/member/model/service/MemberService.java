package com.kh.jsp.member.model.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

import static com.kh.jsp.common.JDBCTemplate.*;

public class MemberService {

	public Member loginCheck(Member reqMember) {
		Connection con = getConnection();

		Member loginUser = new MemberDao().loginCheck(con, reqMember);

		close(con);

		return loginUser;
	}

	public int insertMember(Member reqMember) {
		Connection con = getConnection();

		int result = new MemberDao().insertMember(con, reqMember);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	// 아이디 체크 (회원가입)
	public int idCheck(String userId) {
		Connection con = getConnection();
		
		int result = new MemberDao().idCheck(con, userId);
		
		close(con);
		
		return result;
	}

}
