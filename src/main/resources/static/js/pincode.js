function getAddress(){
    const form = document.getElementById('pincode');
    pin=form.elements[0].value;
    $.getJSON("https://api.postalpincode.in/pincode/" + pin, function (data) {
        if(data[0].PostOffice==null){
            document.getElementById("errormessage").innerHTML="Invalid PINCODE";
            document.getElementById("pincode").elements[0].value="";
            document.getElementById("submitform").disabled = true;
        }
        var x=data[0].PostOffice[0].District;
        var y=data[0].PostOffice[0].State;
        console.log(x);
        console.log(y);
        document.getElementById("district").setAttribute("placeholder",x);
        document.getElementById("state").setAttribute("placeholder",y);
        document.getElementById("errormessage").innerHTML="";
        document.getElementById("submitform").disabled = false;


    });

    return false;
    
}
