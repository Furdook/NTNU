import mongoose from "mongoose";

const userSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
    match: /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/,
  },
  phone: {
    type: String,
    required: true,
    unique: true,
    match: /^[0-9]{8}$/,
  },
  password: {
    type: String,
    required: true,
    minlength: 3,
  },
  listings: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Listing",
    },
  ],
});

const User = mongoose.model("User", userSchema);

export default User;
