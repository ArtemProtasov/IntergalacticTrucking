package com.example.intergalactictrucking.ui.search.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.data.Order;
import com.example.intergalactictrucking.ui.search.SearchController;

@EpoxyModelClass(layout = R.layout.epoxy_order_item)
public abstract class OrderModel extends EpoxyModelWithHolder<Holder> {

    @EpoxyAttribute
    Order order;
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    SearchController.SearchInterfase searchInterfase;

    @Override
    public void bind(@NonNull Holder holder) {
        super.bind(holder);
        holder.whence.setText(order.getFromWhere());
        holder.where.setText(order.getWhere());
        holder.cost.setText(order.getPrice());
        holder.cost1.setText("TEEEEXT");
        holder.weight.setText(order.getWeight());
        holder.volume.setText(order.getVolume());
        holder.type.setText(order.getBodyType());
        holder.date.setText(order.getShipmentDate());
    }
}

class Holder extends EpoxyHolder {

    TextView whence;
    TextView where;
    TextView cost;
    TextView cost1;
    TextView weight;
    TextView volume;
    TextView type;
    TextView date;

    @Override
    protected void bindView(@NonNull View itemView) {
        whence = itemView.findViewById(R.id.whence);
        where = itemView.findViewById(R.id.where);
        cost = itemView.findViewById(R.id.cost);
        cost1 = itemView.findViewById(R.id.cost1);
        weight = itemView.findViewById(R.id.weight);
        volume = itemView.findViewById(R.id.volume);
        type = itemView.findViewById(R.id.type);
        date = itemView.findViewById(R.id.date);
    }
}