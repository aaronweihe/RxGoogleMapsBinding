package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

final class MarkerDragOnSubscribe implements Observable.OnSubscribe<MarkerDragEvent> {
  final GoogleMap googleMap;

  MarkerDragOnSubscribe(GoogleMap googleMap) {
    this.googleMap = googleMap;
  }

  @Override public void call(final Subscriber<? super MarkerDragEvent> subscriber) {
    GoogleMap.OnMarkerDragListener listener = new GoogleMap.OnMarkerDragListener() {
      @Override public void onMarkerDragStart(Marker marker) {
        onEvent(MarkerDragEvent.create(marker, MarkerDragEvent.Kind.START));
      }

      @Override public void onMarkerDrag(Marker marker) {
        onEvent(MarkerDragEvent.create(marker, MarkerDragEvent.Kind.DRAG));
      }

      @Override public void onMarkerDragEnd(Marker marker) {
        onEvent(MarkerDragEvent.create(marker, MarkerDragEvent.Kind.END));
      }

      private void onEvent(MarkerDragEvent event) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(event);
        }
      }
    };

    googleMap.setOnMarkerDragListener(listener);

    subscriber.add(Subscriptions.create(new Action0() {
      @Override public void call() {
        googleMap.setOnMarkerDragListener(null);
      }
    }));
  }
}
