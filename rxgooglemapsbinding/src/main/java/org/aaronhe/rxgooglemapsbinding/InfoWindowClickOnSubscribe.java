package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class InfoWindowClickOnSubscribe implements Observable.OnSubscribe<Marker> {
  final GoogleMap googleMap;

  InfoWindowClickOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Marker> subscriber) {
    GoogleMap.OnInfoWindowClickListener listener = new GoogleMap.OnInfoWindowClickListener() {
      @Override public void onInfoWindowClick(Marker marker) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(marker);
        }
      }
    };

    googleMap.setOnInfoWindowClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnInfoWindowClickListener(null);
      }
    }));
  }
}
