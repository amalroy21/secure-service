<html>
    <head>
         <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
         <script type="text/javascript" src="jquery.jcryption.3.1.0.js"></script>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Shopping Cart</title>
    </head>
<body background="graphics/background.jpg">
<div id = "h1"><h2 align="center">Demo for End to End Security in Web Services</h2></div>
<div id = "h2"><h2 align="center">Shopping Payment Page</h2></div>
<div id = "h3" style="display: none" ><h2 align="center">Payment Confirmation Page</h2></div>
<div id = "h4" style="display: none"><h2 align="center">Payment Successful!!!</h2></div>

<form id="payment" align="center">
<table id="shopping-table" align="center">
            <tr>
                <td><name='header'/>Credit Card</td>
                <td><input id="cc_no" placeholder="16 digit card number"></input></td>
            </tr>
            <tr>
                <td><name='header'/>Expiry</td>
                <td><input id ="mm" placeholder="MM" size="2"></input>
                <input id ="yy" placeholder="YY" size="2"></input></td>
            </tr>
            <tr>
                <td><name='header'/>CVV</td>
                <td><input id="cvv" placeholder="3-digits" size="3"></input></td>
            </tr>
            <tr>
                <td><name='header'/>Post Code</td>
                <td><input id="zipcode" placeholder="5-digits" size="5"></input></td>
            </tr>
            <tr>
                <td><input type="button" value="Order Now" onClick=checkForm()></td>
                <td><input type="reset" value="Reset Form" onClick="this.form.reset()"></td>
            </tr>
</table>
</form>
</body>
<script Language="JavaScript">
        var domain = "http://localhost:9090";
        var keys = null;
        function getKeys() {
        	$.jCryption.getKeys(domain + "/v1/getKeys?userId=ACC1111", function(
        			receivedKeys) {
        		keys = receivedKeys;
        	});
        }

        getKeys();

        function checkForm() {
            var cc = $('#cc_no').val();
            var expiry = $('#mm').val() + $('#yy').val();
            var cvv = $('#cvv').val();
            var zipcode = $('#zipcode').val();
            var salt = Math.floor(1 + Math.random()*1000);
            $.jCryption.encrypt(cc+"~"+expiry +"~"+ cvv+"~"+zipcode+"~"+salt, keys, function(ccencrypted) {
            $.ajax({
                        url: domain + "/submit?userId=ACC1111",
                        global: false,
                        type: "POST",
                        data: ccencrypted,
                        async: false,
                        success: function() {
                            $('#h3').show();
                            $('#h4').show();
                            $('#h1').hide();
                            $('#h2').hide();
                            $('#shopping-table').hide();
                        }
                    });
        });
        }
    </script>
</html>