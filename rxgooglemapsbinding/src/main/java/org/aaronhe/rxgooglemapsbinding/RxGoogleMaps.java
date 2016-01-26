package org.aaronhe.rxgooglemapsbinding;

import android.graphics.Bitmap;
import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import rx.Observable;
import rx.functions.Func1;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link GoogleMap}.
 */
public final class RxGoogleMaps {

  /**
   * Create an observable which emits when {@link GoogleMap} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code mapView}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<GoogleMap> mapReady(MapView mapView) {
    return Observable.create(new MapViewMapReadyOnSubscribe(mapView));
  }

  /**
   * Create an observable which emits when {@link GoogleMap} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code mapFragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<GoogleMap> mapReady(MapFragment mapFragment) {
    return Observable.create(new MapFragmentMapReadyOnSubscribe(mapFragment));
  }

  /**
   * Create an observable which emits when {@link GoogleMap} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code mapFragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<GoogleMap> mapReady(SupportMapFragment mapFragment) {
    return Observable.create(new SupportMapFragmentReadyOnSubscribe(mapFragment));
  }

  /**
   * Create an observable which emits on {@code map} camera position change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<CameraPosition> cameraPositionChanges(GoogleMap map) {
    return Observable.create(new CameraPositionChangeOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} ground overlay click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<GroundOverlay> groundOverlayClicks(GoogleMap map) {
    return Observable.create(new GroundOverlayClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Marker> infoWindowClicks(GoogleMap map) {
    return Observable.create(new InfoWindowClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window close events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Marker> infoWindowCloses(GoogleMap map) {
    return Observable.create(new InfoWindowCloseOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Marker> infoWindowLongClicks(GoogleMap map) {
    return Observable.create(new InfoWindowLongClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<LatLng> clicks(GoogleMap map) {
    return Observable.create(new MapClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<LatLng> longClicks(GoogleMap map) {
    return Observable.create(new MapLongClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} marker click events.
   * It assumes the subscriber is going to consume the marker click event.
   * Otherwise, use {@link #markerClicks(GoogleMap, Func1)}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Marker> markerClicks(GoogleMap map) {
    return markerClicks(map, new Func1<Marker, Boolean>() {
      @Override public Boolean call(Marker marker) {
        return true;
      }
    });
  }

  /**
   * Create an observable which emits on {@code map} marker click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Marker> markerClicks(GoogleMap map,
      Func1<? super Marker, Boolean> handled) {
    return Observable.create(new MarkerClickOnSubscribe(map, handled));
  }

  /**
   * Create an observable which emits on {@code map} marker drag events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<MarkerDragEvent> markerDrags(GoogleMap map) {
    return Observable.create(new MarkerDragOnSubscribe(map));
  }

  /**
   * Create an observable which emits when My Location changes on a {@code map}.
   *
   * <p>
   * <em>Warning:</em> This is deprecated. Use use com.google.android.gms.location.FusedLocationProviderApi
   * instead. See <a href="https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.OnMyLocationChangeListener">GoogleMap.OnMyLocationChangeListener</a>
   * </p>
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @Deprecated public static Observable<Location> myLocationChanges(GoogleMap map) {
    return Observable.create(new MyLocationChangeOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} polygon click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Polygon> polygonClicks(GoogleMap map) {
    return Observable.create(new PolygonClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} polyline click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Polyline> polylineClicks(GoogleMap map) {
    return Observable.create(new PolyLineClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} snapshot taken events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  public static Observable<Bitmap> snapShotReady(GoogleMap map) {
    return snapShotReady(map, null);
  }

  /**
   * Create an observable which emits on {@code map} snapshot taken events.
   * This method is equivalent to {@link RxGoogleMaps#snapShotReady(GoogleMap)} but lets you
   * provide a preallocated {@code bitmap}.
   * See <a href="https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap#snapshot(com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback,android.graphics.Bitmap)">GoogleMap#snapshot(GoogleMap, Bitmap)</a>.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map} and
   * {@code bitmap}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<Bitmap> snapShotReady(GoogleMap map, Bitmap bitmap) {
    return Observable.create(new SnapshotReadyOnSubscribe(map, bitmap));
  }

  /**
   * Create an observable which emits on {@code source} location change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code source}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<Location> locationSourceLocationChanges(LocationSource source) {
    return Observable.create(new LocationChangeOnSubscribe(source));
  }

  private RxGoogleMaps() {
    throw new AssertionError("No instances.");
  }
}
