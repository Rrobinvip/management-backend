package com.rrobinvip.mapper;

import com.rrobinvip.entity.ItemOptions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemOptionsMapper {

    int addItemOptionsBatch(List<ItemOptions> options);
}
