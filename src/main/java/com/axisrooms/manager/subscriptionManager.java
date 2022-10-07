package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.models.Subscription;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public interface subscriptionManager {
    public Response createSubscription(SubscriptionModel subscriptionRequest) ;

    public List<Subscription> getSubscription(String subscriptionId);

    public List<Subscription> listSubscriptions(String customerId) throws Exception;
}
