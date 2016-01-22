package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class MapClickOnSubscribe implements Observable.OnSubscribe<LatLng> {
  final GoogleMap map;

  MapClickOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super LatLng> subscriber) {
    MainThreadSubscription.verifyMainThread();

    GoogleMap.OnMapClickListener listener = new GoogleMap.OnMapClickListener() {
      @Override public void onMapClick(LatLng latLng) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(latLng);
        }
      }
    };

    map.setOnMapClickListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnMapClickListener(null);
      }
    });
  }
}
