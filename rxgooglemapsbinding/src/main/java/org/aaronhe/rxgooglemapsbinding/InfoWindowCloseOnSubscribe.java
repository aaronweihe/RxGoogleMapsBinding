package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class InfoWindowCloseOnSubscribe implements Observable.OnSubscribe<Marker> {
  final GoogleMap googleMap;

  InfoWindowCloseOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Marker> subscriber) {
    GoogleMap.OnInfoWindowCloseListener listener = new GoogleMap.OnInfoWindowCloseListener() {
      @Override public void onInfoWindowClose(Marker marker) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(marker);
        }
      }
    };

    googleMap.setOnInfoWindowCloseListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnInfoWindowCloseListener(null);
      }
    }));
  }
}
