import { useQuery } from "@tanstack/react-query";
import Artist from "./components/Artist";
import "./styles.css";
import { Artist as A } from "./types";
import { useEffect, useState } from "react";
import Like from "./components/Like";
import { useNavigate } from "react-router";
import Filters from "./components/Filters";

function App() {
  const navigate = useNavigate();
  const { isLoading, data } = useQuery({
    queryKey: ["getAllArtists"],
    queryFn: () =>
      fetch(
        "https://musicbrainz.org/ws/2/artist/?query=area:norway&fmt=json"
      ).then((response) => response.json()),
  });

  const [currentArtist, setCurrentArtist] = useState<A>();
  const [artists, setArtists] = useState(data);

  useEffect(() => {
    if (!isLoading) {
      setArtists(data?.artists);
      setCurrentArtist(data?.artists[0]);
    }
  }, [data, isLoading]);

  return (
    <main>
      <Filters
        setCurrentArtist={setCurrentArtist}
        setArtists={setArtists}
        artists={artists}
        data={data}
      />
      <button
        id="lastArtist"
        className="buttons"
        onClick={() => {
          let currentIndex = artists.indexOf(currentArtist);
          if (currentIndex === 0) currentIndex = artists.length;
          setCurrentArtist(artists[currentIndex - 1]);
        }}
      >
        ⬅️
      </button>
      {!isLoading && <Artist {...currentArtist!} />}
      <button
        id="nextArtist"
        className="buttons"
        onClick={() => {
          let currentIndex = artists.indexOf(currentArtist);
          if (currentIndex === artists.length - 1) currentIndex = -1;
          setCurrentArtist(artists[currentIndex + 1]);
        }}
      >
        ➡️
      </button>
      <Like {...currentArtist!} />
      <button
        className="fav"
        id="view"
        onClick={() => {
          navigate("./likes");
        }}
      >
        View Likes
      </button>
    </main>
  );
}

export default App;
