/**
 * show progress dialog.it will block the ui.
 */
function showProgressDialog() {
    $('#progressDialogDivId').OpenDiv();
}

/**
 * Hide the progress dialog.
 */
function hideProgressDialog() {
    $('#progressDialogDivId').CloseDiv();
}


function redirectPage(name) {
    // showProgressDialog();
    $.ajax(name).done(function (html) {
        $("#content").html(html);

    });
    // hideProgressDialog();
}