import com.google.android.gms.maps.StreetViewPanoramaView
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.StreetViewPanoramaFragment
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.StreetViewPanoramaCamera
import com.google.android.gms.maps.model.StreetViewPanoramaLocation
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation
import org.aaronhe.rxgooglemapsbinding.RxStreetViewPanorama
import rx.Observable

public inline fun StreetViewPanoramaView.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

public inline fun StreetViewPanoramaFragment.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

public inline fun SupportStreetViewPanoramaFragment.streetViewPanoramaReady(): Observable<StreetViewPanorama> =
    RxStreetViewPanorama.streetViewPanoramaReady(this)

public inline fun StreetViewPanorama.streetViewPanoramaCameraChanges(): Observable<StreetViewPanoramaCamera> =
    RxStreetViewPanorama.streetViewPanoramaCameraChanges(this)

public inline fun StreetViewPanorama.streetViewPanoramaChanges(): Observable<StreetViewPanoramaLocation> =
    RxStreetViewPanorama.streetViewPanoramaChanges(this)

public inline fun StreetViewPanorama.streetViewPanoramaClicks(): Observable<StreetViewPanoramaOrientation> =
    RxStreetViewPanorama.streetViewPanoramaClicks(this)

public inline fun StreetViewPanorama.streetViewPanoramaLongClicks(): Observable<StreetViewPanoramaOrientation> =
    RxStreetViewPanorama.streetViewPanoramaLongClicks(this)
