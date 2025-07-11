export interface Place {
  placeId: string;
  name: string;
  lat: number;
  lng: number;
  address: string;
  rating?: number;
  types: string[];
  iconUrl?: string;
}
