<html>
    <head>
         <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
         <script type="text/javascript" src="jquery.jcryption.3.1.0.js"></script>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Shopping Cart</title>
    </head>
<body background="graphics/background.jpg">
<h2 align="center">Demo for End to End Security in Web Services</h2>
<h2 align="center">Shopping Payment Page</h2>
<form id="payment" align="center">
<table id="shopping-table" align="center">
            <tr>
                <td><name='header'/>Credit Card</td>
                <td><input id="cc_no" placeholder="16 digit card number"></input></td>
            </tr>
            <tr>
                <td><name='header'/>Expiry</td>
                <td><input id ="mm" placeholder="MM" size="2"></input></td>
                <td><input id ="yy" placeholder="YY" size="2"></input></td>
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
                <td><input type="button" value="Order Now"></td>
                <td><input type="reset" value="Reset Form" onClick="this.form.reset()"></td>
            </tr>
</table>
</form>
</body></html>