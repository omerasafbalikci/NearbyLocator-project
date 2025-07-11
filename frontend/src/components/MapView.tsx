import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import L from 'leaflet';
import type { Place } from '../types/place';
import { useEffect } from 'react';

/* OpenStreetMap marker ikonu ayarı (Leaflet varsayılanı CDN’den çekiyor) */
delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
});

interface Props {
  center: { lat: number; lng: number };
  places: Place[];
}

/* Harita ortasını arama sonrası güncellemek için küçük yardımcı */
function Fly({ center }: { center: { lat: number; lng: number } }) {
  const map = useMap();
  useEffect(() => {
    map.flyTo([center.lat, center.lng], 14);
  }, [center]);
  return null;
}

export default function MapView({ center, places }: Props) {
  return (
    <MapContainer
      center={[center.lat, center.lng]}
      zoom={14}
      scrollWheelZoom
      style={{ height: '70vh', width: '100%' }}
      className="rounded-lg border shadow-sm dark:border-gray-700"
    >
      {/* Ücretsiz OSM karo sunucusu */}
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OSM</a>'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />

      <Fly center={center} />

      {places.map((p) => (
        <Marker key={p.placeId} position={[p.lat, p.lng]}>
          <Popup>
            <strong>{p.name}</strong>
            <br />
            {p.address}
            {p.rating && (
              <>
                <br />⭐ {p.rating}
              </>
            )}
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
}
