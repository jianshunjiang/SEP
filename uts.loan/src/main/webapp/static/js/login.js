$(document).ready(function () {

    $('#login_form').bootstrapValidator({
        message: 'This value is not valid',        //验证错误时的信息
        feedbackIcons: {        //验证时显示的图标
            valid: 'glyphicon glyphicon-ok',      //正确图标
            invalid: 'glyphicon glyphicon-remove',        //错误图标
            validating: 'glyphicon glyphicon-refresh'        //正在更新图标
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: 'Please enter password'
                    },
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'Please enter a valid value.'
                    },
                    callback: {
                        message: "Please enter a valid value",
                        callback: function (value, validator) {
                            var reg_str;

                            var usertype = document.getElementById("userType");
                            var index = usertype.selectedIndex;
                            var option = usertype.options[index];

                            if (option.value === "student") {
                                reg_str = /^[0-9]{8}/;
                            }
                            if (option.value === "manager") {
                                reg_str = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                            }
                            if (option.value === "admin") {
                                reg_str = /^[a-zA-Z0-9_]+$/;
                            }
                            console.log("test something, reg: " + reg_str + ", value: " + value + ", result: "+ reg_str.test(value));
                            return reg_str.test(value);

                        }
                    }
                }
            }

        }

    });
});
    function refreshLabel() {
        var label = document.getElementById("username_label");
        var usertype = document.getElementById("userType");
        var index = usertype.selectedIndex;

        if (usertype.options[index].value === "student") {
            label.innerText = "Student id";
            $("#username_label").html("Student Id");
            document.getElementById("username").setAttribute("placeholder", "Enter student ID...");
        }
        if (usertype.options[index].value === "manager") {
            label.innerText = "Email";
            $("#username_label").html("Email");
            document.getElementById("username").setAttribute("placeholder", "Enter email...");
        }
        if (usertype.options[index].value === "admin") {
            label.innerText = "Username";
            $("#username_label").html("Username");
            document.getElementById("username").setAttribute("placeholder", "Enter username...");
        }
        document.getElementById("username").value ="";

    }