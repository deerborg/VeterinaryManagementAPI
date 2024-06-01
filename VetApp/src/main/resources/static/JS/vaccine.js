document.addEventListener('DOMContentLoaded', function () {
    const customerDropdown = document.getElementById('animal');


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


    document.getElementById('goBackButton').addEventListener('click', function () {
        // Welcome sayfasına yönlendirme
        window.location.href = '/welcome';
    });


    document.getElementById('goAddButton').addEventListener('click', function () {
        // Animal sayfasına yönlendirme
        window.location.href = '/animal';
    });


    document.getElementById('vaccineForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Sayfa yenilenmesini engelle

        // Formdaki değerleri al
        const name = document.getElementById('name').value;
        const code = document.getElementById('code').value;
        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate').value;
        const animal = document.getElementById('animal').value;


        // Eski hata mesajını temizle
        document.getElementById('error-message').innerText = '';
        document.getElementById('error-message').style.display = 'none';

        // API'ye POST isteği gönder
        fetch('/v1/vaccines', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: name,
                code: code,
                startDate: startDate,
                endDate: endDate,
                animal: {
                    id: animal
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