import type { Place } from "../types/place";
import PlaceCard from "./PlaceCard";

export default function PlaceList({ places }: { places: Place[] }) {
  if (!places.length) return null;

  return (
    <div className="grid gap-3 py-4 sm:grid-cols-2 lg:grid-cols-3">
      {places.map((p) => (
        <PlaceCard key={p.placeId} place={p} />
      ))}
    </div>
  );
}
