$(document).ready(function() {
    let csrfToken = $('meta[name="_csrf"]').attr('content');
    let csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    $('.add-to-cart').click(function() {
        let productId = $(this).data('id');

        $.ajax({
            url: '/cart/add',
            method: 'POST',
            data: {
                productId: productId
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('.cart-count').text(response.cartSize);
            },
            error: function(xhr, status, error) {
                if (xhr.status === 400) {
                    alert(xhr.responseText);
                } else {
                    console.error('Произошла ошибка при добавлении товара в корзину:', error);
                    alert('Произошла ошибка при добавлении товара в корзину. Пожалуйста, попробуйте еще раз.');
                }
            }
        });
    });
});