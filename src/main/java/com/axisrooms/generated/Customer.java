package com.axisrooms.generated;

import lombok.Data;
import java.util.List;

@Data
public class Customer {
    private boolean allow_direct_debit;
    private String auto_collection;
    public Billing_address billingaddress;
    private String card_status;
    private float created_at;
    private boolean deleted;
    private String email;
    private float excess_payments;
    private String first_name;
    private String id;
    private String last_name;
    private String locale;
    private float net_term_days;
    private String object;
    private String pii_cleared;
    private String preferred_currency_code;
    private float promotional_credits;
    private float refundable_credits;
    private float resource_version;
    private String taxability;
    private float unbilled_charges;
    private float updated_at;
}