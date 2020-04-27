package com.oliwen.mapper;

import com.oliwen.pojo.SystemRole;
import com.oliwen.pojo.SystemRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemRoleMapper {
    long countByExample(SystemRoleExample example);

    int deleteByExample(SystemRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    List<SystemRole> selectByExampleWithRowbounds(SystemRoleExample example, RowBounds rowBounds);

    List<SystemRole> selectByExample(SystemRoleExample example);

    SystemRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    int updateByExample(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}