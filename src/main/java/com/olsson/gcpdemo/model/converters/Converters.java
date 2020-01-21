package com.olsson.gcpdemo.model.converters;

import com.olsson.gcpdemo.dto.OrderDto;
import com.olsson.gcpdemo.dto.ProductDto;
import com.olsson.gcpdemo.model.Order;
import com.olsson.gcpdemo.model.Product;
import com.olsson.gcpdemo.model.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    private Converters() {
        throw new UnsupportedOperationException("Static class");
    }

    public static Order convertOrder(OrderDto orderDto) {
        var order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setCart(convertProducts(orderDto.getCart()));
        order.setStatus(Status.CONFIRMED);
        return new Order();
    }

    private static List<Product> convertProducts(List<ProductDto> productList) {
        return productList.stream()
                .map(Converters::convertProduct)
                .collect(Collectors.toList());
    }

    private static Product convertProduct(ProductDto productDto) {
        var product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setUnit(productDto.getUnit());
        product.setAmount(productDto.getAmount());
        product.setCost(new BigDecimal(productDto.getCost()));
        product.setVat(new BigDecimal(productDto.getVat()));
        return new Product();
    }

}
