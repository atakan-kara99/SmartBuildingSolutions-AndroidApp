package de.sopro.sbs_app.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.model.BillingUnit;

public class BillingUnitListAdapter extends RecyclerView.Adapter<BillingUnitListAdapter.MyViewHolder> {
    private final Context context;
    private List<BillingUnit> billingUnits;

    public BillingUnitListAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(List<BillingUnit> billingUnits) {
        this.billingUnits = billingUnits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillingUnitListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list_row, parent, false);
        return new BillingUnitListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillingUnitListAdapter.MyViewHolder holder, int position) {
        holder.details.setTag(this.billingUnits.get(position).getId());

        String name = this.billingUnits.get(position).getShortDescription();
        String id = this.billingUnits.get(position).getId().toString();
        holder.title.setText("ID: " + id + " \n" + name);

        holder.ll.setTag(this.billingUnits.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return billingUnits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout ll;
        Button details;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            ll = itemView.findViewById(R.id.linearLayout);
            details = itemView.findViewById(R.id.detailsButton);
        }
    }
}
