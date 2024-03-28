package org.recorder.nightfactory.controller;

import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(password);

        // BoardDTO 객체 생성
        BoardDTO boardDto = new BoardDTO();
        boardDto.setTitle(title);
        boardDto.setAuthor(author);
        boardDto.setContent(content);
        boardDto.setPassword(encryptedPassword); // 암호화된 비밀번호 저장
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
    public ResponseEntity<?> viewPost(@RequestParam("id") Long postId,
                                      @RequestParam("password") String password) {

        BoardDTO post = boardService.getBoardById(postId);

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        // 입력된 비밀번호를 암호화하여 저장된 암호화된 비밀번호와 비교
        if (passwordEncoder.matches(password, post.getPassword())) {
            // 게시물의 내용만 클라이언트에게 반환
            return ResponseEntity.ok(post.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }
    }



}
