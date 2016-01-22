package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class MapViewMapReadyOnSubscribe implements Observable.OnSubscribe<GoogleMap> {
  final MapView mapView;

  MapViewMapReadyOnSubscribe(MapView mapView) {
    this.mapView = mapView;
  }

  @Override public void call(final Subscriber<? super GoogleMap> subscriber) {
    MainThreadSubscription.verifyMainThread();

    OnMapReadyCallback callback = new OnMapReadyCallback() {
      @Override public void onMapReady(GoogleMap googleMap) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(googleMap);
        }
      }
    };

    mapView.getMapAsync(callback);
  }
}
