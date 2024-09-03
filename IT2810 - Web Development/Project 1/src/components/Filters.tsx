import { Artist as A } from "../types";

function Filters({
  setCurrentArtist,
  setArtists,
  artists,
  data,
}: {
  setCurrentArtist: CallableFunction;
  setArtists: CallableFunction;
  artists: A[];
  //eslint-disable-next-line
  data: any;
}) {
  return (
    <search>
      <input
        type="text"
        id="search"
        placeholder="Search"
        onChange={() => {
          setCurrentArtist(
            artists.filter((artist: A) =>
              artist.name
                .toLowerCase()
                .includes(
                  (
                    document.getElementById("search") as HTMLInputElement
                  ).value.toLowerCase()
                )
            )[0]
          );
        }}
      />
      <select
        name="sort"
        id="sort"
        onChange={() => {
          const sort = document.getElementById("sort") as HTMLSelectElement;
          setArtists(
            artists.sort((a: A, b: A) => {
              if (sort.value === "a-z") {
                return a.name.localeCompare(b.name);
              } else if (sort.value === "z-a") {
                return b.name.localeCompare(a.name);
              } else {
                return 0;
              }
            })
          );
          setCurrentArtist(artists[0]);
        }}
      >
        <option value="default">Relevance</option>
        <option value="a-z">Sort A-Z</option>
        <option value="z-a">Sort Z-A</option>
      </select>
      <select
        name="type"
        id="artistType"
        onChange={() => {
          const type = document.getElementById(
            "artistType"
          ) as HTMLSelectElement;
          if (type.value === "all") {
            setArtists(data?.artists);
          } else {
            setArtists(
              data?.artists.filter((artist: A) => artist.type === type.value)
            );
          }
          setCurrentArtist(artists[0]);
        }}
      >
        <option value="all">All</option>
        <option value="Group">Group</option>
        <option value="Person">Person</option>
      </select>
    </search>
  );
}

export default Filters;
