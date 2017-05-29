package com.senla.hotel.util;

public class Constant {

	public static final String DATEFORMAT = "yyyy-MM-dd";
	public static final String SPLIT = ",";
	public static final String SEP = "-";
	public static final String NEXT = "/";
	public static final String DEL = ";";
	
	public static final String ID = "id";
	public static final String DATE = "date";
	public static final String NUMBER = "number";
	public static final String CLASSROOM="classRoom";
	public static final String CAPACITY="capacity";
	public static final String OPTIONS="options";
    
	public static final String COUNT = "count";
	public static final String SERVICEID = "serviceId";
	public static final String IDG="idGuest";
	public static final String IDR="idRoom";
	public static final String DATEST="dateSt";
	public static final String DATEEND="dateEnd";
	public static final String PRICE="price";
	public static final String ORDERID = "orderId";
	public static final String OSTATUS="orderStatus";
	
	
	

	
	
	//controller section
	
	public static final String URL_ANALITICS="/analitics";
	public static final String URL_AUTH="/auth/";
	public static final String URL_AUTHORIZATION="/authorization/";
	public static final String URL_CONFIG="/config/";
	public static final String URL_COUNTRIES="/countries";
	public static final String URL_DOCUMENNTS="/documents";
	public static final String URL_GUEST="/guest/";
	public static final String URL_GUESTID="/guest/{id}";
	public static final String URL_NEWS="/news/";
	public static final String URL_ORDERS="/orders/";
	public static final String URL_PAYMENTID="/payment/{id}";
	public static final String URL_PAYMENT="/payment/";
	public static final String URL_PAYMENTDATE="/payment/date/{date}";
	public static final String URL_SERVICEORDERBYID = "/servicebyorder/{id}";
	public static final String URL_GUESTHISTBYID = "/guesthistory/{id}";
	public static final String URL_SHEDULLER = "/sheduller/";
	public static final String URL_LASTGUESTROOM = "/lastguestroom/{id}";
	public static final String URL_FREEROOMDATE = "/freeroom/{date}";
	public static final String URL_COUNTGUESTDATE = "/countguestondate/{date}";
	public static final String URL_CLOSEORDER = "/closeorder/{id}";
	public static final String URL_PREORDER = "/preorder/";
	public static final String URL_PREORDERID = "/preorder/{id}";
	public static final String URL_ONLINEBOOKING = "/onlinebooking";
	public static final String URL_ROOM = "/room/";
	public static final String URL_SERVICE = "/service/";
	
	
	// db needed
	public static final String STATUS = "status";
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	
	public static final String PASS = "pass";
	public static final String DATECONFIG = "dateConfig";
	public static final String ORDER = "order";
	public static final String DATEPAYMENT = "datePayment";
	public static final String ROOMID = "room.id";
	
	// service
	public static final String ADDNEWDATA = "add new data";
	public static final String ADDDED = "Added";
	public static final String ERROR = " Error operation";
	public static final String USERNOTFOUND = "User not found";
	public static final String SAVECONFIG ="Save config to db";
	public static final String SAVED ="Saved";
	public static final String UPDATECOUNTRY ="Update country to db";
	public static final String UPDATED = "Updated";
	public static final String ADDCOUNTRY = "Add country to db";
	public static final String ADDGUEST = "add new Guest result";
	public static final String UPDATEENT = "Update entity";
	public static final String SAVEENT = "Save entity";
	public static final String DELETEENT = "Delete entity";
	public static final String DELETED ="Deleted";
	public static final String ORDERCLOSED ="Order closed";
	
	//mail
	public static final String MAILSUBJ = "Reject Your booking in Hotel Omega";
	public static final String MAILFROM = "soukhotski@gmail.com";
	public static final String GETPAYMENT ="Get payment by date";
	public static final String CLOSEORDER ="close order";
	
	
	


}
