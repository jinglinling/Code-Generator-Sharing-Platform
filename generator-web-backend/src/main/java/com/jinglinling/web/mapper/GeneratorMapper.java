package com.jinglinling.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinglinling.web.model.entity.Generator;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢骏晖
 * @description 针对表【generator(代码生成器)】的数据库操作Mapper
 * @createDate 2025-01-09 14:56:45
 * @Entity com.jinglinling.web.model.entity.Generator
 */
public interface GeneratorMapper extends BaseMapper<Generator> {
    @Select("SELECT id, distPath FROM generator WHERE isDelete = 1")
    List<Generator> listDeletedGenerator();
}




