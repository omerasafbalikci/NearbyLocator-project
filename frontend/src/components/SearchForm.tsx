import { useState } from "react";

interface Props {
  onSubmit: (lat: number, lng: number, radius: number) => void;
  loading: boolean;
}

export default function SearchForm({ onSubmit, loading }: Props) {
  const [lat, setLat] = useState("");
  const [lng, setLng] = useState("");
  const [radius, setRadius] = useState(500);

  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit(Number(lat), Number(lng), Number(radius));
      }}
      className="grid grid-cols-1 sm:grid-cols-4 gap-3"
    >
      <input
        type="number"
        step="any"
        placeholder="Enlem"
        required
        value={lat}
        onChange={(e) => setLat(e.target.value)}
        className="input"
      />
      <input
        type="number"
        step="any"
        placeholder="Boylam"
        required
        value={lng}
        onChange={(e) => setLng(e.target.value)}
        className="input"
      />
      <input
        type="number"
        min={1}
        placeholder="Mesafe (m)"
        value={radius}
        onChange={(e) => setRadius(Number(e.target.value))}
        className="input"
      />
      <button
        type="submit"
        disabled={loading}
        className="btn btn-primary col-span-full sm:col-auto"
      >
        {loading ? "Aranıyor…" : "Ara"}
      </button>
    </form>
  );
}
