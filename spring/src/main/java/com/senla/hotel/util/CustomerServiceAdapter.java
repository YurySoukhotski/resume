package com.senla.hotel.util;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.senla.hotel.model.CustomerService;

public class CustomerServiceAdapter implements JsonSerializer<CustomerService> {

	@Override
	public JsonElement serialize(CustomerService cs, Type type, JsonSerializationContext jsc) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(Constant.ID, cs.getId());
		jsonObject.addProperty(Constant.COUNT, cs.getCount());
		jsonObject.addProperty(Constant.SERVICEID, cs.getService().getId());
		jsonObject.addProperty(Constant.ORDERID, cs.getOrder().getId());
		return jsonObject;
	}
	


}