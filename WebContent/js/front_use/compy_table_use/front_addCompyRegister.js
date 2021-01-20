$(".reset_compy_info_btn").on('click', function() {
	$('#cp_name').val("");
	$('#cp_id').val("");
	$('#cp_address').val("");
	$('#cp_boss').val("");
})
$(".reset_compy_contact_btn").on('click', function() {
	$('#cp_contact_man').val("");
	$('#cp_contact_email').val("");
	$('#cp_phone').val("");
})
$(".reset_account_pwd_btn").on('click', function() {
	$('#cp_account').val("");
	$('#cp_pwd').val("");
	$('#cp_pwd_again').val("");
})