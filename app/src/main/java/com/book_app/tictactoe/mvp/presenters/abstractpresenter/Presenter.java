package com.book_app.tictactoe.mvp.presenters.abstractpresenter;


import java.lang.ref.WeakReference;

public abstract class Presenter<M,  V> {

    protected M model;

    private WeakReference<V> view;

    public void setModel(M model) { /*TODO:Модель должна составлять бизнес логика, данный метод временный*/
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    public void resetState() {
    }

    public void bindView(V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    public V view() {

         return view == null ? null : view.get();
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null && model != null;
    }
}
