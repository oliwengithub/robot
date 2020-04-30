package com.oliwen.mapper;

import com.oliwen.pojo.SystemQuartzGroup;
import com.oliwen.pojo.SystemQuartzGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemQuartzGroupMapper {
    long countByExample(SystemQuartzGroupExample example);

    int deleteByExample(SystemQuartzGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemQuartzGroup record);

    int insertSelective(SystemQuartzGroup record);

    List<SystemQuartzGroup> selectByExampleWithRowbounds(SystemQuartzGroupExample example, RowBounds rowBounds);

    List<SystemQuartzGroup> selectByExample(SystemQuartzGroupExample example);

    SystemQuartzGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemQuartzGroup record, @Param("example") SystemQuartzGroupExample example);

    int updateByExample(@Param("record") SystemQuartzGroup record, @Param("example") SystemQuartzGroupExample example);

    int updateByPrimaryKeySelective(SystemQuartzGroup record);

    int updateByPrimaryKey(SystemQuartzGroup record);
}