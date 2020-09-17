package com.cgcl.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Data
@JsonPropertyOrder(alphabetic=true)
public class AddressInformation {

	@ApiModelProperty(value = "ADDRESS_0",position = 0)
	@JsonProperty("ADDRESS_0")
	private String address0;
	
	@ApiModelProperty(value = "ADDRESS_ID_0",position = 1)
	@JsonProperty("ADDRESS_ID_0")
	private int addressid0;
	
	@ApiModelProperty(value = "ADDRESS_TYPE_0",position = 2)
	@JsonProperty("ADDRESS_TYPE_0")
	private String addresstype0;
	
	@ApiModelProperty(value = "CITY_0",position = 3)
	@JsonProperty("CITY_0")
	private String city0;
	
	@ApiModelProperty(value = "PINCODE_0",position = 4)
	@JsonProperty("PINCODE_0")
	private String pincode0;
	
	@ApiModelProperty(value = "STATE_0",position = 5)
	@JsonProperty("STATE_0")
	private String state0;
	
	@ApiModelProperty(value = "ADDRESS_1",position = 6)
	@JsonProperty("ADDRESS_1")
	private String address1;
	
	@ApiModelProperty(value = "ADDRESS_ID_1",position = 7)
	@JsonProperty("ADDRESS_ID_1")
	private int addressid1;
	
	@ApiModelProperty(value = "ADDRESS_TYPE_1",position = 8)
	@JsonProperty("ADDRESS_TYPE_1")
	private String addresstype1;
	
	@ApiModelProperty(value = "CITY_1",position = 10)
	@JsonProperty("CITY_1")
	private String city1;
	
	@ApiModelProperty(value = "PINCODE_1",position = 11)
	@JsonProperty("PINCODE_1")
	private String pincode1;
	
	@ApiModelProperty(value = "STATE_1",position = 12)
	@JsonProperty("STATE_1")
	private String state1;
	
	@ApiModelProperty(value = "ADDRESS_2",position = 13)
	@JsonProperty("ADDRESS_2")
	private String address2;
	
	@ApiModelProperty(value = "ADDRESS_ID_2",position =14)
	@JsonProperty("ADDRESS_ID_2")
	private int addressid2;
	
	@ApiModelProperty(value = "ADDRESS_TYPE_2",position = 15)
	@JsonProperty("ADDRESS_TYPE_2")
	private String addresstype2;
	
	@ApiModelProperty(value = "CITY_2",position =16)
	@JsonProperty("CITY_2")
	private String city2;
	
	@ApiModelProperty(value = "PINCODE_2",position = 17)
	@JsonProperty("PINCODE_2")
	private String pincode2;
	
	@ApiModelProperty(value = "STATE_2",position = 18)
	@JsonProperty("STATE_2")
	private String state2;
	
	@ApiModelProperty(value = "ADDRESS_3",position =19)
	@JsonProperty("ADDRESS_3")
	private String address3;
	
	@ApiModelProperty(value = "ADDRESS_ID_3",position =20)
	@JsonProperty("ADDRESS_ID_3")
	private int addressid3;
	
	@ApiModelProperty(value = "ADDRESS_TYPE_3",position = 21)
	@JsonProperty("ADDRESS_TYPE_3")
	private String addresstype3;
	
	@ApiModelProperty(value = "CITY_3",position = 22)
	@JsonProperty("CITY_3")
	private String city3;
	
	@ApiModelProperty(value = "PINCODE_3",position = 23)
	@JsonProperty("PINCODE_3")
	private String pincode3;
	
	@ApiModelProperty(value = "STATE_3",position = 24)
	@JsonProperty("STATE_3")
	private String state3;
	
}
