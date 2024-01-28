package com.RBook.board.service;

import com.RBook.board.domain.Board;
import com.RBook.board.dto.BoardDTO;
import com.RBook.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 5;

    //엔티티  => DTO 변환
    private BoardDTO convertEntityToDto(Board board) {
        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .author(board.getAuthor())
                .content(board.getContent())
                .genre(board.getGenre())
                .user(board.getUser())
                .createDate(board.getCreateDate())
                .modifyDate(board.getModifyDate())
                .build();
    }

    /*
    //BoardImage타입을 List<String>타입으로 저장
    private List<String> getBoardImageUrls(Board board) {
        return board.getBoardImage().stream()
                .map(BoardImage::getUrl)
                .collect(Collectors.toList());
    }
*/
    //게시판 목록 불러오기
    public List<BoardDTO> getBoardList(Integer pageNum) {
        Page<Board> page = boardRepository.findAll(PageRequest.of(
                pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));

        List<Board> boardEntities = page.getContent();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (Board board : boardEntities) {
            boardDTOList.add(this.convertEntityToDto(board)); //boardEntities를 board에 담아 DTO로 변환해서 리스트에 담음
        }
        return boardDTOList;
    }

    // 게시글 가져와서 boardDTO 객체로 만드는 함수
    public BoardDTO getBoard(Long boardId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.get();

        BoardDTO boardDTO = BoardDTO.builder()
                .boardId(board.getBoardId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .author(board.getAuthor())
                .content(board.getContent())
                .genre(board.getGenre())
                .user(board.getUser())
                .createDate(board.getCreateDate())
                .modifyDate(board.getModifyDate())
                .build();

        return boardDTO;
    }

    //게시글 저장, boardDTO에 값을 넣고 toEntity로 데이터 변환해서 저장
    public BoardDTO saveBoard(BoardDTO boardDTO) {
        boardRepository.save(boardDTO.toEntity());
        return boardDTO;
    }

    public BoardDTO modifyBoard(BoardDTO boardDTO, Long userId) {
        Long boardId = boardDTO.getBoardId();
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.get();

        if (board.getUser().getId().equals(userId)) {
            boardRepository.save(boardDTO.toEntity());
        }
        return boardDTO;
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    //검색API
    public List<BoardDTO> searchBoards(String keyword) {
        List<Board> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDTO> boardDTOList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDTOList;

        for (Board board : boardEntities) {
            boardDTOList.add(this.convertEntityToDto(board));
        }

        return boardDTOList;
    }

    public Long getBoardCount() {
        return boardRepository.count(); //전체 게시글 개수 가져옴
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());
        
        // 총 게시글 기준으로 계산한 마지막 페이지 계산
        Integer totalLastPageNum = (int)((Math.ceil(postsTotalCount/PAGE_POST_COUNT)));
        
        // 현재 페이지 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        //페이지 번호 할당
        for (int val = curPageNum, index = 0; val <= blockLastPageNum; val++, index++) {
            pageList[index] = val;
        }

        return pageList;

    }

}
