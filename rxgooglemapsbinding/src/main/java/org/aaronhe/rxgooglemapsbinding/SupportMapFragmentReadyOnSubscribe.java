package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class SupportMapFragmentReadyOnSubscribe implements Observable.OnSubscribe<GoogleMap> {
  private final SupportMapFragment mapFragment;

  SupportMapFragmentReadyOnSubscribe(SupportMapFragment mapFragment) {
    this.mapFragment = mapFragment;
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

    mapFragment.getMapAsync(callback);
  }
}
