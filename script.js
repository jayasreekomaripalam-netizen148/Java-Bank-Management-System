docume// Java Bank Management System JavaScript


// Login Validation

function loginUser(){

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;


    if(username === "" || password === ""){

        alert("Please enter username and password");

    }

    else{

        alert("Login Successful!");

        window.location.href="dashboard.html";

    }

}



// Money Transfer

function transferMoney(){

    let amount = document.getElementById("amount").value;


    if(amount === ""){

        alert("Enter transfer amount");

    }

    else{

        alert("₹" + amount + " transferred successfully");

    }

}




// Loan EMI Calculator

function calculateEMI(){

    let amount =
    document.getElementById("loanAmount").value;


    let rate =
    document.getElementById("interestRate").value;


    let years =
    document.getElementById("loanYears").value;



    let monthlyRate = rate / 12 / 100;

    let months = years * 12;



    let emi =
    (amount * monthlyRate * Math.pow(1 + monthlyRate, months)) /
    (Math.pow(1 + monthlyRate, months)-1);



    document.getElementById("emiResult").innerHTML =
    "Monthly EMI: ₹" + emi.toFixed(2);

}



// Welcome Message

window.onload=function(){

console.log(
"Welcome to Java Bank Management System"
);

}

// Show / Hide Password

function showPassword(){

let password =
document.getElementById("password");


if(password.type === "password"){

password.type="text";

}

else{

password.type="password";

}

}

function darkMode(){

document.body.classList.toggle("dark");

}

