import { useState } from "react";
import { fetchNearby } from "./api/places";
import MapView from "./components/MapView";
import type { Place } from "./types/place";
import "./index.css"; // stil dosyan varsa

export default function App() {
  const [form, setForm] = useState({
    lat: "",
    lng: "",
    radius: 500,
  });
  const [places, setPlaces] = useState<Place[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      const data = await fetchNearby(
        Number(form.lat),
        Number(form.lng),
        Number(form.radius)
      );
      setPlaces(data);
    } catch (err) {
      console.error(err);
      setError("Yerler alınamadı.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 800, margin: "0 auto", padding: "1rem" }}>
      <h1>Yakındaki Yerler</h1>

      <form onSubmit={handleSubmit} style={{ display: "flex", gap: 8 }}>
        <input
          type="number"
          step="any"
          placeholder="Enlem"
          required
          value={form.lat}
          onChange={(e) => setForm({ ...form, lat: e.target.value })}
        />
        <input
          type="number"
          step="any"
          placeholder="Boylam"
          required
          value={form.lng}
          onChange={(e) => setForm({ ...form, lng: e.target.value })}
        />
        <input
          type="number"
          placeholder="Mesafe (m)"
          min={1}
          value={form.radius}
          onChange={(e) =>
            setForm({ ...form, radius: Number(e.target.value) })
          }
        />
        <button type="submit" disabled={loading}>
          {loading ? "Aranıyor…" : "Ara"}
        </button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {form.lat && form.lng && (
        <MapView
          center={{ lat: Number(form.lat), lng: Number(form.lng) }}
          places={places}
        />
      )}
    </div>
  );
}
