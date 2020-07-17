package com.retailsbs.logistikapp.financial.dto;

public class WSProductDetailDTO {
	
	private Long quantity;
	private String code;
	private String name_short;
	private String name_long;
	private Double price_sale;
	private Double price_sug;
	private String typetrx;
	private String image;
	private Integer trx;
	private Integer tax;
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}
	public String getName_long() {
		return name_long;
	}
	public void setName_long(String name_long) {
		this.name_long = name_long;
	}
	public Double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Double price_sale) {
		this.price_sale = price_sale;
	}
	public Double getPrice_sug() {
		return price_sug;
	}
	public void setPrice_sug(Double price_sug) {
		this.price_sug = price_sug;
	}
	public String getTypetrx() {
		return typetrx;
	}
	public void setTypetrx(String typetrx) {
		this.typetrx = typetrx;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getTrx() {
		return trx;
	}
	public void setTrx(Integer trx) {
		this.trx= trx;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax= tax;
	}
	@Override
	public String toString() {
		return "WSProductDetailDTO [quantity=" + quantity + ", code=" + code
				+ ", name_short=" + name_short + ", name_long=" + name_long
				+ ", price_sale=" + price_sale + ", price_sug=" + price_sug
				+ ", typetrx=" + typetrx + ", image=" + image + ", trx=" + trx + ", tax=" + tax + "]";
	}

}
