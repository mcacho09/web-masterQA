<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Ha ocurrido un problema - LogistikApp</title>
    
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->

    <!-- google font libraries -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

    <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body id="error-page">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div id="error-box">
                    <div class="row">
                        <div class="col-xs-12">
                            <div id="error-box-inner">
                                <img src="img/error-500-v1.png" alt="Error 500"/>
                            </div>
                            <h1>Ha ocurrido un problema</h1>
                            <p>Volver a <a href="home.htm">Dashboard</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    
    <!-- this page specific scripts -->
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    
    <!-- this page specific inline scripts -->
    
</body>
</html>