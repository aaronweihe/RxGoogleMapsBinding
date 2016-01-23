import com.google.android.gms.maps.StreetViewPanoramaView
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.StreetViewPanoramaFragment
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.StreetViewPanoramaCamera
import com.google.android.gms.maps.model.StreetViewPanoramaLocation
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation
import org.aaronhe.rxgooglemapsbinding.RxStreetViewPanorama
import rx.Observable

/**
 * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to {@code streetViewPanoramaView}.
 * Unsubscribe to free this reference.
 */
public inline fun StreetViewPanoramaView.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

/**
 * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to {@code fragment}. Unsubscribe to
 * free this reference.
 */
public inline fun StreetViewPanoramaFragment.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

/**
 * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
 *
 * *Warning:* The created observable keeps a strong reference to {@code fragment}. Unsubscribe to
 * free this reference.
 */
public inline fun SupportStreetViewPanoramaFragment.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

/**
 * Create an observable which emits {@code streetViewPanorama} camera position change events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code streetViewPanorama}.
 * Unsubscribe to free this reference.
 */
public inline fun StreetViewPanorama.streetViewPanoramaCameraChanges(): Observable<StreetViewPanoramaCamera> =
    RxStreetViewPanorama.streetViewPanoramaCameraChanges(this)

/**
 * Create an observable which emits on {@code streetViewPanorama} panorama change events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code streetViewPanorama}.
 * Unsubscribe to free this reference.
 */
public inline fun StreetViewPanorama.streetViewPanoramaChanges(): Observable<StreetViewPanoramaLocation> =
    RxStreetViewPanorama.streetViewPanoramaChanges(this)

/**
 * Create an observable which emits on {@code streetViewPanorama} click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code streetViewPanorama}.
 * Unsubscribe to free this reference.
 */
public inline fun StreetViewPanorama.streetViewPanoramaClicks(): Observable<StreetViewPanoramaOrientation> =
    RxStreetViewPanorama.streetViewPanoramaClicks(this)

/**
 * Create an observable which emits on {@code streetViewPanorama} long-click events.
 *
 * *Warning:* The created observable keeps a strong reference to {@code streetViewPanorama}.
 * Unsubscribe to free this reference.
 */
public inline fun StreetViewPanorama.streetViewPanoramaLongClicks(): Observable<StreetViewPanoramaOrientation> =
    RxStreetViewPanorama.streetViewPanoramaLongClicks(this)
