package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import de.sopro.sbs_app.database.ProjectDao;
import de.sopro.sbs_app.listadapter.ContractListAdapter;
import de.sopro.sbs_app.model.Contract;
import de.sopro.sbs_app.model.Project;

public class ProjectDetailsActivity extends ActivityBridge {

    private ProjectDao pDAO;
    private long projectID;
    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        projectID = getIntent().getLongExtra(ProjectListActivity.PROJECT_ID, -1);

        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        pDAO = appDB.projectDao();
        project = pDAO.findById(projectID);

        setProjectInfo();
    }

    private void setProjectInfo() {
        TextView id = findViewById(R.id.showId);
        TextView name = findViewById(R.id.showName);
        TextView description = findViewById(R.id.showBeschreibung);
        TextView creationdate = findViewById(R.id.showErstellungdatum);
        TextView completiondate = findViewById(R.id.showFertigstellungsdatum);
        TextView cost = findViewById(R.id.showGesamtkosten);
        TextView creator = findViewById(R.id.showErsteller);
        TextView orga = findViewById(R.id.showOrganisation);
        TextView status = findViewById(R.id.showStatus);
        TextView address = findViewById(R.id.showAdresse);

        id.setText(project.getId().toString());
        name.setText(project.getName());
        description.setText(project.getDescription());
        creationdate.setText(project.getCreationDate());
        completiondate.setText(project.getCompletionDate());
        cost.setText(project.getOverallCosts().toString() + " €");
        creator.setText(project.getCreator());
        orga.setText(project.getOrganisation());
        status.setText(project.getStatus().getStatus());
        address.setText(project.getAddress().toString());
    }

}
