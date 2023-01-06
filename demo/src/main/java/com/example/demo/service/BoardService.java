package com.example.demo.service;

import com.example.demo.domain.entity.Board;
import com.example.demo.domain.repository.BoardRepository;
import com.example.demo.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    // readOnly
    // for문 말고 람다,스트림(stream)
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .study_project(board.getStudy_project())
                    .person_num(board.getPerson_num())
                    .online_offline(board.getOnline_offline())
                    .duration(board.getDuration())
                    .skill(board.getSkill())
                    .date(board.getDate())
                    .calling(board.getCalling())
                    .title(board.getTitle())
                    .input_content(board.getInput_content())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .study_project(board.getStudy_project())
                .person_num(board.getPerson_num())
                .online_offline(board.getOnline_offline())
                .duration(board.getDuration())
                .skill(board.getSkill())
                .date(board.getDate())
                .calling(board.getCalling())
                .title(board.getTitle())
                .input_content(board.getInput_content())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> list(int page) {
        return boardRepository.findAll(PageRequest.of(page,3, Sort.by(Sort.Direction.DESC,"idx")));

    }

}

