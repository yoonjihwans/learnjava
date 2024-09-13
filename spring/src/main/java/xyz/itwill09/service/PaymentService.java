package xyz.itwill09.service;

import xyz.itwill09.dto.Payment;

public interface PaymentService {
	void addPayment(Payment payment);
	String getAccessToken();
	Payment getPayment(String accessToken, String impUid);
	String cancelPayment(String accessToken, Payment payment);
}
 