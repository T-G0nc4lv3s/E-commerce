package com.ecommerce.store.projections;

public interface AddressMinProjection {

	Long getId();
	String getStreet();
	String getComplement();
	String getNumber();
	String getDistrict();
	String getZip_Code();
	Long getCity_Id();
	Long getClient_Id();
}
