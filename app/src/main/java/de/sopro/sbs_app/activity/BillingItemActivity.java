package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.BillingItemDao;
import de.sopro.sbs_app.database.StatusDao;
import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.Status;

public class BillingItemActivity extends ActivityBridge {

    private BillingItem billingItem;
    private long billingItemId;
    private BillingItemDao bDAO;

    private StatusDao sDAO;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingitem);

        billingItemId = getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        bDAO = appDB.billingItemDao();
        sDAO = appDB.statusDao();

        billingItem = bDAO.findById(billingItemId);

        setBillingItemInfo();

        spinner = findViewById(R.id.statusSpinner);
        setAdapter();
    }

    private void setAdapter() {
        List<Status> statusList = new ArrayList<>();
        Status status = billingItem.getStatus();

        statusList.add(status);
        statusList.addAll(sDAO.findSuccessors(status.getStatus()));

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);

        // Add all status names to adapter
        for (Status s : statusList ) {
            adapter.add(s.getStatus());
        }

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void setBillingItemInfo() {
        TextView id = findViewById(R.id.showId);
        TextView beschreibung = findViewById(R.id.showBeschreibung);
        TextView stückzahl = findViewById(R.id.showStückzahl);
        TextView stückpreis = findViewById(R.id.showStückpreis);
        TextView gesamtkosten = findViewById(R.id.showGesamtkosten);
        TextView status = findViewById(R.id.showStatus);
        TextView enthaltenIn = findViewById(R.id.enthaltenIn);
        TextView showEnthaltenIn = findViewById(R.id.showEnthaltenIn);

        id.setText(billingItem.getId().toString());
        beschreibung.setText(billingItem.getShortDescription());
        stückzahl.setText(String.valueOf(billingItem.getQuantities()));
        stückpreis.setText(String.valueOf(billingItem.getUnitPrice()) + " €");
        gesamtkosten.setText(String.valueOf(billingItem.getPrice()) + " €");
        // TODO: Dropdown menu
        status.setText(billingItem.getStatus().getStatus());

        if (billingItem.getBillingUnit_FK() == null) {
            enthaltenIn.setText("Enhalten in \nLeistungsposition:");
            showEnthaltenIn.setText(billingItem.getBillingItem_FK().toString());
        } else {
            enthaltenIn.setText("Enthalten in \nLeistungseinheit:");
            showEnthaltenIn.setText(billingItem.getBillingUnit_FK().toString());
        }
    }

    public void onConstructionProgress(View view) {
        Intent intent = new Intent(this, ConstructionProgressActivity.class);
        intent.putExtra(BillingItemListActivity.BILLING_ITEM_ID, getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1));
        startActivity(intent);
    }
}
