$(document).ready(function () {});

async function registerUser() {
  let newUser = {};
  newUser.firstName = document.getElementById("inputFirstName").value;
  newUser.lastName = document.getElementById("inputLastName").value;
  newUser.email = document.getElementById("inputEmail").value;
  newUser.phone = document.getElementById("inputPhone").value;
  newUser.password = document.getElementById("inputPassword").value;

  let repeatPassword = document.getElementById("inputRepeatPassword").value;

  if (repeatPassword != newUser.password) {
    alert("Password dont match. Please try again");
    return;
  }

  const request = await fetch("api/users", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newUser),
  });
  alert("Account created successfully!");
  window.location.href = "login.html";
}
