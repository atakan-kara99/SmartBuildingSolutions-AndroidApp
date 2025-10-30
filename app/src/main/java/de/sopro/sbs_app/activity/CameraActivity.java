package de.sopro.sbs_app.activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.BillingItemDao;
import de.sopro.sbs_app.database.BillingUnitDao;
import de.sopro.sbs_app.database.ConstructionProgressDao;
import de.sopro.sbs_app.database.ContractDao;
import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.ConstructionProgress;
import de.sopro.sbs_app.model.Contract;

public class CameraActivity extends ActivityBridge {
    /**
     * Request code for the Image capture intent.
     */
    static final int REQUEST_IMAGE_CAPTURE = 42;
    /**
     * Filepath of the new photo.
     */
    private static String currentPhotoPath;
    /**
     * Imageview in which the new photo will be displayed.
     */
    private ImageView imageView;

    /**
     * Path of the image relative to the picture directory.
     */
    private String path;

    /**
     * The id of the billingItem that receives the ConstructionProgress documentation.
     */
    private long billingItemID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        billingItemID = getIntent().getLongExtra(BillingItemListActivity.BILLING_ITEM_ID, -1);
        createFolder(billingItemID);
        imageView = findViewById(R.id.display_image);
        try {
            dispatchTakePictureIntent();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onDelete(View view) {
        deletePhoto();
        finish();
    }

    /**
     * Delete the photo and set the path to null.
     */
    private void deletePhoto() {
        if (currentPhotoPath == null) {
            throw new IllegalArgumentException("currentPath is null");
        }
        File f = new File(currentPhotoPath);
        if (f.exists()) {
            f.delete();
        }
        currentPhotoPath = null;
    }

    public void onSubmit(View view) {
        // get the users comment
        EditText etComment = findViewById(R.id.comment);
        String comment = etComment.getText().toString();
        //create ConstructionProgress and insert it into the db
        ConstructionProgress cp = new ConstructionProgress(currentPhotoPath, comment, billingItemID);
        ConstructionProgressDao cDAO = RequestHandler.getAppDB(getApplicationContext()).constructionProgressDao();
        cDAO.insertOne(cp);

        currentPhotoPath = null;
        this.finish();
    }

    /**
     * Creates folders specified by the BillingItem's id.
     */
    private void createFolder(long id) {
        // get DAO's
        AppDatabase appDB = RequestHandler.getAppDB(getApplicationContext());
        BillingItemDao bDAO = appDB.billingItemDao();
        BillingUnitDao uDAO = appDB.billingUnitDao();
        ContractDao cDAO = appDB.contractDao();
        BillingItem bItem = bDAO.findById(id);
        String directory = "";

        // check if the billingItem is child of another billingItem -> add those to the path
        while (bItem.getBillingItem_FK() != null) {
            directory = bItem.getBillingItem_FK() + "/" + directory;
            bItem = bDAO.findById(bItem.getBillingItem_FK());
        }
        BillingUnit unit = uDAO.findById(bItem.getBillingUnit_FK());
        Contract contract = cDAO.findById(unit.getContract_FK());

        directory = "/" + contract.getProject_FK() + "/" +
                contract.getId() + "/" +
                unit.getId() + "/" +
                billingItemID + "/" +
                directory;

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path = directory;
        System.out.println("path exists: " + dir.exists() +
                "\npath: " + directory +
                "absolute path: " + Environment.DIRECTORY_PICTURES + directory);
    }


    // photo methods  ----------------------------------

    /**
     * This method uses the camera app to take and store a photo.
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(getApplicationContext(), "Es konnte kein Foto erstellt werden.", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // return to the ConstructionProgressActivity if the user canceled the process of capturing an image
        if (resultCode == RESULT_CANCELED) {
            deletePhoto();
            Toast.makeText(getApplicationContext(), "Es wurde kein Photo gemacht.", Toast.LENGTH_LONG).show();
            this.finish();

        }
        // display image if the user was successfull
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
            setPic();
        }
    }

    /**
     * Creates an image file in which the camera-app will store the photo.
     *
     * @return empty image file
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        ConstructionProgressDao cDAO = RequestHandler.getAppDB(getApplicationContext()).constructionProgressDao();
        int progressNum = cDAO.findConstructionProgressByBillingItemId(billingItemID).size();
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES + path);
        // billingItemID_picNumber
        String imageFileName = "progressPic_" + progressNum;
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * Sets the imageView's content to the image stored at the {@code currentPhotoPath}.
     */
    private void setPic() {

        int targetW = ConstructionProgressActivity.screenWidth;
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, photoW / targetW);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    //TODO: Image handling on profile button

    //TODO: Auslagern in den Standard zurück Button
    public void onBack(View view) {
        deletePhoto();
        this.finish();
    }
}
