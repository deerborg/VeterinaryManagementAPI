function goBackToMain() {
    window.location.href = '/welcome'; // /welcome sayfasına yönlendir
}
document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
});

function fetchUsers() {
    fetch('/v1/users')
        .then(response => response.json())
        .then(data => {
            if (data.status && Array.isArray(data.data)) {
                const userTableBody = document.querySelector('#userTable');
                userTableBody.innerHTML = '';
                data.data.forEach(user => {
                    const formattedDate = user.registerDate ? formatDate(user.registerDate) : 'N/A';
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${user.username}</td>
                        <td>${formattedDate}</td>
                        <td>
                            <button class="btn btn-primary" onclick="showUpdateForm(${user.id})">Update</button>
                            <button class="btn btn-danger" onclick="disableUser(${user.id})">Delete</button>
                        </td>
                        <td>${user.enabled ? '<span style="color:green;">True</span>' : '<span style="color:red;">False</span>'}</td> <!-- Status sütunu -->
                    `;
                    userTableBody.appendChild(row);
                });
            } else {
                console.error('Unexpected response data:', data);
            }
        })
        .catch(error => console.error('Error fetching users:', error));
}

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function showUpdateForm(id) {
    document.getElementById('updateUserId').value = id;
    document.getElementById('updateForm').style.display = 'block';
}

function hideUpdateForm() {
    document.getElementById('updateForm').style.display = 'none';
}

function submitUpdateForm() {
    const id = document.getElementById('updateUserId').value;
    const role = document.getElementById('updateRole').value;

    const updatedUser = {
        id: id,
        roles: [role] // Sadece rol güncellenecek
    };

    fetch('/v1/users', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedUser)
    })
        .then(response => response.json())
        .then(data => {
            console.log('User updated:', data);
            fetchUsers();  // Kullanıcı listesini yeniden yükleyin
            hideUpdateForm();  // Formu kapatın
        })
        .catch(error => console.error('Error updating user:', error));
}

function disableUser(id) {
    fetch(`/v1/users/disabled/${id}`, {
        method: 'PUT'
    })
        .then(response => response.json())
        .then(data => {
            console.log('User disabled:', data);
            fetchUsers();  // Kullanıcı listesini yeniden yükleyin
        })
        .catch(error => console.error('Error disabling user:', error));
}
