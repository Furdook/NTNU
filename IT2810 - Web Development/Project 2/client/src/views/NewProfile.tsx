import { useEffect } from "react";
import { z } from "zod";
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useMutation } from "@apollo/client";
import { addNewUser } from "../utilities/schemas";

import "./styles/FormStyling.css";
import ButtonBack from "../components/ButtonBack";
import { useUser } from "../utilities/context";

function NewProfile() {
  const { user, setUser } = useUser();
  const [addUser, { data, loading }] = useMutation(addNewUser); // Mutation to add user to database
  useEffect(() => {
    console.log(user);
  }, [user]);

  // Form validation schema
  const formSchema = z
    .object({
      profileName: z
        .string()
        .min(2, { message: "Navnet må være minst 2 tegn" }),
      profileEmail: z.string().email({ message: "Ugyldig e-post" }),
      profilePhone: z
        .string()
        .min(8, { message: "Telefonnummeret må være minst 8 tegn" }),
      profilePassword: z
        .string()
        .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{3,}$/, {
          message:
            "Passordet må inneholde minst 1 stor bokstav, 1 liten bokstav og 1 tall",
        }),
      profileConfirmPassword: z
        .string()
        .min(3, { message: "Du må bekrefte passordet ditt" }),
      terms: z.boolean().refine((val) => val === true, {
        message: "Du må godkjenne vilkårene",
      }),
    })
    .refine((data) => data.profilePassword === data.profileConfirmPassword, {
      path: ["profileConfirmPassword"],
      message: "Passordene er ikke like",
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

  // Prints form data to console upon submit
  // Sets the submitted state variable to true, and the submittedEmail and submittedProfileName state variable to the variables provided in the form
  // Will be used to send data to server
  const onSubmit: SubmitHandler<FormSchemaType> = (data) => {
    console.log(data);
    addUser({
      variables: {
        name: data.profileName,
        email: data.profileEmail,
        phone: data.profilePhone,
        password: data.profilePassword,
      },
    }).then((data) => {
      setUser(data.data.createUser);
    });
  };

  return (
    <main id="newProfile">
      {data && !loading ? ( // Render component based on the submission status
        <div className="newProfileSubmitted">
          <h2>Velkommen, {data.createUser.name}</h2>
          <p>En bekreftelsesmail er sendt til {data.createUser.email}.</p>
        </div>
      ) : (
        <div className="form-container">
          <div className="form-box">
            <ButtonBack />
            <h1 className="form-heading">Lag en ny profil</h1>
            <form
              className="new-profile-form"
              onSubmit={handleSubmit(onSubmit)}
            >
              <div className="form-group">
                <label htmlFor="profileName" className="form-label">
                  Navn
                </label>
                <input
                  type="text"
                  id="profileName"
                  className="form-input"
                  placeholder="Navnet andre brukere skal se"
                  {...register("profileName")}
                />
                {errors.profileName && (
                  <span className="error-message">
                    {errors.profileName?.message}
                  </span>
                )}
              </div>

              <div className="form-group">
                <label htmlFor="profileEmail" className="form-label">
                  E-post
                </label>
                <input
                  type="email"
                  id="profileEmail"
                  className="form-input"
                  placeholder="Din epostadresse"
                  {...register("profileEmail")}
                />
                {errors.profileEmail && (
                  <span className="error-message">
                    {errors.profileEmail?.message}
                  </span>
                )}
              </div>

              <div className="form-group">
                <label htmlFor="profilePhone" className="form-label">
                  Kontakt
                </label>
                <input
                  type="tel"
                  id="profilePhone"
                  className="form-input"
                  placeholder="Telefonnummer"
                  {...register("profilePhone")}
                />

                {errors.profilePhone && (
                  <span className="error-message">
                    {errors.profilePhone?.message}
                  </span>
                )}
              </div>

              <div className="form-group">
                <label htmlFor="profilePassword" className="form-label">
                  Passord
                </label>
                <input
                  type="password"
                  id="profilePassword"
                  className="form-input"
                  placeholder="Passord"
                  {...register("profilePassword")}
                  data-testid="password-input"
                />
                {errors.profilePassword && (
                  <span className="error-message">
                    {errors.profilePassword?.message}
                  </span>
                )}
              </div>

              <div className="form-group">
                <label htmlFor="profileConfirmPassword" className="form-label">
                  Bekreft passord
                </label>
                <input
                  type="password"
                  id="profileConfirmPassword"
                  className="form-input"
                  placeholder="Bekreft passord"
                  {...register("profileConfirmPassword")}
                  data-testid="confirmPassword-input"
                />
                {errors.profileConfirmPassword && (
                  <span className="error-message">
                    {errors.profileConfirmPassword?.message}
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
                      <a className="form-link" href="#">
                        Vilkår for Brukerprofil
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
                Lag ny profil
              </button>
            </form>
          </div>
        </div>
      )}
    </main>
  );
}

export default NewProfile;
