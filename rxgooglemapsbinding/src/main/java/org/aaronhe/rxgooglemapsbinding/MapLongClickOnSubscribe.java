package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class MapLongClickOnSubscribe implements Observable.OnSubscribe<LatLng> {
  final GoogleMap map;

  MapLongClickOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super LatLng> subscriber) {
    MainThreadSubscription.verifyMainThread();

    GoogleMap.OnMapLongClickListener listener = new GoogleMap.OnMapLongClickListener() {
      @Override public void onMapLongClick(LatLng latLng) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(latLng);
        }
      }
    };

    map.setOnMapLongClickListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnMapLongClickListener(null);
      }
    });
  }
}
