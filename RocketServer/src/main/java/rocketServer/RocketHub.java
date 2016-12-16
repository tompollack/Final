package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message)
	{
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest)
			try {
				{
					resetOutput();
					
					LoanRequest lq = (LoanRequest) message;
					
					//	TODO - RocketHub.messageReceived

					//	You will have to:
					//	Determine the rate with the given credit score (call RateBLL.getRate)
					//		If exception, show error message, stop processing
					//		If no exception, continue
					//	Determine if payment, call RateBLL.getPayment
					//	
					//	you should update lq, and then send lq back to the caller(s)
					double rate = RateBLL.getRate(lq.getiCreditScore());;
					if(rate>0)
					{
						lq.setdPayment(RateBLL.getPayment(rate, lq.getiTerm(), lq.getdAmount(), f, false));
					}
					sendToAll(lq);
				}
			} catch (RateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
