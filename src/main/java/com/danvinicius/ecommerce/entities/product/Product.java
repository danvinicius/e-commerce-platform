package com.danvinicius.ecommerce.entities.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.danvinicius.ecommerce.dto.product.ProductRequestDTO;
import com.danvinicius.ecommerce.entities.category.Category;
import com.danvinicius.ecommerce.entities.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private BigDecimal price;

    private Integer quantity;

    @OneToMany(mappedBy = "id.product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderItem> items = new HashSet<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.imageUrl = data.imageUrl();
        this.price = data.price();
        this.quantity = data.quantity();
    }
}
