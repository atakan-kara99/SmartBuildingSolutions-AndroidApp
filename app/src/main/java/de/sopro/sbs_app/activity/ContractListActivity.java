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
import de.sopro.sbs_app.database.ContractDao;
import de.sopro.sbs_app.database.ProjectDao;
import de.sopro.sbs_app.listadapter.ContractListAdapter;
import de.sopro.sbs_app.model.Contract;
import de.sopro.sbs_app.model.Status;

public class ContractListActivity extends ActivityBridge {

    private ContractListAdapter contractListAdapter;
    private ContractDao cDAO;
    private long projectID;
    public static final String CONTRACT_ID = "CONTRACT_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        projectID = getIntent().getLongExtra(ProjectListActivity.PROJECT_ID, -1);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        cDAO = appDB.contractDao();

        initRecyclerView();
        loadItemList();

        TextView info = findViewById(R.id.info);
        info.setText("Verträge");
    }

    private void loadItemList() {
        List<Contract> contractList = cDAO.findContractsByProjectId(projectID);
        contractListAdapter.setItemList(contractList);
    }

    private void initRecyclerView() {
        RecyclerView rw = findViewById(R.id.recyclerView);
        rw.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 0);
        rw.addItemDecoration(dividerItemDecoration);
        contractListAdapter = new ContractListAdapter(this);
        rw.setAdapter(contractListAdapter);
    }


    public void onItem(View view) {
        Long id = Long.parseLong(view.findViewById(R.id.linearLayout).getTag().toString());


        Intent intent = new Intent(this, BillingUnitListActivity.class);
        intent.putExtra(CONTRACT_ID, id);
        startActivity(intent);
    }

    public void onDetailsButton(View view) {
        Button details = view.findViewById(R.id.detailsButton);
        long contractID = Long.parseLong(details.getTag().toString());

        Intent intent = new Intent(this, ContractDetailsActivity.class);
        intent.putExtra(CONTRACT_ID, contractID);
        startActivity(intent);
    }
}
