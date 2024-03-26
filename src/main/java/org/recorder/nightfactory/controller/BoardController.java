package org.recorder.nightfactory.controller;

import io.micrometer.common.util.StringUtils;
import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "/list";
    }

    @GetMapping("/post")
    public String post() {
        return "/post";
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

    @PostMapping("/save")
    public ResponseEntity<String> savePost(@ModelAttribute BoardDTO boardDto) {
        if (StringUtils.isEmpty(boardDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be empty.");
        }

        try {
            Long postId = boardService.savePost(boardDto);
            // 성공적으로 저장된 경우 postId를 응답으로 전송
            return ResponseEntity.ok(postId.toString());
        } catch (IllegalArgumentException e) {
            // 에러가 발생한 경우 "error" 문자열을 응답으로 전송
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/list";
    }
}
