package com.ecommerce.store.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientUpdateDTO {

	@NotBlank(message = "Required field")
	@Length(min = 5, max = 80, message = "Length should be between 5 to 80")
	private String name;
	
	@NotBlank(message = "Required field")
	@Email
	private String email;
	
	private Set<@Pattern(regexp = "^(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")String> phones = new HashSet<>();
	
	public ClientUpdateDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public ClientUpdateDTO(Client entity) {
		BeanUtils.copyProperties(entity, this);
		phones = entity.getPhones();
	}
}
