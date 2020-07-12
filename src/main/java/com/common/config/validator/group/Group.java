package com.common.config.validator.group;

import javax.validation.GroupSequence;

/**
 * @description： 参数校验分组- 添加时校验 和 更新时校验
 */
@GroupSequence(value = {AddGroup.class, UpdateGroup.class})
public interface Group {
}
