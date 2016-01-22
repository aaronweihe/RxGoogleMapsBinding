package org.aaronhe.rxgooglemapsbinding;

import android.location.Location;
import com.google.android.gms.maps.LocationSource;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class LocationChangeOnSubscribe implements Observable.OnSubscribe<Location> {
  final LocationSource locationSource;

  LocationChangeOnSubscribe(LocationSource locationSource) {
    this.locationSource = locationSource;
  }

  @Override public void call(final Subscriber<? super Location> subscriber) {
    MainThreadSubscription.verifyMainThread();

    LocationSource.OnLocationChangedListener listener =
        new LocationSource.OnLocationChangedListener() {
          @Override public void onLocationChanged(Location location) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(location);
            }
          }
        };

    locationSource.activate(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        locationSource.deactivate();
      }
    });
  }
}
