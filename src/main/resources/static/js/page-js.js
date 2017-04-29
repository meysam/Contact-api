$.formUtils.addValidator({
    name: 'nid',
    validatorFunction: function (value, $el, config, language, $form) {
        return validate_nid(value);
    },
    errorMessage: 'کد ملی صحیح وارد نمایید',
    errorMessageKey: 'badNid'
});
$.validate({
    lang: 'fa',
    modules: 'date, security,file,sepa'
});

$(document).on('hidden.bs.modal', '#modal-search-address', function () {
    var _adress = document.getElementById('modal-address').value;
    var splited_address = new Object();
    if (_adress) {
        $("input[name='address.ostan']").val('');
        $("input[name='address.shahrestan']").val('');
        $("input[name='address.bakhsh']").val('');
        $("input[name='address.dehestan']").val('');
        $("input[name='address.dehyari']").val('');

        $("input[name='address.country']").val('ایران');
        $.each(_adress.split('/'), function (index, val) {
            switch (index) {
                case 0:
                    $("input[name='address.ostan']").val(val);
                    break;
                case 1:
                    $("input[name='address.shahrestan']").val(val);
                    break;
                case 2:
                    $("input[name='address.bakhsh']").val(val);
                    break;
                case 3:
                    $("input[name='address.dehestan']").val(val);
                    break;
                case 4:
                    $("input[name='address.dehyari']").val(val);
                    break;
            }

        });
    }
});
$(document).ready(function () {

    $("#search-address").click(function () {
        $("#modal-search-address").modal();
    });
    // $('#address')
    //     .autocomplete({
    //         source: function (request, response) {
    //             $.get('/persons/address', {name: document.getElementById('address').value},
    //                 function (data) {
    //                     response(data);
    //                 })
    //         },
    //         minLength: 1
    //     });
    $('#modal-address')
        .autocomplete({
            source: function (request, response) {
                $.get('/persons/address', {name: document.getElementById('modal-address').value},
                    function (data) {
                        response(data);
                    })
            },
            appendTo: "#modal-search-address",
            minLength: 1
        });

    $(function () {
        $("input:not([name*=english],[name*=email])").farsiInput();
    });
    document.getElementById("firstName").onkeyup = function () {
        //alert(document.getElementById("firstName").value);
        var x = document.getElementById("firstName");
        document.getElementById("fname").innerHTML = x.value;
    };
    document.getElementById("lastName").onkeyup = function () {
        //alert(document.getElementById("firstName").value);
        var x = document.getElementById("lastName");
        document.getElementById("lname").innerHTML = x.value;
    };
    document.getElementById("nationalId").onkeyup = function () {
        //alert(document.getElementById("firstName").value);
        var x = document.getElementById("nationalId");
        document.getElementById("nid").innerHTML = x.value;
    };
});