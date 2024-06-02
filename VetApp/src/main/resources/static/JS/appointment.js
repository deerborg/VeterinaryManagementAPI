document.addEventListener('DOMContentLoaded', function () {
    // Hayvan dropdown'u doldurma
    fetch('/v1/animals/all-animals-id')
        .then(response => response.json())
        .then(data => {
            const idList = data.data;
            const animalDropDown = document.getElementById('animal');

            idList.forEach(animal => {
                const option = document.createElement('option');
                option.value = animal.id;
                option.textContent = animal.id;
                animalDropDown.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching animal IDs:', error);
        });

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
    document.getElementById('appointmentForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Sayfa yenilenmesini engelle

        // Formdaki değerleri al
        const startDate = document.getElementById('startDate').value;
        const animal = document.getElementById('animal').value;
        const doctor = document.getElementById('doctor').value;

        // Eski hata mesajını temizle
        const errorMessage = document.getElementById('error-message');
        errorMessage.innerText = '';
        errorMessage.style.display = 'none';

        // API'ye POST isteği gönder
        fetch('/v1/appointments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                dateTime: startDate,
                animal: {
                    id: animal
                },
                doctor: {
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
                } else {
                    // Başarılı kayıt sonrası geçici bilgilendirme mesajı gösterir
                    errorMessage.innerText = data.message;
                    errorMessage.style.backgroundColor = '#28a745'; // Başarı için yeşil arkaplan
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
            });

    });
});
