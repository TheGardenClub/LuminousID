package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import static com.luminousid.luminousid.R.id.plantThumbnail;

/**
 * Created by chase on 4/17/2017.
 */

public class forbsFilterableAdapter extends RecyclerView.Adapter<forbsFilterableAdapter.forbsHolder> {

    public class forbsHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public forbsHolder(View mView, final Context mContext) {
            super(mView);

            plantThumbnail = (ImageView) mView.findViewById(R.id.plantThumbnail);
            speciesnameText = (TextView) mView.findViewById(R.id.species_nameText);
            commonnameText = (TextView) mView.findViewById(R.id.common_nameText);

            plantThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "forbs");

                    // Get plant clicked on and send to plantDetailActivity.
                    forbsDetails forbsPlant = forbsFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(forbsPlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "forbs");

                    // Get plant clicked on and send to plantDetailActivity.
                    forbsDetails forbsPlant = forbsFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(forbsPlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "forbs");

                    // Get plant clicked on and send to plantDetailActivity.
                    forbsDetails forbsPlant = forbsFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(forbsPlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<forbsDetails> mforbsDetails;
    Context mContext;

    public forbsFilterableAdapter(Context context, ArrayList<forbsDetails> forbsdetails) {
        mforbsDetails = forbsdetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public forbsFilterableAdapter.forbsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View forbsView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        forbsHolder forbsHolder = new forbsHolder(forbsView, parent.getContext());
        return forbsHolder;
    }

    @Override
    public void onBindViewHolder(forbsFilterableAdapter.forbsHolder mforbsHolder, int position) {
        forbsDetails thisForb = mforbsDetails.get(position);

        ImageView plantThumbnail = mforbsHolder.plantThumbnail;
        TextView speciesnameText = mforbsHolder.speciesnameText;
        TextView commonnameText = mforbsHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisForb.getPlant_code() + "_1.jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/forbs/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisForb.getSpecies_name());
        commonnameText.setText(thisForb.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mforbsDetails.size();
    }

    public static forbsDetails getItem(int position) {
        return mforbsDetails.get(position);
    }

}
