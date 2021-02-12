package me.erudev.restaurant.dao;

import me.erudev.restaurant.po.RestaurantPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:26
 */
@Mapper
@Repository
public interface RestaurantDao {
    @Select("SELECT id,name,address,status,settlement_id settlementId,date FROM restaurant WHERE id = #{id}")
    RestaurantPO selectRestaurant(Integer id);
}
