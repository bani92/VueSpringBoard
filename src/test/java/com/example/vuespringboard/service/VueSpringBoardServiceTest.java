package com.example.vuespringboard.service;

import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.entity.VueSpringBoardEntity;
import com.example.vuespringboard.repository.VueSpringBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void 게시물_생성시_제목_없음() {

        // given
        VueSpringBoardDto boardDto = VueSpringBoardDto.builder()
                .title("")  // 빈 제목
                .author("테스트_저자")
                .content("테스트_내용")
                .build();

        // when
        Long createId = vueSpringBoardService.create(boardDto);

        // then
        assertEquals(0L, createId, "제목이 없는 게시물은 생성되지 않아야 합니다.");
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

    @Test
    void 게시물_수정_성공() {
        // given
        VueSpringBoardDto createBoardDto = VueSpringBoardDto.builder()
                .title("생성_제목")
                .author("홍길동")
                .content("생성_게시물").build();

        // when
        Long createId = vueSpringBoardService.create(createBoardDto);

        VueSpringBoardDto createResultDto = vueSpringBoardService.getBoard(createId);

        VueSpringBoardDto updateBoardDto = VueSpringBoardDto.builder()
                .id(createResultDto.getId())
                .title("수정_제목")
                .author("홍길동22")
                .content("수정_게시물")
                .createdAt(createResultDto.getCreatedAt())
                .build();

        Long updateId = vueSpringBoardService.update(updateBoardDto);

        VueSpringBoardDto result = vueSpringBoardService.getBoard(updateId);

        // then
        assertThat(result.getTitle()).isEqualTo("수정_제목");
        assertThat(result.getAuthor()).isEqualTo("홍길동22");
        assertThat(result.getContent()).isEqualTo("수정_게시물");
    }

    @Test
    void 게시물_수정_실패() {
        // given
        VueSpringBoardDto createBoardDto = VueSpringBoardDto.builder()
                .title("생성_제목")
                .author("홍길동")
                .content("생성_게시물").build();

        // when
        Long createId = vueSpringBoardService.create(createBoardDto);

        VueSpringBoardDto createResultDto = vueSpringBoardService.getBoard(createId);

        VueSpringBoardDto updateBoardDto = VueSpringBoardDto.builder()
                .id(999L)
                .title("수정_제목")
                .author("홍길동22")
                .content("수정_게시물")
                .createdAt(createResultDto.getCreatedAt())
                .build();


        Long updateId = vueSpringBoardService.update(updateBoardDto);

        // then
        assertThat(updateId).isEqualTo(0);
    }

    @Test
    void 게시물_삭제_성공() {
        // given
        VueSpringBoardDto boardDto = VueSpringBoardDto.builder()
                .title("3번 게시물")
                .author("작성자")
                .content("작성내용").build();
        // when
        Long createId = vueSpringBoardService.create(boardDto);

        assertNotNull(createId, "게시물 생성 시 ID가 null이어야 합니다.");

        boolean deleteResult = vueSpringBoardService.delete(createId);
        // then
        assertTrue(deleteResult, "게시물 삭제에 실패하였습니다.");
    }

    @Test
    void 게시물_삭제_실패() {
        // given
        // when
        boolean deleteResult = vueSpringBoardService.delete(2L);
        // then
        assertFalse(deleteResult,"게시물 삭제에 성공하였습니다.");
    }

    @Test
    void 예외_발생시_트랜잭션_롤백_확인() {
        // given
        VueSpringBoardDto createBoardDto = VueSpringBoardDto.builder()
                .title("제목")
                .author("저자")
                .content("내용")
                .build();

        Long createId = vueSpringBoardService.create(createBoardDto);
        // when
        Long updateId = vueSpringBoardService.update(VueSpringBoardDto.builder()
                .id(999L) // 존재하지 않는 ID로 업데이트 시도
                .title("수정된 제목")
                .author("수정된 저자")
                .content("수정된 내용")
                .build());

        // then
        assertEquals(0L, updateId, "업데이트가 실패한 경우 반환값이 0L이어야 합니다.");

        VueSpringBoardDto result = vueSpringBoardService.getBoard(createId);

        assertNotNull(result,"트랜잭션 롤백 후에도 게시물이 유지되어야 합니다.");
        assertEquals("제목", result.getTitle(), "업데이트 실패 후 게시물의 제목이 원래대로 유지되어야 합니다.");
        assertEquals("저자", result.getAuthor(), "업데이트 실패 후 게시물의 저자가 원래대로 유지되어야 합니다.");
        assertEquals("내용", result.getContent(), "업데이트 실패 후 게시물의 내용이 원래대로 유지되어야 합니다.");
    }
}