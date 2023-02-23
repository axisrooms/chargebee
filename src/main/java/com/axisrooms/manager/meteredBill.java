package com.axisrooms.manager;

import com.axisrooms.model.ItemModel;
import com.axisrooms.model.Response;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface meteredBill {
    public Response setUsage(String customerId,Double usage) ;
    public Response closeInvoice(String invoiceId) ;

    Response createInvoice();
}
