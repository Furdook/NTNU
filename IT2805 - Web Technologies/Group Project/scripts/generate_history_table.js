const table_div = document.getElementById("history_table");
// Loop for each existing link on the webpage (i < num of links in website)
for (i = 1; i < 40; i++) {
  let log_btn = "log_btn" + i;
  let log_btn_time = "log_btn" + i + "time";
  // Gets all the locally stored log_btn id's and displays them if their not equal to "off" or null.
  if (
    localStorage.getItem(log_btn) != "off" &&
    localStorage.getItem(log_btn) != null
  ) {
    // Creating necessary elements with attributes
    this["table_row" + i] = document.createElement("tr");
    this["table_item" + i] = document.createElement("td");
    this["anchor" + i] = document.createElement("a");
    this["anchor" + i].href = localStorage.getItem(log_btn);
    this["anchor" + i].style.marginLeft = "20px";

    this["anchor" + i].append(localStorage.getItem(log_btn));
    this["table_item" + i].append(localStorage.getItem(log_btn_time));
    this["table_item" + i].append(this["anchor" + i]);
    this["table_item" + i].setAttribute("class", "table_item");
    this["table_row" + i].appendChild(this["table_item" + i]);
    table_div.prepend(this["table_row" + i]);
  }
}
