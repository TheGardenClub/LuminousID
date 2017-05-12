package com.luminousid.luminousid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chase on 4/1/2017.
 * Used to create a dividing line between the view of plants in Field Guide.
 * Code pulled from http://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
 */

public class plantDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable divider;

    // Use default divider
    public plantDividerItemDecoration(Context context){
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    // Use custom divider
    public plantDividerItemDecoration(Context context, int resId){
        divider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void onDraw(Canvas mCanvas, RecyclerView parent, RecyclerView.State mState){
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(mCanvas);
        }
    }
}
