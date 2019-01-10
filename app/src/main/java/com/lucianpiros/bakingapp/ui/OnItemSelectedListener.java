package com.lucianpiros.bakingapp.ui;

/**
 * A callback interface that all activities containing this fragment must
 * implement. This mechanism allows activities to be notified of item
 * selections.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public interface OnItemSelectedListener {
    /**
     * When an item has been selected - notify up.
     */
    void onItemSelected(int itemIdx);
}