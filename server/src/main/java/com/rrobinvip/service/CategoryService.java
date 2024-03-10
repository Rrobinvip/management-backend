package com.rrobinvip.service;

import com.rrobinvip.dto.CategoryDTO;
import com.rrobinvip.dto.CategoryPageQueryDTO;
import com.rrobinvip.entity.Category;
import com.rrobinvip.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * Add category
     *
     * @param categoryDTO category data transfer object
     */
    int addCategory(CategoryDTO categoryDTO);

    /**
     * Page based category search
     *
     * @param categoryPageQueryDTO Page query data transfer object
     * @return PageResult
     */
    PageResult pageBasedCategorySearch(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Remove category based on ID
     *
     * @param id category id
     */
    int deleteById(Long id);

    /**
     * Update category
     *
     * @param categoryDTO category transfer object
     */
    int update(CategoryDTO categoryDTO);

    /**
     * Enable-disable category status
     *
     * @param status 1-enable, 0-disable
     * @param id     Category ID
     */
    int startOrStop(Integer status, Long id);

    /**
     * Search category based on type
     *
     * @param type Category type, 1-items, 2-combos
     * @return List of Category entity
     */
    List<Category> list(Integer type);
}
