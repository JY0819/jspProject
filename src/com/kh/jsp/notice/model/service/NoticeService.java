package com.kh.jsp.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;
import static com.kh.jsp.common.JDBCTemplate.*;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection con = getConnection();

		ArrayList<Notice> list = new NoticeDao().selectList(con);

		close(con);

		return list;
	}

	// 공지사항 등록
	public int insertNotice(Notice reqNotice) {
		Connection con = getConnection();
		System.out.println("서비스");
		int result = new NoticeDao().insertNotice(con, reqNotice);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

}
