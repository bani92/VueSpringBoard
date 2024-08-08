package com.example.vuespringboard.controller;

import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.entity.VueSpringBoardEntity;
import com.example.vuespringboard.service.VueSpringBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * class       :VueSpringBoardController
 * Package      :com.example.vuespringboard.controller
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-07 오후 2:25
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-07 :            : 신규 개발.
 */
@Slf4j  // Lombok logging 관련 어노테이션 선언
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class VueSpringBoardController {

    private final VueSpringBoardService vueSpringBoardService;

    @GetMapping("/board/list")
    public List<VueSpringBoardDto> boardList() {
        return vueSpringBoardService.getBoardList();
    }

    @GetMapping("/board/{id}")
    public VueSpringBoardDto getBoard(@PathVariable("id") Long id) {
        return vueSpringBoardService.getBoard(id);
    }

    @PostMapping("/board")
    public VueSpringBoardEntity create(@RequestBody VueSpringBoardDto vueSpringBoardDto) {
        return vueSpringBoardService.create(vueSpringBoardDto);
    }

    @PatchMapping("/board")
    public VueSpringBoardEntity update(@RequestBody VueSpringBoardDto vueSpringBoardDto) {
        return vueSpringBoardService.update(vueSpringBoardDto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        vueSpringBoardService.delete(id);
    }
}
