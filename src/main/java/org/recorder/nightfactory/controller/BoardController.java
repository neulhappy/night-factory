package org.recorder.nightfactory.controller;

import io.micrometer.common.util.StringUtils;
import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.dto.PasswordRequest;
import org.recorder.nightfactory.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String savePost(@ModelAttribute BoardDTO boardDto) {
        if (StringUtils.isEmpty(boardDto.getPassword())) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        try {
            Long postId = boardService.savePost(boardDto);
            // 글이 성공적으로 저장되면 목록 페이지로 리다이렉트
            return "redirect:/list";
        } catch (IllegalArgumentException e) {

            return "errorPage";
        }
    }


    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/list";
    }

    @PostMapping("/view")
    public String viewPost(@RequestBody PasswordRequest passwordRequest, Model model) {
        // 게시글 정보 가져오기
        BoardDTO post = boardService.getBoardById(passwordRequest.getId());

        // 비밀번호 확인
        if (post != null && post.getPassword().equals(passwordRequest.getPassword())) {
            // 비밀번호가 일치하면 게시글을 모델에 추가하여 보여줌
            model.addAttribute("post", post);
            return "textPage";
        } else {
            // 비밀번호가 일치하지 않으면 에러 메시지를 반환
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


}
