package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

final class CameraPositionChangeOnSubscribe implements Observable.OnSubscribe<CameraPosition> {
  final GoogleMap map;

  CameraPositionChangeOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super CameraPosition> subscriber) {
    MainThreadSubscription.verifyMainThread();

    GoogleMap.OnCameraChangeListener listener = new GoogleMap.OnCameraChangeListener() {
      @Override public void onCameraChange(CameraPosition cameraPosition) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(cameraPosition);
        }
      }
    };
    map.setOnCameraChangeListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnCameraChangeListener(null);
      }
    });
  }
}
