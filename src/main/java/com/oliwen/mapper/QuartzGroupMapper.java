package com.oliwen.mapper;

import com.oliwen.pojo.QuartzGroup;
import com.oliwen.pojo.QuartzGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuartzGroupMapper {
    long countByExample(QuartzGroupExample example);

    int deleteByExample(QuartzGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuartzGroup record);

    int insertSelective(QuartzGroup record);

    List<QuartzGroup> selectByExampleWithRowbounds(QuartzGroupExample example, RowBounds rowBounds);

    List<QuartzGroup> selectByExample(QuartzGroupExample example);

    QuartzGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuartzGroup record, @Param("example") QuartzGroupExample example);

    int updateByExample(@Param("record") QuartzGroup record, @Param("example") QuartzGroupExample example);

    int updateByPrimaryKeySelective(QuartzGroup record);

    int updateByPrimaryKey(QuartzGroup record);
}