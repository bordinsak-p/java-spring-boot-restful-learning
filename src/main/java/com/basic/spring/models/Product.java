package com.basic.spring.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true) // ทำให้เราไม่ต้อง setter obj ทีละตัว จะทำให้เราสามารถ .(dot) obj ต่อๆ กันได้
public class Product {
    @Id //pk
    @Column(name = "product_id") // declare a column name in table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment // IDENTITY database mange running number
    private long id;

    @Column(name = "product_name", length = 150, nullable = false, unique = false)
    private String name;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_stock")
    private int stock;

    @Setter(AccessLevel.NONE) // ไม่ต้องการให้ obj ตัวนี้มี setter
    @CreationTimestamp // มีการสร้าง data row ใหม่
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Setter(AccessLevel.NONE) // ไม่ต้องการให้ obj ตัวนี้มี setter
    @UpdateTimestamp // มีการ update data
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;
}
