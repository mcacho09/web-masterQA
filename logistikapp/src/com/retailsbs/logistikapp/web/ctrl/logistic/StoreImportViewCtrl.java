package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.csvreader.CsvReader;
import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategorySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.web.ctrl.upload.FileUpload;

/**
 * Class controller para la vista importar de tiendas
 * @author Juan Carlos Ramos Campos
 * @since 19-03-2015
 */
public class StoreImportViewCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private RetailService retailService;
	private String ACTIVE;
	private String IMPORT_FILE;
	
	public void setIMPORT_FILE(String iMPORT_FILE) {
		IMPORT_FILE = iMPORT_FILE;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}

	public StoreImportViewCtrl(){
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}
	public SupplierService getSupplierService() {
		return supplierService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws ServletRequestBindingException, Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		
		/*-------------------------------------------------------*/

		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Se obtiene el parametro de id_retail y su respectivo objeto de dominio
		Long id_retail = ServletRequestUtils.getLongParameter(arg0, "id");
		Retail retail = retailService.getRetailById(id_retail);
		logger.debug("IMPORT ==> id retail = "+retail.getId_retail());
		//obtiene valor de id_local
		Long id_local = ServletRequestUtils.getLongParameter(arg0, "sel2", 0);
		logger.debug("IMPORT ==> id_local = "+id_local);
		//==========================================================
		HttpSession sess = arg0.getSession();
		
		String SESSION = (String)sess.getAttribute("STRIMP");
		logger.debug("IMPORT ==> SESSION = "+SESSION);
		//==========================================================
		String filename = ServletRequestUtils.getStringParameter(arg0, "filename");
		if(SESSION!=null && SESSION.equals("str"))
			filename = (String)sess.getAttribute("str_filename");
		logger.debug("IMPORT ==> filename = "+filename);
		
		Long id_categoria = null;
		if(arg0.getParameter("id_categoria") != null && !arg0.getParameter("id_categoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(arg0, "id_categoria");
		else if(SESSION!=null && SESSION.equals("str"))
			id_categoria = (Long)sess.getAttribute("str_id_categoria");
		logger.info("id_categoria = "+id_categoria);
		
		Long id_state = null;
		if(arg0.getParameter("id_state")!=null && !arg0.getParameter("id_state").equals(""))
			id_state = ServletRequestUtils.getLongParameter(arg0, "id_state");
		else if(SESSION!=null && SESSION.equals("str"))
			id_state = (Long)sess.getAttribute("str_id_state");
		logger.info("id_state = "+id_state);
		
		Long id_city = null;
		if(arg0.getParameter("id_city")!=null && !arg0.getParameter("id_city").equals(""))
			id_city = ServletRequestUtils.getLongParameter(arg0, "id_city");
		else if(SESSION!=null && SESSION.equals("str"))
			id_city = (Long)sess.getAttribute("str_id_city");
		logger.info("id_city = "+id_city);
		
		//se obtiene lista state
		GetStateSearchCriteria dto_stt = new GetStateSearchCriteria();
		dto_stt.setActive(ACTIVE);
		
		List<StateDTO> list_state = retailService.getStateByCriteria(dto_stt);
		
		logger.info("list_state = "+list_state.size());
		
		StoreCategorySearchCriteria dto = new StoreCategorySearchCriteria();
		dto.setActive(ACTIVE);
		dto.setId_supplier(id_supplier);
		//lista store category
		List<StoreCategory> list_category = retailService.getAllStoreCategoryBySupplier(dto );
		logger.info("list_category="+list_category.size());
		
		Boolean readFile = ServletRequestUtils.getBooleanParameter(arg0, "readFile", false);
		if(SESSION!=null && SESSION.equals("str"))
			readFile = Boolean.parseBoolean((String)sess.getAttribute("str_readFile"));
		
		readFile = (filename != null && !filename.isEmpty());
		
		logger.info("readFile = "+readFile);
		
		ArrayList<Store> storeNoValid = new ArrayList<Store>();
		ArrayList<Store> storeOk = new ArrayList<Store>();
		ArrayList<Store> storeFileError = new ArrayList<Store>();		
		boolean correctFile = true;
		if(readFile){
			//Se lee el archivo
	        if ( filename.endsWith(".csv") ) {
	        	CsvReader locales = new CsvReader(IMPORT_FILE+id_supplier+"/"+filename);
	//	        boolean headers = locales.readHeaders();
		        boolean ok = true;
		        
		        while (locales.readRecord())
		        {
		        	Store store = new Store();
	        		store.setCode(locales.get(0));
	        		if(store.getCode() == null || store.getCode().equals("") || store.getCode().length() > 10  )
	        			ok = false;
		            
		            store.setName(locales.get(1));
		            if(store.getName() == null || store.getName().equals("") || store.getName().length() > 255 )
	        			ok = false;
		            
		            store.setAddress1(locales.get(2));
		            if(store.getAddress1() == null || store.getAddress1().equals("") || store.getAddress1().length() > 255)
	        			ok = false;
		            
		            store.setAddress2(locales.get(3));
		            if(locales.get(3).length() > 255)
	        			ok = false;
	
		            try{
		            	store.setPostal(Integer.parseInt(locales.get(4)));
			            if(store.getPostal() == null || store.getPostal().equals(""))
			            	store.setPostal(00000);
		            }catch(Exception e){
	        			store.setPostal(00000);
		            }
	
		            try{
						//Se verifica que el codigo que se va a ingresar no exista 
			            StoreCodeAvaibleSearchCriteria dto_str = new StoreCodeAvaibleSearchCriteria();
						dto_str.setCode(store.getCode());
						dto_str.setActive(ACTIVE);
						dto_str.setId_retail(id_retail);
					
						retailService.getStoreCodeAvaibleByCriteria(dto_str);
					}catch(Exception e){
						ok = false;
						logger.error("CSV ==> code="+store.getCode()+", retail="+id_retail+" duplicado");
					}
					
		            if(ok)
		            	storeOk.add(store);
		            else	
		            	storeNoValid.add(store);
		            ok = true;
		            
		        } // while locales.readRecord
		        
		        for(int i=0; i<storeOk.size(); i++){
		        	for(int j=i+1; j<storeOk.size(); j++){
		        		if(storeOk.get(i).getCode().equals(storeOk.get(j).getCode()))
		        			storeFileError.add(storeOk.get(i));
		        	}
		        }
	
		        locales.close();
	        }else{
	        	
	        	Workbook book = Workbook.getWorkbook(new File(IMPORT_FILE+id_supplier+"/"+filename));
	        	Sheet sheet = book.getSheet(0);
	        	if (sheet.getColumns() == 8){
		        	for (int i = 1; i < sheet.getRows();i++){
		        		boolean ok = true;
		        		Store store = new Store();
		        		store.setCode(replaceCharacters(sheet.getCell(0,i).getContents().toString()));
		        		if(store.getCode() == null || store.getCode().equals("") || store.getCode().length() > 20  )
		        			ok = false;
			            
		        		String storeName = replaceCharacters(sheet.getCell(1,i).getContents().toString());
			            store.setName(storeName);
			            //Codigo
			            if(store.getName() == null || store.getName().equals("") || store.getName().length() > 255 )
		        			ok = false;
			            
			            //Calle y numero
			            
			            if (sheet.getCell(2,i) != null && !sheet.getCell(2,i).toString().trim().isEmpty() && sheet.getCell(3,i) != null && !sheet.getCell(3,i).toString().trim().isEmpty())
		            		store.setAddress1(replaceCharacters(sheet.getCell(2,i).getContents().toString() + ", " + sheet.getCell(3,i).getContents().toString()));
			            else
		        			ok = false;		            	
			            
			            //Colonia
			            store.setAddress2(replaceCharacters(sheet.getCell(4,i).getContents().toString()));
			            if(sheet.getCell(4,i).getContents().length() > 255)
		        			ok = false;
			            
			            //CP
			            try{
			            	store.setPostal(Integer.parseInt(sheet.getCell(5,i).getContents().toString()));
			            	logger.info("Store Post");
				            if(store.getPostal() == null || store.getPostal().equals(""))
				            	store.setPostal(00000);
			            }catch(NumberFormatException e){
		        			store.setPostal(00000);
			            }
			            //Tel�fono
			            
			            if (sheet.getCell(6, i).getContents().toString() != null && !sheet.getCell(6,i).getContents().toString().trim().isEmpty() && sheet.getCell(6,i).getContents().toString().length() <= 20){
			            	store.setPhone(sheet.getCell(6,i).getContents().toString());
			            }else{
			            	store.setPhone("");
			            }
			            
			            //Correo
			            if (sheet.getCell(7, i).getContents().toString() != null && !sheet.getCell(7,i).getContents().toString().trim().isEmpty()){
			            	store.setEmail(sheet.getCell(7,i).getContents().toString());
			            }else{
			            	store.setEmail("");
			            }
		
			            try{
							//Se verifica que el codigo que se va a ingresar no exista 
				            StoreCodeAvaibleSearchCriteria dto_str = new StoreCodeAvaibleSearchCriteria();
							dto_str.setCode(store.getCode());
							dto_str.setActive(ACTIVE);
							dto_str.setId_retail(id_retail);
						
							retailService.getStoreCodeAvaibleByCriteria(dto_str);
						}catch(Exception e){
							ok = false;
							logger.error("EXCEL ==> code="+store.getCode()+", retail="+id_retail+" duplicado");
						}
						
			            if(ok)
			            	storeOk.add(store);
			            else	
			            	storeNoValid.add(store);
			            ok = true;
		        	}
		        	book.close();
		        	for(int i=0; i<storeOk.size(); i++){
			        	for(int j=i+1; j<storeOk.size(); j++){
			        		if(storeOk.get(i).getCode().equals(storeOk.get(j).getCode()))
			        			storeFileError.add(storeOk.get(i));
			        	}
			        }
	        	
	        	}else{
	        		correctFile = false;
	        	}
	        	
	        }
		}

		sess.removeAttribute("STRIMP");
		sess.removeAttribute("str_id_state");
		sess.removeAttribute("str_id_city");
		sess.removeAttribute("str_id_categoria");
		sess.removeAttribute("str_readFile");
		sess.removeAttribute("str_filename");
		
		int tot_ok = storeOk.size();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("id_retail", id_retail);
		model.put("id_categoria", id_categoria);
		
		model.put("readFile", readFile);
		model.put("storeNoValid", storeNoValid);
		model.put("storeOk", storeOk);
		model.put("storeFileError", storeFileError);
		model.put("filename", filename);
		
		model.put("list_state", list_state);
		model.put("list_category", list_category);
		model.put("id_state", id_state);
		model.put("id_city", id_city);
		model.put("retail", retail);
		model.put("tot_ok", tot_ok);
		model.put("useraccess",useraccess);
		model.put("correctFile", correctFile);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		FileUpload command = new FileUpload();
		return command;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();		
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		
		Integer storesImported = 0;
		boolean importar = ServletRequestUtils.getBooleanParameter(request, "importar", false);
		logger.info("importar="+importar);
		
		String userlogin = ServletRequestUtils.getStringParameter(request, "userlogin");
		logger.info("userlogin="+userlogin);
		
		String filename = ServletRequestUtils.getStringParameter(request, "filename");
		logger.info("filename="+filename);
		
		Long id_retail = null;
		if(request.getParameter("id_retail") != null && !request.getParameter("id_retail").equals(""))
			id_retail = ServletRequestUtils.getLongParameter(request, "id_retail");
		logger.info("id_retail="+id_retail);
		
		Long id_state = null;
		if(request.getParameter("idstate")!=null && !request.getParameter("idstate").equals(""))
			id_state = ServletRequestUtils.getLongParameter(request, "idstate");
		logger.info("id_state = "+id_state);
		
		State state = retailService.getStateById(id_state);
		
		logger.info("state name = "+state.getName());
		
		Long id_city = null;
		
		
		if(request.getParameter("idcity")!=null && !request.getParameter("idcity").equals(""))
			id_city = ServletRequestUtils.getLongParameter(request, "idcity");
		
		City city = retailService.getCityById(id_city);
		logger.info("id_city = "+id_city);
		
		Long id_categoria = null;
		if(request.getParameter("idcategoria") != null && !request.getParameter("idcategoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(request, "idcategoria");
		logger.info("id_categoria = "+id_categoria);
		
		
		if(importar){
			if (filename.endsWith(".csv")){
		        CsvReader locales = new CsvReader(IMPORT_FILE+id_supplier+"/"+filename);
		        //locales.readHeaders();
		        while (locales.readRecord())
		        {
		            AddStoreDTO dto_str = new AddStoreDTO();
		            //se setean datos de archivo de importacion
		            dto_str.setCode(locales.get(0));
		            dto_str.setName(locales.get(1));
		            dto_str.setAddress1(locales.get(2));
		            dto_str.setAddress2(locales.get(3));
		            dto_str.setPostal(Integer.parseInt(locales.get(4)));
		            
		            
		            //setean datos que no estan en archivo de importacion            
		            dto_str.setActive(ACTIVE);
					dto_str.setCreated(new Date());
					dto_str.setId_city(id_city);
					dto_str.setId_country(state.getId_country());
	//				dto_str.setId_locality(id_locality);
					dto_str.setId_retail(id_retail);
					dto_str.setId_state(id_state);
					dto_str.setId_store_category(id_categoria);
					dto_str.setOrderby(1);
					dto_str.setLogin(userlogin);
					String fulladdress = dto_str.getAddress1()+" "+dto_str.getAddress2()+", "+city.getName()+" "+state.getName();
					Double [] coord = getLatLon(fulladdress);
					
		            dto_str.setLatitude(coord[0]);
		            dto_str.setLongitude(coord[1]);				
					//se persiste el objeto
					Long id_store = retailService.addStore(dto_str);
					storesImported++;
					logger.info("id_store agregado = "+id_store);
		             
		        }// while locales.readRecord
		        locales.close();
			}else{
				Workbook book = Workbook.getWorkbook(new File(IMPORT_FILE+id_supplier+"/"+filename));
				Sheet sheet = book.getSheet(0);
				int rows = sheet.getRows();
				for (int i = 1; i < rows; i++){
					AddStoreDTO dto_str = new AddStoreDTO();
					//se setean datos de archivo de importacion
		            dto_str.setCode(replaceCharacters(sheet.getCell(0,i).getContents().toString()));
		            String name = replaceCharacters(sheet.getCell(1,i).getContents().toString());
		            dto_str.setName(name);
		            dto_str.setAddress1(replaceCharacters(sheet.getCell(2,i).getContents().toString() + " " + sheet.getCell(3,i).getContents().toString()));
		            dto_str.setAddress2(replaceCharacters(sheet.getCell(4,i).getContents().toString()));
		            try{
		            	int cp = sheet.getCell(5,i).getContents().toString().trim().isEmpty()?0:Integer.parseInt(sheet.getCell(5,i).getContents().toString());
		            	dto_str.setPostal(cp);
		            }catch(NumberFormatException ex){
		            	dto_str.setPostal(0);
		            }
		            try{
		            	dto_str.setPhone(sheet.getCell(6,i).getContents().toString().trim());
		            }catch(Exception ex){}
		            try{
		            	dto_str.setEmail(sheet.getCell(7,i).getContents().toString().trim());
		            }catch(Exception ex){}
		            //setean datos que no estan en archivo de importacion            
		            dto_str.setActive(ACTIVE);
					dto_str.setCreated(new Date());
					dto_str.setId_city(id_city);
					dto_str.setId_country(state.getId_country());
	//				dto_str.setId_locality(id_locality);
					dto_str.setId_retail(id_retail);
					dto_str.setId_state(id_state);
					dto_str.setId_store_category(id_categoria);
					dto_str.setOrderby(1);
					dto_str.setLogin(userlogin);
					String fulladdress = dto_str.getAddress1()+" "+dto_str.getAddress2()+", "+ dto_str.getPostal() + " " +city.getName()+" "+state.getName();
					Double [] coord = getLatLon(fulladdress);
					
		            dto_str.setLatitude(coord[0]);
		            dto_str.setLongitude(coord[1]);				
					//se persiste el objeto
					Long id_store = retailService.addStore(dto_str);
					storesImported++;
					logger.info("id_store agregado = "+id_store);
				}
				book.close();
			}
	        // Se genera una notificacion
	        Retail retail = retailService.getRetailById(id_retail);	        
	        
			AddNotificationDTO dtn = new AddNotificationDTO();
			dtn.setCreated( new Date() );
			dtn.setIcon( "fa fa-user" );
			dtn.setId_user( useracegi.getId_user() );
			dtn.setId_supplier( id_supplier );
			dtn.setMessage( "Se importaron <span class='label label-success'>"+storesImported+"</span> tiendas al cliente "+ retail.getName()+" <br> ");
			dtn.setPriority( "1");
			dtn.setLink("store.htm?id=" + id_retail);
			// Se persiste la notificacion
			Long idn = userNotificationService.createNotification(dtn, "002");
			logger.debug("Notification, id="+idn);

			File file = new File(IMPORT_FILE+id_supplier+"/");
			File[] archivos = file.listFiles();
			for(int i=0; i<archivos.length; i++){
				if(!archivos[i].isHidden()){
					File archivoBorrar = new File(IMPORT_FILE+id_supplier+"/"+archivos[i].getName());
					if(archivoBorrar.delete()) logger.info("archivo "+archivos[i].getName()+" borrado");
				}//if archivos
			}// for i
		}
		
		return new ModelAndView(getSuccessView()+"?id="+id_retail);
	}
	Double [] getLatLon (String address) throws IOException{
		address = address.replace("#", "");
		String[] url_address_vector = address.split(" ");
        String url_address="";
        for(String a:url_address_vector){        	
        	url_address += a + "+";	            	
        }
        logger.debug("Address -->" + url_address);
        Double [] coord = new Double[2];
		coord[0] = new Double(0);
		coord[1] = new Double(0);
        try{
	        String url = "https://maps.googleapis.com/maps/api/geocode/xml?address="+url_address;
	        
			URL obj = new URL(url);  		
			String USER_AGENT = "";
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			logger.debug("\nSending 'GET' request to URL : " + url);
			logger.debug("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response2 = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("<lat>")){
					String newString = inputLine.replaceFirst("</lat>","<lat>");
					String[] latArr = newString.split("<lat>");
					logger.debug("INPUT LINE LAT --->"+ latArr[1]);
					coord[0] = Double.parseDouble(latArr[1]);
				}
				if (inputLine.contains("<lng>")){
					String newString = inputLine.replaceFirst("</lng>","<lng>");
					String[] lngArr = newString.split("<lng>");
					logger.debug("INPUT LINE LAT --->"+ lngArr[1]);
					coord[1] = Double.parseDouble(lngArr[1]);
					break;
				}
				response2.append(inputLine);
			}
			in.close();
        }
        catch(Exception e){
        	
        }
		return coord;
		
	}
	
	public String replaceCharacters(String data){
		String res = data;
		if (res != null && !res.trim().isEmpty() && res.length() > 0){
			if (res.contains("?")){
				res = res.replaceAll("?", "");
			}
			res = res.replaceAll("\"", "");
			res = res.replaceAll("'", "");
			res = res.replaceAll("�", "\u00e1");
			res = res.replaceAll("�", "\u00e9");
			res = res.replaceAll("�", "\u00ed");
			res = res.replaceAll("�", "\u00f3");
			res = res.replaceAll("�", "\u00fa");
			res = res.replaceAll("�", "\u00c1");
			res = res.replaceAll("�", "\u00c9");
			res = res.replaceAll("�", "\u00cd");
			res = res.replaceAll("�", "\u00d3");
			res = res.replaceAll("�", "\u00da");
			res = res.replaceAll("�", "\u00f1");
			res = res.replaceAll("�", "\u00d1");
		}
		return res;
	}
	
}
