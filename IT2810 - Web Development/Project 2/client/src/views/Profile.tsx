import { gql, useQuery } from "@apollo/client";
import Listing from "../components/Listing";
import "./styles/Profile.css";
import { useUser } from "../utilities/context";
import { useEffect } from "react";
import { useNavigate } from "react-router";

const GET_USER = gql`
  query Query($getUserId: ID!) {
    getUser(id: $getUserId) {
      email
      name
      phone
      listings {
        id
        title
        datePosted
        description
        price
      }
    }
  }
`;

function Profile() {
  const navigate = useNavigate();
  const { user } = useUser();

  const { data, loading } = useQuery(GET_USER, {
    variables: {
      getUserId: user?.id,
    },
  });

  useEffect(() => {
    if (!user) navigate("/login");
  });

  return (
    <>
      <h1 id="header">My profile</h1>
      <div id="profilePage">
        <div id="ppAndInfo">
          <img
            id="pp"
            src="https://avatars.dicebear.com/api/adventurer-neutral/mail%40ashallendesign.co.uk.svg"
            alt=""
          ></img>
          <section id="profileInfo">
            <h2 id="name">{!loading && data && data.getUser.name}</h2>
            <p id="address">Navneveien 46, 7050</p>
          </section>
        </div>
        <div id="postings">
          <h2>Postings</h2>
          <hr id="line" />
          <div id="post">
            {!loading &&
              data &&
              data.getUser.listings.map(
                (item: {
                  id: string;
                  title: string;
                  description: string;
                  price: number;
                }) => {
                  return <Listing category={""} key={item.id} {...item} />;
                },
              )}
          </div>
        </div>
      </div>
    </>
  );
}

export default Profile;
