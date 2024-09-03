import { useNavigate } from "react-router-dom";
import "./styles.css";

function Likes() {
  const navigate = useNavigate();
  const likes = localStorage.getItem("likes")?.split(",");

  return (
    <section className="container">
      <h1 id="likesTitle">Likes</h1>
      <ul className="releases">
        {likes?.slice(36).map((like: string) => {
          const like_split = like.replace(/[^a-zA-Z0-9-& ]/g, "").split("&");

          return (
            <li
              key={like_split[0]}
              onClick={() => {
                navigate(`../project1/artist/${like_split[0]}`);
              }}
              className="like_li"
            >
              {like_split[1]}
            </li>
          );
        })}
      </ul>
      <button id="goBack" onClick={() => navigate("../project1")}>
        Go Back
      </button>
    </section>
  );
}

export default Likes;
