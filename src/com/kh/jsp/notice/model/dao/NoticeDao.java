package com.kh.jsp.notice.model.dao;

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
import static com.kh.jsp.common.JDBCTemplate.*;

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

	public ArrayList<Notice> selectList(Connection con) {
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
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

	
	
	public int insertNotice(Connection con, Notice reqNotice) {
		System.out.println();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int uno = Integer.parseInt(reqNotice.getnWriter());
			
			pstmt.setString(1, reqNotice.getnTitle());
			pstmt.setString(2, reqNotice.getnContent());
			pstmt.setInt(3, uno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
