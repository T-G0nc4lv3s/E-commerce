package com.ecommerce.store.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldMessage {

	private String fieldName;
	private String message;
}
