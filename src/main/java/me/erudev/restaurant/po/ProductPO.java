package me.erudev.restaurant.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.erudev.restaurant.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:21
 */
@Getter
@Setter
@ToString
public class ProductPO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer restaurantId;
    private ProductStatus status;
    private Date date;
}
