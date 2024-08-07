package com.example.vuespringboard.repository;

import com.example.vuespringboard.entity.VueSpringBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class       :VueSpringBoardRepository
 * Package      :com.example.vuespringboard.repository
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-07 오후 2:41
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-07 :            : 신규 개발.
 */
public interface VueSpringBoardRepository extends JpaRepository<VueSpringBoardEntity, Long> {
}
