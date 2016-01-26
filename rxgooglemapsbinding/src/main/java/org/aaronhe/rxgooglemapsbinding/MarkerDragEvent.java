package org.aaronhe.rxgooglemapsbinding;

import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.Marker;

public final class MarkerDragEvent {
  public enum Kind {
    START, DRAG, END
  }

  private final Kind kind;
  private final Marker marker;

  public static MarkerDragEvent create(@NonNull Marker marker, @NonNull Kind kind) {
    return new MarkerDragEvent(marker, kind);
  }

  private MarkerDragEvent(@NonNull Marker marker, @NonNull Kind kind) {
    this.marker = marker;
    this.kind = kind;
  }

  @NonNull public Kind getKind() {
    return kind;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MarkerDragEvent that = (MarkerDragEvent) o;

    if (kind != that.kind) return false;
    return marker.equals(that.marker);
  }

  @Override public int hashCode() {
    int result = kind.hashCode();
    result = 31 * result + marker.hashCode();
    return result;
  }

  @Override public String toString() {
    return "MarkerDragEvent{" +
        "kind=" + kind +
        ", marker=" + marker +
        '}';
  }
}
