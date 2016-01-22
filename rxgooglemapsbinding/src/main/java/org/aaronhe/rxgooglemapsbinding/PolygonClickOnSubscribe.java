package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class PolygonClickOnSubscribe implements Observable.OnSubscribe<Polygon> {
  final GoogleMap googleMap;

  PolygonClickOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Polygon> subscriber) {
    GoogleMap.OnPolygonClickListener listener = new GoogleMap.OnPolygonClickListener() {
      @Override public void onPolygonClick(Polygon polygon) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(polygon);
        }
      }
    };

    googleMap.setOnPolygonClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnPolygonClickListener(null);
      }
    }));
  }
}
