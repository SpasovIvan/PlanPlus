function validatePasswords() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    if (password !== confirmPassword) {
        document.getElementById('passwordError').innerText = 'Пароли не совпадают!';
        return false;
    } else {
        document.getElementById('passwordError').innerText = '';
        return true;
    }
}