package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import rx.Observable;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * StreetViewPanorama}.
 */
public final class RxStreetViewPanorama {

  /**
   * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanoramaView}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      StreetViewPanoramaView streetViewPanoramaView) {
    return Observable.create(
        new StreetViewPanoramaViewPanoramaReadyOnSubscribe(streetViewPanoramaView));
  }

  /**
   * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code fragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      StreetViewPanoramaFragment fragment) {
    return Observable.create(new StreetViewPanoramaFragmentPanoramaReadyOnSubscribe(fragment));
  }

  /**
   * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code fragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      SupportStreetViewPanoramaFragment fragment) {
    return Observable.create(
        new StreetViewPanoramaSupportFragmentPanoramaReadyOnSubscribe(fragment));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} camera position change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanoramaCamera> streetViewPanoramaCameraChanges(
      StreetViewPanorama streetViewPanorama) {
    return Observable.create(new StreetViewPanoramaCameraChangeOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} panorama change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanoramaLocation> streetViewPanoramaChanges(
      StreetViewPanorama streetViewPanorama) {
    return Observable.create(new StreetViewPanoramaChangeOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanoramaOrientation> streetViewPanoramaClicks(
      StreetViewPanorama streetViewPanorama) {
    return Observable.create(new StreetViewPanoramaClickOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  public static Observable<StreetViewPanoramaOrientation> streetViewPanoramaLongClicks(
      StreetViewPanorama streetViewPanorama) {
    return Observable.create(new StreetViewPanoramaLongClickOnSubscribe(streetViewPanorama));
  }

  private RxStreetViewPanorama() {
    throw new AssertionError("No instances.");
  }
}
