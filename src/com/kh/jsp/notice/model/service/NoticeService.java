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

}
