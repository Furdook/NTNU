import { useLayoutEffect } from "react";
import "../styles.css";
import { Artist } from "../types";

function Like(artist: Artist) {
  if (!localStorage.getItem("likes")) {
    localStorage.setItem("likes", JSON.stringify([]));
  }

  const updateLikes = () => {
    if (localStorage.getItem(artist.id) === "true") {
      localStorage.setItem(
        "likes",
        JSON.stringify([
          ...JSON.parse(localStorage.getItem("likes")!),
          `${artist.id}&${artist.name}`,
        ])
      );
    } else {
      localStorage.setItem(
        "likes",
        JSON.stringify(
          JSON.parse(localStorage.getItem("likes")!).filter(
            (like: string) => like.split("&")[0] !== artist.id
          )
        )
      );
    }
  };

  const updateColour = () => {
    const button = document.getElementById("like")!;
    if (localStorage.getItem(artist.id) === "true") {
      button.innerHTML = "Liked";
      button.style.backgroundColor = "var(--clr-green)";
      button.style.color = "var(--clr-primary)";
      if (window.innerWidth < 800) {
        button.style.outline = "2px solid var(--clr-primary)";
      }
    } else {
      button.innerHTML = "Like";
      button.style.backgroundColor = "transparent";
      button.style.color = "var(--clr-green)";

      if (window.innerWidth < 800) {
        button.style.backgroundColor = "var(--clr-primary)";
        button.style.outline = "none";
      }
    }
  };

  const handleLike = () => {
    if (localStorage.getItem(artist.id) !== "true") {
      localStorage.setItem(artist.id, "true");
    } else {
      localStorage.removeItem(artist.id);
    }
    updateColour();
    updateLikes();
  };

  useLayoutEffect(() => {
    updateColour();
  });

  return (
    <button
      id="like"
      className="fav"
      onClick={() => {
        handleLike();
      }}
    ></button>
  );
}

export default Like;
