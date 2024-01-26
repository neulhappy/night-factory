package org.recorder.nightfactory.controller;

import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDTO boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}