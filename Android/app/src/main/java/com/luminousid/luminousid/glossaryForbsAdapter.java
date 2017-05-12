package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by chase on 4/21/2017.
 */

public class glossaryForbsAdapter extends RecyclerView.Adapter<glossaryForbsAdapter.glossaryForbsHolder> {

    public class glossaryForbsHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView glossaryThumbnail;
        public TextView glossarynameText;

        public glossaryForbsHolder(View mView, final Context mContext) {
            super(mView);

            glossaryThumbnail = (ImageView) mView.findViewById(R.id.glossaryThumbnail);
            glossarynameText = (TextView) mView.findViewById(R.id.glossary_nameText);

            glossaryThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, glossaryDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("glossaryType", "forbs");

                    // Get glossary clicked on and send to glossaryDetailActivity.
                    glossaryDetails glossaryEntry = glossaryForbsAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("glossaryInfo", Parcels.wrap(glossaryEntry));

                    mContext.startActivity(intent);
                }
            });
            glossarynameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, glossaryDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("glossaryType", "forbs");

                    // Get glossary clicked on and send to glossaryDetailActivity.
                    glossaryDetails glossaryEntry = glossaryForbsAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("glossaryInfo", Parcels.wrap(glossaryEntry));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<glossaryDetails> mglossaryDetails;
    Context mContext;

    public glossaryForbsAdapter(Context context, ArrayList<glossaryDetails> glossarydetails) {
        mglossaryDetails = glossarydetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public glossaryForbsAdapter.glossaryForbsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View glossaryView = inflater.inflate(R.layout.glossarylist, parent, false);

        // Return new holder instance
        glossaryForbsHolder glossaryHolder = new glossaryForbsHolder(glossaryView, parent.getContext());
        return glossaryHolder;
    }

    @Override
    public void onBindViewHolder(glossaryForbsAdapter.glossaryForbsHolder mglossaryHolder, int position) {
        glossaryDetails thisGlossary = mglossaryDetails.get(position);

        ImageView glossaryThumbnail = mglossaryHolder.glossaryThumbnail;
        TextView glossary_nameText = mglossaryHolder.glossarynameText;

        // Using Glide library for image loading.
        String pictureFile = thisGlossary.getGlossary_imagename() + ".png";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/glossaryphotos/forbs_small_keyed/" + pictureFile)).into(glossaryThumbnail);

        glossary_nameText.setText(thisGlossary.getGlossary_name());
    }

    @Override
    public int getItemCount() {
        return mglossaryDetails.size();
    }

    public static glossaryDetails getItem(int position) {
        return mglossaryDetails.get(position);
    }

}

