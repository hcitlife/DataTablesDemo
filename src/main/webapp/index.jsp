<html>
<head>
    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <!-- DataTables -->
    <script type="text/javascript"charset="utf8"src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css">

</head>
<body>

<!--Table表格显示-->
<table id="datatable" width="100%" border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Operation</th>
    </tr>
    <thead>
</table>
<script type="text/javascript">

    $(document).ready(function() {
        $("#datatable").dataTable({
            "processing": true,
            "serverSide": true,
            "ajax" : "load",
            "columns": [
                {"data": "id", "bSortable": false},
                {"data": "firstName"},
                {"data": "lastName"}
            ]
        });
    });
</script>
</body>
</html>
