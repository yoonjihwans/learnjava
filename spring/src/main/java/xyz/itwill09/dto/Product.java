package xyz.itwill09.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Product {
	@NotEmpty
	private String productCode;
	@NotEmpty
	private String productName;
	@Min(value = 1)//필드에 저장될 최소값을 검증하기 위한 어너테이션
	@Max(value = 100)//필드에 저장될 최대값을 검증하기 위한 어너테이션
	private int productQuantity;
}