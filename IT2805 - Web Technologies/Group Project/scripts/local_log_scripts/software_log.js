// Can be changed to the interval 1-40 once every id is in place
for (i = 11; i <= 20; i++) {
  if (localStorage.getItem("log_btn" + i) === null) {
    localStorage.setItem("log_btn" + i, "off");
  }
  this["log" + i] = document.getElementById("log_btn" + i);
  let log_btn = this["log" + i];
  let log_btn_id = log_btn.getAttribute("id");
  let log_btn_id_time = log_btn.getAttribute("id") + "time";

  log_btn.addEventListener("click", function () {
    localStorage.setItem(log_btn_id, log_btn.getAttribute("href"));
    // Code by Rahul from: https://tecadmin.net/get-current-date-time-javascript/ (Start)
    var today = new Date();
    var date =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();
    var time =
      today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    var dateTime = date + " " + time;
    // Code by Rahul from: https://tecadmin.net/get-current-date-time-javascript/ (End)
    localStorage.setItem(log_btn_id_time, dateTime);
  });
}
