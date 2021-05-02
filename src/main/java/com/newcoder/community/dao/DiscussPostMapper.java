package com.newcoder.community.dao;

import com.newcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //以下两方法基于动态sql实现，主页时userId为0
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    //该注解取别名
    //如果只有一个参数，并且在<if>里使用，则必须用别名
    int selectDiscussPostRows(@Param("userId") int userId);




}
