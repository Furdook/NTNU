import { Link } from "react-router-dom";
import "./styles/Listing.css";
import car from "../assets/car.svg";
import service from "../assets/service.svg";
import property from "../assets/property.svg";
import furniture from "../assets/furniture.svg";
import tools from "../assets/tools.svg";
import tech from "../assets/tech.svg";
import clothing from "../assets/clothing.svg";
import leisure from "../assets/leisure.svg";

interface ListingProps {
  id: string;
  title: string;
  price: number;
  category: string;
}

function Listing(props: ListingProps) {
  const { id, title, price, category } = props;
  const c = category as typeof category & keyof typeof images;
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

  return (
    <Link to={`/posting/${id}`}>
      <section
        data-listing-id={id}
        data-testid="listing-item"
        className="posting-card"
        style={{ backgroundImage: `url(${images[c]})` }}
      >
        <div className="card__info">
          <h3 className="card__title">{title}</h3>
          <p className="card__price">{price},-</p>
        </div>
      </section>
    </Link>
  );
}

export default Listing;
