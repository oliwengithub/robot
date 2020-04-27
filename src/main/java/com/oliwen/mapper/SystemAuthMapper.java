package com.oliwen.mapper;

import com.oliwen.pojo.SystemAuth;
import com.oliwen.pojo.SystemAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemAuthMapper {
    int countByExample(SystemAuthExample example);

    int deleteByExample(SystemAuthExample example);

    int deleteByPrimaryKey(Integer authId);

    int insert(SystemAuth record);

    int insertSelective(SystemAuth record);

    List<SystemAuth> selectByExampleWithRowbounds(SystemAuthExample example, RowBounds rowBounds);

    List<SystemAuth> selectByExample(SystemAuthExample example);

    SystemAuth selectByPrimaryKey(Integer authId);

    int updateByExampleSelective(@Param("record") SystemAuth record, @Param("example") SystemAuthExample example);

    int updateByExample(@Param("record") SystemAuth record, @Param("example") SystemAuthExample example);

    int updateByPrimaryKeySelective(SystemAuth record);

    int updateByPrimaryKey(SystemAuth record);
}