package com.oliwen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 * @author: olw
 * @date: 2020/4/29 0029 17:43
 * @description:  测试任务调度bean
 */
public class QuartzEntity {
   String jobName;
   String jobGroup;
   String description;
   String jobClassName;
   String cronExpression;
   String jobMethodName;
}
