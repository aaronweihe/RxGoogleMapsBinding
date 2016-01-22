import android.graphics.Bitmap
import android.location.Location
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.GroundOverlay
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.Polyline
import org.aaronhe.rxgooglemapsbinding.RxGoogleMaps
import rx.Observable

/**
 * Create an observable which emits when `GoogleMap` is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to `mapView`. Unsubscribe to free this
 * reference.
 */
public inline fun MapView.mapReady(): Observable<GoogleMap> = RxGoogleMaps.mapReady(this)

/**
 * Create an observable which emits when `GoogleMap` is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to `mapFragment`. Unsubscribe to free
 * this reference.
 */
public inline fun MapFragment.mapReady(): Observable<GoogleMap> = RxGoogleMaps.mapReady(this)

/**
 * Create an observable which emits when `GoogleMap` is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to `mapFragment`. Unsubscribe to free
 * this reference.
 */
public inline fun SupportMapFragment.mapReady(): Observable<GoogleMap> = RxGoogleMaps.mapReady(this)

/**
 * Create an observable which emits on {@code map} camera position change events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.cameraPositionChanges(): Observable<CameraPosition> =
    RxGoogleMaps.cameraPositionChanges(this)

/**
 * Create an observable which emits on {@code map} ground overlay click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.groundOverLayClicks(): Observable<GroundOverlay> =
    RxGoogleMaps.groundOverlayClicks(this)

/**
 * Create an observable which emits on a {@code map} marker's info window click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.infoWindowClicks(): Observable<Marker> =
    RxGoogleMaps.infoWindowClicks(this)

/**
 * Create an observable which emits on a {@code map} marker's info window close events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.infoWindowCloses(): Observable<Marker> =
    RxGoogleMaps.infoWindowCloses(this)

/**
 * Create an observable which emits on a {@code map} marker's info window long-click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.infoWindowLongClicks(): Observable<Marker> =
    RxGoogleMaps.infoWindowLongClicks(this)

/**
 * Create an observable which emits on {@code map} click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.clicks(): Observable<LatLng> = RxGoogleMaps.clicks(this)

/**
 * Create an observable which emits on {@code map} long-click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.longClicks(): Observable<LatLng> = RxGoogleMaps.longClicks(this)

/**
 * Create an observable which emits when My Location changes on a {@code map}.
 *
 * *Warning:* This is deprecated. Use use com.google.android.gms.location.FusedLocationProviderApi
 * instead. See [GoogleMap.OnMyLocationChangeListener](https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.OnMyLocationChangeListener)
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.myLocationChanges(): Observable<Location> =
    RxGoogleMaps.myLocationChanges(this)

/**
 * Create an observable which emits on {@code map} polygon click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.polygonClicks(): Observable<Polygon> = RxGoogleMaps.polygonClicks(this)

/**
 * Create an observable which emits on {@code map} polyline click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.polylineClicks(): Observable<Polyline> =
    RxGoogleMaps.polylineClicks(this)

/**
 * Create an observable which emits on {@code map} snapshot taken events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.snapShotReady(): Observable<Bitmap> = RxGoogleMaps.snapShotReady(this)

/**
 * Create an observable which emits on {@code map} snapshot taken events.
 * This method is equivalent to {@link GoogleMap#snapShotReady(GoogleMap)} but lets you provide a
 * preallocated {@code bitmap}.
 * See [GoogleMap#snapshot(GoogleMap, Bitmap)](https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap#snapshot(com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback,android.graphics.Bitmap)).
 *
 * *Warning:* The created observable keeps a strong reference to {@code map}. Unsubscribe
 * to free this reference.
 */
public inline fun GoogleMap.snapshotReady(bitmap: Bitmap): Observable<Bitmap> =
    RxGoogleMaps.snapShotReady(this, bitmap)

/**
 * Create an observable which emits on {@code map} location change events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code source}. Unsubscribe
 * to free this reference.
 */
public inline fun LocationSource.locationSourceLocationChanges(): Observable<Location> =
    RxGoogleMaps.locationSourceLocationChanges(this)
