const table_div = document.getElementById("favorite_table");
// Loop for each existing link on the webpage (i < num of links in website)
for (i = 1; i < 40; i++) {
  let fav_btn = "fav" + i;
  let fav_btn_link = "favlink" + i;
  // Gets all the locally stored fav_btn id's and displays them if their not equal to "off" or null.
  if (
    localStorage.getItem(fav_btn) != "off" &&
    localStorage.getItem(fav_btn) != null
  ) {
    // Creating necessary elements with attributes
    this["table_row" + i] = document.createElement("tr");
    this["table_item" + i] = document.createElement("td");
    this["anchor" + i] = document.createElement("a");
    this["anchor" + i].href = localStorage.getItem(fav_btn_link);
    this["anchor" + i].style.marginLeft = "20px";

    this["anchor" + i].append(localStorage.getItem(fav_btn_link));
    // this["table_item"+i].append(localStorage.getItem(log_btn_time))
    this["table_item" + i].append(this["anchor" + i]);
    this["table_item" + i].setAttribute("class", "table_item");
    this["table_row" + i].appendChild(this["table_item" + i]);
    table_div.prepend(this["table_row" + i]);
  }
}
