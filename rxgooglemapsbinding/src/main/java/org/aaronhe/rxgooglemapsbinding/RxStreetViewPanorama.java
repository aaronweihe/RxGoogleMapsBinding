package org.aaronhe.rxgooglemapsbinding;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import rx.Observable;

import static org.aaronhe.rxgooglemapsbinding.Preconditions.checkNotNull;

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
  @CheckResult @NonNull
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      @NonNull StreetViewPanoramaView streetViewPanoramaView) {
    checkNotNull(streetViewPanoramaView, "streetViewPanoramaView == null");
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
  @CheckResult @NonNull
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      @NonNull StreetViewPanoramaFragment fragment) {
    checkNotNull(fragment, "fragment == null");
    return Observable.create(new StreetViewPanoramaFragmentPanoramaReadyOnSubscribe(fragment));
  }

  /**
   * Create an observable which emits when {@link StreetViewPanorama} is ready to use.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code fragment}.
   * Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<StreetViewPanorama> streetViewPanoramaReady(
      @NonNull SupportStreetViewPanoramaFragment fragment) {
    checkNotNull(fragment, "fragment == null");
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
  @CheckResult @NonNull
  public static Observable<StreetViewPanoramaCamera> streetViewPanoramaCameraChanges(
      @NonNull StreetViewPanorama streetViewPanorama) {
    checkNotNull(streetViewPanorama, "streetViewPanorama == null");
    return Observable.create(new StreetViewPanoramaCameraChangeOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} panorama change events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<StreetViewPanoramaLocation> streetViewPanoramaChanges(
      @NonNull StreetViewPanorama streetViewPanorama) {
    checkNotNull(streetViewPanorama, "streetViewPanorama == null");
    return Observable.create(new StreetViewPanoramaChangeOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<StreetViewPanoramaOrientation> streetViewPanoramaClicks(
      StreetViewPanorama streetViewPanorama) {
    checkNotNull(streetViewPanorama, "streetViewPanorama == null");
    return Observable.create(new StreetViewPanoramaClickOnSubscribe(streetViewPanorama));
  }

  /**
   * Create an observable which emits on {@code streetViewPanorama} long-click events.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * streetViewPanorama}. Unsubscribe to free this reference.
   * </p>
   */
  @CheckResult @NonNull
  public static Observable<StreetViewPanoramaOrientation> streetViewPanoramaLongClicks(
      @NonNull StreetViewPanorama streetViewPanorama) {
    checkNotNull(streetViewPanorama, "streetViewPanorama == null");
    return Observable.create(new StreetViewPanoramaLongClickOnSubscribe(streetViewPanorama));
  }

  private RxStreetViewPanorama() {
    throw new AssertionError("No instances.");
  }
}
