import { Artist as A } from "../types";
import "../styles.css";
import Person from "../assets/person.svg";
import Group from "../assets/group.svg";
import { useNavigate } from "react-router";

function Artist(artist: A) {
  const navigate = useNavigate();
  return (
    <section
      id="artist"
      onClick={() => {
        navigate(`./artist/${artist.id}`);
      }}
      tabIndex={1}
    >
      <div id="artistContainer">
        <h1 id="name">
          {artist.name}
          <span id="country"> ({artist.country})</span>
        </h1>
        <p id="type">{artist.type}</p>
      </div>
      <img
        src={artist.type === "Person" ? Person : Group}
        alt={artist.type}
        id="image"
      />
      <p className="id">{artist.id}</p>
    </section>
  );
}

export default Artist;
