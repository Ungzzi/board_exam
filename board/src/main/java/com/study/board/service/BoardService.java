package com.study.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository; // Dependency Injection

	// 글 작성 처리
	public void write(Board board, MultipartFile file) throws Exception {

		String projectPath = System.getProperty("user.dir") + "/src/main/webapp/"; // 프로젝트 경로를
		// 담아줌

		UUID uuid = UUID.randomUUID(); // 식별자

		String fileName = uuid + "_" + file.getOriginalFilename();

		File saveFile = new File(projectPath, fileName);

		file.transferTo(saveFile);

		board.setFilename(fileName);
		board.setFilepath("/webapp/" + fileName);

		boardRepository.save(board);
	}

	// DB 게시글 리스트 처리
	public List<Board> boardList() {
		return boardRepository.findAll();
	}

	// 특정 게시글 불러오기
	public Board boardView(Integer id) {
		return boardRepository.findById(id).get(); // findById는 optional 값을 받아오므로 .get()을 사용해서 optional 객체에 접
	}

	// 특정 게시글 삭제
	public void boardDelete(Integer id) {

		boardRepository.deleteById(id);
	}
}
