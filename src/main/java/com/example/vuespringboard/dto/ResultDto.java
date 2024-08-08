package com.example.vuespringboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * class       :ResultDto
 * Package      :com.example.vuespringboard.dto
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-08 오후 3:03
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-08 :            : 신규 개발.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "결과응답 ")
public class ResultDto<T> {

    @Schema(description = "코드")
    private String cd;
    @Schema(description = "메세지")
    private String message;
}
