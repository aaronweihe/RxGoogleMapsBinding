package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class MapFragmentMapReadyOnSubscribe implements Observable.OnSubscribe<GoogleMap> {
  final MapFragment fragment;

  MapFragmentMapReadyOnSubscribe(MapFragment fragment) {
    this.fragment = fragment;
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

    fragment.getMapAsync(callback);
  }
}
