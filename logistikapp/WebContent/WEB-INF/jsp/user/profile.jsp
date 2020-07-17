<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Usuario - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!-- Sweetalert -->
	<link rel="stylesheet" type="text/css" href="css/sweetalert.css">

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
    <style>
        hr { 
            display: block;
            margin-top: 0.5em;
            margin-bottom: 0.5em;
            margin-left: auto;
            margin-right: auto;
            border-style: inset;
            border-width: 1px;
        }      
		.containersito
     	{
      		width:150px;
     		height:150px; 
     		display:block; 
     		position:relative;
     		overflow:hidden;
     	}
		.containersito img 
    	{
     		position:absolute;
     		top:0;
		    left:0; 
		    height:100%;
     		width:100%;
     	}
     	
     	.pictureedit{
     		position:absolute;
     		top:0;
		    left:0; 
		    height:100%;
     		width:100%;
     		z-index:9;
     		opacity: .0;
     		-webkit-transition: 1s ease-in-out;
     		transition: 1s ease-in-out;
     		background: grey;
     		border-radius: 50%!important;
     		cursor: pointer!important;
     	}

        .pictureedit:hover{
        	opacity: .9;
        }
        .containercito img:hover{
        	opacitu: .4;
        	filter: blur(100px);
        	-webkit-filter: blur(10px);
        }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<header class="navbar" id="header-navbar">
	    <c:import url="/html/menu-top.jsp"/>
	</header>

    <div id="page-wrapper" class="container ">
        <div class="row">
            <div id="nav-col">
                <section id="col-left" class="col-left-nano">
                    <c:import url="/html/menu-left.jsp"/>
                </section>
            </div>

            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">

                        <div class="row" id="user-profile">
                            <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="main-box clearfix">
                                
                                    <header class="main-box-header clearfix">
                                        <h2>${user.username} <span class="badge badge-${user.active == 'S' ? 'success' : 'danger' }"><c:out value="${user.active == 'S' ? 'Activo' : 'Inactivo' }"/></span></h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                    
                                        <div class="form-group">
                                            <span class="badge bagde-default"><c:out value="${user.userlogin}"/></span>
                                            <label>(login)</label>
                                        </div>        
                                        
                                        <div class="form-group">
                                           <c:if test="${isme}">
                                           		<c:if test="${not defaultimage}">
	                                        		<a href="javascript:borrarImagen();"><i style="float:right" class="fa fa-times"></i></a>
	                                        	</c:if>
     		                                   	<div class="profile-img img-responsive center-block containersito">
     		                                   		<!-- <a href="fileupload.htm?redireccion=${redireccion}&login_user=${useracegi.userlogin}">
     		                                   		</a> -->
     		                                   		<img src="${user.image}"/>
     		                                   		<div class="pictureedit">
     		                                   			<span style="text-align:center; position:absolute; top:35%; left:15%; color: lightgrey;">
     		                                   				<i class="fa fa-edit fa-2x"></i>
     		                                   				<br>Cambiar imagen
     		                                   			</span>
     		                                   		</div>
     		                                   	</div>    
	                                       </c:if>
                                           <c:if test="${not isme}">	                                        	
     		                                   	<div class="profile-img img-responsive center-block containersito"><img src="${user.image}"/></div>    
	                                       </c:if>
                                        	                                             	                  	                                      
                                         
                                        </div>
                                        
                                        <c:set var="userprofile" value="NaN"/>
                                        <c:choose>
	                                        <c:when test="${user.profile == PERFIL_ADM}"><c:set var="userprofile" value="${GLO_ADM}"/></c:when>
	                                        <c:when test="${fn:contains(user.profile, 'SUP')}"><c:set var="userprofile" value="${GLO_SUP}"/></c:when>
	                                        <c:when test="${user.profile == PERFIL_RET}"><c:set var="userprofile" value="${GLO_RET}"/></c:when>
	                                        <c:when test="${fn:contains(user.profile, 'DRI')}"><c:set var="userprofile" value="${GLO_DRI}"/></c:when>
	                                        <c:when test="${user.profile == PERFIL_STO}"><c:set var="userprofile" value="${GLO_STO}"/></c:when>
	                                        <c:when test="${user.profile == PERFIL_DEM}"><c:set var="userprofile" value="${GLO_DEM}"/></c:when>
	                                        <c:when test="${user.profile == PERFIL_LGK}"><c:set var="userprofile" value="${GLO_LGK}"/></c:when>
	                                        <c:when test="${user.profile == PERFIL_CCT}"><c:set var="userprofile" value="${GLO_CCT}"/></c:when>
                                        </c:choose>
                                        
                                        <div class="form-group">
                                            <div class="profile-label">
                                                <span class="badge badge-danger"><c:out value="${userprofile}"/></span>
                                                <label>(perfil)</label>
                                            </div>
	                                        <c:if test="${user.superuser == 'S' }">
	                                            <div class="profile-stars">
	                                                <i class="fa fa-star"></i>
	                                                <span>Super Usuario</span>
	                                            </div>
	                                        </c:if>
	                                        <div align="center">
	                                            <!-- edit -->
	                                            <a href="profileupd.htm?id=${user.id_user}" class="table-link edit">
	                                                <span class="fa-stack">
	                                                    <i class="fa fa-square fa-stack-2x"></i>
	                                                    <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
	                                                </span>
	                                            </a><!-- /edit -->
	
	                                            <!-- edit passwd -->
	                                            <a href="passwdprofileupd.htm?id=${user.id_user}" class="table-link edit">
	                                                <span class="fa-stack">
	                                                    <i class="fa fa-square fa-stack-2x"></i>
	                                                    <i class="fa fa-lock fa-stack-1x fa-inverse"></i>
	                                                </span>
	                                            </a><!-- /edit passwd -->
	                                        </div>
                                        </div>
                                        
                                        <div class="form-group">
	                                        <div class="profile-details">
	                                            <ul class="fa-ul">
	                                                <li><i class="fa-li fa fa-gear"></i>Puesto <span><c:out value="${user.job}"/></span></li>
	                                                <li><i class="fa-li fa fa-envelope"></i>Email <a href="mailto:${user.email}"><c:out value="${user.email}"/></a></li>
	                                                <li><i class="fa-li fa fa-phone"></i>Tel&eacute;fono 1 <span><c:out value="${user.phone1}"/></span></li>
	                                                <!-- 
	                                                <li><i class="fa-li fa fa-phone"></i>Tel&eacute;fono 2 <span><c:out value="${user.phone2}"/></span></li>
	                                                 -->
	                                            </ul>
	                                        </div>
                                        </div>
                                        
                                        <div class="form-group">
	                                        <div class="profile-since">
	                                            Creado el <fmt:formatDate type="date" pattern="dd/MMM, yyyy" value="${user.created}"/>
	                                            <c:if test="${empty user.modified}">
	                                                <span class="badge badge-default">${user.login}</span>
	                                            </c:if>
	                                        </div>

                                            <c:if test="${not empty user.modified}">
                                                <div class="profile-since">
                                                    Modificado el <fmt:formatDate type="date" pattern="dd/MMM, yyyy" value="${user.modified}"/> <span class="badge badge-default">${user.login}</span>
                                                </div>
                                            </c:if>

                                        </div>

                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
                <form action="#" style="display:none;" enctype="multipart/form-data" method="POST">
                	<input type="hidden" name="login_user" id="login_user" value="${login_user}" />
                	<input name="file" type="file" accept=".png, .jpg, .jpeg">
                </form>

                <footer id="footer-bar" class="row">
                    <c:import url="/html/footer.html" />
                </footer>
                    
            </div>
        </div>
    </div>
	
	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.js"></script>
	
	<script src="js/sweetalert.min.js" type="text/javascript"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script>
		function borrarImagen(){	
			location.href = "profile.htm?id="+${user.id_user}+"&imagen="+0;
		}
		
		$('.pictureedit').on('click',function(){
			//$('input[type="file"]').click();
			location.href = 'fileupload.htm?redireccion=${redireccion}&login_user=${useracegi.userlogin}';
		});
		
		$('input[type="file"]').on('change',function(){
			if (this.files[0].size > (5*1024*1024)){
				swal('Alerta',"La imagen que intenta subir es mayor a 5mb","warning");
			}else{
				swal('Mensaje',"Imagen buena","info");
				console.log(this.files[0]);
				var reader = new FileReader();
				reader.onload = function(e){
					
					swal({   
						title: "Nueva imagen de perfil",   
						text: "¿Deseas guardar la imagen?",   
						showCancelButton: true,   
						confirmButtonColor: "#DD6B55",   
						confirmButtonText: "Si",   
						cancelButtonText: "No",   
						closeOnConfirm: false,   
						closeOnCancel: false,
						imageUrl: "" + e.target.result 
					}, function(isConfirm){   
						if (isConfirm) {     
							$('form').submit();
						} else {     
							swal("Cancelado", "La imagen no se guardará", "error");
							$('form').reset();
					} });
				}
				reader.readAsDataURL(this.files[0]);
			}
		});
	</script>

</body>
 
</html>