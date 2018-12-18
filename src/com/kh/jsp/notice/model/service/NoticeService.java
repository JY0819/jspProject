package com.kh.jsp.notice.model.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection con = getConnection();

		ArrayList<Notice> list = new NoticeDao().selectList(con);

		close(con);

		return list;
	}

	// 공지사항 등록
	public int insertNotice(Notice n) {
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, n);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	// 상세보기
	public Notice selectOne(String num) {
		Connection con = getConnection();

		Notice n = new NoticeDao().selectOne(con, num);

		int result = 0;

		// 상세보기 성공 시 조회수를 올려주는 메소드를 호출
		if (n != null) {
			result = new NoticeDao().updateCount(con, n.getNno());
			if (result > 0)
				commit(con);
			else
				rollback(con);
		}

		close(con);

		return n;
	}

	// 공지사항 수정
	public int updateNotice(Notice n) {
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, n);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	// 공지사항 삭제
	public int deleteNotice(int nno) {
		Connection con = getConnection();

		int result = new NoticeDao().deleteNotice(con, nno);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	// 페이징 처리 적용한 공지사항 조회용 메소드
	public ArrayList<Notice> selectList(int currentPage, int limit) {
		Connection con = getConnection();

		ArrayList<Notice> list = new NoticeDao().selectList(con, currentPage, limit);

		close(con);

		return list;
	}

	// 전체 공지사항 수 조회
	public int getListCount() {
		Connection con = getConnection();

		int listCount = new NoticeDao().getListCount(con);

		close(con);

		return listCount;
	}

}
