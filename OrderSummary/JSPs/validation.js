function validate(form) {
	valid = form.lastname.value != "" && form.firstname.value != "" && form.address.value != "" && form.city.value != "" && form.state.value != "" && form.zipcode.value != "";
	if(!valid) {
		alert("Please fill in all required fields.");
	}
	return valid;
}