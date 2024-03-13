package com.rrobinvip.service.impl;

import com.rrobinvip.dto.ItemDTO;
import com.rrobinvip.entity.Item;
import com.rrobinvip.entity.ItemOptions;
import com.rrobinvip.mapper.ItemMapper;
import com.rrobinvip.mapper.ItemOptionsMapper;
import com.rrobinvip.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ItemOptionsMapper itemOptionsMapper;
    /**
     * Add new items and its options. @Transactional annotation keeps the atomicity of the service, due to the operations
     * on two tables (item and itemOptions)
     *
     * @param itemDTO item data transfer object
     * @return rows affected int[], because it requires to insert into 2 tables. index 0 is result of 1st table, index 1 is result of 2nd table.
     */
    @Transactional
    public int[] addItemWithOptions(ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        log.info("Adding item: {}", item);

        // Add to item table
        int addItemResult = itemMapper.addItem(item);

        // Acquire itemID, this is used together with `useGeneratedKeys="true" keyProperty="id"` in mapper.
        Long itemID = item.getId();

        // Add to itemOption table
        List<ItemOptions> options = itemDTO.getFlavors();
        log.info("Options size: {}", options.size());
        int addItemOptionResult = 0;
        if (!options.isEmpty()) {
            options.forEach(option -> {
                option.setItemId(itemID);
            });

            addItemOptionResult = itemOptionsMapper.addItemOptionsBatch(options);
        }

        return new int[]{addItemResult, addItemOptionResult};
    }
}
