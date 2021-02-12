package me.erudev.restaurant.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.erudev.restaurant.enums.OrderStatus;

import java.math.BigDecimal;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:25
 */
@Getter
@Setter
@ToString
public class OrderMessageDTO {
    private Integer orderId;
    private OrderStatus orderStatus;
    private BigDecimal price;
    private Integer deliverymanId;
    private Integer productId;
    private Integer accountId;
    private Integer settlementId;
    private Integer rewardId;
    private BigDecimal rewardAmount;
    private Boolean confirmed;
}
