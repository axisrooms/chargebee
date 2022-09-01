package com.axisrooms.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubscriptionModel {
    public int activated_at;
    public int billing_period;
    public String billing_period_unit;
    public int created_at;
    public String currency_code;
    public int current_term_end;
    public int current_term_start;
    public String customer_id;
    public boolean deleted;
    public int due_invoices_count;
    public int due_since;
    public boolean has_scheduled_changes;
    public String id;
    public int mrr;
    public int next_billing_at;
    public String object;
    public int remaining_billing_cycles;
    public long resource_version;
    public int started_at;
    public String status;
    public List<SubscriptionItem> subscription_items;
    public int total_dues;
    public int updated_at;
}