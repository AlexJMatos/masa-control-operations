// Call the dataTables jQuery plugin
$(document).ready(function () {
  loadUsers();
  $("#users").DataTable();
  setEmailUser();
});

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}

function setEmailUser() {
  document.getElementById("emailUser").outerHTML = localStorage.email;
}

async function loadUsers() {
  const request = await fetch("api/users", {
    method: "GET",
    headers: getHeaders(),
  });
  let usersHtml = "";
  const users = await request.json();

  for (let user of users) {
    let deleteButton = `<a href="#" 
    onclick="deleteUser(${user.id})"
    class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>`;
    let userHtml = `<tr>
    <td>${user.id}</td>
    <td>${user.firstName} ${user.lastName}</td>
    <td>${user.email}</td>
    <td>${user.phone}</td>
    <td>${deleteButton}</td></tr>`;
    usersHtml += userHtml;
  }
  document.querySelector("#users tbody").outerHTML = usersHtml;
}

async function deleteUser(id) {
  if (!confirm("Do you want to delete the user?")) {
    return;
  }
  const request = await fetch("api/users/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });
  location.reload();
}
