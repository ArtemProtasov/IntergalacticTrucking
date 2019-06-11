package com.example.intergalactictrucking.ui.orders;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.data.Order;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class OrdersFragment extends BaseFragment {

    private NavController navController;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("userOrders");

    EditText editTextfromWhere;
    EditText editTextWhere;
    EditText editTextWeight;
    EditText editTextVolume;
    EditText editTextBodyType;
    EditText editTextShipmentDate;
    EditText editTextPrice;
    Button buttonCleanFields;
    Button buttonSendOrder;

    @Override
    protected int contentResource() {
        return R.layout.fragment_orders;
    }

    @Override
    protected void setupViewById() {
        editTextfromWhere = getView().findViewById(R.id.whence);
        editTextWhere = getView().findViewById(R.id.where);
        editTextWeight = getView().findViewById(R.id.weight);
        editTextVolume = getView().findViewById(R.id.volume);
        editTextBodyType = getView().findViewById(R.id.bodytype);
        editTextShipmentDate = getView().findViewById(R.id.shipmentdate);
        editTextPrice = getView().findViewById(R.id.price);
        buttonCleanFields = getView().findViewById(R.id.clean);
        buttonSendOrder = getView().findViewById(R.id.sendOrder);

        editTextVolume.setHint(Html.fromHtml( " Объем, м<sup>3</sup>"));
    }

    @Override
    protected void setupView() {

        firebaseAuth = FirebaseAuth.getInstance();

        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        buttonSendOrder.setOnClickListener(v -> {
            Order order = new Order();
            order.setUserId(firebaseAuth.getCurrentUser().getUid());
            order.setFromWhere(editTextfromWhere.getText().toString());
            order.setWhere(editTextWhere.getText().toString());
            order.setWeight(editTextWeight.getText().toString());
            order.setVolume(editTextVolume.getText().toString());
            order.setBodyType(editTextBodyType.getText().toString());
            order.setShipmentDate(editTextShipmentDate.getText().toString());
            order.setPrice(editTextPrice.getText().toString());

            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child(UUID.randomUUID().toString())
                    .setValue(order).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            UtilsDialog.showBasicDialog(getActivity(), "Ok", "Заказ успешно размещен!").show();
                            clearData();
                        } else {
                            UtilsDialog.showBasicDialog(getActivity(), "Ok", "Произошла ошибка при попытке разместить заказ. Повторите попытку...").show();
                        }
                    });

        });

        buttonCleanFields.setOnClickListener(v -> {
            clearData();
        });

    }

    private void clearData() {
        editTextfromWhere.setText("");
        editTextWhere.setText("");
        editTextWeight.setText("");
        editTextVolume.setText("");
        editTextBodyType.setText("");
        editTextShipmentDate.setText("");
        editTextPrice.setText("");
    }
}
