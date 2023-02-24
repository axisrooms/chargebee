package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.Invoice;
import com.chargebee.models.Subscription;
import com.chargebee.models.Usage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class meteredBillImpl implements meteredBill{

    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Value("${siteName}")
    private String siteName;

    @Autowired
    private com.axisrooms.manager.subscriptionManager subscriptionManager;

    @Override
    public Response setUsage(String customerId, Double usage) {
        try {
            String itemPriceId = "";
            Integer quantity = Math.toIntExact(Math.round(usage));
            List<Subscription> subscriptionList = subscriptionManager.listSubscriptions(customerId);
            String subId="";
            if(!subscriptionList.isEmpty()){
                Subscription subscription = (subscriptionManager.getSubscription(String.valueOf(subscriptionList.get(0)))).get(0);
                subId = subscription.id();
                Subscription.SubscriptionItem subscriptionItem = subscription.subscriptionItems().get(0);
                // if(!subscriptionItem.isEmpty())
                    itemPriceId = subscriptionItem.itemPriceId();
            }
            Environment.configure(siteName,acceptedToken);
            Result result = Usage.create(subId)
                    .itemPriceId(itemPriceId)
                    .usageDate(Timestamp.from(Instant.now()))
                    .note("Last month usage as on "+ Timestamp.from(Instant.now()).getDate()+" from customerId :"+customerId)
                    .quantity(quantity.toString())
                    .request();
            Usage usageRes = result.usage();
            log.info("Usage response : "+usageRes);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Response closeInvoice(String invoiceId) {
        try {
            Environment.configure(siteName,acceptedToken);
            Result result = Invoice.close(invoiceId).request();
            Invoice invoice = result.invoice();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    protected static final Logger log = LoggerFactory.getLogger(meteredBillImpl.class);

    @Override
    public Response createInvoice() {
        try {
//            Environment.configure(siteName,acceptedToken);
//            Result result = Invoice.createForChargeItemsAndCharges()
//                    .customerId("__test__KyVkkWS1xLskm8")
//                    .itemPriceItemPriceId(0,"ssl-charge-USD")
//                    .itemPriceUnitPrice(0,2000)
//                    .shippingAddressFirstName("John")
//                    .shippingAddressLastName("Mathew")
//                    .shippingAddressCity("Walnut")
//                    .shippingAddressState("California")
//                    .shippingAddressZip("91789")
//                    .shippingAddressCountry("US")
//                    .request();
//            Invoice invoice = result.invoice();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/x-www-form-urlencoded");
            headers.set("Authorization","Basic dGVzdF9BVGxCcTdCSzBOMU5YMFNCc3FqU3NxVDU1Y2R1bnpCWGs6");
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("customer_id","Azz5egTKdD5jH1JbP");
            map.add("item_prices[item_price_id][0]","Transaction-P1-INR-Monthly");
            map.add("item_prices[date_from][0]","1672531230");
            map.add("item_prices[date_to][0]","1675123230");
            map.add("item_prices[quantity][0]","20");
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            String url ="https://axisrooms-test.chargebee.com/api/v2/invoices/create_for_charge_items_and_charges";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url,
                            HttpMethod.POST,
                            entity,
                            String.class);
            log.info("resp : "+response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
