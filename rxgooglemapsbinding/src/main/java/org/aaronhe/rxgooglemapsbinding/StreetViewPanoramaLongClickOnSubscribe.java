package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class StreetViewPanoramaLongClickOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanoramaOrientation> {
  final StreetViewPanorama streetViewPanorama;

  StreetViewPanoramaLongClickOnSubscribe(StreetViewPanorama streetViewPanorama) {
    this.streetViewPanorama = streetViewPanorama;
  }

  @Override public void call(final Subscriber<? super StreetViewPanoramaOrientation> subscriber) {
    MainThreadSubscription.verifyMainThread();

    StreetViewPanorama.OnStreetViewPanoramaLongClickListener listener =
        new StreetViewPanorama.OnStreetViewPanoramaLongClickListener() {
          @Override public void onStreetViewPanoramaLongClick(
              StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(streetViewPanoramaOrientation);
            }
          }
        };

    streetViewPanorama.setOnStreetViewPanoramaLongClickListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        streetViewPanorama.setOnStreetViewPanoramaLongClickListener(null);
      }
    });
  }
}
