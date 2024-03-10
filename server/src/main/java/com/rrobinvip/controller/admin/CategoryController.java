package com.rrobinvip.controller.admin;

import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.dto.CategoryDTO;
import com.rrobinvip.dto.CategoryPageQueryDTO;
import com.rrobinvip.entity.Category;
import com.rrobinvip.result.PageResult;
import com.rrobinvip.result.Result;
import com.rrobinvip.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "Category API")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Add category
     *
     * @param categoryDTO Category date transfer object
     * @return Result with success or failure.
     */
    @PostMapping
    @ApiOperation("Add category")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("Adding new category：{}", categoryDTO);
        return categoryService.addCategory(categoryDTO) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);

    }

    /**
     * Page Based category search
     *
     * @param categoryPageQueryDTO Data transfer object
     * @return Result with success or failure.
     */
    @GetMapping("/page")
    @ApiOperation("Page query on category")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("Page query：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageBasedCategorySearch(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Remove category
     *
     * @param id category id
     * @return Result with success or failure.
     */
    @DeleteMapping
    @ApiOperation("Remove category")
    public Result<String> deleteById(Long id) {
        log.info("Remove category：{}", id);
        return categoryService.deleteById(id) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * Update category
     *
     * @param categoryDTO category data transfer object
     * @return Result with success or failure.
     */
    @PutMapping
    @ApiOperation("Update category record")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(categoryDTO) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * Enable-disable category by id.
     *
     * @param status 1-enable, 0-disable
     * @param id     category id
     * @return Result with success or failure.
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable or disable category")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id) {

        return categoryService.startOrStop(status, id) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * Search category based on type
     *
     * @param type 1-item, 2-combo
     * @return Result contains list of category entity.
     */
    @GetMapping("/list")
    @ApiOperation("Search category based on type")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
