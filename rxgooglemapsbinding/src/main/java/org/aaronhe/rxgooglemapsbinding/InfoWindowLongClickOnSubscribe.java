package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class InfoWindowLongClickOnSubscribe implements Observable.OnSubscribe<Marker> {
  final GoogleMap googleMap;

  InfoWindowLongClickOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Marker> subscriber) {
    GoogleMap.OnInfoWindowLongClickListener listener =
        new GoogleMap.OnInfoWindowLongClickListener() {
          @Override public void onInfoWindowLongClick(Marker marker) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(marker);
            }
          }
        };

    googleMap.setOnInfoWindowLongClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnInfoWindowLongClickListener(null);
      }
    }));
  }
}
