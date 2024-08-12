package com.example.vuespringboard.controller;

import com.example.vuespringboard.dto.ResultDto;
import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.service.VueSpringBoardService;
import com.example.vuespringboard.utils.CommonEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getBoard(@PathVariable("id") Long id) {

        ResultDto resultDto = new ResultDto();
        VueSpringBoardDto vueSpringBoardDto = vueSpringBoardService.getBoard(id);

        if(vueSpringBoardDto.getId() == null) {
            resultDto.setCd(CommonEnum.VIEW_FAIL.getCode());
            resultDto.setMessage(CommonEnum.VIEW_FAIL.getDescription());
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(vueSpringBoardDto, HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<?> create(@RequestBody VueSpringBoardDto vueSpringBoardDto) {

        Long insertId = vueSpringBoardService.create(vueSpringBoardDto);

        if (insertId == 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(vueSpringBoardService.getBoard(insertId), HttpStatus.OK);
    }

    @PatchMapping("/board")
    public ResponseEntity<?> update(@RequestBody VueSpringBoardDto vueSpringBoardDto) {

        Long updateId = vueSpringBoardService.update(vueSpringBoardDto);

        if (updateId == 0) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(vueSpringBoardService.getBoard(updateId), HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return new ResponseEntity<>(vueSpringBoardService.delete(id), HttpStatus.OK);

    }
}
