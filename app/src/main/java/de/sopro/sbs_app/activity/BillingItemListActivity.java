package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.BillingItemDao;
import de.sopro.sbs_app.listadapter.BillingItemListAdapter;
import de.sopro.sbs_app.listadapter.BillingUnitListAdapter;
import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;

public class BillingItemListActivity extends ActivityBridge {

    private BillingItemListAdapter billingItemlistAdapter;
    private BillingItemDao bDAO;
    private long billingID;
    public static final String BILLING_ITEM_ID = "BILLING_ITEM_ID";
    private boolean isBillingItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        isBillingItem = getIntent().getBooleanExtra("ISBILLINGITEM", false);

        if (isBillingItem) {
            billingID = getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1);
        } else {
            billingID = getIntent().getLongExtra(BillingUnitListActivity.BILLING_UNIT_ID, -1);
        }

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        bDAO = appDB.billingItemDao();

        initRecyclerView();
        loadItemList();

        TextView info = findViewById(R.id.info);
        info.setText("Leistungspositionen");
    }

    private void loadItemList() {
        List<BillingItem> billingItemList;
        if (isBillingItem) {
            billingItemList = bDAO.findBillingItemsByBillingItemId(billingID);
        } else {
            billingItemList = bDAO.findBillingItemsByBillingUnitId(billingID);
        }

        billingItemlistAdapter.setItemList(billingItemList);
    }

    private void initRecyclerView() {
        RecyclerView rw = findViewById(R.id.recyclerView);
        rw.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,0);
        rw.addItemDecoration(dividerItemDecoration);
        billingItemlistAdapter = new BillingItemListAdapter(this);
        rw.setAdapter(billingItemlistAdapter);
    }

    public void onItem(View view) {
        Long id = Long.parseLong(view.findViewById(R.id.linearLayout).getTag().toString());
        List<BillingItem> billingItems = bDAO.findBillingItemsByBillingItemId(id);
        if (billingItems.isEmpty()) {
            Toast.makeText(this, "Keine weiteren Leistungspositionen", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, BillingItemListActivity.class);
            intent.putExtra(BILLING_ITEM_ID, id);
            intent.putExtra("ISBILLINGITEM", true);
            startActivity(intent);
        }
    }

    public void onDetailsButton(View view) {
        Long id = Long.parseLong(view.findViewById(R.id.detailsButton).getTag().toString());
        Intent  intent = new Intent(this, BillingItemActivity.class);
        intent.putExtra(BILLING_ITEM_ID, id);
        startActivity(intent);
    }
}
