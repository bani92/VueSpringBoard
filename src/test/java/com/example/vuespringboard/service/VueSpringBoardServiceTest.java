package com.example.vuespringboard.service;

import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.entity.VueSpringBoardEntity;
import com.example.vuespringboard.repository.VueSpringBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VueSpringBoardServiceTest {


    @Autowired
    VueSpringBoardService vueSpringBoardService;

    @Test
    void 게시물_생성() {
        // given
        VueSpringBoardDto boardDto = VueSpringBoardDto.builder()
                .title("2번 게시물")
                .author("김작성자")
                .content("안녕하세요").build();


        // when
        Long createId = vueSpringBoardService.create(boardDto);

        VueSpringBoardDto boardTwoItem = vueSpringBoardService.getBoard(createId);

        // then
        assertEquals("2번 게시물",boardTwoItem.getTitle());
        assertEquals("김작성자",boardTwoItem.getAuthor());
        assertEquals("안녕하세요",boardTwoItem.getContent());
    }

    @Test
    void 존재하는_게시글을_조회하는_경우() {
        // given
        // when
        VueSpringBoardDto board = vueSpringBoardService.getBoard(1L);
        // then
        assertNotNull(board,"엔티티가 존재해야합니다.");

        assertEquals("테스트 제목",board.getTitle());
        assertEquals("테스트 작성자",board.getAuthor());
        assertEquals("테스트 내용",board.getContent());
        assertNotNull(board.getCreatedAt());
        assertNotNull(board.getUpdatedAt());
    }

    @Test
    void 존재하지않는_게시글을_조회하는_경우() {

        // when
        VueSpringBoardDto board = vueSpringBoardService.getBoard(999L);
        // then
        assertNull(board.getId(), "엔티티가 존재하지 않아야 합니다.");

    }
}