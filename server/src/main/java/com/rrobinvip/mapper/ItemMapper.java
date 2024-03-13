package com.rrobinvip.mapper;

import com.rrobinvip.annotation.AutoFill;
import com.rrobinvip.entity.Item;
import com.rrobinvip.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper {

    /**
     * Query item quantity based on category ID.
     *
     * @param categoryId category ID
     * @return Quantity of items under the given category ID.
     */
    @Select("select count(id) from item where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.INSERT)
    int addItem(Item item);

}
