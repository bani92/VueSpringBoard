package com.example.vuespringboard.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class       :ModelMapperConfig
 * Package      :com.example.vuespringboard.config
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-08 오후 4:38
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-08 :            : 신규 개발.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
