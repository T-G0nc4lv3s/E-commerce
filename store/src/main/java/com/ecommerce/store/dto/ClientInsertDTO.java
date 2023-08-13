package com.ecommerce.store.dto;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.store.domain.Client;
import com.ecommerce.store.domain.enums.ClientType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientInsertDTO {

	private Long id;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 80, message = "Length should be between 5 to 80")
	private String name;
	
	@NotBlank(message = "Required field, should be a valid email")
	@Email
	private String email;
	
	@NotBlank(message = "Required field")
	private String cpfOrCnpj;
	private ClientType type;
	
	
	
	//https://medium.com/@igorrozani/criando-uma-express%C3%A3o-regular-para-telefone-fef7a8f98828
	@Pattern(regexp = "^(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	@NotBlank(message = "Required field")
	private String phone1;
	
	@Pattern(regexp = "^(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	private String phone2;
	
	@Pattern(regexp = "^(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
	private String phone3;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 200, message = "Length should be between 5 to 200")
	private String street;
	
	@NotBlank(message = "Required field")
	private String number;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 200, message = "Length should be between 5 to 200")
	private String district;
	private String complement;
	
	@NotBlank(message = "Required field")
	private String zipCode;
	
	@NotBlank(message = "Required field")
	private String cityId;
	
	public ClientInsertDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpfOrCnpj = entity.getCpfOrCnpj();
		this.type = entity.getType();
	}
}
