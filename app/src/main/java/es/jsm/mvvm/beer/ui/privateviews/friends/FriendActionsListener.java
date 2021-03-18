package es.jsm.mvvm.beer.ui.privateviews.friends;

import android.content.Context;

import es.jsm.mvvm.beer.model.Friend;

public interface FriendActionsListener {
    void callFriend(Context c, Friend friend);
    void deleteFriend(Context c, Friend friend);
}
