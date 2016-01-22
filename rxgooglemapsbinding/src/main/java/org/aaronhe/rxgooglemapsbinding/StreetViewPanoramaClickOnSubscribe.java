package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class StreetViewPanoramaClickOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanoramaOrientation> {
  final StreetViewPanorama streetViewPanorama;

  StreetViewPanoramaClickOnSubscribe(StreetViewPanorama streetViewPanorama) {
    this.streetViewPanorama = streetViewPanorama;
  }

  @Override public void call(final Subscriber<? super StreetViewPanoramaOrientation> subscriber) {
    MainThreadSubscription.verifyMainThread();

    StreetViewPanorama.OnStreetViewPanoramaClickListener listener =
        new StreetViewPanorama.OnStreetViewPanoramaClickListener() {
          @Override public void onStreetViewPanoramaClick(
              StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(streetViewPanoramaOrientation);
            }
          }
        };

    streetViewPanorama.setOnStreetViewPanoramaClickListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        streetViewPanorama.setOnStreetViewPanoramaClickListener(null);
      }
    });
  }
}
