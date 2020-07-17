package com.retailsbs.logistikapp.financial.dto;

public class WSProduct {

	private Long id_product;
	private String name_short;
	private String name_long;
	private String code;
	private Double price_sale;
	private Double price_sale_piece;
	private String type;
	private Long piece_in_box;
	private Integer tax;
	
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Double price_sale) {
		this.price_sale = price_sale;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPiece_in_box() {
		return piece_in_box;
	}
	public void setPiece_in_box(Long piece_in_box) {
		this.piece_in_box = piece_in_box;
	}
	public Double getPrice_sale_piece() {
		return price_sale_piece;
	}
	public void setPrice_sale_piece(Double price_sale_piece) {
		this.price_sale_piece = price_sale_piece;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "WSProduct [id_product=" + id_product + ", name_short="
				+ name_short + ", name_long=" + name_long + ", code=" + code
				+ ", price_sale=" + price_sale + ", tax=" + tax + "]";
	}
	
}
