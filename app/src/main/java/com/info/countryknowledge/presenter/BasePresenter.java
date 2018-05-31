package com.info.countryknowledge.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Sravanthi_B01 on 5/31/2018.
 */

public abstract class BasePresenter<V> {
    private WeakReference<V> view;


    public V getView() {
        return view.get();
    }

    public void setV(V view) {
        this.view = new WeakReference<>(view);
    }
}
