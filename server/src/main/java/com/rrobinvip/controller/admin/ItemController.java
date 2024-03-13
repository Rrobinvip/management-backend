package com.rrobinvip.controller.admin;

import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.dto.ItemDTO;
import com.rrobinvip.result.Result;
import com.rrobinvip.service.ItemService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manage items
 */
@RestController
@RequestMapping("/admin/item")
@Api(tags = "Items")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;
    /**
     * Add new item
     *
     * @param itemDTO item Data transfer object
     * @return A Result object indicating the success of the operation with message
     */
    @PostMapping
    public Result addItem(@RequestBody ItemDTO itemDTO) {
        log.info("Adding items.. {}", itemDTO);
        int[] result = itemService.addItemWithOptions(itemDTO);

        return result[0] > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }
}
