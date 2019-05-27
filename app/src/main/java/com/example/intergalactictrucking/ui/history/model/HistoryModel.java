package com.example.intergalactictrucking.ui.history.model;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.example.intergalactictrucking.R;

@EpoxyModelClass(layout = R.layout.epoxy_history_model)
public abstract class HistoryModel extends EpoxyModelWithHolder<HistoryModel.Holder> {

    @Override
    public void bind(@NonNull Holder holder) {
        super.bind(holder);

        holder.textViewTestItem.setText("Это тестовый элемент!");
    }

    public static class Holder extends EpoxyHolder {

        public TextView textViewTestItem;

        @Override
        protected void bindView(@NonNull View itemView) {
            textViewTestItem = itemView.findViewById(R.id.textViewTestItem);
        }
    }

}
