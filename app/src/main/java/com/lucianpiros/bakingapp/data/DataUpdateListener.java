package com.lucianpiros.bakingapp.data;

/**
 * Data update listener interface. Implemented by all fragments interested in
 * data changes.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public interface DataUpdateListener<ArrayType> {
    public void updateData(ArrayType array);
}
