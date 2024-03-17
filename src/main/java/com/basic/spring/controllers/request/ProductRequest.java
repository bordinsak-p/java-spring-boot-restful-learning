package com.basic.spring.controllers.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class ProductRequest {
    // @NotEmpty(message = "is Empty")  // field name ห้ามเป็นค่า null, empty
    // @Size(min = 2, max = 100) // กรอกอักษรชันต่ำ 2 ตัว และไม่เกิน 100
    private String name;
    private MultipartFile imageFile;
    private double price;
    private int stock;
}
