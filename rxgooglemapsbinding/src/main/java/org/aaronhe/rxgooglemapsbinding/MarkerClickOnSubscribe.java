package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

final class MarkerClickOnSubscribe implements Observable.OnSubscribe<Marker> {
  final GoogleMap googleMap;
  final Func1<? super Marker, Boolean> handled;

  MarkerClickOnSubscribe(GoogleMap googleMap, Func1<? super Marker, Boolean> handled) {
    this.googleMap = googleMap;
    this.handled = handled;
  }

  @Override public void call(final Subscriber<? super Marker> subscriber) {
    GoogleMap.OnMarkerClickListener listener = new GoogleMap.OnMarkerClickListener() {
      @Override public boolean onMarkerClick(Marker marker) {
        if (handled.call(marker)) {
          if (subscriber.isUnsubscribed()) {
            subscriber.onNext(marker);
          }
          return true;
        }
        return false;
      }
    };

    googleMap.setOnMarkerClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnMarkerClickListener(null);
      }
    }));
  }
}
