package com.example.vuespringboard.service;

import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.entity.VueSpringBoardEntity;
import com.example.vuespringboard.repository.VueSpringBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * class       :VueSpringBoardService
 * Package      :com.example.vuespringboard.service
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-07 오후 2:25
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-07 :            : 신규 개발.
 */
@Service
@RequiredArgsConstructor
public class VueSpringBoardService {

    private final VueSpringBoardRepository vueSpringBoardRepository;

    /**
     * 게시글 목록 조회
     */
    public List<VueSpringBoardDto> getBoardList() {
        List<VueSpringBoardEntity> boardEntities = vueSpringBoardRepository.findAll();
        List<VueSpringBoardDto> dtos = new ArrayList<>();

        for (VueSpringBoardEntity entity : boardEntities) {
            VueSpringBoardDto dto = VueSpringBoardDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .author(entity.getAuthor())
                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .updatedAt(entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * 게시글 상세정보 조회
     */
    public VueSpringBoardDto getBoard(Long id) {
        VueSpringBoardEntity entity = vueSpringBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return VueSpringBoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .updatedAt(entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }


    /**
     * 게시글 등록
     */
    public VueSpringBoardEntity create(VueSpringBoardDto vueSpringBoardDto) {
        VueSpringBoardEntity entity = VueSpringBoardEntity.builder()
                .title(vueSpringBoardDto.getTitle())
                .content(vueSpringBoardDto.getContent())
                .author(vueSpringBoardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return vueSpringBoardRepository.save(entity);
    }

    /**
     * 게시글 수정
     */
    public VueSpringBoardEntity update(VueSpringBoardDto vueSpringBoardDto) {
        VueSpringBoardEntity vueSpringBoardEntity =
                vueSpringBoardRepository.findById(vueSpringBoardDto.getId()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        vueSpringBoardEntity.setTitle(vueSpringBoardDto.getTitle());
        vueSpringBoardEntity.setContent(vueSpringBoardEntity.getContent());
        vueSpringBoardEntity.setUpdatedAt(LocalDateTime.now());
        return vueSpringBoardEntity;
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        VueSpringBoardEntity entity = vueSpringBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        vueSpringBoardRepository.delete(entity);
    }
}
