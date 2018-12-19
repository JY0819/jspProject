package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.common.MyFileRenamePolicy;
import com.kh.jsp.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/insert.tn")
public class InsertThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertThumbnailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		System.out.println(title);

		/*
		 * 폼 전송을 multipart/form-data로 전송하는 경우에는 기존처럼 request.getParameter로 값을 받을 수 없다.
		 * cos.jar가 파일과 다른 값들도 받아주는 역할을 한다. com.oreilly.servlet의 약자이다.
		 */

		if (ServletFileUpload.isMultipartContent(request)) {
			// 전송 파일 용량 제한 : 10MB로 제한
			int maxSize = 1024 * 1024 * 10;

			String root = request.getSession().getServletContext().getRealPath("/");
			System.out.println(root);

			String filePath = root + "thumbnail_uploadFiles/";

			/*
			 사용자가 올린 파일명을 그대로 저장하지 않는 것이 일반적
			 
			 1. 같은 파일명이 있는 경우 이전 파일을 덮어 쓸 수 있다. 
			 2. 한글로 된 파일명, 특수기호, 띄어쓰기는 서버에 따라 
			 문제가 생길 수도 있다.
			 
			 DafaultFileRenamePolicy는 cos.jar에서 제공하는 클래스 같은 
			 파일명이 존재하는지를 검사하고 있을 경우에는 뒤에 숫자를 붙여준다.
			 */

			/*
			 MultipartRequest multiRequest = new MultipartRequest(request, filePath,
			 maxSize , "UTF-8", new DefaultFileRenamePolicy());
			 */

			MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			// 다중 파일을 묶어서 업로드하기 위해 컬렉션 사용
			// 저장한 파일의 이름을 저장할 arrayList 생성
			ArrayList<String> saveFiles = new ArrayList<String>();

			// 원본 파일의 이름을 저장할 ArrayList 생성
			ArrayList<String> originFiles = new ArrayList<String>();

			// 각 파일의 정보를 구해온 뒤 DB에 저장할 목적의 데이터를 꺼내온다.
			Enumeration<String> files = multiRequest.getFileNames();

			while (files.hasMoreElements()) {
				String name = files.nextElement();

				System.out.println("name : " + name);

				saveFiles.add(multiRequest.getFilesystemName(name));
				originFiles.add(multiRequest.getOriginalFileName(name));

				System.out.println("fileSystem name : " + multiRequest.getFilesystemName(name));
				System.out.println("originFile name : " + multiRequest.getOriginalFileName(name));
			}

			String multiTitle = multiRequest.getParameter("title");
			String multiContent = multiRequest.getParameter("content");
			System.out.println(multiTitle);
			System.out.println(multiContent);

			// Board 객체 생성
			Board b = new Board();
			b.setbTitle(multiTitle);
			b.setbContent(multiContent);
			b.setbWriter(String.valueOf(((Member) (request.getSession().getAttribute("loginUser"))).getUno()));

			//Attachment 객체 생성하여 arrayList 객체 생성
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				Attachment at = new Attachment();
				at.setFilePath(filePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				fileList.add(at);
			}
			
			int result = new BoardService().insertThumbnail(b, fileList);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/selectList.tn");
			} else {
				// 실패 시 저장된 사진 삭제
				for(int i = 0; i < saveFiles.size(); i++) {
					// 파일 시스템에 저장된 이름으로 파일 객체 생성
					File failedFile = new File(filePath + saveFiles.get(i));
					
					// true / false 리턴
					failedFile.delete();
				}
				
				
				request.setAttribute("msg", "사진 게시판 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
