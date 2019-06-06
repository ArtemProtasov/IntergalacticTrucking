package com.example.intergalactictrucking.ui.search;

import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.retrofit.RestController;
import com.example.intergalactictrucking.utils.ErrorUtils;
import com.example.intergalactictrucking.utils.UtilsDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends BaseFragment {

    EditText editTextwhence;
    EditText editTextwhere;
    EditText editTextweight;
    EditText editTextvolume;
    EditText editTextbodytype;
    EditText editTextshipmentdate;
    Button buttonclean;
    Button buttonsavefilters;
    Button buttonsearchshipments;
    private NavController navController;

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

        buttonsearchshipments.setOnClickListener(v -> {

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
}
