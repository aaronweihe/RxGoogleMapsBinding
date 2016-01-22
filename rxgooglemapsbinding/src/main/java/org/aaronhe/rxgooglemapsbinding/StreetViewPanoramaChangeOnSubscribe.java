package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class StreetViewPanoramaChangeOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanoramaLocation> {
  final StreetViewPanorama streetViewPanorama;

  StreetViewPanoramaChangeOnSubscribe(StreetViewPanorama streetViewPanorama) {
    this.streetViewPanorama = streetViewPanorama;
  }

  @Override public void call(final Subscriber<? super StreetViewPanoramaLocation> subscriber) {
    MainThreadSubscription.verifyMainThread();

    StreetViewPanorama.OnStreetViewPanoramaChangeListener listener =
        new StreetViewPanorama.OnStreetViewPanoramaChangeListener() {
          @Override public void onStreetViewPanoramaChange(
              StreetViewPanoramaLocation streetViewPanoramaLocation) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(streetViewPanoramaLocation);
            }
          }
        };

    streetViewPanorama.setOnStreetViewPanoramaChangeListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        streetViewPanorama.setOnStreetViewPanoramaChangeListener(null);
      }
    });
  }
}
