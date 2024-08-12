package com.example.vuespringboard.service;

import com.example.vuespringboard.dto.VueSpringBoardDto;
import com.example.vuespringboard.entity.VueSpringBoardEntity;
import com.example.vuespringboard.repository.VueSpringBoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    private final ModelMapper modelMapper;
    /**
     * 게시글 목록 조회
     */
    @Transactional(readOnly = true)
    public List<VueSpringBoardDto> getBoardList() {
        List<VueSpringBoardEntity> boardEntities = vueSpringBoardRepository.findAll();

        return boardEntities.stream()
                .map(entity -> {
                    VueSpringBoardDto dto = modelMapper.map(entity, VueSpringBoardDto.class);
                    // 포맷팅 후 DTO에 값 설정
                    dto.setCreatedAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                    dto.setUpdatedAt(entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 게시글 상세정보 조회
     */
    @Transactional(readOnly = true)
    public VueSpringBoardDto getBoard(Long id) {
        VueSpringBoardEntity entity = vueSpringBoardRepository.findById(id).orElse(null);

        if(entity == null) {
            return new VueSpringBoardDto();
        }
        VueSpringBoardDto vueSpringBoardDto = modelMapper.map(entity, VueSpringBoardDto.class);

        vueSpringBoardDto.setCreatedAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        vueSpringBoardDto.setUpdatedAt(entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

        return vueSpringBoardDto;
    }


    /**
     * 게시글 등록
     */
    @Transactional
    public Long create(VueSpringBoardDto vueSpringBoardDto) {

        if (vueSpringBoardDto == null) {
            return 0L;
        }

        VueSpringBoardEntity entity = modelMapper.map(vueSpringBoardDto, VueSpringBoardEntity.class);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        vueSpringBoardRepository.save(entity);

        return entity.getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(VueSpringBoardDto vueSpringBoardDto) {

        if (vueSpringBoardDto == null || vueSpringBoardDto.getId() == null) {
            return 0L;
        }


        VueSpringBoardEntity entity = modelMapper.map(vueSpringBoardDto, VueSpringBoardEntity.class);

        entity.setUpdatedAt(LocalDateTime.now());

        vueSpringBoardRepository.save(entity);

        return entity.getId();
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public boolean delete(Long id) {

        if (vueSpringBoardRepository.existsById(id)) {
            vueSpringBoardRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
