<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flight</title>
</head>
<body ${flight}>
    <h1>${flight.getNumber()} raqamli reysga o'zgartirish kiritildi!</h1>
    <table border="1">
        <thead>
            <tr>
                <td>Number</td>
                <td>Plane</td>
                <td>From</td>
                <td>To</td>
                <td>Price</td>
                <td>Flight time</td>
                <td>Company</td>
                <td>Airport</td>
                <td>Ticket count</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${flight.getNumber()}</td>
                <td>${flight.getPlane()}</td>
                <td>${flight.getTownFrom()}</td>
                <td>${flight.getTownTo()}</td>
                <td>${flight.getPrice()}</td>
                <td>${flight.getFlight_time()}</td>
                <td>${flight.getCompany()}</td>
                <td>${flight.getAirport()}</td>
                <td>${flight.getTicket_count()}</td>
            </tr>
        </tbody>
    </table>
</body>
</html>