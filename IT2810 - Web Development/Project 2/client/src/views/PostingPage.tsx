import { useParams } from "react-router-dom";
import "./styles/PostingPage.css";
import ButtonBack from "../components/ButtonBack";
import { useQuery } from "@apollo/client";
import { getListing } from "../utilities/schemas";

import { useLocation } from "react-router-dom";

import car from "../assets/car.svg";
import service from "../assets/service.svg";
import property from "../assets/property.svg";
import furniture from "../assets/furniture.svg";
import tools from "../assets/tools.svg";
import tech from "../assets/tech.svg";
import clothing from "../assets/clothing.svg";
import leisure from "../assets/leisure.svg";

function PostingPage() {
  const { id } = useParams<{ id: string }>();
  const location = useLocation();
  new URLSearchParams(location.search);
  const { data, loading } = useQuery(getListing, {
    variables: {
      getListingId: id,
    },
  });

  const images = {
    Tjenester: service,
    Møbler: furniture,
    Verktøy: tools,
    Biler: car,
    Elektronikk: tech,
    Eiendom: property,
    Klær: clothing,
    Fritid: leisure,
  };

  const c = data?.getListing.category as keyof typeof images;

  return (
    <main id="postingPage">
      <div className="postingContainer">
        <ButtonBack />
        <div className="postingSegmentWrapper">
          <h1>Annonse</h1>
        </div>
        <img src={images[c]} alt="Posting image" className="postingImage" />
        <section>
          <p className="postingTitle">
            {!loading && data && data.getListing.title}
          </p>
          <p className="postingPrice">
            Til salgs: {!loading && data && data.getListing.price},-
          </p>
        </section>
        <hr className="seperator" />
        <section>
          <p className="postingDate">
            Lagt ut:{" "}
            {!loading &&
              data &&
              !loading &&
              data &&
              new Date(Number(data.getListing.datePosted)).toLocaleDateString()}
          </p>
          <p className="postingCategory">
            {!loading && data && data.getListing.category}
          </p>
        </section>
        <p className="postingDescription">
          {!loading && data && data.getListing.description}
        </p>

        <div className="postingSegmentWrapper postingSegmentWrapperBottom">
          <p className="postingArea">
            Selger: {!loading && data && data.getListing.user.name} {"("}
            {!loading && data && data.getListing.user.phone}
            {")"} - {!loading && data && data.getListing.location}:{" "}
            {!loading && data && data.getListing.address},{" "}
            {data && data.getListing.zipcode}
          </p>
        </div>
        <div className="postingSegmentWrapper">
          <p className="postingNr">
            Annonse nr.: {!loading && data && data.getListing.id}
          </p>
        </div>
      </div>
    </main>
  );
}

export default PostingPage;
