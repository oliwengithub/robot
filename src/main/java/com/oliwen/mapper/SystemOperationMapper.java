package com.oliwen.mapper;

import com.oliwen.pojo.SystemOperation;
import com.oliwen.pojo.SystemOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemOperationMapper {
    long countByExample(SystemOperationExample example);

    int deleteByExample(SystemOperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemOperation record);

    int insertSelective(SystemOperation record);

    List<SystemOperation> selectByExampleWithBLOBsWithRowbounds(SystemOperationExample example, RowBounds rowBounds);

    List<SystemOperation> selectByExampleWithBLOBs(SystemOperationExample example);

    List<SystemOperation> selectByExampleWithRowbounds(SystemOperationExample example, RowBounds rowBounds);

    List<SystemOperation> selectByExample(SystemOperationExample example);

    SystemOperation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemOperation record, @Param("example") SystemOperationExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemOperation record, @Param("example") SystemOperationExample example);

    int updateByExample(@Param("record") SystemOperation record, @Param("example") SystemOperationExample example);

    int updateByPrimaryKeySelective(SystemOperation record);

    int updateByPrimaryKeyWithBLOBs(SystemOperation record);

    int updateByPrimaryKey(SystemOperation record);
}