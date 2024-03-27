package org.recorder.nightfactory.controller;

import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.dto.PasswordRequest;
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

        model.addAttribute("post", post);

        return "textPage";
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestParam("title") String title,
                                      @RequestParam("author") String author,
                                      @RequestParam("content") String content,
                                      @RequestParam("password") String password,
                                      @RequestParam("phone") String phone) {
        // 폼 데이터 검증
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("비밀번호를 입력해주세요.");
        }

        // BoardDTO 객체 생성
        BoardDTO boardDto = new BoardDTO();
        boardDto.setTitle(title);
        boardDto.setAuthor(author);
        boardDto.setContent(content);
        boardDto.setPassword(password);
        boardDto.setPhone(phone);

        try {
            Long postId = boardService.savePost(boardDto);
            return ResponseEntity.ok("게시글이 성공적으로 저장되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("게시글 저장에 실패했습니다.");
        }
    }


    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/list";
    }

    @PostMapping("/view")
    public ResponseEntity<?> viewPost(@RequestBody PasswordRequest passwordRequest) {
        Long postId = passwordRequest.getId();
        String password = passwordRequest.getPassword();

        BoardDTO post = boardService.getBoardById(postId);

        if (post == null) {
            return ResponseEntity.notFound().build(); // 게시물을 찾을 수 없는 경우 404 응답 반환
        }

        if (post.getPassword().equals(password)) {
            return ResponseEntity.ok(post); // 비밀번호가 일치하는 경우 게시글 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다."); // 비밀번호가 일치하지 않는 경우 401 응답 반환
        }
    }


}
