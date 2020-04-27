package com.oliwen.mapper;

import com.oliwen.pojo.TradePlatform;
import com.oliwen.pojo.TradePlatformExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradePlatformMapper {
    long countByExample(TradePlatformExample example);

    int deleteByExample(TradePlatformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradePlatform record);

    int insertSelective(TradePlatform record);

    List<TradePlatform> selectByExampleWithRowbounds(TradePlatformExample example, RowBounds rowBounds);

    List<TradePlatform> selectByExample(TradePlatformExample example);

    TradePlatform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradePlatform record, @Param("example") TradePlatformExample example);

    int updateByExample(@Param("record") TradePlatform record, @Param("example") TradePlatformExample example);

    int updateByPrimaryKeySelective(TradePlatform record);

    int updateByPrimaryKey(TradePlatform record);
}