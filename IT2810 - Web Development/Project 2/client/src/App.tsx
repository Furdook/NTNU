import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import BarNav from "./components/BarNav";
import NewPosting from "./views/NewPosting";
import Profile from "./views/Profile";
import Results from "./views/Results";
import Landing from "./views/Landing";
import PostingPage from "./views/PostingPage";
import NewProfile from "./views/NewProfile";
import Login from "./views/Login";

function App() {
  return (
    <Router basename="project2/">
      <BarNav />
      <div id="routesWrapper">
        <Routes>
          <Route path="/results/:filters" element={<Results />} />
          <Route path="/new-posting" element={<NewPosting />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="*" element={<Landing />} />
          <Route path="/posting/:id" element={<PostingPage />} />
          <Route path="/new-profile" element={<NewProfile />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
