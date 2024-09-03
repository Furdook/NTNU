import { useEffect } from "react";
import { z } from "zod";
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { gql, useLazyQuery } from "@apollo/client";

import "./styles/FormStyling.css";
import ButtonBack from "../components/ButtonBack";
import { useUser } from "../utilities/context";
import { useNavigate } from "react-router";

const LOGIN = gql`
  query ExampleQuery($email: String!, $password: String!) {
    loginUser(email: $email, password: $password) {
      id
      name
      email
      phone
    }
  }
`;

function Login() {
  const navigate = useNavigate();
  const { user, setUser } = useUser();
  const [loginUser] = useLazyQuery(LOGIN);

  useEffect(() => {
    console.log(user);
    if (user) {
      navigate("/");
    }
  });

  // Form validation schema
  const formSchema = z.object({
    profileEmail: z.string().email({ message: "Ugyldig e-post" }),
    profilePassword: z
      .string()
      .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{3,}$/, {
        message:
          "Passordet må inneholde minst 1 stor bokstav, 1 liten bokstav og 1 tall",
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

  // Prints form data to console upon submit
  // Sets the submitted state variable to true, and the submittedEmail and submittedProfileName state variable to the variables provided in the form
  // Will be used to send data to server
  const onSubmit: SubmitHandler<FormSchemaType> = (data) => {
    console.log(data);
    loginUser({
      variables: {
        email: data.profileEmail,
        password: data.profilePassword,
      },
    })
      .then((data) => {
        setUser(data.data.loginUser);
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <main id="newProfile">
      <div className="form-container">
        <div className="form-box">
          <ButtonBack />
          <h1 className="form-heading">Logg inn</h1>
          <form className="new-profile-form" onSubmit={handleSubmit(onSubmit)}>
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
              <label htmlFor="profilePassword" className="form-label">
                Passord
              </label>
              <input
                type="password"
                id="profilePassword"
                className="form-input"
                placeholder="Passord"
                {...register("profilePassword")}
              />
              {errors.profilePassword && (
                <span className="error-message">
                  {errors.profilePassword?.message}
                </span>
              )}
            </div>

            <button
              type="submit"
              className="form-submit-button"
              disabled={isSubmitting}
            >
              Logg inn!
            </button>
          </form>
        </div>
      </div>
    </main>
  );
}

export default Login;
