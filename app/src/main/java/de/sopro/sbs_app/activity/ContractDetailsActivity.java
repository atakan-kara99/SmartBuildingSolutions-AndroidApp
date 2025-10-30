package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.ContractDao;
import de.sopro.sbs_app.model.Contract;

public class ContractDetailsActivity extends ActivityBridge {

    private ContractDao cDAO;
    private long contractID;
    private Contract contract;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_details);

        contractID = getIntent().getLongExtra(ContractListActivity.CONTRACT_ID, -1);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        cDAO = appDB.contractDao();
        contract = cDAO.findById(contractID);

        setContractInfo();
    }

    private void setContractInfo() {
        TextView id = findViewById(R.id.showId);
        TextView name = findViewById(R.id.showName);
        TextView description = findViewById(R.id.showBeschreibung);
        TextView auftraggeber = findViewById(R.id.showAuftraggeber);
        TextView auftragnehmner = findViewById(R.id.showAuftragnehmer);
        TextView projekt = findViewById(R.id.showProjekt);
        TextView status = findViewById(R.id.showStatus);

        id.setText(contract.getId().toString());
        name.setText(contract.getName());
        description.setText(contract.getDescription());
        auftraggeber.setText(contract.getConsignee());
        auftragnehmner.setText(contract.getContractor());
        projekt.setText(contract.getProject_FK().toString());
        status.setText(contract.getStatus().getStatus());
    }

}
