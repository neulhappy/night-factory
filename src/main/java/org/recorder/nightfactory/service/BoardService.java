package org.recorder.nightfactory.service;

import org.recorder.nightfactory.domain.Board;
import org.recorder.nightfactory.dto.BoardDTO;
import org.recorder.nightfactory.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDTO boardDto) {
        // 저장된 게시글의 ID를 반환
        return boardRepository.save(boardDto.toEntity()).getId();
    }


    @Transactional
    public List<BoardDTO> getBoardList() {
        // 게시글 목록을 조회하여 BoardDTO 목록으로 변환하여 반환
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(board -> {
                    BoardDTO boardDto = new BoardDTO();
                    boardDto.setId(board.getId());
                    boardDto.setAuthor(board.getAuthor());
                    boardDto.setTitle(board.getTitle());
                    boardDto.setContent(board.getContent());
                    boardDto.setCreatedDate(board.getCreatedDate());
                    return boardDto;
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public BoardDTO getBoardById(Long id) {
        // ID에 해당하는 게시글을 조회하여 BoardDTO로 변환하여 반환
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다."));
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
        // 삭제할 게시글 조회
        Board deletedBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다."));

        // 게시글 삭제
        boardRepository.delete(deletedBoard);
    }
}
