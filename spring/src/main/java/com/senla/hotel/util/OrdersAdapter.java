package com.senla.hotel.util;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.senla.hotel.model.Orders;

public class OrdersAdapter implements JsonSerializer<Orders> {


	public JsonElement serialize(Orders order, Type type, JsonSerializationContext jsc) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(Constant.ID, order.getId());
		jsonObject.addProperty(Constant.IDG, order.getGuest().getId());
		jsonObject.addProperty(Constant.IDR, order.getRoom().getId());
		jsonObject.addProperty(Constant.DATEST, order.getDataStart().toString());
		jsonObject.addProperty(Constant.DATEEND, order.getDataEnd().toString());
		jsonObject.addProperty(Constant.PRICE, order.getPrice());
		jsonObject.addProperty(Constant.OSTATUS, order.getStatusOrder().name());
		return jsonObject;
	}

	
}
