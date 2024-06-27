const axios = require('axios');
const CryptoJS = require('crypto-js');

const SECRET_KEY = '1234567890123456'; // 16-byte key for AES-128
const IV = '1234567890123456'; // 16-byte IV for AES

function encrypt(data, key, iv) {
    const keyUtf8 = CryptoJS.enc.Utf8.parse(key);
    const ivUtf8 = CryptoJS.enc.Utf8.parse(iv);
    const encrypted = CryptoJS.AES.encrypt(data, keyUtf8, {
        iv: ivUtf8,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}

function decrypt(encryptedData, key, iv) {
    const keyUtf8 = CryptoJS.enc.Utf8.parse(key);
    const ivUtf8 = CryptoJS.enc.Utf8.parse(iv);
    const decrypted = CryptoJS.AES.decrypt(encryptedData, keyUtf8, {
        iv: ivUtf8,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return CryptoJS.enc.Utf8.stringify(decrypted);
}

// Example UserDetailsFormDTO object
const userDetails = {
    name: 'John Doe',
    idNumber: '1234567890',
    idValidityBegin: '2023-01-01',
    idValidityEnd: '2033-01-01',
    occupation: 'Software Engineer',
    annualSalary: 100000,
    province: 'Some Province',
    city: 'Some City',
    district: 'Some District',
    addressDetail: '123 Some Street'
};

// Convert UserDetailsFormDTO object to JSON string
const data = JSON.stringify(userDetails);

// Encrypt data and send to server
const encryptedData = encrypt(data, SECRET_KEY, IV);
console.log('Encrypted data:', encryptedData);
axios.post('http://localhost:8080/api/process', { data: encryptedData })
    .then(response => {
        console.log('Encrypted data from server:', response.data);

        // Decrypt data received from server
        const decryptedData = decrypt(response.data, SECRET_KEY, IV);
        console.log('Decrypted data:', decryptedData);
    })
    .catch(error => {
        console.error('Error:', error);
    });
