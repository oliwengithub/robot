package com.oliwen.mapper;

import com.oliwen.pojo.TradeConfig;
import com.oliwen.pojo.TradeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradeConfigMapper {
    long countByExample(TradeConfigExample example);

    int deleteByExample(TradeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeConfig record);

    int insertSelective(TradeConfig record);

    List<TradeConfig> selectByExampleWithRowbounds(TradeConfigExample example, RowBounds rowBounds);

    List<TradeConfig> selectByExample(TradeConfigExample example);

    TradeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeConfig record, @Param("example") TradeConfigExample example);

    int updateByExample(@Param("record") TradeConfig record, @Param("example") TradeConfigExample example);

    int updateByPrimaryKeySelective(TradeConfig record);

    int updateByPrimaryKey(TradeConfig record);
}