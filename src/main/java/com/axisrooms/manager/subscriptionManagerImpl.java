package com.axisrooms.manager;

//import com.axisrooms.model.CustomerModel;
import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionItem;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.*;
import com.chargebee.models.enums.AutoCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class subscriptionManagerImpl implements subscriptionManager{

    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Value("${siteName}")
    private String siteName;

        @Override
        public Response createSubscription(SubscriptionModel subscriptionRequest)  {
        try {
            Environment.configure(siteName,acceptedToken);
            List<SubscriptionItem> subList = subscriptionRequest.getSubscription_items();
            SubscriptionItem subItem = subList.get(0);
            //SubscriptionItem subItem= subs.subscriptionItems().get(0);
            Result result = Subscription.createWithItems(subscriptionRequest.customer_id)
                    .subscriptionItemItemPriceId(0,subItem.item_price_id)//"CM-P41-INR-Monthly"
                    .subscriptionItemBillingCycles(0,2)
                    .subscriptionItemQuantity(0,1)
                    .autoCollection(AutoCollection.OFF)
//                    .subscriptionItemItemPriceId(1,"day-pass-USD")
//                    .subscriptionItemUnitPrice(1,100)
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
            Result result = Subscription.retrieve(subscriptionId).request();
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
