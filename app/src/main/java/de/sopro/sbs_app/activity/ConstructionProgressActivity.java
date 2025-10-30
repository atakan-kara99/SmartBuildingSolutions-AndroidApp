package de.sopro.sbs_app.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.ConstructionProgressDao;
import de.sopro.sbs_app.listadapter.ConstructionProgressListAdapter;
import de.sopro.sbs_app.model.ConstructionProgress;

public class ConstructionProgressActivity extends ActivityBridge {
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    /**
     * Request code for camera permission.
     */
    private static final int CAMERA_REQUEST_CODE = 1286451;
    /**
     * Request code for storage permission.
     */
    static final int REQUEST_STORAGE_PERMISSION = 2;

    private ConstructionProgressListAdapter constructionProgressListAdapter;
    private ConstructionProgressDao cDAO;
    private long billingItemID;
    public static int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_progress);

        billingItemID = getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1);
        cDAO = RequestHandler.getAppDB(getApplicationContext()).constructionProgressDao();

        initRecyclerView();
        loadItemList();
        WindowManager wm = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("on resume");
        loadItemList();
//        constructionProgressListAdapter.notifyDataSetChanged();
    }

    // ListActivity --------------------------------------------------------------------------------
    private void loadItemList() {
        List<ConstructionProgress> constructionProgressList = cDAO.findConstructionProgressByBillingItemId(billingItemID);
        constructionProgressListAdapter.setItemList(constructionProgressList);
    }

    public void initRecyclerView() {
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        rv.addItemDecoration(dividerItemDecoration);
        // cache loaded views
        // implement preloading views if time permits
        // https://androiddevx.wordpress.com/2014/12/05/recycler-view-pre-cache-views/
        rv.setItemViewCacheSize(20);
        constructionProgressListAdapter = new ConstructionProgressListAdapter(this);
        rv.setAdapter(constructionProgressListAdapter);
    }

    // permissions ---------------------------------------------------------------------------------
    public void onCameraButton(View v) {
        // check permissions
        if (hasCameraPermission() && hasStoragePermission()) {
            //has all permissions -> camera activity
            startCamera();
        } else if (!hasCameraPermission()) {
            requestCameraPermission();
        } else {
            requestStoragePermission();
        }
    }

    /**
     * Check if the app has the write permission for external storage.
     *
     * @return true if the permissions were granted
     */
    public boolean hasStoragePermission() {
        return ContextCompat
                .checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request write permission for external storage.
     */
    private void requestStoragePermission() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);

    }

    /**
     * Check if the app has the permission to use the camera.
     *
     * @return true if the permissions were granted
     */
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests camera permissions.
     */
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSION,
                CAMERA_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // give user feedback for necessary permissions that weren't granted yet
        if (!hasCameraPermission()) {
            Toast.makeText(getApplicationContext(), "Keine Kamera Berechtigung.", Toast.LENGTH_SHORT).show();
        }
        if (!hasStoragePermission()) {
            Toast.makeText(getApplicationContext(), "Keine Speicher Berechtigung.", Toast.LENGTH_SHORT).show();
        }
    }

    // start CameraActivity
    private void startCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(BillingItemListActivity.BILLING_ITEM_ID, getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1));
        startActivity(intent);
    }
}
