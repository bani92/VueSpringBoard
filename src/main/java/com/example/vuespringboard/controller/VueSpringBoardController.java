package com.example.vuespringboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class VueSpringBoardController {

    @GetMapping("/test")
    public String hello() {
        return "Hello";
    }
}
