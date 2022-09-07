package com.axisrooms.manager;

import com.axisrooms.model.ItemPriceModel;
import com.axisrooms.model.Response;
import com.axisrooms.model.ItemModel;
import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.Result;
import com.chargebee.models.Item;
import com.chargebee.models.ItemFamily;
import com.chargebee.models.ItemPrice;
import com.chargebee.models.enums.PricingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class itemManagerImpl implements itemManager {

    @Value("${getItemsUrl}")
    private String getItemsUrl;

    @Override
    public Response createItem(ItemModel subscriptionRequest) {
        return null;
    }

    @Override
    public List<?> listItemFamily() {
        List<String> itemList = new ArrayList<>();
        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            ListResult result = ItemFamily.list()
                    .limit(5)
                    .request();
            for(ListResult.Entry entry:result) {
                ItemFamily itemFamily = entry.itemFamily();
                itemList.add(itemFamily.id());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<?> listItems(String itemFamily) {
        List<String> planList = new ArrayList<>();
        List<String> itemList = new ArrayList<>();
        //ItemFamily itemFam = new ItemFamily(itemFamily);

        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            ListResult result = Item.list()
                    .itemFamilyId().is(itemFamily)
                    .limit(99)
                    .request();
            for(ListResult.Entry entry:result) {
                Item item = entry.item();
               // if(item.itemFamilyId().equals(itemFamily)) {
                    itemList.add(item.id());
               // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<?> listItemPrices(String itemPlan) {
        List<String> planList = new ArrayList<>();
        List<String> itemList = new ArrayList<>();
        try {
           // String itemPLanId = itemPlan.replace(' ','-');
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            ListResult result = ItemPrice.list()
                    .itemId().is(itemPlan)
                    .limit(100)
                    .request();
            for(ListResult.Entry entry:result) {
                ItemPrice itemPrice = entry.itemPrice();
                log.info("Itemprice: "+itemPrice.externalName());
               // if(itemPrice.externalName().equals(itemPlan)) {
                    itemList.add(itemPrice.id());

               // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Response createItemPrice(ItemPriceModel subscriptionRequest) {
        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            Result result = ItemPrice.create()
                    .id("silver-USD-monthly")
                    .itemId("silver")
                    .name("silver USD monthly")
                    .pricingModel(PricingModel.PER_UNIT)
                    .price(1000)
                    .externalName("silver USD")
                    .periodUnit(ItemPrice.PeriodUnit.MONTH)
                    .period(1)
                    .request();
            ItemPrice itemPrice = result.itemPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
