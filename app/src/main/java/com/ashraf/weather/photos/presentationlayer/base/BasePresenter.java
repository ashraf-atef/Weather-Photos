package com.ashraf.weather.photos.presentationlayer.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BaseView> extends BasePresenterDependency {

    protected final V view;

    private CompositeDisposable disposables = new CompositeDisposable();

    protected BasePresenter(V view) {
        this.view = view;
    }

    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public abstract void start() ;

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public void stop() {
        disposables.clear();
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
