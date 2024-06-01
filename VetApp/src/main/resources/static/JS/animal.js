document.addEventListener('DOMContentLoaded', function () {
    const customerDropdown = document.getElementById('customer');

    // Veritabanından tüm customer id'lerini al
    fetch('/v1/customers/all-id')
        .then(response => response.json())
        .then(data => {
            const idList = data.data;
            const customerDropdown = document.getElementById('customer');

            // Her bir customer id'sini dropdown menüsüne ekle
            idList.forEach(customer => {
                const option = document.createElement('option');
                option.value = customer.id;
                option.textContent = customer.id;
                customerDropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching customer IDs:', error);
        });

    // Go Back butonunun tıklanma olayını dinleme
    document.getElementById('goBackButton').addEventListener('click', function () {
        // Welcome sayfasına yönlendirme
        window.location.href = '/welcome';
    });

    // Go Add Animal butonunun tıklanma olayını dinleme
    document.getElementById('goAddButton').addEventListener('click', function () {
        // Animal sayfasına yönlendirme
        window.location.href = '/animal';
    });

    // Form gönderildiğinde çalışacak fonksiyon
    document.getElementById('animalForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Sayfa yenilenmesini engelle

        // Formdaki değerleri al
        const name = document.getElementById('name').value;
        const species = document.getElementById('species').value;
        const breed = document.getElementById('breed').value;
        const gender = document.getElementById('gender').value;
        const colour = document.getElementById('colour').value;
        const dateOfBirth = document.getElementById('dateOfBirth').value;
        const customerId = document.getElementById('customer').value; // Değişen kısım

        // Eski hata mesajını temizle
        document.getElementById('error-message').innerText = '';
        document.getElementById('error-message').style.display = 'none';

        // API'ye POST isteği gönder
        fetch('/v1/animals', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: name,
                species: species,
                breed: breed,
                gender: gender,
                colour: colour,
                dateOfBirth: dateOfBirth,
                customer: {
                    id: customerId
                }
            }),
        })
            .then(response => response.json()) // Yanıtı JSON formatında parse eder
            .then(data => {
                console.log('Response:', data); // Başarılı yanıtı konsola yazdırır
                if (!data.status) {
                    // Eğer bir hata varsa yeni hata mesajını gösterir
                    document.getElementById('error-message').innerText = data.message;
                    document.getElementById('error-message').style.display = 'block';
                } else {
                    // Başarılı kayıt sonrası geçici bilgilendirme mesajı gösterir
                    document.getElementById('error-message').innerText = data.message;
                    document.getElementById('error-message').style.backgroundColor = '#28a745'; // Green background for success
                    document.getElementById('error-message').style.display = 'block';

                    // 3 saniye bekledikten sonra kullanıcıyı yönlendirir
                    setTimeout(() => {
                        window.location.href = '/welcome';
                    }, 2000);
                }
            })
            .catch((error) => {
                // Hata durumunda hatayı konsola yazdırır
                console.error('Error:', error);
            });
    });
});