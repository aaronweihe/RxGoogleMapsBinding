package org.aaronhe.rxgooglemapsbinding.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import org.aaronhe.rxgooglemapsbinding.RxGoogleMaps;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = MainActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
    mapFragment.onCreate(savedInstanceState);

    BehaviorSubject<GoogleMap> subject = BehaviorSubject.create();
    RxGoogleMaps.mapReady(mapFragment).subscribe(subject);

    subject.subscribe(new Action1<GoogleMap>() {
      @Override public void call(GoogleMap googleMap) {
        RxGoogleMaps.cameraPositionChanges(googleMap).subscribe(new Action1<CameraPosition>() {
          @Override public void call(CameraPosition cameraPosition) {
            Log.i(TAG, "Camera Position: " + cameraPosition);
          }
        });
      }
    });

    subject.subscribe(new Action1<GoogleMap>() {
      @Override public void call(GoogleMap googleMap) {
        RxGoogleMaps.clicks(googleMap).subscribe(new Action1<LatLng>() {
          @Override public void call(LatLng latLng) {
            Log.i(TAG, "LatLng: " + latLng);
          }
        });
      }
    });
  }
}
