package com.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String boardWritePro(Board board, Model model) {

		boardService.write(board);

		model.addAttribute("message", "글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");

		return "message";
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

	@GetMapping("/board/delete")
	public String boardDelete(Integer id, Model model) {
		boardService.boardDelete(id);

		model.addAttribute("message", "게시글이 삭제되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		return "message";
	}

	// PathVariable : GetMapping의 id 부분을 인식해서, id 라는 Integer 변수에 담
	@GetMapping("/board/modify/{id}")
	public String boardModify(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("board", boardService.boardView(id));
		return "boardmodify";
	}

	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model) {

		Board boardTemp = boardService.boardView(id);
		boardTemp.setTitle(board.getTitle());
		boardTemp.setContent(board.getContent());

		boardService.write(boardTemp);

		model.addAttribute("message", "글 수정이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");

		return "message";
	}
}
