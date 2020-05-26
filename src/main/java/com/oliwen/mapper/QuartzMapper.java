package com.oliwen.mapper;

import com.oliwen.pojo.Quartz;
import com.oliwen.pojo.QuartzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuartzMapper {
    long countByExample(QuartzExample example);

    int deleteByExample(QuartzExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Quartz record);

    int insertSelective(Quartz record);

    List<Quartz> selectByExampleWithRowbounds(QuartzExample example, RowBounds rowBounds);

    List<Quartz> selectByExample(QuartzExample example);

    Quartz selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Quartz record, @Param("example") QuartzExample example);

    int updateByExample(@Param("record") Quartz record, @Param("example") QuartzExample example);

    int updateByPrimaryKeySelective(Quartz record);

    int updateByPrimaryKey(Quartz record);
}