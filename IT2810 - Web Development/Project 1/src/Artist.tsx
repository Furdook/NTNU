import { useNavigate, useParams } from "react-router-dom";
import "./styles.css";
import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { Artist as A } from "./types";

import Person from "./assets/person.svg";
import Group from "./assets/group.svg";
import Like from "./components/Like";

function Artist() {
  const navigate = useNavigate();
  const artist_id = useParams().id;
  const [artist, setArtist] = useState<A>();

  useQuery({
    queryKey: ["getArtist"],
    queryFn: () =>
      fetch(
        `https://musicbrainz.org/ws/2/artist/${artist_id}?inc=release-groups&fmt=json`
      )
        .then((response) => response.json())
        .then((data) =>
          setArtist({ ...data, releases: data["release-groups"] })
        ),
  });

  return (
    <section className="container">
      <button id="goBack" onClick={() => navigate("../project1")}>
        Go Back
      </button>
      {artist && <Like {...artist} />}
      <h1 id="name">
        {artist?.name}
        <span id="country"> ({artist?.country})</span>
      </h1>
      <img
        src={artist?.type === "Person" ? Person : Group}
        alt="Artsit Type"
        id="artist_type"
      />
      <h2 id="releases_title">Releases:</h2>
      <ul className="releases">
        {artist?.releases.map((release) => {
          return (
            <li key={release.id} tabIndex={1}>
              {release.title}
            </li>
          );
        })}
      </ul>
      <p className="id">{artist?.id}</p>
    </section>
  );
}

export default Artist;
