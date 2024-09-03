import { test, expect } from "vitest";
import { render, fireEvent } from "@testing-library/react";
import { MockedProvider } from "@apollo/client/testing";
import NewProfile from "../src/views/NewProfile";
import React from "react";
import { addNewUser } from "../src/utilities/schemas";

const mockdata = [
  {
    request: {
      query: addNewUser,
      variables: {
        name: "Ola Nordmann",
        email: "ola.nordmann@ntnu.no",
        phone: "90807060",
        password: "Test123",
      },
    },
    result: {
      data: {
        createUser: {
          name: "Ola Nordmann",
          email: "ola.nordmann@ntnu.no",
          phone: "90807060",
        },
      },
    },
  },
];

test("Render form", () => {
  const { getByLabelText } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <NewProfile />
    </MockedProvider>
  );
  const input = getByLabelText(/Navn/i);
  expect(input).toBeDefined();
});

test("Submits form for creation of new user, Ola Nordmann", async () => {
  const { getByLabelText, getByText, getByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <NewProfile />
    </MockedProvider>
  );
  fireEvent.change(getByLabelText(/Navn/i), {
    target: { value: "Ola Nordmann" },
  });
  fireEvent.change(getByLabelText(/E-post/i), {
    target: { value: "ola.nordmann@ntnu.no" },
  });
  fireEvent.change(getByLabelText(/Kontakt/i), {
    target: { value: "90807060" },
  });
  fireEvent.change(getByTestId("password-input"), {
    target: { value: "Test123" },
  });
  fireEvent.change(getByTestId("confirmPassword-input"), {
    target: { value: "Test123" },
  });
  fireEvent.click(getByLabelText(/Jeg godkjenner/i));

  fireEvent.click(getByText(/Lag ny profil/i));
});

test("Submit form with insufficient data", async () => {
  const { getByText, findAllByText } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <NewProfile />
    </MockedProvider>
  );

  // Form subimission without any user input
  fireEvent.click(getByText(/Lag ny profil/i));

  // Looks for error message stating that therms must be accepted.
  const errorMessages = await findAllByText(/Du må godkjenne vilkårene/i);
  expect(errorMessages).toHaveLength(1);
});

test("Submits form with wrongfull data", async () => {
  const { getByLabelText, getByText, findAllByText, getByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <NewProfile />
    </MockedProvider>
  );

  // User input with only one letter
  fireEvent.change(getByLabelText(/Navn/i), {
    target: { value: "O" },
  });
  fireEvent.change(getByLabelText(/E-post/i), {
    target: { value: "ola.nordmann@ntnu.no" },
  });
  fireEvent.change(getByLabelText(/Kontakt/i), {
    target: { value: "90807060" },
  });
  fireEvent.change(getByTestId("password-input"), {
    target: { value: "Test123" },
  });
  fireEvent.change(getByTestId("confirmPassword-input"), {
    target: { value: "Test123" },
  });
  fireEvent.click(getByLabelText(/Jeg godkjenner/i));

  // Form submission
  fireEvent.click(getByText(/Lag ny profil/i));

  // Looks for error message stating that name must be at least 2 characters long.
  const errorMessages = await findAllByText(/Navnet må være minst 2 tegn/i);
  expect(errorMessages).toHaveLength(1);
});
