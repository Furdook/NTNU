// Creating the nav_bar and styling it
const nav_bar = document.getElementById("nav_bar");
nav_bar.style.position = "fixed";
nav_bar.style.width = "100%";
nav_bar.style.textAlign = "center";
nav_bar.style.top = 0;
nav_bar.style.zIndex = "10";

array = ["Homepage", "Software", "Hardware", "Games", "About us", "Contact us"]; //Array to define our navigation bar names

for (i of array) {
  this[i + "span"] = document.createElement("span"); //creates dynamic variables using the array
  this[i + "anchor"] = document.createElement("a");
  this[i + "anchor"].classList = "hyperlink";
  // Adds a class to the span
  let span = this[i + "span"];
  span.classList = "span";

  this[i + "anchor"].href = i + ".html"; //adds the link to our navigation bar
  this[i + "anchor"].appendChild(document.createTextNode(i));

  span.appendChild(this[i + "anchor"]);
  //span designs
  span.style.display = "inline-block";
  span.style.padding = "20px";
  span.style.marginLeft = "8%";

  nav_bar.appendChild(span); //adds to navigation bar
}
// Checks if the text should be "Dark Mode" or "Light Mode" on the sidebar, depending on the locally stored "mode".
if (localStorage.getItem("mode") === "light") {
  side = ["History", "Favorites", "Dark Mode"];
} else {
  side = ["History", "Favorites", "Light Mode"];
}
// Creating the sidebar
const sidebar_div = document.getElementById("sidebar");
for (i in side) {
  const ul = document.createElement("ul");
  this[i + "li"] = document.createElement("li");
  this[i + "anchor"] = document.createElement("a");
  this[i + "anchor"].appendChild(document.createTextNode(side[i]));
  // Checks what the current list item is and makes it into a function or a link
  if (side[i] === "Dark Mode" || side[i] === "Light Mode") {
    this[i + "anchor"].href = "javascript:darkMode()";
  } else {
    this[i + "anchor"].href = side[i] + ".html";
  }
  // Adding a class to the link and dark mode function text to style it.
  this[i + "anchor"].classList = "hyperlink";
  this[i + "li"].appendChild(this[i + "anchor"]);
  ul.appendChild(this[i + "li"]);
  sidebar_div.appendChild(ul);
}
// To make the sidebar disappear when not hovered. (Left position is different when "active" is toggled.)
sidebar_div.addEventListener("mouseover", function () {
  document.getElementById("sidebar").classList.toggle("active");
});
sidebar_div.addEventListener("mouseout", function () {
  document.getElementById("sidebar").classList.toggle("active");
});
// Variables for Dark Mode
const dark_mode_card_color = "rgba(100, 0, 0, 0.9)";
const light_mode_card_color = "#f1f1f1";
const light_mode_background_image = 'url("images/bg_light.jpg")';
const dark_mode_background_image = 'url("images/bg_dark.jpg")';
const dark_mode_navbar_color = "rgba(100, 0, 0, 0.9)";
const light_mode_navbar_color = "rgba(0, 0, 0, 0.9)";

// Sets default mode to light if the user hasn't visited the site before.
if (localStorage.getItem("mode") === null) {
  localStorage.setItem("mode", "light");
}
// Gets the locally stored mode and gives the user its preferred settings.
if (localStorage.getItem("mode") === "light") {
  // Styling the nav_bar and sidebar background
  nav_bar.style.backgroundColor = light_mode_navbar_color;
  sidebar_div.style.backgroundColor = light_mode_navbar_color;
  const x = document.getElementsByClassName("card");
  for (i = 0; i < x.length; i++) {
    x[i].style.backgroundColor = light_mode_card_color; // Styling each card background
  }
  document.body.style.backgroundImage = light_mode_background_image; // Styling the background image
} else if (localStorage.getItem("mode") === "dark") {
  // Styling the nav_bar and sidebar background
  nav_bar.style.backgroundColor = dark_mode_navbar_color;
  sidebar_div.style.backgroundColor = dark_mode_navbar_color;
  document.body.style.color = "#fff";
  // Styling each card background
  const x = document.getElementsByClassName("card");
  for (i = 0; i < x.length; i++) {
    x[i].style.backgroundColor = dark_mode_card_color; // Styling each card background
  }
  const y = document.getElementsByTagName("a");
  for (i = 0; i < y.length; i++) {
    y[i].style.color = "#f1f1f1"; // Styling each link to improve readability in darkmode
  }
  document.body.style.backgroundImage = dark_mode_background_image; // Styling the background image
  // (Start) Code by Daniel Aranda on https://stackoverflow.com/questions/16611497/how-can-i-get-the-name-of-an-html-page-in-javascript/21343880
  var path = window.location.pathname;
  var page = path.split("/").pop();
  // (End) Code by Daniel Aranda on https://stackoverflow.com/questions/16611497/how-can-i-get-the-name-of-an-html-page-in-javascript/21343880
  if (page === "Contact%20us") {
    document.getElementById("fieldset").style.backgroundColor =
      "rgba(100, 0, 0, 0.9)"; // Prevent console errors from trying to style fieldset on a page it doesn't exist
  }
}
// This is the function that is called when ("Dark mode"/"Light mode") is clicked. It changes the locally stored value for mode.
function darkMode() {
  if (localStorage.getItem("mode") === "dark") {
    location.reload();
    localStorage.setItem("mode", "light");
  } else {
    location.reload();
    localStorage.setItem("mode", "dark");
  }
}
