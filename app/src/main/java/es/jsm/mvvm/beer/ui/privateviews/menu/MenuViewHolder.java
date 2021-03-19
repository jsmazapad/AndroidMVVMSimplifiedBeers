package es.jsm.mvvm.beer.ui.privateviews.menu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewHolder;

public class MenuViewHolder extends BaseRecyclerViewHolder<MenuElement> {

    private final TextView titleTV;
    private final ImageView imageIV;
    private final LinearLayout filledLl;


    protected MenuViewHolder(View v) {
        super(v);

        titleTV = v.findViewById(R.id.titleTV);
        imageIV = v.findViewById(R.id.imageTV);
        filledLl = v.findViewById(R.id.filledLl);
    }

    public void fillViewHolder(MenuElement element) {
        titleTV.setText(element.getName());
        imageIV.setImageResource(element.getImageResource());
        Context context = titleTV.getContext();
        filledLl.setBackgroundColor(ContextCompat.getColor(context, element.getColor()));
        LinearLayout.LayoutParams imageLayoutParams = (LinearLayout.LayoutParams) imageIV.getLayoutParams();


        if (element.getOrientation() == MenuElement.ElementGravity.LEFT) {
            titleTV.setGravity(Gravity.START);
            imageLayoutParams.gravity = Gravity.START;


        } else if (element.getOrientation() == MenuElement.ElementGravity.RIGHT) {
            titleTV.setGravity(Gravity.END);
            imageLayoutParams.gravity = Gravity.END;
        } else {
            titleTV.setGravity(Gravity.LEFT);
            imageLayoutParams.gravity = Gravity.START;
        }
        imageIV.setLayoutParams(imageLayoutParams);

    }

}
