<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Dashbaord - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/libs/fullcalendar.css" type="text/css" />
    <link rel="stylesheet" href="css/libs/fullcalendar.print.css" type="text/css" media="print" />
    <link rel="stylesheet" href="css/compiled/calendar.css" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/libs/morris.css" />
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

<body>

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
                                    <li><a href="home.htm">HOME</a></li>
                                    <li class="active"><span><a href="home.htm">Dashboard</a></span></li>
								</ol>
								
                                <div class="clearfix">
                                    <h1 class="pull-left">Dashboard</h1>
                                </div>
							</div>
						</div>
					</div>
				</div><!-- /row -->

                <div class="row">
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
                        <div class="main-box infographic-box">
                            <i class="fa fa-cube emerald-bg"></i>
                            <span class="headline">SKU/u</span>
                            <span class="value">150</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
                        <div class="main-box infographic-box">
                            <i class="fa fa-dollar green-bg"></i>
                            <span class="headline">Ventas/$</span>
                            <span class="value">2.5k</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
                        <div class="main-box infographic-box">
                            <i class="fa fa-dollar yellow-bg"></i>
                            <span class="headline">Costos/$</span>
                            <span class="value">658</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
                        <div class="main-box infographic-box">
                            <i class="fa fa-dollar yellow-bg"></i>
                            <span class="headline">Gastos/$</span>
                            <span class="value">1.4k</span>
                        </div>
                    </div>
                </div><!-- /row -->

                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2><i class="fa fa-bar-chart-o"></i> Ventas, Costos y Gastos</h2>
                            </header>
                            
                            <div class="main-box-body clearfix">
                                <div class="row">
                                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                        <div id="graph1"></div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                        <div class="row graph-nice-legend">
                                            <div class="graph-legend-row col-lg-12 col-md-12 col-sm-12 col-xs-6">
                                                <div class="graph-legend-row-inner">
                                                    <span class="badge">Utilidad Bruta</span>
                                                    <span class="graph-legend-value">&dollar;94,382</span>
                                                </div>
                                            </div>
                                            <div class="graph-legend-row col-lg-12 col-md-12 col-sm-12 col-xs-6">
                                                <div class="graph-legend-row-inner">
                                                    <span class="badge">Utilidad Operativa</span>
                                                    <span class="graph-legend-value">&dollar;77,059</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /legend -->
                                </div>
                            </div>

                        </div>
                    </div>
                </div><!-- /row -->

                <div class="row">
                    <div class="col-lg-4">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2><i class="fa fa-bar-chart-o"></i> clientes por Plaza</h2>
                            </header>
                            
                            <div class="main-box-body clearfix" style="height: 286px;">
                                <div id="hero-donut" style="height: 270px;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-8">
                        <div class="main-box clearfix">
                            <header class="main-box-header clearfix">
                                <h2><i class="fa fa-bar-chart-o"></i> Metas Comerciales</h2>
                            </header>
                            
                            <div class="main-box-body clearfix">
                                <label><strong>Meta Global</strong> <span class="badge">30%</span></label>
                                <div class="progress progress-4x">
                                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%;"></div>
                                </div>

                                <hr/>

                                <label><strong>Oxxo</strong> <span class="badge">70%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;"></div>
                                </div>
                                <label><strong>Tiendita</strong> <span class="badge">25%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" style="width: 25%;"></div>
                                </div>
                                <label><strong>Hogar</strong> <span class="badge">20%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;"></div>
                                </div>
                                <label><strong>Super</strong> <span class="badge">5%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="100" style="width: 5%;"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div><!-- /row -->

                <div class="row">
                    <div class="col-lg-6">
                        <div class="main-box">
                            
                            <div class="main-box-body clearfix no-header">
                                <div id="calendar"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2><i class="fa fa-bar-chart-o"></i> M&eacute;tricas Operativas</h2>
                            </header>
                            

                                <div class="main-box">
                                    <div class="clearfix">
                                        <div class="infographic-box merged merged-top pull-left">
                                            <i class="fa fa-map-marker emerald-bg"></i>
                                            <span class="value emerald">800</span>
                                            <span class="headline">Total de clientes</span>
                                        </div>
                                        <div class="infographic-box merged merged-top merged-right pull-left">
                                            <i class="fa fa-map-marker green-bg"></i>
                                            <span class="value green">100</span>
                                            <span class="headline">clientes para Hoy</span>
                                        </div>
                                    </div>
                                    <div class="clearfix">
                                        <div class="infographic-box merged pull-left">
                                            <i class="fa fa-check yellow-bg"></i>
                                            <span class="value yellow">50</span>
                                            <span class="headline">Avance</span>
                                        </div>
                                        <div class="infographic-box merged merged-right pull-left">
                                            <i class="fa fa-times red-bg"></i>
                                            <span class="value red">50</span>
                                            <span class="headline">No Visitadas</span>
                                        </div>
                                    </div>
                                    
                                    <div class="clearfix">
                            <div class="main-box-body clearfix">
                                
                                <hr/>
                                
                                <label><strong>Avance Semanal</strong> <span class="badge">30%</span></label>
                                <div class="progress progress-4x">
                                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%;"></div>
                                </div>

                                <hr/>

                                <label><strong>Oxxo</strong> <span class="badge">70%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;"></div>
                                </div>
                                <label><strong>Tiendita</strong> <span class="badge">25%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" style="width: 25%;"></div>
                                </div>
                                <label><strong>Hogar</strong> <span class="badge">20%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;"></div>
                                </div>
                                <label><strong>Super</strong> <span class="badge">5%</span></label>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="100" style="width: 5%;"></div>
                                </div>
                            </div>
                                    </div>
                                    
                                </div>

                            
                        </div>
                    </div>
                </div><!-- /row -->

				<footer id="footer-bar" class="row">
                    <p id="footer-copyright" class="col-xs-12">&copy; 2015 <a href="http://www.logistikapp.com/" target="_blank">LogistikApp</a> - Powered by <a href="http://www.retailsbs.com/" target="_blank">Retail Software Business Solutions</a></p>
				</footer>
				
			</div>
		</div>
	</div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

    <!-- this page specific scripts -->
    <script src="js/jquery-ui.custom.min.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    <script src="js/raphael-min.js"></script>
    <script src="js/morris.min.js"></script>
    <script src="js/jquery.knob.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

    <!-- this page specific inline scripts -->
    <script>
    $(document).ready(function() {

        graphArea2 = Morris.Line({
            element: 'graph1',
            data: [
                {period: '2010 Q1', iphone: 2666, ipad: null, itouch: 2647},
                {period: '2010 Q2', iphone: 2778, ipad: 2294, itouch: 2441},
                {period: '2010 Q3', iphone: 4912, ipad: 1969, itouch: 2501},
                {period: '2010 Q4', iphone: 3767, ipad: 3597, itouch: 5689},
                {period: '2011 Q1', iphone: 6810, ipad: 1914, itouch: 2293},
                {period: '2011 Q2', iphone: 5670, ipad: 4293, itouch: 1881},
                {period: '2011 Q3', iphone: 4820, ipad: 3795, itouch: 1588},
                {period: '2011 Q4', iphone: 15073, ipad: 5967, itouch: 5175},
                {period: '2012 Q1', iphone: 10687, ipad: 4460, itouch: 2028},
                {period: '2012 Q2', iphone: 8432, ipad: 5713, itouch: 1791}
            ],
            /*lineColors: ['#2980b9', '#7f8c8d', '#27ae60', '#8e44ad', '#c0392b', '#f39c12'],*/
            lineColors: ['#2ecc71', '#f1c40f', '#e74c3c', '#3498db', '#9b59b6', '#95a5a6'],
            xkey: 'period',
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['iPhone', 'iPad', 'iPod Touch'],
            hideHover: 'auto',
            resize: true
        });

        graphDonut = Morris.Donut({
            element: 'hero-donut',
            data: [
                {label: 'Super', value: 75 },
                {label: 'Oxxo', value: 750 },
                {label: 'Tiendita', value: 287 },
                {label: 'Hogar', value: 300 }
            ],
            colors: ['#2ecc71', '#f1c40f', '#e74c3c', '#3498db', '#9b59b6', '#95a5a6'],
            /*formatter: function (y) { return y + "%" },*/
            resize: true
        });

        
        
        
        $('#external-events div.external-event').each(function() {
            
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };
            
            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);
            
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
            
        });
    
    
        /* initialize the calendar
        -----------------------------------------------------------------*/
        
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        var calendar = $('#calendar').fullCalendar({
            header: {
                left: '',
                center: 'title',
                right: 'prev,next'
            },
            selectable: true,
            selectHelper: true,
            select: function(start, end, allDay) {
                var title = prompt('Event Title:');
                if (title) {
                    calendar.fullCalendar('renderEvent',
                        {
                            title: title,
                            start: start,
                            end: end,
                            allDay: allDay
                        },
                        true // make the event "stick"
                    );
                }
                calendar.fullCalendar('unselect');
            },
            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function(date, allDay) { // this function is called when something is dropped
            
                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');
                
                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);
                
                // assign it the date that was reported
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;
                
                // copy label class from the event object
                var labelClass = $(this).data('eventclass');
                
                if (labelClass) {
                    copiedEventObject.className = labelClass;
                }
                
                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
                
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
                
            },
            buttonText: {
                prev: '<i class="fa fa-chevron-left"></i>',
                next: '<i class="fa fa-chevron-right"></i>'
            },
            events: [
                {
                    title: 'Oxxo +1',
                    start: new Date(y, m, 1),
                    className: 'label-success'
                },
                {
                    title: 'Capacitación Zona Bajío',
                    start: new Date(y, m, d-5),
                    end: new Date(y, m, d-2),
                    allDay: true
                },
                {
                    id: 999,
                    title: 'Oxxo +1',
                    start: new Date(y, m, d-3, 16, 0),
                    allDay: false,
                    className: 'label-danger'
                },
                {
                    id: 999,
                    title: 'Oxxo +1',
                    start: new Date(y, m, d+4, 16, 0),
                    allDay: false
                },
                {
                    title: 'Reunión',
                    start: new Date(y, m, d, 9, 0),
                    allDay: false,
                    className: 'label-danger'
                },
                {
                    title: 'Oxxo Ags +1',
                    start: new Date(y, m, d+1, 19, 0),
                    end: new Date(y, m, d+1, 22, 30),
                    allDay: false,
                    className: 'label-info'
                },
                {
                    title: 'Ir a logistikapp.com',
                    start: new Date(y, m, 28),
                    end: new Date(y, m, 29),
                    url: 'http://logistikapp.com/',
                    className: 'label-primary'
                }
            ]
        });
        
        
    });
    </script>
</body>

</html>