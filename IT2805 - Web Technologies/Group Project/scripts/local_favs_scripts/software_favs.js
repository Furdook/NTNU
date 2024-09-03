// Can be changed to the interval 1-40 once every id is in place
for (i = 11; i <= 20; i++) {
  if (localStorage.getItem("fav" + i) === null) {
    localStorage.setItem("fav" + i, "off");
  }
  this["fav" + i] = document.getElementById("fav" + i);
  let fav_btn = this["fav" + i];
  let fav_btn_id = fav_btn.getAttribute("id");
  let fav_btn_link = "favlink" + i;
  let fav_color = "favcolor" + i;
  // Styling so the visuals on button doesn't reset on page refresh
  if (localStorage.getItem(fav_color) === null) {
    localStorage.setItem(fav_color, "#f1f1f1");
  }
  let color = "'" + localStorage.getItem(fav_color) + "'";
  console.log(color + fav_btn_id);
  // Getting the fav_btn link through the corresponding log_btn
  this["log" + i] = document.getElementById("log_btn" + i);
  let log_btn = this["log" + i];
  // Designing the buttons based on their locally stored values
  fav_btn.addEventListener("click", function () {
    if (localStorage.getItem(fav_btn_id) === "off") {
      localStorage.setItem(fav_color, "indianred");
      localStorage.setItem(fav_btn_id, "on");
      localStorage.setItem(fav_btn_link, log_btn.getAttribute("href"));
      document.getElementById(fav_btn_id).style.backgroundColor =
        localStorage.getItem(fav_color);
    } else if (localStorage.getItem(fav_btn_id) != "off") {
      localStorage.setItem(fav_color, "#f1f1f1");
      localStorage.setItem(fav_btn_id, "off");
      document.getElementById(fav_btn_id).style.backgroundColor =
        localStorage.getItem(fav_color);
    }
  });
  document.getElementById(fav_btn_id).style.backgroundColor =
    localStorage.getItem(fav_color);
}
