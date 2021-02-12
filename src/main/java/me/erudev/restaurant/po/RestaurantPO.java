package me.erudev.restaurant.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.erudev.restaurant.enums.RestaurantStatus;

import java.util.Date;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:21
 */
@Getter
@Setter
@ToString
public class RestaurantPO {
    private Integer id;
    private String name;
    private String address;
    private RestaurantStatus status;
    private Date date;
}
