package com.axisrooms.manager;

//import com.axisrooms.model.CustomerModel;
import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionItem;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.Result;
import com.chargebee.models.*;
import com.chargebee.models.enums.AutoCollection;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            Response response = new Response();
        try {
            Environment.configure(siteName,acceptedToken);
            List<SubscriptionItem> subList = subscriptionRequest.getSubscription_items();
            SubscriptionItem subItem = subList.get(0);
            //SubscriptionItem subItem= subs.subscriptionItems().get(0);
            Result result = Subscription.createWithItems(subscriptionRequest.customer_id)
                    .subscriptionItemItemPriceId(0,subItem.item_price_id)//"CM-P41-INR-Monthly"
                    .subscriptionItemBillingCycles(0,subItem.billing_cycles)
                    .subscriptionItemQuantity(0,subItem.quantity)
                    .autoCollection(AutoCollection.OFF)

//                    .subscriptionItemItemPriceId(1,"day-pass-USD")
//                    .subscriptionItemUnitPrice(1,100)
                    .request();
            Subscription subscription = result.subscription();
           // Customer customer = result.customer();
            Card card = result.card();
            Invoice invoice = result.invoice();
            List<UnbilledCharge> unbilledCharges = result.unbilledCharges();

            if(subscription.toString().contains("error")) {
                response.setMessage("Error");
            }
            else{
                response.setMessage("Subscription created successfully - " + subscription.id());
            }
          //  return response;
        } catch (Exception e) {
            response.setMessage(e.getMessage().toString());
            e.printStackTrace();
            return response;
        }
            return response;
        }

    @Override
    public List<Subscription> getSubscription(String subscriptionId) {
        List subscriptionList = new ArrayList();
        try {
            Environment.configure(siteName,acceptedToken);
            Result result = Subscription.retrieve(subscriptionId).request();
            Subscription subscription = result.subscription();
            SubscriptionModel sub = new SubscriptionModel();
            sub.setId(subscription.id());
            //sub.setActivated_at(subscription.activatedAt());
            sub.setDue_invoices_count(subscription.dueInvoicesCount());
           // sub.setDue_since(subscription.dueSince());
           // sub.setTotal_dues(subscription.totalDues());
                    subscriptionList.add(subscription);
            return subscriptionList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subscription> getTransactionalSubscription(String customerId) {
        List subscriptionList = new ArrayList();
        try {

                Environment.configure(siteName,acceptedToken);
                ListResult result = Subscription.list()
                        .customerId().is(customerId)
                        .limit(99)
                        .request();
                for(ListResult.Entry entry:result) {
                    Subscription subscription = entry.subscription();
                    Gson gson = new Gson();
                    String subsc = gson.toJson(subscription);
                    if (subsc.contains("metered")) {
                        log.info("Subsc: " + subscription.id());
                        subscriptionList.add(subscription.id());
                    }

                }

            return subscriptionList;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subscription> listSubscriptions(String customerId) throws Exception {
            List subscriptionList = new ArrayList();
        try {
            Environment.configure(siteName,acceptedToken);
            ListResult result = Subscription.list()
                    .customerId().is(customerId)
                    .limit(99)
                    .request();
            for(ListResult.Entry entry:result) {
                Subscription subscription = entry.subscription();
                log.info("Subsc: "+subscription.id());
                subscriptionList.add(subscription.id());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriptionList;
    }
}
