package com.rrobinvip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.constant.StatusConstant;
import com.rrobinvip.context.BaseContext;
import com.rrobinvip.dto.CategoryDTO;
import com.rrobinvip.dto.CategoryPageQueryDTO;
import com.rrobinvip.entity.Category;
import com.rrobinvip.exception.DeletionNotAllowedException;
import com.rrobinvip.mapper.CategoryMapper;
import com.rrobinvip.mapper.ComboMapper;
import com.rrobinvip.mapper.ItemMapper;
import com.rrobinvip.result.PageResult;
import com.rrobinvip.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类业务层
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ComboMapper comboMapper;

    /**
     * Add category
     *
     * @param categoryDTO category data transfer object
     * @return number of rows affected
     */
    public int addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        category.setStatus(StatusConstant.DISABLE);

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        return categoryMapper.addCategory(category);
    }

    /**
     * Page based category search
     *
     * @param categoryPageQueryDTO Page query data transfer object
     * @return PageResult
     */
    public PageResult pageBasedCategorySearch(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageBasedCategorySearch(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * Remove category based on ID
     *
     * @param id category id
     * @return number of rows affected
     */
    public int deleteById(Long id) {
        // Check if current category has items
        Integer count = itemMapper.countByCategoryId(id);
        if (count > 0) {
            // cannot remove category with items
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_ITEM);
        }

        // Check if current category has combos
        count = comboMapper.countByCategoryId(id);
        if (count > 0) {
            // cannot remove category with combos
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_COMBO);
        }

        // remove category
        return categoryMapper.deleteById(id);
    }

    /**
     * Update category
     *
     * @param categoryDTO category transfer object
     * @return number of rows affected
     */
    public int update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        // update updateTime and updateUser
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        return categoryMapper.update(category);
    }

    /**
     * Enable-disable category status
     *
     * @param status 1-enable, 0-disable
     * @param id     Category ID
     * @return number of rows affected
     */
    public int startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        return categoryMapper.update(category);
    }

    /**
     * Search category based on type
     *
     * @param type Category type, 1-items, 2-combos
     * @return List of Category entity
     */
    public List<Category> list(Integer type) {
        return categoryMapper.listCategoryByType(type);
    }
}
