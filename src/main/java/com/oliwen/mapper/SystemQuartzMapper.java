package com.oliwen.mapper;

import com.oliwen.pojo.SystemQuartz;
import com.oliwen.pojo.SystemQuartzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemQuartzMapper {
    long countByExample(SystemQuartzExample example);

    int deleteByExample(SystemQuartzExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemQuartz record);

    int insertSelective(SystemQuartz record);

    List<SystemQuartz> selectByExampleWithRowbounds(SystemQuartzExample example, RowBounds rowBounds);

    List<SystemQuartz> selectByExample(SystemQuartzExample example);

    SystemQuartz selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemQuartz record, @Param("example") SystemQuartzExample example);

    int updateByExample(@Param("record") SystemQuartz record, @Param("example") SystemQuartzExample example);

    int updateByPrimaryKeySelective(SystemQuartz record);

    int updateByPrimaryKey(SystemQuartz record);
}