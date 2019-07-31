<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CSC561 | Lab1c</title>
  </head>
<body>

<h3>User 1 Checkout Times</h3>

<table border="1">
				<thead>
                    <th>Check Out Times for User 1</th>
				</thead>

				<tbody>
					@foreach ($transactions->where('user_id', 1) as $transaction)
                    <tr>
                            <td>{{ $transaction->checkout_time }}</td>
                    </tr>
                     @endforeach

                </tbody>
</table> 

<h3>Inventory Items Checked out by User 1 before 2019/05/31</h3>
<table border="1">
				<thead>
                    <th>First Name</th>
					<th>Description</th>
          <th>Checkout Time</th>
				</thead>

				<tbody>

          @foreach($transactions->where('checkout_time', '<', '2019-05-31') as $data)
                    <tr>
                            <td>{{ $data->user->first_name }}</td>
							<td>{{ $data->equipment->first()->description }}</td>
              <td>{{ $data->checkout_time }}
                    </tr>
          @endforeach

        </tbody>
</table> 

</body>
</html>
