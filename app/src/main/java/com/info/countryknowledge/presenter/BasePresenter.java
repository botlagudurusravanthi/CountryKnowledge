package com.info.countryknowledge.presenter;

import java.lang.ref.WeakReference;

/**
 * BasePresenter to get and set the view for the presenter.
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
