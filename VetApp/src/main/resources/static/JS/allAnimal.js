function goBackMain() {
    window.location.href = "/welcome";
}
fetch('/v1/animals/all-animals')
    .then(response => response.json())
    .then(data => {
        if (data.status === true) {
            const animal = data.data;
            const htmlContent = animal.map(animal => {
                return `<li id="animal-${animal.id}">
                            <span id="animal-name-${animal.id}">${animal.name}</span>
                            <div class="button-container">
                                <button class="delete" data-id="${animal.id}" data-name="${animal.name}">Delete</button>
                                <button class="update" data-id="${animal.id}" data-name="${animal.name}">Update</button>
                            </div>
                            <div class="update-form" id="update-form-${animal.id}" style="display: none;">

                                <label for="update-name-${animal.id}">Name:</label>
                                <input type="text" id="update-name-${animal.id}" placeholder="Name" value="${animal.name}">

                                <button class="save-update" data-id="${animal.id}">Save</button>
                            </div>
                        </li>`;
            }).join("");
            document.getElementById("animalList").innerHTML = "<ul>" + htmlContent + "</ul>";

            const deleteButtons = document.querySelectorAll(".delete");
            deleteButtons.forEach(button => {
                button.addEventListener("click", function() {
                    const animalId = this.getAttribute("data-id");
                    const animalName = this.getAttribute("data-name");
                    // Silme isteği gönderme
                    fetch(`/v1/animals/${animalId}`, {
                        method: 'DELETE'
                    })
                        .then(response => response.json())
                        .then(data => {
                            document.getElementById('error-message').innerText = data.message;
                            document.getElementById('error-message').style.backgroundColor = '#28a745'; // Yeşil arka plan başarılı işlem için
                            document.getElementById('error-message').style.display = 'block';

                            // 2 saniye bekleyip sayfayı yenileme
                            setTimeout(() => {
                                window.location.reload();
                            }, 2000);

                            if (data.status === true) {
                                console.log("Müşteri ID'si: ", animalId, " başarıyla silindi.");
                                // Silinen müşteriyi listeden kaldırma
                                document.getElementById(`customer-${animalId}`).remove(); // Müşteriyi listeden kaldırır
                            } else {
                                console.error("Silme işlemi başarısız oldu: " + data.message);
                            }
                        })
                        .catch(error => console.error("Hata oluştu:", error));
                });
            });

            const updateButtons = document.querySelectorAll(".update");
            updateButtons.forEach(button => {
                button.addEventListener("click", function() {
                    const animalId = this.getAttribute("data-id");
                    const updateForm = document.getElementById(`update-form-${animalId}`);
                    updateForm.style.display = 'block';

                    // "Cancel" butonunu oluştur
                    const cancelButton = document.createElement("button");
                    cancelButton.innerText = "Cancel";
                    cancelButton.classList.add("cancel-update");
                    cancelButton.setAttribute("data-id", animalId);
                    cancelButton.style.marginTop = "10px"; // Butonun üstündeki boşluğu ayarla
                    updateForm.appendChild(cancelButton);

                    // "Cancel" butonuna tıklanınca formu kapat
                    cancelButton.addEventListener("click", function() {
                        updateForm.style.display = 'none';
                        this.remove(); // "Cancel" butonunu kaldır
                        // "Update" butonunu tekrar etkinleştir
                        button.disabled = false;
                    });

                    // "Update" butonunu devre dışı bırak
                    button.disabled = true;
                });
            });
            // CustomerAllResponse kısmında hassas verileri silip, service kısmında verileri ekleyebilirsiniz.
            // Sonrasında JSON olarak hassas verileri kaldırın
            const saveUpdateButtons = document.querySelectorAll(".save-update");
            saveUpdateButtons.forEach(button => {
                button.addEventListener("click", function() {
                    const animalId = this.getAttribute("data-id");
                    const updatedName = document.getElementById(`update-name-${animalId}`).value;
                    fetch(`/v1/animals/update-name`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id : animalId,
                            name: updatedName,
                        })
                    })
                        .then(response => response.json())
                        .then(data => {
                            document.getElementById('error-message').innerText = data.message;
                            document.getElementById('error-message').style.backgroundColor = '#28a745'; // Yeşil arka plan başarılı işlem için
                            document.getElementById('error-message').style.display = 'block';

                            // 2 saniye bekleyip sayfayı yenileme
                            setTimeout(() => {
                                window.location.reload();
                            }, 2000);

                            if (data.status === true) {
                                console.log("Müşteri ID'si: ", customerId, " başarıyla güncellendi.");
                                // Güncellenen verileri formdan alıp listeye yansıtma
                                const customerElement = document.getElementById(`customer-name-${customerId}`);
                                customerElement.innerText = updatedName;
                                document.getElementById(`update-form-${customerId}`).style.display = 'none'; // Formu gizle
                            } else {
                                console.error("Güncelleme işlemi başarısız oldu: " + data.message);
                            }
                        })
                        .catch(error => console.error("Hata oluştu:", error));
                });
            });
        } else {
            console.error("İstek başarısız oldu: " + data.message);
        }
    })
    .catch(error => console.error("Hata oluştu:", error));