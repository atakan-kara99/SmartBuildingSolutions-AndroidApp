package de.sopro.sbs_app.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.model.BillingItem;


public class BillingItemListAdapter extends RecyclerView.Adapter<de.sopro.sbs_app.listadapter.BillingItemListAdapter.MyViewHolder> {
        private final Context context;
        private List<BillingItem> billingItems;

        public BillingItemListAdapter(Context context) {
            this.context = context;
        }

        public void setItemList(List<BillingItem> billingItems) {
            this.billingItems = billingItems;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public de.sopro.sbs_app.listadapter.BillingItemListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_list_row, parent, false);
            return new de.sopro.sbs_app.listadapter.BillingItemListAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull de.sopro.sbs_app.listadapter.BillingItemListAdapter.MyViewHolder holder, int position) {
            holder.bearbeiten.setText("BEARBEITEN");
            holder.bearbeiten.setTag(this.billingItems.get(position).getId());

            String name = this.billingItems.get(position).getShortDescription();
            String id = this.billingItems.get(position).getId().toString();
            holder.title.setText("ID: " + id + " \n" + name);

            holder.ll.setTag(this.billingItems.get(position).getId());
        }

        @Override
        public int getItemCount() {
            return billingItems.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            LinearLayout ll;
            Button bearbeiten;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                ll = itemView.findViewById(R.id.linearLayout);
                bearbeiten = itemView.findViewById(R.id.detailsButton);
            }
        }
    }
