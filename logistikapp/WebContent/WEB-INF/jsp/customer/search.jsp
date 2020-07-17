<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Busqueda de clientes - LogistikApp</title>

    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
	
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
<![endif]-->
</head>

<body class="fixed-header fixed-leftmenu theme-blue">
	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>

	<div id="page-wrapper" class="container">
		<div class="row">
			<div id="nav-col">
				<section id="col-left" class="col-left-nano">
					<c:import url="/html/menu-left.jsp" />
				</section>
			</div>

			<div id="content-wrapper">
				<div class="row">
					<div class="col-lg-12">

						<div class="row">
							<div class="col-lg-12">
								<ol class="breadcrumb">
									<li><a href="home.htm">Home</a></li>
									<li class="active"><a href="customer.htm"><span>Clientes</span></a></li>
								</ol>
								<h1 class="pull-left">B&uacute;squeda de clientes</h1>
							</div>
						</div>
						
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <h2>Se encontraron 4 clientes con "<b>CA</b>"</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">

                                        <div class="table-responsive">
                                            <table id="table-example" class="table table-hover table-condensed">
                                                <thead>
                                                    <tr>
                                                        <th>Nombre</th>
                                                        <th>Plaza</th>
                                                        <th>Ruta</th>
                                                        <th>Viaje</th>
                                                        <th>Fecha</th>
                                                        <th>Estado</th>
                                                        <th>Checkin</th>
                                                        <th>Chofer</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
                                                    <c:forEach var="sto" items="${list}">
	                                                    <tr>
	                                                        <td>
	                                                            <a href="#">${sto.store_name}</a><br/>
	                                                            <small>Venustiano Carranza #104</small>
	                                                        </td>
	                                                        <td>
	                                                            Oxxo Aguascalientes<br/>
	                                                            <small><span class="label label-default">General</span></small>
	                                                        </td>
	                                                        <td>
	                                                            Oxxo Ags 1
	                                                        </td>
	                                                        <td>
	                                                            Oxxo Viernes 14
	                                                        </td>
	                                                        <td>
	                                                            14/03
	                                                        </td>
	                                                        <td>
	                                                            <span class="label label-info">Programado</span>
	                                                        </td>
	                                                        <td></td>
	                                                        <td>
	                                                            Jerónimo
	                                                        </td>
	                                                    </tr>
                                                    </c:forEach>
                                                
                                                    <tr>
                                                        <td>
                                                            <a href="#">CARRANZA 1 AGU</a><br/>
                                                            <small>Venustiano Carranza #104</small>
                                                        </td>
                                                        <td>
                                                            Oxxo Aguascalientes<br/>
                                                            <small><span class="label label-default">General</span></small>
                                                        </td>
                                                        <td>
                                                            Oxxo Ags 1
                                                        </td>
                                                        <td>
                                                            Oxxo Viernes 14
                                                        </td>
                                                        <td>
                                                            14/03
                                                        </td>
                                                        <td>
                                                            <span class="label label-info">Programado</span>
                                                        </td>
                                                        <td></td>
                                                        <td>
                                                            Jerónimo
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <a href="#">CATEDRAL AGU</a><br/>
                                                            <small>Moctezuma #101</small>
                                                        </td>
                                                        <td>
                                                            Oxxo Aguascalientes<br/>
                                                            <small><span class="label label-default">General</span></small>
                                                        </td>
                                                        <td>
                                                            Oxxo Ags 1
                                                        </td>
                                                        <td>
                                                            Oxxo Viernes 11
                                                        </td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <td rowspan="2">
                                                            <a href="#">CENTRAL CAMIONERA</a><br/>
                                                            <small>Av de la Convención S/N</small>
                                                        </td>
                                                        <td rowspan="2">
                                                            Oxxo Aguascalientes<br/>
                                                            <small><span class="label label-default">General</span></small>
                                                        </td>
                                                        <td rowspan="2">
                                                            Oxxo Ags 1
                                                        </td>
                                                        <td>
                                                            Oxxo Lunes 11
                                                        </td>
                                                        <td>
                                                            11/03
                                                        </td>
                                                        <td>
                                                            <span class="label label-primary">En Transito</span>
                                                        </td>
                                                        <td>
                                                            <span class="label label-success"><i class="fa fa-check"></i> Visitado</span><br/>
                                                            <small><i class="fa fa-clock-o"></i> 11/03 13:45</small>
                                                        </td>
                                                        <td>
                                                            Rodolfo
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Oxxo Viernes 10
                                                        </td>
                                                        <td>
                                                            10/03
                                                        </td>
                                                        <td>
                                                            <span class="label label-success">Finalizado</span>
                                                        </td>
                                                        <td>
                                                            <span class="label label-danger"><i class="fa fa-times"></i> No Visitado</span>
                                                        </td>
                                                        <td>
                                                            Maximiliano
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <a href="#">CARRANZA 2 AGU</a><br/>
                                                            <small>Venustiano Carranza #204</small>
                                                        </td>
                                                        <td>
                                                            Oxxo Aguascalientes<br/>
                                                            <small><span class="label label-default">General</span></small>
                                                        </td>
                                                        <td>
                                                            Oxxo Ags 1
                                                        </td>
                                                        <td>
                                                            Oxxo Viernes 14
                                                        </td>
                                                        <td>
                                                            14/03
                                                        </td>
                                                        <td>
                                                            <span class="label label-info">Programado</span>
                                                        </td>
                                                        <td></td>
                                                        <td>
                                                            Jerónimo
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
						
					</div>
					
					<footer id="footer-bar" class="row">
						<c:import url="/html/footer.html" />
					</footer>
				</div>
			</div>
		</div>
	</div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- this page specific scripts -->
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.fixedHeader.js"></script>
	<script src="js/dataTables.tableTools.js"></script>
	<script src="js/jquery.dataTables.bootstrap.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    </script>
		
</body>
</html>
