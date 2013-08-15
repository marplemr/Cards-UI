package com.afollestad.cardsui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.afollestad.silk.cache.SilkComparable;

/**
 * Represents a single card displayed in a {@link CardAdapter}.
 *
 * @author Aidan Follestad (afollestad)
 */
public class Card implements SilkComparable<Card> {

    protected Card() {
    }

    protected Card(String title, String subtitle, boolean isHeader) {
        this(title, subtitle);
        this.isHeader = isHeader;
    }

    public Card(String title, String content) {
        this.isClickable = true;
        this.title = title;
        this.content = content;
    }

    public Card(Context context, String title, int contentRes) {
        this(title, context.getString(contentRes));
    }

    public Card(Context context, int titleRes, String content) {
        this(context.getString(titleRes), content);
    }

    public Card(Context context, int titleRes, int contentRes) {
        this(context.getString(titleRes), context.getString(contentRes));
    }

    public interface CardMenuListener {
        public void onMenuItemClick(Card card, MenuItem item);
    }

    private String title;
    private String content;
    private boolean isHeader;
    private int mPopupMenu;
    private CardMenuListener mPopupListener;
    private boolean isClickable;
    private Object mTag;
    private Drawable mThumbnail;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public Object getTag() {
        return mTag;
    }

    public int getPopupMenu() {
        return mPopupMenu;
    }

    public CardMenuListener getPopupListener() {
        return mPopupListener;
    }

    public Drawable getThumbnail() {
        return mThumbnail;
    }

    /**
     * Sets an optional thumbnail drawable that's displayed on the left side of the card.
     *
     * @param drawable The drawable to be displayed as a thumbnail.
     */
    public Card setThumbnail(Drawable drawable) {
        mThumbnail = drawable;
        return this;
    }

    /**
     * Sets an optional thumbnail image resource that's displayed on the left side of the card.
     *
     * @param context The context used to resolve the drawable resource ID.
     * @param resId   The resource ID of the drawable to use as a thumbnail.
     */
    public Card setThumbnail(Context context, int resId) {
        setThumbnail(context.getResources().getDrawable(resId));
        return this;
    }

    /**
     * Sets an optional thumbnail image that's displayed on the left side of the card.
     *
     * @param context The context used to convert the Bitmap to a Drawable.
     * @param bitmap  The bitmap thumbnail to be displayed.
     */
    public Card setThumbnail(Context context, Bitmap bitmap) {
        setThumbnail(new BitmapDrawable(context.getResources(), bitmap));
        return this;
    }

    /**
     * Sets whether or not the card is clickable, setting it to false will turn the card's list selector off
     * and the list's OnItemClickListener will not be called.
     * <p/>
     * This <b>does not</b> override the isClickable value set to a {@link CardAdapter}, however.
     */
    public Card setClickable(boolean clickable) {
        isClickable = clickable;
        return this;
    }

    /**
     * Sets a tag of any type that can be used to keep track of cards.
     */
    public Card setTag(Object tag) {
        mTag = tag;
        return this;
    }

    /**
     * Sets a unique popup menu for the card, this will override any popup menu set for the {@link CardAdapter}
     * that the card is displayed in.
     *
     * @param menuRes  The menu resource ID to use for the card's popup menu.
     * @param listener A listener invoked when an option in the popup menu is tapped by the user.
     */
    public Card setPopupMenu(int menuRes, CardMenuListener listener) {
        mPopupMenu = menuRes;
        mPopupListener = listener;
        return this;
    }


    @Override
    public boolean isSameAs(Card another) {
        boolean equal = getTitle().equals(another.getTitle()) &&
                isHeader() == another.isHeader();
        if (getContent() != null) equal = equal && getContent().equals(another.getContent());
        return equal;
    }

    @Override
    public boolean shouldIgnore() {
        return isHeader;
    }
}
