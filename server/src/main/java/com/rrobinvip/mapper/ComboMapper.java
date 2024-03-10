package com.rrobinvip.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    /**
     * Query quantity of combos under a category
     *
     * @param id category ID
     * @return Quantity of combos under a category
     */
    @Select("select count(id) from combo where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

}
