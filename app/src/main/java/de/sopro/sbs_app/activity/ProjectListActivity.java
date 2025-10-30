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

import java.util.ArrayList;
import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.ProjectDao;
import de.sopro.sbs_app.listadapter.ProjectListAdapter;
import de.sopro.sbs_app.model.Project;

public class ProjectListActivity extends ActivityBridge {


    private ProjectListAdapter projectListAdapter;
    private List<Project> projectList = new ArrayList<>();
    private ProjectDao pDAO;
    public static final String PROJECT_ID = "PROJECT_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_activity);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        pDAO = appDB.projectDao();

        initRecyclerView();
        loadItemList();

        TextView info = findViewById(R.id.info);
        info.setText("Projekte");
    }

    private void loadItemList() {

        projectList = pDAO.findAll();
        projectListAdapter.setItemList(projectList);
    }

    private void initRecyclerView() {
        RecyclerView rw = findViewById(R.id.recyclerView);
        rw.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 0);
        rw.addItemDecoration(dividerItemDecoration);
        projectListAdapter = new ProjectListAdapter(this);
        rw.setAdapter(projectListAdapter);
    }


    public void onItem(View view) {
        // find the project id in the tag of the LinearLayout in the RecyclerView
        long projectID = Long.parseLong(view.findViewById(R.id.linearLayout).getTag().toString());

        //        Project p = pDAO.findById(projectID);

        Intent intent = new Intent(this, ContractListActivity.class);
        intent.putExtra(PROJECT_ID, projectID);
        startActivity(intent);
    }

    public void onDetailsButton(View view) {
        Button details = view.findViewById(R.id.detailsButton);
        long projectID = Long.parseLong(details.getTag().toString());

        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(PROJECT_ID, projectID);
        startActivity(intent);
    }

}
