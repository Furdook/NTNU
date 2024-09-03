import { createContext, useContext, useEffect, useState } from "react";

// eslint-disable-next-line
export const userContext = createContext<{
  user: null | {
    id: string;
    name: string;
    email: string;
    phone: string;
  };
  setUser: (user: User) => void;
}>({
  user: null,
  setUser: () => {},
});

// eslint-disable-next-line
export const useUser = () => {
  return useContext(userContext);
};

// eslint-disable-next-line
export const UserProvider = ({ children }: any) => {
  const [user, setUser] = useState<null | {
    id: string;
    name: string;
    email: string;
    phone: string;
  }>(() => {
    const storedUser = sessionStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  });

  useEffect(() => {
    if (user) {
      sessionStorage.setItem("user", JSON.stringify(user));
    } else {
      sessionStorage.removeItem("user");
    }
  }, [user]);

  return (
    <userContext.Provider value={{ user, setUser }}>
      {children}
    </userContext.Provider>
  );
};

export const SearchContext = createContext({
  landingSearchTerm: "",
  // eslint-disable-next-line
  setLandingSearchTerm: (_: any) => {},
});

// eslint-disable-next-line
export const SearchProvider = ({ children }: any) => {
  const [landingSearchTerm, setLandingSearchTerm] = useState("");

  return (
    <SearchContext.Provider value={{ landingSearchTerm, setLandingSearchTerm }}>
      {children}
    </SearchContext.Provider>
  );
};

export interface Listing {
  id: string;
  title: string;
  description: string;
  price: number;
  category: string;
  address: string;
  zipcode: string;
  location: string;
  datePosted: number;
  dateLastEdited: string;
  user: User;
}

export interface User {
  id: string;
  name: string;
  email: string;
  phone: string;
  password: string;
  listings: Listing[];
}
