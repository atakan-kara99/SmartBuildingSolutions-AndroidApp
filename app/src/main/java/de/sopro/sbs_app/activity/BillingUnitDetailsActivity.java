package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.BillingUnitDao;
import de.sopro.sbs_app.database.ContractDao;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.Contract;

public class BillingUnitDetailsActivity extends ActivityBridge {

    private BillingUnitDao bDAO;
    private long billingUnitID;
    private BillingUnit billingUnit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingunit_details);

        billingUnitID = getIntent().getLongExtra(BillingUnitListActivity.BILLING_UNIT_ID, -1);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        bDAO = appDB.billingUnitDao();
        billingUnit = bDAO.findById(billingUnitID);

        setBillingUnitInfo();
    }

    private void setBillingUnitInfo() {
        TextView id = findViewById(R.id.showId);
        TextView beschreibung = findViewById(R.id.showBeschreibung);
        TextView einheit = findViewById(R.id.showEinheit);
        TextView fertigstellung = findViewById(R.id.showFertigstellung);
        TextView anzahl = findViewById(R.id.showGesamtanzahl);
        TextView kosten = findViewById(R.id.showGesamtkosten);
        TextView status = findViewById(R.id.showStatus);
        TextView vertrag = findViewById(R.id.showVertrag);

        id.setText(billingUnit.getId().toString());
        beschreibung.setText(billingUnit.getLongDescription());
        einheit.setText(billingUnit.getUnit());
        fertigstellung.setText(billingUnit.getCompletionDate());
        anzahl.setText(String.valueOf(billingUnit.getTotalQuantity()));
        kosten.setText(String.valueOf(billingUnit.getTotalPrice()) + " €");
        status.setText(billingUnit.getStatus().getStatus());
        vertrag.setText(billingUnit.getContract_FK().toString());
    }

}
