package de.sopro.sbs_app.listadapter;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.activity.ConstructionProgressActivity;
import de.sopro.sbs_app.model.ConstructionProgress;

public class ConstructionProgressListAdapter extends RecyclerView.Adapter<ConstructionProgressListAdapter.MyViewHolder> {
    private final Context context;
    private List<ConstructionProgress> constructionProgress;

    public ConstructionProgressListAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(List<ConstructionProgress> constructionProgress) {
        this.constructionProgress = constructionProgress;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConstructionProgressListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_cp, parent, false);
        return new ConstructionProgressListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstructionProgressListAdapter.MyViewHolder holder, int position) {
        ConstructionProgress cp = this.constructionProgress.get(position);
        System.out.println("cp comment: " + cp.getDescription());
        holder.ll.setTag(cp.getId());
        holder.comment.setText(cp.getDescription());
        setPic(holder.photo, cp.getImg());
    }


    private void setPic(ImageView imageView, String photoPath) {

        int targetW = ConstructionProgressActivity.screenWidth;
        System.out.println("target width: " + targetW);

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(photoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        System.out.println("photo width: " + photoW);

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, photoW / targetW);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return constructionProgress.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll;
        TextView comment;
        ImageView photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.linearLayout);
            comment = itemView.findViewById(R.id.description);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}
