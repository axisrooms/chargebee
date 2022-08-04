package com.axisrooms.manager;

//import com.axisrooms.model.CustomerModel;
import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class subscriptionManagerImpl implements subscriptionManager{

        @Override
        public Response createSubscription(SubscriptionModel subscriptionRequest)  {
        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            Result result = Subscription.createWithItems("__test__8asz8Ru9WhHOJO")
                    .subscriptionItemItemPriceId(0,"basic-USD")
                    .subscriptionItemBillingCycles(0,2)
                    .subscriptionItemQuantity(0,1)
                    .subscriptionItemItemPriceId(1,"day-pass-USD")
                    .subscriptionItemUnitPrice(1,100)
                    .request();
            Subscription subscription = result.subscription();
           // Customer customer = result.customer();
            Card card = result.card();
            Invoice invoice = result.invoice();
            List<UnbilledCharge> unbilledCharges = result.unbilledCharges();
            Response response = new Response();
            response.setMessage("");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Subscription getSubscription(String subscriptionId) {
        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            Result result = Subscription.retrieve("__test__8asukSOXe0W3SU").request();
            Subscription subscription = result.subscription();
          //  Customer customer = result.customer();
            Card card = result.card();
            Response response = new Response();
            response.setMessage("");
            return subscription;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
