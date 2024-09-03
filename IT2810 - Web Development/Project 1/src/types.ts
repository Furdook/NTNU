export type Artist = {
  id: string;
  name: string;
  country: string;
  type: string;
  releases: Release[];
};

export type Release = {
  id: string;
  title: string;
  date: string;
  country: string;
};
