function goBackMain() {
    window.location.href = "/welcome";
}
fetch('/v1/customers/all-customer')
    .then(response => response.json())
    .then(data => {
        if (data.status === true) {
            const customers = data.data;
            const htmlContent = customers.map(customer => {
                return `<li id="customer-${customer.id}">
                            <span id="customer-name-${customer.id}">${customer.name}</span>
                            <div class="button-container">
                                <button class="delete" data-id="${customer.id}" data-name="${customer.name}">Delete</button>
                                <button class="update" data-id="${customer.id}" data-name="${customer.name}" data-phone="${customer.phone}" data-mail="${customer.mail}" data-city="${customer.city}">Update</button>
                            </div>
                            <div class="update-form" id="update-form-${customer.id}" style="display: none;">
                                <label for="update-name-${customer.id}">Name:</label>
                                <input type="text" id="update-name-${customer.id}" placeholder="Name" value="${customer.name}">

                                <label for="update-phone-${customer.id}">Phone:</label>
                                <input type="text" id="update-phone-${customer.id}" placeholder="Phone" value="${customer.phone}">

                                <label for="update-mail-${customer.id}">Mail:</label>
                                <input type="text" id="update-mail-${customer.id}" placeholder="Mail" value="${customer.mail}">

                                <label for="update-city-${customer.id}">City:</label>
                                <select id="update-city-${customer.id}">
                                    <option value="Adana" ${customer.city === 'Adana' ? 'selected' : ''}>Adana</option>
                                    <option value="Ankara" ${customer.city === 'Ankara' ? 'selected' : ''}>Ankara</option>
                                    <option value="İstanbul" ${customer.city === 'İstanbul' ? 'selected' : ''}>İstanbul</option>
                                    <!-- Diğer şehir seçenekleri -->
                                </select>

                                <button class="save-update" data-id="${customer.id}">Save</button>
                            </div>
                        </li>`;
            }).join("");
            document.getElementById("customerList").innerHTML = "<ul>" + htmlContent + "</ul>";

            const deleteButtons = document.querySelectorAll(".delete");
            deleteButtons.forEach(button => {
                button.addEventListener("click", function() {
                    const customerId = this.getAttribute("data-id");
                    const customerName = this.getAttribute("data-name");
                    // Silme isteği gönderme
                    fetch(`/v1/customers/name/${customerName}`, {
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
                                console.log("Müşteri ID'si: ", customerId, " başarıyla silindi.");
                                // Silinen müşteriyi listeden kaldırma
                                document.getElementById(`customer-${customerId}`).remove(); // Müşteriyi listeden kaldırır
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
                    const customerId = this.getAttribute("data-id");
                    const updateForm = document.getElementById(`update-form-${customerId}`);
                    updateForm.style.display = 'block';

                    // "Cancel" butonunu oluştur
                    const cancelButton = document.createElement("button");
                    cancelButton.innerText = "Cancel";
                    cancelButton.classList.add("cancel-update");
                    cancelButton.setAttribute("data-id", customerId);
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
                    const customerId = this.getAttribute("data-id");
                    const updatedName = document.getElementById(`update-name-${customerId}`).value;
                    const updatedPhone = document.getElementById(`update-phone-${customerId}`).value;
                    const updatedMail = document.getElementById(`update-mail-${customerId}`).value;
                    const updatedCity = document.getElementById(`update-city-${customerId}`).value;

                    fetch(`/v1/customers`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id : customerId,
                            name: updatedName,
                            phone: updatedPhone,
                            mail: updatedMail,
                            city: updatedCity,
                            address: "Turkey"
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