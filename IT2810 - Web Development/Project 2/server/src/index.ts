import { ApolloServer } from "@apollo/server";
import mongoose from "mongoose";
import User from "./models/users.js";
import Listings from "./models/listings.js";
import { startStandaloneServer } from "@apollo/server/standalone";
import { typeDefs } from "./schema.js";

const MONGO_URI = "mongodb://admin:sAlpinus23@it2810-57.idi.ntnu.no:27017/";
await mongoose
  .connect(MONGO_URI, {
    dbName: "Marketplace",
  })
  .then(() => {
    console.log(`Connected to Marketplace database through ` + MONGO_URI);
  })
  .catch((err) => {
    console.log(err.message);
  });

const resolvers = {
  Query: {
    async getUsers() {
      const users = await User.find();
      return users;
    },

    loginUser: async (_, { email, password }) => {
      const user = await User.findOne({ email: email, password: password });
      return user;
    },

    getListings: async () => {
      const listings = await Listings.find();
      return listings;
    },

    getUser: async (_, args) => {
      const user = await User.findById(args.id);
      return user;
    },

    getListing: async (_, args) => {
      const listing = await Listings.findById(args.id);
      return listing;
    },

    getListingsByCategory: async (_, args) => {
      const listings = await Listings.find({ category: args.category });
      return listings;
    },

    getListingsByFilters: async (_, { category, location }) => {
      const query: any = {};
      if (category) {
        query.category = category;
      }
      if (location) {
        query.location = { $regex: new RegExp(location, "i") };
      }
      const listings = await Listings.find(query);
      return listings;
    },

    getListingsByFiltersLists: async (_, { category, locations }) => {
      const query: any = {};
      if (category) {
        query.category = { $in: category };
      }
      if (locations) {
        query.location = {
          $in: locations.map((location) => new RegExp(location, "i")),
        };
      }
      const listings = await Listings.find(query);
      return listings;
    },

    getListingsByTitle: async (_, { input }) => {
      const { title } = input;
      const query = { title: { $regex: new RegExp(title, "i") } };
      const listings = await Listings.find(query);
      return listings;
    },
  },
  User: {
    listings: async (parent) => {
      const listings = await Listings.find({ user: parent.id });
      return listings;
    },
  },
  Listing: {
    user: async (parent) => {
      const users = await User.findById(parent.user);
      return users;
    },
  },

  Mutation: {
    createUser: async (_, { name, email, phone, password }) => {
      const user = await User.create({
        name,
        email,
        phone,
        password,
      });
      return user;
    },
    createListing: async (
      _,
      {
        title,
        description,
        price,
        category,
        address,
        zipcode,
        location,
        datePosted,
        user,
      }
    ) => {
      const listing = await Listings.create({
        title,
        description,
        price,
        category,
        address,
        zipcode,
        location,
        datePosted,
        dateLastEdited: datePosted,
        user,
      });
      await User.updateOne({ id: user }, { $push: { listings: listing.id } });
      return listing;
    },
    updateUser: async (_, { id, name, email, phone, password }) => {
      const user = await User.findByIdAndUpdate(id, {
        name,
        email,
        phone,
        password,
      });
      return user;
    },
    updateListing: async (
      _,
      {
        id,
        title,
        description,
        price,
        category,
        address,
        zipcode,
        location,
        dateLastEdited,
      }
    ) => {
      const listing = await Listings.findByIdAndUpdate(id, {
        title,
        description,
        price,
        category,
        address,
        zipcode,
        location,
        dateLastEdited,
      });
      return listing;
    },
  },
};

const server = new ApolloServer({
  typeDefs,
  resolvers,
});

const { url } = await startStandaloneServer(server, {
  listen: { port: 4000 },
});

console.log(`Server started at: ${url}`);
