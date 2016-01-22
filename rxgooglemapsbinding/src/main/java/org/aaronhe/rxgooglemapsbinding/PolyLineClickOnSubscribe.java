package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class PolyLineClickOnSubscribe implements Observable.OnSubscribe<Polyline> {
  final GoogleMap googleMap;

  PolyLineClickOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super Polyline> subscriber) {
    GoogleMap.OnPolylineClickListener listener = new GoogleMap.OnPolylineClickListener() {
      @Override public void onPolylineClick(Polyline polyline) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(polyline);
        }
      }
    };

    googleMap.setOnPolylineClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnPolylineClickListener(null);
      }
    }));
  }
}
