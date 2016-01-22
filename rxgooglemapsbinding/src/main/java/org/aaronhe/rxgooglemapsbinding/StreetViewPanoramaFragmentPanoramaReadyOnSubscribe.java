package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import rx.Observable;
import rx.Subscriber;

final class StreetViewPanoramaFragmentPanoramaReadyOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanorama> {
  final StreetViewPanoramaFragment fragment;

  StreetViewPanoramaFragmentPanoramaReadyOnSubscribe(StreetViewPanoramaFragment fragment) {
    this.fragment = fragment;
  }

  @Override public void call(final Subscriber<? super StreetViewPanorama> subscriber) {
    OnStreetViewPanoramaReadyCallback callback = new OnStreetViewPanoramaReadyCallback() {
      @Override public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(streetViewPanorama);
        }
      }
    };

    fragment.getStreetViewPanoramaAsync(callback);
  }
}
