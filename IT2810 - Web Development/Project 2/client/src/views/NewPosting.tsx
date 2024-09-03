import { z } from "zod";
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useNavigate } from "react-router-dom";
import "./styles/NewPosting.css";
import "./styles/FormStyling.css";
import ButtonBack from "../components/ButtonBack";
import { gql, useMutation } from "@apollo/client";
import { useUser } from "../utilities/context";

const CREATE_LISTING = gql`
  mutation CreateListing(
    $title: String!
    $description: String!
    $price: Int!
    $category: String!
    $address: String!
    $zipcode: String!
    $location: String!
    $datePosted: String!
    $user: String!
  ) {
    createListing(
      title: $title
      description: $description
      price: $price
      category: $category
      address: $address
      zipcode: $zipcode
      location: $location
      datePosted: $datePosted
      user: $user
    ) {
      id
      title
    }
  }
`;

async function assignLocation(zipcode: string) {
  let kommunenummer = "0010";
  let regionName = "";
  // If the zipcode is 8099, it is Jan Mayen
  if (zipcode === "8099") {
    return (regionName = "Svalbard og Jan Mayen");
  }
  try {
    const response = await fetch(
      "https://ws.geonorge.no/adresser/v1/sok?fuzzy=false&postnummer=" +
        zipcode +
        "&utkoordsys=4258&treffPerSide=1&side=0&asciiKompatibel=true"
    );
    const data = await response.json();
    kommunenummer = data.adresser[0].kommunenummer;
    console.log("Returnerer fra API kommunenr.: " + kommunenummer);
    // If kommunenummer is 2100, it is Svalbard
    if (kommunenummer === "2100") {
      return (regionName = "Svalbard og Jan Mayen");
    }
    // If API is able to fetch the kommunenummer, try fetch the region name
    try {
      const response = await fetch(
        "https://ws.geonorge.no/kommuneinfo/v1/kommuner/" + kommunenummer
      );
      const data = await response.json();
      regionName = data.fylkesnavn;
      console.log("Returnerer fra API region: " + regionName);
      return regionName;
    } catch (error) {
      console.log(error);
      return (regionName = estimatedLocation(zipcode));
    }
  } catch (error) {
    console.log(error);
    return (regionName = estimatedLocation(zipcode));
  }
}
// If the API is not able to fetch the kommunenummer, use this function to estimate the location
function estimatedLocation(zipcode: string) {
  const region = parseInt(String(zipcode).slice(0, 2));
  if (region <= 12) {
    return "Oslo";
  } else if ((region >= 13 && region <= 15) || region === 19 || region === 20) {
    return "Viken";
  } else if (region >= 16 && region <= 18) {
    return "Innlandet";
  } else if ((region >= 21 && region <= 25) || region === 35) {
    return "Innlandet";
  } else if (region === 26 || (region >= 30 && region <= 36)) {
    return "Vestfold og Telemark";
  } else if ((region >= 40 && region <= 44) || region === 55) {
    return "Rogaland";
  } else if (region >= 45 && region <= 49) {
    return "Agder";
  } else if ((region >= 50 && region <= 56) || region === 59) {
    return "Vestland";
  } else if (region === 57 || (region >= 60 && region <= 66)) {
    return "Møre og Romsdal";
  } else if ((region >= 70 && region <= 76) || region === 77) {
    return "Trøndelag";
  } else if (region >= 78 && region <= 89) {
    return "Nordland";
  } else if (region === 84 || (region >= 90 && region <= 94)) {
    return "Troms og Finnmark";
  } else if (region === 95 || (region >= 96 && region <= 99)) {
    return "Troms og Finnmark";
  } else {
    return "Ukjent område";
  }
}

