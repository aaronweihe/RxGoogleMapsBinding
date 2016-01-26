# RxGoogleMapsBinding
[![Build Status](https://travis-ci.org/aaronhe42/RxGoogleMapsBinding.svg?branch=master)](https://travis-ci.org/aaronhe42/RxGoogleMapsBinding)

RxJava binding APIs for Google Maps.

## Usage

Observe changes to `CameraPosition` from a `GoogleMap`:
```java
RxGoogleMaps.cameraPositionChanges(map).subscribe(new Action1<CameraPosition>() {
  @Override public void call(CameraPosition cameraPosition) {
    Log.i(TAG, "CameraPosition: " + cameraPosition);
  }
};
```

Or observe changes to `CameraPosition` from a `MapView` or `MapFragment` or `SupportMapFragment`:
```java
RxGoogleMaps.mapReady(mapView).flatMap(new Func1<GoogleMap, Observable<CameraPosition>>() {
  @Override public Observable<CameraPosition> call(GoogleMap googleMap) {
    return RxGoogleMaps.cameraPositionChanges(googleMap);
  }
}).subscribe(new Action1<CameraPosition>() {
  @Override public void call(CameraPosition cameraPosition) {
    Log.i(TAG, "CameraPosition: " + cameraPosition);
  }
});
```

See the [Javadoc](http://aaronhe42.github.io/RxGoogleMapsBinding/0.x/rxgooglemapsbinding/) for more.

## Download
Java bindings:
```groovy
compile 'org.aaronhe.rxgooglemapsbinding:rxgooglemapsbinding:0.1.1'
```

Kotlin bindings:
```groovy
compile 'org.aaronhe.rxgooglemapsbinding:rxgooglemapsbinding-kotlin:0.1.1'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

## License

    Copyright (C) 2016 Aaron He

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 
[snap]: https://oss.sonatype.org/content/repositories/snapshots/
