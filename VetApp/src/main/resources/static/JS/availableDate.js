document.addEventListener('DOMContentLoaded', function () {
    // Doktor dropdown'u doldurma
    fetch('/v1/doctors/all-doctor-id')
        .then(response => response.json())
        .then(data => {
            const idList = data.data;
            const doctorDropDown = document.getElementById('doctor');

            idList.forEach(doctor => {
                const option = document.createElement('option');
                option.value = doctor.id;
                option.textContent = doctor.id;
                doctorDropDown.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching doctor IDs:', error);
        });

    // Geri dön butonuna tıklama olayını ekleme
    document.getElementById('goBackButton').addEventListener('click', function () {
        window.location.href = '/welcome';
    });

    // Form gönderim olayını ekleme
    const availableDateForm = document.getElementById('availableDateForm');
    if (availableDateForm) {
        availableDateForm.addEventListener('submit', function (event) {
            event.preventDefault(); // Sayfa yenilenmesini engelle

            // Formdaki değerleri al
            const startDate = document.getElementById('startDate').value;
            const doctor = document.getElementById('doctor').value;

            // Eski hata mesajını temizle
            const errorMessage = document.getElementById('error-message');
            errorMessage.innerText = '';
            errorMessage.style.display = 'none';
            errorMessage.style.backgroundColor = ''; // Hata mesajı arka plan rengini resetle

            // API'ye POST isteği gönder
            fetch('/v1/available-dates', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    date: startDate,
                    doctors: {
                        id: doctor
                    }
                }),
            })
                .then(response => response.json()) // Yanıtı JSON formatında parse eder
                .then(data => {
                    console.log('Response:', data); // Başarılı yanıtı konsola yazdırır
                    if (!data.status) {
                        // Eğer bir hata varsa yeni hata mesajını gösterir
                        errorMessage.innerText = data.message;
                        errorMessage.style.display = 'block';
                        errorMessage.style.backgroundColor = '#dc3545'; // Hata için kırmızı arka plan
                    } else {
                        // Başarılı kayıt sonrası geçici bilgilendirme mesajı gösterir
                        errorMessage.innerText = data.message;
                        errorMessage.style.backgroundColor = '#28a745'; // Başarı için yeşil arka plan
                        errorMessage.style.display = 'block';

                        // 2 saniye bekledikten sonra kullanıcıyı yönlendirir
                        setTimeout(() => {
                            window.location.href = '/welcome';
                        }, 2000);
                    }
                })
                .catch((error) => {
                    // Hata durumunda hatayı konsola yazdırır
                    console.error('Error:', error);
                    errorMessage.innerText = 'An error occurred. Please try again.';
                    errorMessage.style.display = 'block';
                    errorMessage.style.backgroundColor = '#dc3545'; // Hata için kırmızı arka plan
                });
        });
    } else {
        console.error('Form element not found');
    }
});
