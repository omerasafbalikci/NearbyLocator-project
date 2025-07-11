import {
  GoogleMap,
  Marker,
  InfoWindow,
  useJsApiLoader,
} from "@react-google-maps/api";
import { useState } from "react";
import type { Place } from "../types/place";

interface Props {
  center: { lat: number; lng: number };
  places: Place[];
}

export default function MapView({ center, places }: Props) {
  const { isLoaded } = useJsApiLoader({
    id: "google-map-script",
    googleMapsApiKey: import.meta.env.VITE_GOOGLE_MAPS_KEY as string,
  });

  const [active, setActive] = useState<Place | null>(null);

  if (!isLoaded) return <p>Harita yükleniyor…</p>;

  return (
    <GoogleMap
      mapContainerStyle={{ width: "100%", height: "70vh" }}
      zoom={14}
      center={center}
      onClick={() => setActive(null)}
    >
      {places.map((p) => (
        <Marker
          key={p.placeId}
          position={{ lat: p.lat, lng: p.lng }}
          onClick={() => setActive(p)}
          icon={p.iconUrl || undefined}
        />
      ))}

      {active && (
        <InfoWindow
          position={{ lat: active.lat, lng: active.lng }}
          onCloseClick={() => setActive(null)}
        >
          <div style={{ maxWidth: 200 }}>
            <strong>{active.name}</strong>
            <br />
            {active.address}
            {active.rating && (
              <>
                <br />⭐ {active.rating}
              </>
            )}
          </div>
        </InfoWindow>
      )}
    </GoogleMap>
  );
}
