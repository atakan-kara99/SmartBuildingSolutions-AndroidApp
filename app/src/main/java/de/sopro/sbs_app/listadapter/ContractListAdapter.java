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
import de.sopro.sbs_app.model.Contract;

public class ContractListAdapter extends RecyclerView.Adapter<ContractListAdapter.MyViewHolder> {
    private final Context context;
    private List<Contract> contracts;
    public ContractListAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(List<Contract> contracts){
        this.contracts =contracts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContractListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list_row, parent,false);
        return new ContractListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractListAdapter.MyViewHolder holder, int position) {
        holder.details.setTag(this.contracts.get(position).getId());

        String name = this.contracts.get(position).getName();
        String id = this.contracts.get(position).getId().toString();
        holder.title.setText("ID: " + id + " \n" + name);

        holder.ll.setTag(this.contracts.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return contracts.size();
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
