import axios from "axios";
import type { Place } from "../types/place";

const api = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_URL,
});

export async function fetchNearby(
  lat: number,
  lng: number,
  radius: number
): Promise<Place[]> {
  const { data } = await api.get<Place[]>("/places", {
    params: { lat, lng, radius },
  });
  return data;
}
