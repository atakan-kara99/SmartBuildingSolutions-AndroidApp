package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.BillingUnitDao;
import de.sopro.sbs_app.listadapter.BillingItemListAdapter;
import de.sopro.sbs_app.listadapter.BillingUnitListAdapter;
import de.sopro.sbs_app.model.BillingUnit;

public class BillingUnitListActivity extends ActivityBridge {

    private BillingUnitListAdapter billingUnitListAdapter;
    private BillingUnitDao bDAO;
    private long contractID;
    public static final String BILLING_UNIT_ID = "BILLING_UNIT_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        contractID = getIntent().getLongExtra(ContractListActivity.CONTRACT_ID, -1);
        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        bDAO = appDB.billingUnitDao();

        initRecyclerView();
        loadItemList();

        TextView info = findViewById(R.id.info);
        info.setText("Leistungseinheiten");
    }

    private void loadItemList() {
        List<BillingUnit> billingUnitList = bDAO.findBillingUnitsByContractId(contractID);
        billingUnitListAdapter.setItemList(billingUnitList);
    }


    private void initRecyclerView() {
        RecyclerView rw = findViewById(R.id.recyclerView);
        rw.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 0);
        rw.addItemDecoration(dividerItemDecoration);
        billingUnitListAdapter = new BillingUnitListAdapter(this);
        rw.setAdapter(billingUnitListAdapter);
    }

    public void onItem(View view) {
        Long id = Long.parseLong(view.findViewById(R.id.linearLayout).getTag().toString());
        Intent  intent = new Intent(this, BillingItemListActivity.class);
        intent.putExtra(BILLING_UNIT_ID, id);
        startActivity(intent);
    }

    public void onDetailsButton(View view) {
        Button details = view.findViewById(R.id.detailsButton);
        long billingUnitID = Long.parseLong(details.getTag().toString());

        Intent intent = new Intent(this, BillingUnitDetailsActivity.class);
        intent.putExtra(BILLING_UNIT_ID, billingUnitID);
        startActivity(intent);
    }

}