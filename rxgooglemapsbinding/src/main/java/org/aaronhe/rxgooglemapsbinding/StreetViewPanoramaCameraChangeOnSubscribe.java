package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class StreetViewPanoramaCameraChangeOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanoramaCamera> {
  final StreetViewPanorama streetViewPanorama;

  StreetViewPanoramaCameraChangeOnSubscribe(StreetViewPanorama streetViewPanorama) {
    this.streetViewPanorama = streetViewPanorama;
  }

  @Override public void call(final Subscriber<? super StreetViewPanoramaCamera> subscriber) {
    MainThreadSubscription.verifyMainThread();

    StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener listener =
        new StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener() {
          @Override public void onStreetViewPanoramaCameraChange(
              StreetViewPanoramaCamera streetViewPanoramaCamera) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(streetViewPanoramaCamera);
            }
          }
        };

    streetViewPanorama.setOnStreetViewPanoramaCameraChangeListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        streetViewPanorama.setOnStreetViewPanoramaCameraChangeListener(null);
      }
    });
  }
}
