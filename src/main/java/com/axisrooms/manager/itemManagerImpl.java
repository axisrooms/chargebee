package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.axisrooms.model.ItemModel;
import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.models.Item;
import com.chargebee.models.ItemFamily;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
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
                itemList.add(itemFamily.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<?> listItems() {
        List<String> planList = new ArrayList<>();
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders httpHeaders = new HttpHeaders();
//           // httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//         //   httpHeaders.set("Authorization", "Bearer "+"test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
//            String credentials = "test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k";
//            String encodedAuthorization = Base64.getEncoder().encodeToString(credentials.getBytes());
//
//            Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthorization);
//            httpHeaders.add(header);
//            HttpEntity entity = new HttpEntity<>(null, header);
//            ResponseEntity<String> responseEntity = restTemplate.exchange(getItemsUrl, HttpMethod.GET,entity,String.class);
//            log.info(responseEntity.getBody());
//            //            for(ListResult.Entry entry:result) {
////                ItemFamily itemFamily = entry.itemFamily();
////                planList.add(itemFamily.name());
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        List<String> itemList = new ArrayList<>();
        try {
            Environment.configure("axisrooms-test","test_WZcu6gTPcunkWwpkzWEbqO7Ei1AqIpe03k");
            ListResult result = Item.list()
                    .limit(150)
                    .request();
            for(ListResult.Entry entry:result) {
                Item item = entry.item();
                itemList.add(item.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
