package org.aaronhe.rxgooglemapsbinding;

import android.graphics.Bitmap;
import com.google.android.gms.maps.GoogleMap;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class SnapshotReadyOnSubscribe implements Observable.OnSubscribe<Bitmap> {
  final GoogleMap googleMap;
  final Bitmap bitmap;

  SnapshotReadyOnSubscribe(GoogleMap googleMap, Bitmap bitmap) {
    this.googleMap = googleMap;
    this.bitmap = bitmap;
  }

  @Override public void call(final Subscriber<? super Bitmap> subscriber) {
    GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
      @Override public void onSnapshotReady(Bitmap bitmap) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(bitmap);
        }
      }
    };

    // Even the bitmap was uninitialized, it behaves like snapshot(SnapshotReadyCallback).
    googleMap.snapshot(callback, bitmap);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.snapshot(null);
      }
    }));
  }
}
