package com.oliwen.mapper;

import com.oliwen.pojo.TradeThread;
import com.oliwen.pojo.TradeThreadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradeThreadMapper {
    long countByExample(TradeThreadExample example);

    int deleteByExample(TradeThreadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeThread record);

    int insertSelective(TradeThread record);

    List<TradeThread> selectByExampleWithRowbounds(TradeThreadExample example, RowBounds rowBounds);

    List<TradeThread> selectByExample(TradeThreadExample example);

    TradeThread selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeThread record, @Param("example") TradeThreadExample example);

    int updateByExample(@Param("record") TradeThread record, @Param("example") TradeThreadExample example);

    int updateByPrimaryKeySelective(TradeThread record);

    int updateByPrimaryKey(TradeThread record);
}