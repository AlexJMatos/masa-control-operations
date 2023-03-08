$(document).ready(function () {});

async function loginUser() {
  let loginUser = {};
  loginUser.email = document.getElementById("inputEmail").value;
  loginUser.password = document.getElementById("inputPassword").value;

  const request = await fetch("api/login", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginUser),
  });

  const statusCode = await request.status;
  const tokenBody = await request.json();

  if (statusCode === 200) {
    localStorage.token = tokenBody.token;
    localStorage.email = loginUser.email;
    window.location.href = "users.html";
  } else if (statusCode === 401) {
    alert("Invalid credentials. Please try again");
  } else if (statusCode === 404) {
    alert("User with given email not found");
  } else {
    alert("Unknown error has ocurred");
  }
}
