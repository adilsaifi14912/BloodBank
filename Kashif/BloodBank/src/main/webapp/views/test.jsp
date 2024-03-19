<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Request Status Table</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    .accepted {
        background-color: #c9f9c9;
    }
    .rejected {
        background-color: #ffb3b3;
    }
    .pending {
        background-color: #ffff99;
    }
    .blood-group-info {
        display: inline-block;
        padding: 10px;
        text-align: center;
        background-color: #f5f5f5;
        color: #333;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    .accepted-info {
        background-color: #c9f9c9;
        color: #228B22;
    }
    /* Styling thead */
    .table-header {
        background-color: #007bff;
        color: white;
    }
    .table-header th {
        padding: 10px;
        font-weight: bold;
        text-transform: uppercase;
        border-bottom: 2px solid #ddd; /* increase border size */
    }
</style>
</head>
<body>

<div class="blood-group-info">
    <p style="margin: 0;">Blood Group: A+</p>
</div>

<table>
  <thead class="table-header">
    <tr>
      <th>Serial No.</th>
      <th>Request Date</th>
      <th>Donor/Receiver</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>2024-03-18</td>
      <td>Donor</td>
      <td class="accepted">Accepted</td>
    </tr>
    <tr>
      <td>2</td>
      <td>2024-03-17</td>
      <td>Receiver</td>
      <td class="rejected">Rejected</td>
    </tr>
    <tr>
      <td>3</td>
      <td>2024-03-16</td>
      <td>Donor</td>
      <td class="pending">Pending</td>
    </tr>
    <!-- Add more rows as needed -->
  </tbody>
</table>

</body>
</html>
