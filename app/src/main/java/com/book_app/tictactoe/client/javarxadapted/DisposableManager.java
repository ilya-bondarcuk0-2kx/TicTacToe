package com.book_app.tictactoe.client.javarxadapted;


import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class DisposableManager {

    private static CompositeDisposable disposables = new CompositeDisposable();

    public static void add(Disposable disposable){
        disposables.add(disposable);
    }

    public static void clear(){
        disposables.clear();
    }
}
