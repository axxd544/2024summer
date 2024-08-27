package com.example.socialservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.socialservice.model.domain.Complaints;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 8:49
 */
@Mapper
public interface ComplaintsMapper extends BaseMapper<Complaints> {
}
