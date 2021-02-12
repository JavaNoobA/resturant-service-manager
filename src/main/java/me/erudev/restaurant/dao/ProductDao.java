package me.erudev.restaurant.dao;

import me.erudev.restaurant.po.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:26
 */
@Mapper
@Repository
public interface ProductDao {
    @Select("SELECT id,name,price,restaurant_id restaurantId,status,date FROM product WHERE id = #{id}")
    ProductPO selectProduct(Integer id);
}
