package com.study.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository; // Dependency Injection

	// 글 작성 처리
	public void write(Board board) {
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
}
