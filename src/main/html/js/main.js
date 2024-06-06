//Aller à la page trajet
$('body').on('click', '#bouttonCalendrier', function (e) {
    $.ajax({
        url: 'http://localhost:8080/getAllEpreuves',
        type: 'get',
        success: function (response) {
            $('#page_maincontent').html(response);
            console.log(response);
        }
    });
});

// connexion avec la fonction onSubmit
$('body').on('click', '#bouttonLogin', function (e) {
    var username = $('#inputLogin').val()
    var password = $('#inputPass').val()
    if(username == "" || password == ""){
        addError("Veuillez remplir tous les champs", "loginBandeauError")
        return
    }
    var body = { login: username, password: password }
    body = JSON.stringify(body)
    $.ajax({
        url: 'http://localhost:8080/auth/signin',
        type: 'post',
        data: body,
        contentType: 'application/json; charset=utf-8',
        traditional: true,
        dataType: 'json',
        success: function (response) {
            //store the token in the local storage
            localStorage.setItem('token', response.accessToken);
            localStorage.setItem('username', username);
            //refresh the page
            location.reload();
        },
        error: function (response) {
            var errorMsg = "Une erreur fatale est survenue";
            response = JSON.parse(response.responseText);
            if(response.apierror.message != null){
                errorMsg = response.apierror.message;
            }
            addError(errorMsg, "loginBandeauError");
        }
    });
});

function addError(message, idBandeau) {
    console.log("erreur : " + message);
    $('#loginBandeauErreur').html(message);
    setTimeout(function () {
        $('#loginBandeauErreur').html("");
    }, 5000);
}
