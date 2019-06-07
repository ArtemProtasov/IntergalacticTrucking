package com.example.intergalactictrucking.ui.search;

import com.airbnb.epoxy.EpoxyController;
import com.example.intergalactictrucking.data.Order;
import com.example.intergalactictrucking.ui.search.model.OrderModel_;

import java.util.List;
import java.util.UUID;

public class SearchController extends EpoxyController {

    private List<Order> orderList = null;
    private SearchInterfase searchInterfase;

    SearchController(SearchInterfase searchInterfase) {
        this.searchInterfase = searchInterfase;
    }

    @Override
    protected void buildModels() {
        if (orderList != null) {
            for (Order order : orderList) {
                new OrderModel_()
                        .order(order)
                        .searchInterfase(searchInterfase)
                        .id(UUID.randomUUID().toString())
                        .addTo(this);
            }
        }
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        requestModelBuild();
    }

    public interface SearchInterfase {
        void onClickOrder(Order order);
    }
}
