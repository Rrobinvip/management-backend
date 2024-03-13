package com.rrobinvip.service;

import com.rrobinvip.dto.ItemDTO;

public interface ItemService {

    /**
     * Add new items and its options. @Transactional annotation keeps the atomicity of the service, due to the operations
     * on two tables (item and itemOptions)
     *
     * @param itemDTO item data transfer object
     * @return rows affected int[], because it requires to insert into 2 tables. index 0 is result of 1st table, index 1 is result of 2nd table.
     */
    int[] addItemWithOptions(ItemDTO itemDTO);
}
