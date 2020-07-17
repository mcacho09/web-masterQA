<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Punto de Venta - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
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
                    <c:import url="/html/menu-left.jsp"/>
                </section>
            </div>

				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">

							<div class="row">
								<div class="col-lg-12">
									<ol class="breadcrumb">
                                        <li><a href="home.htm">Dashboard</a></li>
                                        <li class="active"><span>Ventas</span></li>
									</ol>
									
                                    <div class="clearfix">
                                        <h1 class="pull-left">Punto de Venta</h1>


                                        <div class="filter-block">
                                            <div class="form-group pull-right">
                                                <button class="btn btn-primary"><i class="fa fa-plus-circle"></i> Nueva</button>
                                            </div>

                                            <div class="form-group form-group-select2 pull-right">
                                                <select style="width:150px" id="sel21">
                                                    <option value="United States">- Tienda -</option>
                                                    <option value="United States">AGS Plaza Patria</option>
                                                    <option value="United States">AGS 2do anillo</option> 
                                                    <option value="United States">AGS Centro 1</option> 
                                                    <option value="United States">AGS Centro 2</option> 
                                                </select>
                                            </div>
                                            <div class="form-group form-group-select2 pull-right">
                                                <select style="width:150px" id="sel22">
                                                    <option value="OXXO1">- Plaza -</option> 
                                                    <option value="OXXO1">Oxxo Aguasacalientes</option> 
                                                    <option value="OXXO1">Oxxo Le&oacute;n</option> 
                                                    <option value="OXXO1">Oxxo Quer&eacute;taro</option> 
                                                    <option value="OXXO1">Oxxo San Luis</option> 
                                                </select>
                                            </div>
                                        </div>
                                    </div>
								</div>
							</div>

                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="main-box">
                                        <header class="main-box-header clearfix">
                                            <h2>Ingreso R&aacute;pido de Productos</h2>
                                        </header>
                                        <div class="main-box-body clearfix">
                                            <form class="form-inline" role="form">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" id="exmaplePrependButton" placeholder="7501055305339">
                                                        <span class="input-group-addon"><i class="fa fa-barcode fa-lg"></i></span>
                                                    </div>
                                                    <button type="submit" class="btn btn-default btn-sm"><i class="fa fa-search-plus"></i> Buscar</button>
                                                </div>


                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" id="exmaplePrependButton" placeholder="Mega Cookies" readonly/>
                                                    </div>
                                                    <!--div class="input-group col-md-2">
                                                        <input type="text" class="form-control" id="exmaplePrependButton" placeholder="$7.99" readonly/>
                                                    </div-->
                                                    <!--div class="input-group col-md-2">
                                                        <input type="text" class="form-control" id="exmaplePrependButton" placeholder="$11.50" readonly/>
                                                    </div-->
                                                    <button type="submit" class="btn btn-success btn-sm"><i class="fa fa-check"></i> Agregar</button>
                                                </div>
                                            </form>

                                        </div> 
                                        <!--div class="alert alert-warning fade in">
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                            <i class="fa fa-warning fa-fw fa-lg"></i>
                                            <strong>¡Advertencia!</strong> No hay ningún producto con el código <b>123456</b>
                                        </div-->
                                    </div>
                                </div>  
                            </div><!-- /row -->


                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="main-box clearfix">
                                        <div class="tabs-wrapper profile-tabs">
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#tab-venta" data-toggle="tab">Venta</a></li>
                                                <li><a href="#tab-cambio" data-toggle="tab">Cambio</a></li>
                                                <li><a href="#tab-devolucion" data-toggle="tab">Devolucion</a></li>
                                            </ul>
                                            
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="tab-venta">

                                                    <div class="table-responsive">
                                                        <table class="table table-striped table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Cantidad</th>
                                                                    <th>Producto</th>
                                                                    <th class="text-right hidden-xs"><span>Costo</span></th>
                                                                    <th class="text-right hidden-xs"><span>Venta</span></th>
                                                                    <th class="text-right hidden-lg hidden-md hidden-sm"><span>Precio</span></th>
                                                                    <th class="text-right"><span>Total</span></th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                    <tr>
                                                                        <td class="col-md-2 col-sm-2 col-xs-4">
                                                                            <input type="text" value="3" class="col-md-5 col-sm-5 col-xs-6"/>
                                                                            <a href="#"><i class="fa fa-minus-circle"></i></a>
                                                                            <a href="#"><i class="fa fa-plus-circle"></i></a>
                                                                        </td>
                                                                        <td>Mega Cookies <br/><span class="badge badge-default">7501055305339</span></td>
                                                                        <td class="text-right hidden-xs"><font color="red">$7.99</font></div></td>
                                                                        <td class="text-right hidden-xs"><font color="green">$11.5</font></td>
                                                                        <td class="text-right hidden-lg hidden-md hidden-sm"><font color="red">$7.99</font><br/><font color="green">$11.5</font></td>
                                                                        <td class="text-right"><span class="badge badge-default">$34.5</span></td>
                                                                        <td>
                                                                            <a href="#" class="table-link">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-image fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                            <a href="#" class="table-link danger">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="col-md-2 col-sm-2 col-xs-4">
                                                                            <input type="text" value="1" class="col-md-5 col-sm-5 col-xs-6"/>
                                                                            <a href="#"><i class="fa fa-minus-circle"></i></a>
                                                                            <a href="#"><i class="fa fa-plus-circle"></i></a>
                                                                        </td>
                                                                        <td>Mega Nuez <br/><span class="badge badge-default">7501055305339</span></td>
                                                                        <td class="text-right hidden-xs"><font color="red">$7.99</font></div></td>
                                                                        <td class="text-right hidden-xs"><font color="green">$11.5</font></td>
                                                                        <td class="text-right hidden-lg hidden-md hidden-sm"><font color="red">$7.99</font><br/><font color="green">$11.5</font></td>
                                                                        <td class="text-right"><span class="badge badge-default">$11.5</span></td>
                                                                        <td>
                                                                            <a href="#" class="table-link">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-image fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                            <a href="#" class="table-link danger">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="col-md-2 col-sm-2 col-xs-4">
                                                                            <input type="text" value="20" class="col-md-5 col-sm-5 col-xs-6"/>
                                                                            <a href="#"><i class="fa fa-minus-circle"></i></a>
                                                                            <a href="#"><i class="fa fa-plus-circle"></i></a>
                                                                        </td>
                                                                        <td>Mega Almendras <br/><span class="badge badge-default">7501055305339</span></td>
                                                                        <td class="text-right hidden-xs"><font color="red">$7.99</font></div></td>
                                                                        <td class="text-right hidden-xs"><font color="green">$11.5</font></td>
                                                                        <td class="text-right hidden-lg hidden-md hidden-sm"><font color="red">$7.99</font><br/><font color="green">$11.5</font></td>
                                                                        <td class="text-right"><span class="badge badge-default">$230</span></td>
                                                                        <td>
                                                                            <a href="#" class="table-link">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-image fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                            <a href="#" class="table-link danger">
                                                                                <span class="fa-stack">
                                                                                    <i class="fa fa-square fa-stack-2x"></i>
                                                                                    <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                                                                </span>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>

                                                </div><!-- /tab -->
                                            
                                                <div class="tab-pane fade" id="tab-cambio">
                                                Cambio
                                                </div><!-- /tab -->

                                                <div class="tab-pane fade" id="tab-devolucion">
                                                Devolucion
                                                </div><!-- /tab -->
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div><!-- /row -->

                            <div class="row">
                                <div class="col-lg-4 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-dollar green-bg"></i>
                                        <span class="value">Venta</span>
                                        <span class="headline">Subtotal <span class="badge badge-default">$ 858.13</span></span>
                                        <span class="headline">Iva <span class="badge badge-default">$ 107.2</span></span>
                                        <span class="headline">Total <span class="badge badge-default">1,153.89</span></span>

                                    </div>
                                </div>
                                <div class="col-lg-5 col-md-6 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-retweet yellow-bg"></i>
                                        <span class="value">Movimientos</span>
                                        <span class="headline">Cambios <span class="badge badge-default">5</span></span>
                                        <span class="headline">Devoluciones <span class="badge badge-default">100</span></span>
                                        <span class="headline">&nbsp;</span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-check emerald-bg"></i>
                                        <span class="headline">¿Todo listo?</span>
                                        <span class="headline"><button class="btn btn-primary"><b>¡Procesar!</b></button></span>
                                        <span class="headline">&nbsp;</span>
                                        <span class="headline">&nbsp;</span>
                                    </div>
                                </div>
                            </div>
                            <!-- /row -->

						</div>
					</div>

					<footer id="footer-bar" class="row">
						<c:import url="/html/footer.html" />
					</footer>
					
				</div>
			</div>
		</div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
    <script src="js/select2.min.js"></script>

    <!-- this page specific inline scripts -->
    <script>
    $(function($) {
        //nice select boxes
        $('#sel21').select2();
        $('#sel22').select2();
    });
    </script>
</body>

</html>