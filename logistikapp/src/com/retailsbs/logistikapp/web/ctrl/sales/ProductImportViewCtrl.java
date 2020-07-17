package com.retailsbs.logistikapp.web.ctrl.sales;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.retailsbs.logistikapp.financial.domain.CategoryProduct;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductCodeAvailableSearchCriteria;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.web.ctrl.upload.FileUpload;

/**
 * Class controller para la vista importar de productos
 * @author Juan Carlos Ramos Campos
 * @since 24-03-2015
 */
public class ProductImportViewCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private FinancialService financialService;
	private String ACTIVE;
	private String IMPORT_FILE;
	private String IMG_PRODUCT_DEFAULT; 
	
	public void setIMG_PRODUCT_DEFAULT(String iMG_PRODUCT_DEFAULT) {
		IMG_PRODUCT_DEFAULT = iMG_PRODUCT_DEFAULT;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	public void setIMPORT_FILE(String iMPORT_FILE) {
		IMPORT_FILE = iMPORT_FILE;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProductImportViewCtrl(){
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}
 
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws ServletRequestBindingException, Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		// TODO se debe cambiar por el proveedor activo y s�lo de los que tiene acceso
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
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
		HttpSession sess = arg0.getSession();
		String SESSION = (String)sess.getAttribute("PROIMP");
		
		String filename = ServletRequestUtils.getStringParameter(arg0, "filename");
		if(SESSION!=null && SESSION.equals("pro"))
			filename = (String)sess.getAttribute("pro_filename");
		logger.info("filename = "+filename);
		
		Long id_categoria = null;
		if(arg0.getParameter("id_categoria") != null && !arg0.getParameter("id_categoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(arg0, "id_categoria");
		if(SESSION!=null && SESSION.equals("pro"))
			id_categoria = (Long)sess.getAttribute("pro_id_categoria");
		logger.info("id_categoria = "+id_categoria);		
		
		List<CategoryProduct> list_category = financialService.getAllCategoryProduct();
		logger.info("list_category = "+list_category.size());
		
		/*-------------------------------------------------------*/
		// Se obtiene una lista de categorias de productos asociado a un proveedor
		CategoryProductSearchCriteria dto = new CategoryProductSearchCriteria();
		dto.setId_supplier(idsupplier);
		dto.setActive(ACTIVE);
		List<CategoryProductDTO> list_catproduct = financialService.selectCategoryProductByCriteria(dto);
		logger.debug("list category_product="+list_catproduct.size());
		/*-------------------------------------------------------*/
		
		// Se obtiene una lista de marcas de productos asociado a un proveedor
				BrandProductSearchCriteria dtb = new BrandProductSearchCriteria();
				dtb.setId_supplier(idsupplier);
				List<BrandProductDTO> list_brand = financialService.selectBrandProductByCriteria(dtb);
				logger.debug("list_brand="+list_brand.size());
				/*-------------------------------------------------------*/
		
		boolean readFile = ServletRequestUtils.getBooleanParameter(arg0, "readFile", false);
		if(SESSION!=null && SESSION.equals("pro"))
			readFile = Boolean.parseBoolean((String)sess.getAttribute("pro_readFile"));

		ArrayList<Product> productNoValid = new ArrayList<Product>();
		ArrayList<Product> productOk = new ArrayList<Product>();
		ArrayList<Product> productFileError = new ArrayList<Product>();
		if(readFile){
			//Se lee el archivo
	        CsvReader productos = new CsvReader(IMPORT_FILE+useracegi.getUserlogin()+"/"+filename);
	        productos.readHeaders();
	        boolean ok = true;
	        
	        while (productos.readRecord())
	        {
	        	Product product = new Product();
        		product.setCode(productos.get(0));
        		if(product.getCode() == null || product.getCode().equals("") || product.getCode().length() > 10  )
        			ok = false;
	            
        		product.setName_short(productos.get(1));
	            if(product.getName_short() == null || product.getName_short().equals("") || product.getName_short().length() > 100 )
        			ok = false;
	            
	            product.setName_long(productos.get(2));
	            if(product.getName_long() == null || product.getName_long().equals("") || product.getName_long().length() > 255)
        			ok = false;

	            try{
	            	product.setPrice_cost(Double.parseDouble(productos.get(3)));
	            	if(product.getPrice_cost() == null || product.getPrice_cost().equals(""))
	            		ok = false;
	            }catch(Exception e){
	            	ok = false;
	            }
	            
	            try{
	            	product.setPrice_sale(Double.parseDouble(productos.get(4)));
		            if(product.getPrice_sale() == null || product.getPrice_sale().equals(""))
	        			ok = false;
	            }catch(Exception e){
        			ok = false;
	            }

				//Se verifica que el codigo que se va a ingresar no exista 
	            ProductCodeAvailableSearchCriteria dto_pro = new ProductCodeAvailableSearchCriteria();
	            dto_pro.setActive(ACTIVE);
	            dto_pro.setCode(productos.get(0));
				
				try{
					financialService.getProductCodeAvaibleByCriteria(dto_pro);
				}catch(Exception e){
					ok = false;
				}
				
	            if(ok)
	            	productOk.add(product);
	            else	
	            	productNoValid.add(product);
	            ok = true;
	            
	        } // while locales.readRecord

	        // verifica si hay algun codigo repetido dentro del archivo
	        for(int i=0; i<productOk.size(); i++){
	        	for(int j=i+1; j<productOk.size(); j++){
	        		if(productOk.get(i).getCode().equals(productOk.get(j).getCode()))
	        			productFileError.add(productOk.get(i));
	        	}
	        }

	        productos.close();
	        
		}
		sess.removeAttribute("PROIMP");
		sess.removeAttribute("pro_id_categoria");
		sess.removeAttribute("pro_filename");
		sess.removeAttribute("pro_readFile");
		int tot_ok = productOk.size();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);
		model.put("id_categoria", id_categoria);
		model.put("list_category", list_category);
		model.put("list_catproduct", list_catproduct);
		model.put("list_brand", list_brand);

		model.put("readFile", readFile);
		model.put("filename", filename);

		model.put("productNoValid", productNoValid);
		model.put("productOk", productOk);
		model.put("productFileError", productFileError);
		model.put("tot_ok", tot_ok);
		
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		FileUpload command = new FileUpload();
		return command;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

		boolean importar = ServletRequestUtils.getBooleanParameter(request, "importar", false);
		logger.info("importar="+importar);
		
		String userlogin = ServletRequestUtils.getStringParameter(request, "userlogin");
		logger.info("userlogin="+userlogin);
		
		String filename = ServletRequestUtils.getStringParameter(request, "filename");
		logger.info("filename="+filename);
		
		Long id_categoria = null;
		if(request.getParameter("idcategoria") != null && !request.getParameter("idcategoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(request, "idcategoria");
		logger.info("id_categoria = "+id_categoria);
		
		if(importar){
	        CsvReader productos = new CsvReader(IMPORT_FILE+userlogin+"/"+filename);
	        productos.readHeaders();
	        while (productos.readRecord())
	        {
				//se persiste el objeto
	        	AddProductDTO dto_pro = new AddProductDTO();
	        	dto_pro.setCode(productos.get(0));
	        	dto_pro.setName_short(productos.get(1));
	        	dto_pro.setName_long(productos.get(2));
	        	dto_pro.setPrice_cost(Double.parseDouble(productos.get(3)));
	        	dto_pro.setPrice_sale(Double.parseDouble(productos.get(4)));
	        	dto_pro.setActive(ACTIVE);
	        	dto_pro.setCreated(new Date());
	        	dto_pro.setId_category_product(id_categoria);
	        	dto_pro.setImage(IMG_PRODUCT_DEFAULT);
	        	dto_pro.setLogin(userlogin);
	        	dto_pro.setOrderby(1);
	        	
				Long id_product = financialService.addProduct(dto_pro);
				logger.info("id producto agregado = "+id_product);
	             
	        }// while locales.readRecord
	         
	        productos.close();

			File file = new File(IMPORT_FILE+userlogin+"/");
			File[] archivos = file.listFiles();
			for(int i=0; i<archivos.length; i++){
				if(!archivos[i].isHidden()){
					File archivoBorrar = new File(IMPORT_FILE+userlogin+"/"+archivos[i].getName());
					if(archivoBorrar.delete()) logger.info("archivo "+archivos[i]+" borrado");
				}//if archivos
			}// for i
		}
		
		return new ModelAndView(getSuccessView());
	}
	
}
