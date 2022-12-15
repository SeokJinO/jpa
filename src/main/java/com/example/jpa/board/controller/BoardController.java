package com.example.jpa.board.controller;

import com.example.jpa.board.entity.BoardEntity;
import com.example.jpa.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/list")
    public List<BoardEntity> boardList() {
        return boardRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<BoardEntity> viewBoard(@PathVariable(name = "id") Integer id) {

        return boardRepository.findById(id);
    }

    @PostMapping("")
    public String insertBoard(@RequestBody BoardEntity board) {
        boardRepository.save(board);
        return board.toString();
    }

    @PutMapping("/{id}")
    public Optional<BoardEntity> updateBoard(@PathVariable(name = "id") Integer id, @RequestBody BoardEntity board) {
        Optional<BoardEntity> dbBoard = boardRepository.findById(id);
        return dbBoard;
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable(name = "id") Integer id) {
        if(boardRepository.findById(id).isEmpty()) {
            return "해당 게시글은 존재하지 않습니다1123";
        } else {
            boardRepository.delete(BoardEntity.builder().id(id).build());
            return "삭제 완료";
        }
    }
}
