package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.axisrooms.model.ItemModel;
import com.chargebee.models.ItemFamily;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface itemManager {
    public Response createItem(ItemModel subscriptionRequest) ;

    public List<?> listItemFamily();

    public List<?> listItems();
}
