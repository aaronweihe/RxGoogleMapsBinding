package org.aaronhe.rxgooglemapsbinding;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class MyLocationChangeOnSubscribe implements Observable.OnSubscribe<Location> {
  final GoogleMap googleMap;

  MyLocationChangeOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Location> subscriber) {
    GoogleMap.OnMyLocationChangeListener listener = new GoogleMap.OnMyLocationChangeListener() {
      @Override public void onMyLocationChange(Location location) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(location);
        }
      }
    };

    googleMap.setOnMyLocationChangeListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnMyLocationChangeListener(null);
      }
    }));
  }
}
