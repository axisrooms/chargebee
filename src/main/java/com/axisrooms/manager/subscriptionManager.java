package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.models.Subscription;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public interface subscriptionManager {
    public Response createSubscription(SubscriptionModel subscriptionRequest) ;

    public Subscription getSubscription(String subscriptionId);
}
