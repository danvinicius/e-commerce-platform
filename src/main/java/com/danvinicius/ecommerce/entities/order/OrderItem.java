package com.danvinicius.ecommerce.entities.order;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_order_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {

    private final static long serialVersionUID = 1L;
    
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;

    private BigDecimal price;

}
