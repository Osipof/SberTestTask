package com.example.SberTestTask.dto;

import lombok.Data;

/**
 * DTO для MobilePhone
 *
 * @author Иван Осипов
 */
@Data
public class MobilePhoneDTO {
    private String brand;
    private String model;
    private Integer price;
    private String color;
    private Integer amount;
}
