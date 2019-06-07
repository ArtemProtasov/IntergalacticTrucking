package com.example.intergalactictrucking.ui.search;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.data.Order;
import com.example.intergalactictrucking.data.OrderList;
import com.example.intergalactictrucking.retrofit.RestController;
import com.example.intergalactictrucking.utils.ErrorUtils;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends BaseFragment implements SearchController.SearchInterfase {

    EditText editTextwhence;
    EditText editTextwhere;
    EditText editTextweight;
    EditText editTextvolume;
    EditText editTextbodytype;
    EditText editTextshipmentdate;
    Button buttonclean;
    Button buttonsavefilters;
    Button buttonsearchshipments;
    EpoxyRecyclerView epoxyRecyclerViewOrders;

    private NavController navController;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("userOrders");
    private SearchController searchController;

    @Override
    protected int contentResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void setupView() {

        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        editTextwhence = getView().findViewById(R.id.whence);
        editTextwhere = getView().findViewById(R.id.where);
        editTextweight = getView().findViewById(R.id.weight);
        editTextvolume = getView().findViewById(R.id.volume);
        editTextbodytype = getView().findViewById(R.id.bodytype);
        editTextshipmentdate = getView().findViewById(R.id.shipmentdate);
        buttonclean = getView().findViewById(R.id.clean);
        buttonsavefilters = getView().findViewById(R.id.savefilters);
        buttonsearchshipments = getView().findViewById(R.id.sendOrder);
        epoxyRecyclerViewOrders = getView().findViewById(R.id.epoxyRecyclerViewOrders);

        buttonsearchshipments.setOnClickListener(v -> {

        });

        setupEpoxyController();

        getOrders();

    }

    private void setupEpoxyController() {
        searchController = new SearchController(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                epoxyRecyclerViewOrders.getContext(),
                LinearLayoutManager.VERTICAL
        );

        epoxyRecyclerViewOrders.addItemDecoration(dividerItemDecoration);

        epoxyRecyclerViewOrders.setController(searchController);
    }

    private void getOrders() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, HashMap<String, HashMap<String,String>>> hashMapDatabase = (HashMap<String, HashMap<String, HashMap<String, String>>>) dataSnapshot.getValue();
                List<Order> orderList = new ArrayList<>();
                // filter
                for (HashMap<String, HashMap<String, String>> value1 : hashMapDatabase.values()) {
                    for (HashMap<String, String> value2 : value1.values()) {
                        Order order = new Order();
                        order.setFromWhere(value2.get("fromWhere"));
                        order.setWhere(value2.get("where"));
                        order.setPrice(value2.get("price"));
                        order.setShipmentDate(value2.get("shipmentDate"));
                        order.setBodyType(value2.get("bodyType"));
                        order.setUserId(value2.get("userId"));
                        order.setWeight(value2.get("weight"));
                        order.setVolume(value2.get("volume"));
                        order.setDeliveryInProgress("true");
                        orderList.add(order);
                    }
                }

                searchController.setOrderList(orderList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Не удалять! метод для примера написания rest запросов
     */
    private void testRest() {
        // Перед запросом мы можем показать progressDialog
        UtilsDialog.showLoading(getActivity(), progressDialog);
        progressDialog.show();

        RestController.CLIENT.getTestObject().enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                UtilsDialog.dismissLoading(progressDialog);
                if (response.isSuccessful()) {
                    // Тут у нас успех. Значит, выполняем всю нужную нам работу
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // Тут показываем диалог и сообщаем об ошибке
                UtilsDialog.dismissLoading(progressDialog);
                UtilsDialog.showBasicDialog(getActivity(), "Ok", ErrorUtils.parseError(t.toString()).getMessage()).show();
            }
        });
    }

    @Override
    public void onClickOrder(Order order) {

    }
}
