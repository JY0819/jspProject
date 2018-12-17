package com.kh.jsp.notice.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jsp.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop = new Properties();

	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 공지사항 목록
	public ArrayList<Notice> selectList(Connection con) {
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectList");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			list = new ArrayList<Notice>();

			while (rset.next()) {
				Notice n = new Notice();

				n.setNno(rset.getInt("NNO"));
				n.setnTitle(rset.getString("NTITLE"));
				n.setnContent(rset.getString("NCONTENT"));
				n.setnWriter(rset.getString("NICK_NAME"));
				n.setnCount(rset.getInt("NCOUNT"));
				n.setnDate(rset.getDate("NDATE"));

				list.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	// 공지사항 등록
	public int insertNotice(Connection con, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertNotice");

		try {
			pstmt = con.prepareStatement(query);

			int uno = Integer.parseInt(n.getnWriter());

			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setInt(3, uno);
			pstmt.setDate(4, n.getnDate());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 상세보기
	public Notice selectOne(Connection con, String num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;

		String query = prop.getProperty("selectOne");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(num));

			rset = pstmt.executeQuery();

			if (rset.next()) {
				n = new Notice();

				n.setNno(rset.getInt("NNO"));
				n.setnTitle(rset.getString("NTITLE"));
				n.setnContent(rset.getString("NCONTENT"));
				n.setnWriter(rset.getString("NICK_NAME"));
				n.setnCount(rset.getInt("NCOUNT"));
				n.setnDate(rset.getDate("NDATE"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return n;
	}

	// 조회수 증가
	public int updateCount(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateCount");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, nno);
			pstmt.setInt(2, nno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 공지사항 수정
	public int updateNotice(Connection con, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateNotice");

		try {
			pstmt = con.prepareStatement(query);

			int uno = Integer.parseInt(n.getnWriter());

			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setDate(3, n.getnDate());
			pstmt.setInt(4, uno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	
	public int deleteNotice(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteNotice");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

}
