package com.example.intergalactictrucking.ui.search;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

import butterknife.BindView;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.editText_whence)
    EditText editText_whence;
    @BindView(R.id.editText_where)
    EditText editText_where;
    @BindView(R.id.editText_weight)
    EditText editText_weight;
    @BindView(R.id.editText_volume)
    EditText editText_volume;
    @BindView(R.id.editText_body_type)
    EditText editText_body_type;
    @BindView(R.id.editText_shipment_date)
    EditText editText_shipment_date;
    @BindView(R.id.button_clean)
    Button button_clean;
    @BindView(R.id.button_save_filters)
    Button button_save_filters;
    @BindView(R.id.button_search_shipments)
    Button button_search_shipments;

    @Override
    protected int contentResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void setupView() {
        button_search_shipments.setOnClickListener(v -> {

        });
}
