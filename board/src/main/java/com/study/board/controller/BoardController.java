package com.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board/write")
	public String boardWriteForm() {

		return "boardwrite";
	}

	@PostMapping("/board/writepro")
	public String boardWritePro(Board board) {

		boardService.write(board);

		return "";
	}

	@GetMapping("/board/list")
	public String boardList(Model model) {

		// 첫번째 인자 "list" 에 boardList에서 반환된 리스트를 넘김
		model.addAttribute("list", boardService.boardList());

		return "boardlist";
	}

	@GetMapping("/board/view") // 8080/board/view?id=1
	public String boardView(Model model, Integer id) {

		model.addAttribute("board", boardService.boardView(id));

		return "boardview";
	}
}
