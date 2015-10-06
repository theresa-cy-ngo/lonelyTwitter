package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;


/**
 * Created by tcngo on 10/6/15.
 */

// Currently all controllers
public class MyObservable {

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer){
        observers.add(observer);

    }

    private void notifyAllObservers(){
        for (MyObserver observer: observers){
            observer.myNotify(this);
        }

    }
}
