document.addEventListener('DOMContentLoaded', function() {
    // Go Back butonunun tıklanma olayını dinleme
    document.getElementById('goBackButton').addEventListener('click', function() {
        // Welcome sayfasına yönlendirme
        window.location.href = '/welcome';
    });
    // Go Add Animal butonunun tıklanma olayını dinleme
    document.getElementById('goAddAvailableDateButton').addEventListener('click', function() {
        // Animal sayfasına yönlendirme
        window.location.href = '/availableDate';
    });

    // Form gönderme olayını dinleme
    document.getElementById('registerForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Formun varsayılan submit davranışını engeller

        // Form alanlarından değerleri alır
        const fullname = document.getElementById('fullname').value;
        const phone = document.getElementById('phone').value;
        const emailName = document.getElementById('emailName').value;
        const emailDomain = document.getElementById('emailDomain').value;
        const email = emailName + emailDomain; // Mail adresini birleştirir
        const address = document.getElementById('address').value;
        const city = document.getElementById('city').value;

        // Verileri sunucuya göndermek için fetch API'sini kullanır
        fetch('/v1/doctors', {
            method: 'POST', // HTTP POST metodunu belirtir
            headers: {
                'Content-Type': 'application/json', // İçeriğin JSON formatında olduğunu belirtir
            },
            body: JSON.stringify({
                name: fullname, // Kullanıcının adı
                phone: phone, // Telefon numarası
                mail: email, // E-posta adresi
                address: address, // Adres bilgisi
                city: city, // Şehir bilgisi
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

    // Telefon numarasının uzunluğunu sınırlar (max 11 hane)
    document.getElementById('phone').addEventListener('input', function() {
        const phoneInput = document.getElementById('phone');
        if (phoneInput.value.length > 11) {
            phoneInput.value = phoneInput.value.slice(0, 11); // Fazla karakterleri siler
        }
    });
});