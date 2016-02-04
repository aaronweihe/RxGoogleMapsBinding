package org.aaronhe.rxgooglemapsbinding;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import static org.aaronhe.rxgooglemapsbinding.Preconditions.checkNotNull;

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
  @CheckResult @NonNull
  public static Observable<GoogleMap> mapReady(@NonNull MapView mapView) {
    checkNotNull(mapView, "mapView == null");
    return Observable.create(new MapViewMapReadyOnSubscribe(mapView));
  }

  /**
   * Create an observable which emits when {@link GoogleMap} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code mapFragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<GoogleMap> mapReady(@NonNull MapFragment mapFragment) {
    checkNotNull(mapFragment, "mapFragment == null");
    return Observable.create(new MapFragmentMapReadyOnSubscribe(mapFragment));
  }

  /**
   * Create an observable which emits when {@link GoogleMap} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code mapFragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<GoogleMap> mapReady(@NonNull SupportMapFragment mapFragment) {
    checkNotNull(mapFragment, "mapFragment == null");
    return Observable.create(new SupportMapFragmentReadyOnSubscribe(mapFragment));
  }

  /**
   * Create an observable which emits on {@code map} camera position change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<CameraPosition> cameraPositionChanges(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new CameraPositionChangeOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} ground overlay click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<GroundOverlay> groundOverlayClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new GroundOverlayClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Marker> infoWindowClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new InfoWindowClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window close events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Marker> infoWindowCloses(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new InfoWindowCloseOnSubscribe(map));
  }

  /**
   * Create an observable which emits on a {@code map} marker's info window long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Marker> infoWindowLongClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new InfoWindowLongClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<LatLng> clicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new MapClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<LatLng> longClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
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
  @CheckResult @NonNull
  public static Observable<Marker> markerClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
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
  @CheckResult @NonNull
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
  @CheckResult @NonNull
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
  @CheckResult @NonNull
  @Deprecated public static Observable<Location> myLocationChanges(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new MyLocationChangeOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} polygon click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Polygon> polygonClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new PolygonClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} polyline click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Polyline> polylineClicks(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
    return Observable.create(new PolyLineClickOnSubscribe(map));
  }

  /**
   * Create an observable which emits on {@code map} snapshot taken events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Bitmap> snapShotReady(@NonNull GoogleMap map) {
    checkNotNull(map, "map == null");
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
  @CheckResult @NonNull
  public static Observable<Bitmap> snapShotReady(@NonNull GoogleMap map, @Nullable Bitmap bitmap) {
    checkNotNull(map, "map == null");
    return Observable.create(new SnapshotReadyOnSubscribe(map, bitmap));
  }

  /**
   * Create an observable which emits on {@code source} location change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code source}.
   * Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<Location> locationSourceLocationChanges(@NonNull LocationSource source) {
    checkNotNull(source, "source == null");
    return Observable.create(new LocationChangeOnSubscribe(source));
  }

  private RxGoogleMaps() {
    throw new AssertionError("No instances.");
  }
}
