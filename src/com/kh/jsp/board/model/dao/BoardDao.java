package com.kh.jsp.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import static com.kh.jsp.common.JDBCTemplate.*;

public class BoardDao {
	private Properties prop = new Properties();

	public BoardDao() {
		String fileName = Board.class.getResource("/sql/board/board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Board> selectList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;

		String query = prop.getProperty("selectList");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			list = new ArrayList<Board>();

			while (rset.next()) {
				Board b = new Board();

				b.setBid(rset.getInt("BID"));
				b.setbType(rset.getInt("BTYPE"));
				b.setBno(rset.getInt("BNO"));
				b.setCategory(rset.getString("CNAME"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setbWriter(rset.getString("NICK_NAME"));
				b.setbCount(rset.getInt("BCOUNT"));
				b.setRefBid(rset.getInt("REF_BID"));
				b.setReplyLevel(rset.getInt("REPLY_LEVEL"));
				b.setbDate(rset.getDate("BDATE"));
				b.setModifyDate(rset.getDate("MODIFY_DATE"));
				b.setStatus(rset.getString("STATUS"));

				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	// 게시글 작성용 메소드
	public int insertBoard(Connection con, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertBoard");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, b.getCategory());
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setString(4, b.getbWriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 페이징 처리 적용한 게시물 조회용 메소드
	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;

		String query = prop.getProperty("selectList");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<Board>();

			while (rset.next()) {
				Board b = new Board();

				b.setBid(rset.getInt("BID"));
				b.setbType(rset.getInt("BTYPE"));
				b.setBno(rset.getInt("BNO"));
				b.setCategory(rset.getString("CNAME"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setbWriter(rset.getString("NICK_NAME"));
				b.setbCount(rset.getInt("BCOUNT"));
				b.setRefBid(rset.getInt("REF_BID"));
				b.setReplyLevel(rset.getInt("REPLY_LEVEL"));
				b.setbDate(rset.getDate("BDATE"));
				// b.setModifyDate(rset.getDate("MODIFY_DATE"));
				b.setStatus(rset.getString("STATUS"));

				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 전체 게시글 수 조회
	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;

		String query = prop.getProperty("listCount");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public int insertThumbnailContent(Connection con, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertThumb");

		System.out.println(query);

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setInt(3, Integer.parseInt(b.getbWriter()));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectCurrval(Connection con) {
		// 조회
		Statement stmt = null;
		ResultSet rset = null;

		int bid = 0;

		String query = prop.getProperty("selectCurrval");

		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				bid = rset.getInt("CURRVAL");
			}
			System.out.println(bid);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return bid;
	}

	public int insertAttachment(Connection con, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertAttachment");

		try {
			for (int i = 0; i < fileList.size(); i++) {
				pstmt = con.prepareStatement(query);

				pstmt.setInt(1, fileList.get(i).getBid());
				pstmt.setString(2, fileList.get(i).getOriginName());
				pstmt.setString(3, fileList.get(i).getChangeName());
				pstmt.setString(4, fileList.get(i).getFilePath());

				int level = 0;
				if (i == 0)
					level = 0;
				else
					level = 1;

				pstmt.setInt(5, level);

				// 마지막 결과만 들어가면 양수(1)이기 때문에 누적 연산 사용
				result += pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 썸네일 리스트
	public ArrayList<HashMap<String, Object>> selectThumbnailList(Connection con) {
		Statement stmt = null;
		ArrayList<HashMap<String, Object>> list = null;
		HashMap<String, Object> hmap = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectThumbnailMap");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			list = new ArrayList<HashMap<String, Object>>();

			while (rset.next()) {
				hmap = new HashMap<String, Object>();

				hmap.put("bid", rset.getInt("bid"));
				hmap.put("bno", rset.getInt("bno"));
				hmap.put("btitle", rset.getString("btitle"));
				hmap.put("bcontent", rset.getString("bcontent"));
				hmap.put("bwriter", rset.getString("nick_name"));
				hmap.put("bcount", rset.getInt("bcount"));
				hmap.put("bdate", rset.getDate("bdate"));
				hmap.put("fid", rset.getInt("fid"));
				hmap.put("originName", rset.getString("origin_name"));
				hmap.put("changeName", rset.getString("change_name"));
				hmap.put("filePath", rset.getString("file_path"));
				hmap.put("uploadDate", rset.getDate("upload_date"));

				list.add(hmap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}

		return list;
	}

	// 사진게시판 조회수
	public int updateCount(Connection con, int num) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateCount");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, num);
			pstmt.setInt(2, num);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 사진게시판 상세보기
	public HashMap<String, Object> selectThumbnailMap(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Object> hmap = null;
		Board b = null;
		Attachment at = null;
		ArrayList<Attachment> list = null;

		String query = prop.getProperty("selectThumbnailOne");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			list = new ArrayList<Attachment>();

			while (rset.next()) {
				b = new Board();

				b.setBid(rset.getInt("BID"));
				b.setBno(rset.getInt("BNO"));
				b.setbTitle(rset.getString("BTITLE"));
				b.setbContent(rset.getString("BCONTENT"));
				b.setbWriter(rset.getString("NICK_NAME"));
				b.setbCount(rset.getInt("BCOUNT"));
				b.setbDate(rset.getDate("BDATE"));

				at = new Attachment();

				at.setFid(rset.getInt("FID"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setUploadDate(rset.getDate("UPLOAD_DATE"));
				at.setFilePath(rset.getString("FILE_PATH"));

				list.add(at);
			}

			hmap = new HashMap<String, Object>();
			hmap.put("board", b);
			hmap.put("attachment", list);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return hmap;
	}

	// 파일 다운로드
	public Attachment selectOneAttachment(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment file = null;

		String query = prop.getProperty("selectOneAttachment");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				file = new Attachment();

				file.setFid(rset.getInt("FID"));
				file.setBid(rset.getInt("BID"));
				file.setOriginName(rset.getString("ORIGIN_NAME"));
				file.setChangeName(rset.getString("CHANGE_NAME"));
				file.setFilePath(rset.getString("FILE_PATH"));
				file.setFileLevel(rset.getInt("FILE_LEVEL"));
				file.setUploadDate(rset.getDate("UPLOAD_DATE"));
				file.setDownloadCount(rset.getInt("DOWNLOAD_COUNT"));
				file.setStatus(rset.getString("STATUS"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return file;
	}

}
