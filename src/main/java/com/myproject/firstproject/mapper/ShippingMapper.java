package com.myproject.firstproject.mapper;

import com.myproject.firstproject.entity.Shipping;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByShippingIdUserId(@Param("shippingId") Integer shippingId, @Param("UserId") Integer UserId);

    int updateByShipping(Shipping shipping);

    Shipping selectByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId") Integer shippingId);

    List<Shipping> selectByUserId(@Param("userId")Integer userId);
}