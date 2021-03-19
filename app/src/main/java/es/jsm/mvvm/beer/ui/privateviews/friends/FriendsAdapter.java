package es.jsm.mvvm.beer.ui.privateviews.friends;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.model.Friend;


public class FriendsAdapter extends BaseRecyclerAdapter<Friend, FriendsViewHolder, Friend> {

    FriendActionsListener listener;

    public FriendsAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource,  FriendActionsListener listener) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
        this.listener = listener;
    }

    @Override
    public FriendsViewHolder instanceViewHolder(View v) {
        return new FriendsViewHolder(v, listener);
    }
}
