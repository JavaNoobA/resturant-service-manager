package me.erudev.restaurant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import me.erudev.restaurant.dao.ProductDao;
import me.erudev.restaurant.dao.RestaurantDao;
import me.erudev.restaurant.dto.OrderMessageDTO;
import me.erudev.restaurant.enums.ProductStatus;
import me.erudev.restaurant.enums.RestaurantStatus;
import me.erudev.restaurant.po.ProductPO;
import me.erudev.restaurant.po.RestaurantPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pengfei.zhao
 * @date 2021/2/12 8:42
 */
@Service
public class OrderMessageService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private RestaurantDao restaurantDao;

    ObjectMapper objectMapper = new ObjectMapper();

    @Async
    public void handleMessage() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(
                    "exchange.order.restaurant",
                    BuiltinExchangeType.DIRECT,
                    true,
                    false,
                    null);

            channel.queueDeclare(
                    "queue.restaurant",
                    true,
                    false,
                    false,
                    null);

            channel.queueBind(
                    "queue.restaurant",
                    "exchange.order.restaurant",
                    "key.restaurant");

            channel.basicConsume("queue.restaurant", true, deliverCallback, consumerTag -> {});

            while (true) {
                Thread.sleep(100000);
            }

        } catch (TimeoutException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    DeliverCallback deliverCallback = new DeliverCallback() {
        @Override
        public void handle(String consumerTag, Delivery message) throws IOException {
            String msg = new String(message.getBody());
            OrderMessageDTO messageDTO = objectMapper.readValue(msg, OrderMessageDTO.class);
            ProductPO product = productDao.selectProduct(messageDTO.getProductId());
            RestaurantPO restaurant = restaurantDao.selectRestaurant(product.getRestaurantId());

            if (restaurant.getStatus() == RestaurantStatus.OPEN && product.getStatus() == ProductStatus.AVALIABIE) {
                messageDTO.setConfirmed(true);
                messageDTO.setPrice(product.getPrice());
            } else {
                messageDTO.setConfirmed(false);
            }

            ConnectionFactory connectionFactory = new ConnectionFactory();
            try (Connection connection = connectionFactory.newConnection();
                 Channel channel = connection.createChannel()) {
                String msgToSend = objectMapper.writeValueAsString(messageDTO);
                channel.basicPublish(
                        "exchange.order.restaurant",
                        "key.order",
                        null,
                        msgToSend.getBytes());

            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    };
}
