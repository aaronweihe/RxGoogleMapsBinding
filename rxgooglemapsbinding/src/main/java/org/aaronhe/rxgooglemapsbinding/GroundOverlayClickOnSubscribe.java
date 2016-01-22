package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class GroundOverlayClickOnSubscribe implements Observable.OnSubscribe<GroundOverlay> {
  final GoogleMap map;

  GroundOverlayClickOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super GroundOverlay> subscriber) {
    GoogleMap.OnGroundOverlayClickListener listener = new GoogleMap.OnGroundOverlayClickListener() {
      @Override public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(groundOverlay);
        }
      }
    };

    map.setOnGroundOverlayClickListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        map.setOnGroundOverlayClickListener(null);
      }
    }));
  }
}