function NewPosting() {
  const navigate = useNavigate();
  const { user } = useUser();

  const [createListing] = useMutation(CREATE_LISTING);
  // Form validation schema
  const formSchema = z.object({
    postingTitle: z.string().min(1, "Annonsen må ha en tittel").max(100),
    postingDescription: z
      .string()
      .min(1, "Annonsen må ha en beskrivelse")
      .max(1000),
    postingCategory: z.string().refine((data) => data !== "---", {
      message: "Du må velge en kategori",
    }), // Data cannot be "---", which is the default value. User must pick a category
    postingContact: z
      .string()
      .regex(/^\+?[0-9]{8,}$/i, "Ugyldig telefonnummer") // Regex for phone number of minimum 8 digits
      .min(8, "Ugyldig telefonnummer")
      .max(20),
    postingLocationAddress: z
      .string()
      .regex(/^[A-Za-z]+\s\d+/, "Ugyldig gateadresse"), // Regex for street name, then number
    postingLocationZipCode: z
      .string()
      .regex(/^[0-9]{4}$/i, "Ugyldig postnummer"), // Regex for 4 digits zipcode
    postingPrice: z.string().regex(/^[0-9]+$/i, "Ugyldig pris"), // Regex for price, only digits
    terms: z.literal(true, {
      errorMap: () => ({ message: "Du må godkjennte vilkårene for annonser" }),
    }),
  });

  // Defining the type of the form data using the inferred type from the form schema
  type FormSchemaType = z.infer<typeof formSchema>;

  // Using the useForm hook to handle the form state and validation
  const {
    register, // Registering the forms input
    handleSubmit, // Handling the submit event
    formState: { errors, isSubmitting }, // Getting the form state and errors
  } = useForm<FormSchemaType>({
    resolver: zodResolver(formSchema), // Using resolver to validate the form data
  });

  const onSubmit: SubmitHandler<FormSchemaType> = async (data) => {
    createListing({
      variables: {
        title: data.postingTitle,
        description: data.postingDescription,
        price: parseInt(data.postingPrice),
        category: data.postingCategory,
        address: data.postingLocationAddress,
        zipcode: data.postingLocationZipCode,
        location: await assignLocation(data.postingLocationZipCode),
        datePosted: new Date().toISOString(),
        user: user!.id,
      },
    }).then((data) => {
      navigate(`/posting/${data.data.createListing.id}`);
    });
  };

  // Error message for invalid address, if both address and zipcode are invalid use one error message
  const isInvalidAddress =
    errors.postingLocationAddress && errors.postingLocationZipCode;
  const errorMessage = isInvalidAddress ? "Ugyldig adresse og postnummer" : "";

  return (
    <main id="newPosting">
      <div className="form-container">
        <div className="form-box">
          <ButtonBack />
          <h1 className="form-heading">Lag en ny annonse</h1>
          <form className="posting-form" onSubmit={handleSubmit(onSubmit)}>
            <div className="form-group">
              <label htmlFor="postingTitle" className="form-label">
                Tittel
              </label>
              <input
                type="text"
                id="postingTitle"
                className="form-input"
                placeholder="Annonse tittel"
                {...register("postingTitle")}
              />
              {errors.postingTitle && (
                <span className="error-message">
                  {errors.postingTitle?.message}
                </span>
              )}
            </div>
            <div className="form-group">
              <label htmlFor="postingDescription" className="form-label">
                Beskrivelse
              </label>
              <textarea
                id="postingDescription"
                className="form-input"
                placeholder="Beskrivelse av din annonse"
                {...register("postingDescription")}
              />
              {errors.postingDescription && (
                <span className="error-message">
                  {errors.postingDescription?.message}
                </span>
              )}
            </div>
            <div className="form-group">
              <label htmlFor="postingCategory" className="form-label">
                Kategori
              </label>
              <select
                id="postingCategory"
                className="form-input"
                {...register("postingCategory")}
              >
                <option value="---">---</option>
                <option value="Tjenester">Tjenester</option>
                <option value="Møbler">Møbler</option>
                <option value="Verktøy">Verktøy</option>
                <option value="Biler">Biler</option>
                <option value="Elektronikk">Elektronikk</option>
                <option value="Eiendom">Eiendom</option>
                <option value="Klær">Klær</option>
                <option value="Fritid">Fritid</option>
              </select>
              {errors.postingCategory && (
                <span className="error-message">
                  {errors.postingCategory?.message}
                </span>
              )}
            </div>
            <div className="form-group">
              <label htmlFor="postingContact" className="form-label">
                Kontakt
              </label>
              <input
                type="tel"
                id="postingContact"
                className="form-input"
                placeholder="Telefonnummer"
                {...register("postingContact")}
              />

              {errors.postingContact && (
                <span className="error-message">
                  {errors.postingContact?.message}
                </span>
              )}
            </div>
            <div className="form-group">
              <label htmlFor="postingLocation" className="form-label">
                Adresse
              </label>
              <div className="address-form-container">
                <input
                  type="text"
                  id="postingLocationAddress"
                  className="form-input left_2_3_input"
                  placeholder="Gateadresse"
                  {...register("postingLocationAddress")}
                />
                <input
                  type="text"
                  id="postingLocationZipCode"
                  className="form-input right_1_3_input"
                  placeholder="Postnummer"
                  {...register("postingLocationZipCode")}
                />
              </div>
              {errorMessage && (
                <span className="error-message">{errorMessage}</span>
              )}
              {errors.postingLocationAddress && !isInvalidAddress && (
                <span className="error-message">
                  {errors.postingLocationAddress?.message}
                </span>
              )}
              {errors.postingLocationZipCode && !isInvalidAddress && (
                <span className="error-message">
                  {errors.postingLocationZipCode?.message}
                </span>
              )}
            </div>
            <div className="form-group">
              <label htmlFor="postingPrice" className="form-label">
                Pris
              </label>
              <input
                type="text"
                id="postingPrice"
                className="form-input"
                placeholder="NOK"
                {...register("postingPrice")}
              />
              {errors.postingPrice && (
                <span className="error-message">
                  {errors.postingPrice?.message}
                </span>
              )}
            </div>
            <div className="checkboxAgreementWrapper">
              <div className="form-checkbox-group">
                <div className="form-checkbox">
                  <input
                    id="terms"
                    aria-describedby="terms"
                    type="checkbox"
                    className="form-checkbox-input"
                    {...register("terms")}
                  />
                </div>
                <div className="form-checkbox-label">
                  <label htmlFor="terms" className="form-checkbox-text">
                    Jeg godkjenner{" "}
                    <a
                      className="form-link"
                      href="#"
                      onClick={(e) => {
                        e.preventDefault();
                        window.confirm(
                          "Ikke noe spesielt. Bare for å sjekke at du følger med ;^)"
                        );
                      }}
                    >
                      Vilkår for Annonser
                    </a>
                  </label>
                </div>
              </div>
              {errors.terms && (
                <span className="error-message">{errors.terms?.message}</span>
              )}
            </div>
            <button
              type="submit"
              className="form-submit-button"
              disabled={isSubmitting}
            >
              Lag en ny annonse
            </button>
          </form>
        </div>
      </div>
    </main>
  );
}

export default NewPosting;
