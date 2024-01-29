package org.recorder.nightfactory.service;


import org.recorder.nightfactory.domain.Board;
import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDTO boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDTO> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDTO boardDto = BoardDTO.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseGet(() -> Board.builder().build()); // 게시글이 없을 때만 새로운 Board 생성

        // Board 엔티티를 BoardDTO로 변환
        return BoardDTO.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }

    @Transactional
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }

}
