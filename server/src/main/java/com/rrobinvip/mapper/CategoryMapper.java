package com.rrobinvip.mapper;

import com.github.pagehelper.Page;
import com.rrobinvip.enumeration.OperationType;
import com.rrobinvip.dto.CategoryPageQueryDTO;
import com.rrobinvip.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * Insert category
     *
     * @param category Category entity/
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    int addCategory(Category category);

    /**
     * Page query
     *
     * @param categoryPageQueryDTO Category Page data transfer object
     * @return Page object
     */
    Page<Category> pageBasedCategorySearch(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Remove category based on ID
     *
     * @param id ID of category
     */
    @Delete("delete from category where id = #{id}")
    int deleteById(Long id);

    /**
     * Update category based on ID
     *
     * @param category Category entity
     */
    int update(Category category);

    /**
     * Search category based on type
     *
     * @param type 1-product, 2-combo
     * @return List of Category entity
     */
    List<Category> listCategoryByType(Integer type);
}
