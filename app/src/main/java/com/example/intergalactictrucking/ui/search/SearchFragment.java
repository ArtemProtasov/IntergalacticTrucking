package com.example.intergalactictrucking.ui.search;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.data.Order;
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
    ImageButton imageButtonCloseOrders;
    ConstraintLayout constraintLayoutOrders;

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
        imageButtonCloseOrders = getView().findViewById(R.id.imageButtonCloseOrders);
        constraintLayoutOrders = getView().findViewById(R.id.constraintLayoutOrders);

        editTextvolume.setHint(Html.fromHtml( " Объем, м<sup>3</sup>"));


        buttonsearchshipments.setOnClickListener(v -> {
            getOrders();
        });

        imageButtonCloseOrders.setOnClickListener(v -> {
            constraintLayoutOrders.setVisibility(View.GONE);
        });

        setupEpoxyController();

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
                HashMap<String, HashMap<String, HashMap<String, String>>> hashMapDatabase = (HashMap<String, HashMap<String, HashMap<String, String>>>) dataSnapshot.getValue();
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

                List<Order> filteredListOrder = filterOrders(orderList);

                if (filteredListOrder.isEmpty()) {
                    constraintLayoutOrders.setVisibility(View.GONE);
                } else {
                    constraintLayoutOrders.setVisibility(View.VISIBLE);
                    searchController.setOrderList(filteredListOrder);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private List<Order> filterOrders(List<Order> orderList) {
        List<Order> filteredListOrder = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getFromWhere().equals(editTextwhence.getText().toString()) ||
                    order.getWhere().equals(editTextwhere.getText().toString()) ||
                    order.getBodyType().equals(editTextbodytype.getText().toString()) ||
                    order.getShipmentDate().equals(editTextshipmentdate.getText().toString()) ||
                    order.getVolume().equals(editTextvolume.getText().toString()) ||
                    order.getWeight().equals(editTextweight.getText().toString())
            ) {
                filteredListOrder.add(order);
            }
        }

        return filteredListOrder;
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
