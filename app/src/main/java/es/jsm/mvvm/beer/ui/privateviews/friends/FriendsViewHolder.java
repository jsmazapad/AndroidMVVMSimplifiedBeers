package es.jsm.mvvm.beer.ui.privateviews.friends;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewHolder;
import es.jsm.mvvm.beer.model.Friend;


public class FriendsViewHolder extends BaseRecyclerViewHolder<Friend> {

    private final TextView nameTV;
    private final ImageView deleteIV;
    private final ImageView callIV;
    FriendActionsListener listener;

    protected FriendsViewHolder(View v, FriendActionsListener listener) {
        super(v);

        nameTV = v.findViewById(R.id.nameTV);
        deleteIV = v.findViewById(R.id.deleteIV);
        callIV = v.findViewById(R.id.callIV);
        this.listener = listener;
    }

    @Override
    public void fillViewHolder(Friend friend) {
        nameTV.setText(friend.getName());
        deleteIV.setOnClickListener(v -> listener.deleteFriend(callIV.getContext(), friend));
        callIV.setOnClickListener(v -> listener.callFriend(callIV.getContext(), friend));
    }

}
