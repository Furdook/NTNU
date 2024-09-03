export default function uriParser(
  search: string | undefined,
  category: string | undefined,
  area: string[] | undefined,
) {
  const original_uri = document.location.pathname;
  const split_uri = original_uri.split("&");

  let new_uri = "../results/";
  let priorArea;

  split_uri.forEach((element) => {
    if (element.includes("area") && area?.length === 0) {
      priorArea = element.split("area=")[1];
    }
  });

  if (search) {
    new_uri += `&search=${search}`;
  } else if (original_uri.includes("search")) {
    new_uri += `&search=${original_uri.split("search=")[1].split("&")[0]}`;
  } else {
    new_uri += `&all`;
  }

  if (category) {
    new_uri += `&category=${category}`;
  } else if (original_uri.includes("category")) {
    new_uri += `&category=${original_uri.split("category=")[1].split("&")[0]}`;
  }

  if (area !== undefined && area.length > 0) {
    new_uri += `&area=${area.join("-")}`;
  } else if (priorArea !== undefined) {
    new_uri += `&area=${priorArea}`;
  }

  return new_uri;
}
