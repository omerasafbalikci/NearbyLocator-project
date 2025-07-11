import { useState } from "react";
import Header from "./components/Header";
import SearchForm from "./components/SearchForm";
import MapView from "./components/MapView";
import PlaceList from "./components/PlaceList";
import { fetchNearby } from "./api/places";
import type { Place } from "./types/place";
import 'leaflet/dist/leaflet.css';

export default function App() {
  const [places, setPlaces] = useState<Place[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [coords, setCoords] = useState<{ lat: number; lng: number } | null>(
    null
  );

  const handleSearch = async (lat: number, lng: number, radius: number) => {
    setLoading(true);
    setError(null);
    try {
      const data = await fetchNearby(lat, lng, radius);
      setCoords({ lat, lng });
      setPlaces(data);
    } catch {
      setError("Yerler alınamadı.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 px-4 text-gray-900 dark:bg-gray-900 dark:text-gray-100">
      <div className="mx-auto max-w-6xl">
        <Header />

        <section className="my-6">
          <SearchForm onSubmit={handleSearch} loading={loading} />
          {error && <p className="mt-2 text-red-600">{error}</p>}
        </section>

        {coords && (
          <section className="grid gap-6 lg:grid-cols-3">
            <div className="lg:col-span-2">
              <MapView center={coords} places={places} />
            </div>
            <div>
              <h2 className="mb-2 text-lg font-semibold">
                Sonuçlar ({places.length})
              </h2>
              <PlaceList places={places} />
            </div>
          </section>
        )}
      </div>
    </div>
  );
}
