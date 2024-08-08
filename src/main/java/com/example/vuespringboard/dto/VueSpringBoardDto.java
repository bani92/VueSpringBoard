package com.example.vuespringboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * class       :VueSpringBoardDto
 * Package      :com.example.vuespringboard.dto
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-07 오후 2:59
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-07 :            : 신규 개발.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VueSpringBoardDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String updatedAt;
}
