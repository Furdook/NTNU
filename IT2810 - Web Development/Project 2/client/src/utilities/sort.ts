import { Listing } from "./context";

/**
 * Method to sort a list of listings based on a given sorting method
 *
 * @param listings list of listings to sort
 * @param sort sorting method, price-low-high, price-high-low, newest
 * @returns the sorted list of listings
 */
export default function sortListings(listings: Listing[], sort: string) {
  const listings_copy = [...listings];
  if (sort === "price-low-high") {
    return listings_copy.sort((a: Listing, b: Listing) => {
      return a.price - b.price;
    });
  } else if (sort === "price-high-low") {
    return listings_copy.sort((a: Listing, b: Listing) => {
      return b.price - a.price;
    });
  } else if (sort === "newest") {
    return listings_copy.sort((a: Listing, b: Listing) => {
      return b.datePosted - a.datePosted;
    });
  } else if (sort === "standard") {
    return listings_copy.sort((a: Listing, b: Listing) => {
      return a.id.localeCompare(b.id);
    });
  } else {
    return listings_copy;
  }
}
