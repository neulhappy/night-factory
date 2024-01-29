package org.recorder.nightfactory.controller;

import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/page/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        // id에 해당하는 게시글 정보를 가져옴
        BoardDTO post = boardService.getBoardById(id);

        // 모델에 "post" 속성으로 게시글 정보 추가
        model.addAttribute("post", post);

        // "textPage.html" 뷰를 반환
        return "textPage";
    }

    @PostMapping("/post")
    public String write(BoardDTO boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/list";
    }
}