import type { Place } from "../types/place";
import { MapPinIcon } from "@heroicons/react/24/solid";

export default function PlaceCard({ place }: { place: Place }) {
  return (
    <div className="flex items-start gap-3 rounded-lg border p-3 shadow-sm dark:border-gray-600">
      {place.iconUrl && (
        <img src={place.iconUrl} alt="" className="h-10 w-10 rounded" />
      )}
      <div className="flex-1">
        <h3 className="font-semibold">{place.name}</h3>
        <p className="text-xs text-gray-600 dark:text-gray-400 flex items-center gap-1">
          <MapPinIcon className="h-4 w-4" />
          {place.address}
        </p>
        {place.rating && (
          <p className="text-xs mt-1">⭐ {place.rating}</p>
        )}
      </div>
    </div>
  );
}
