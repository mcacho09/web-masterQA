package dao;

import domain.Product;
import domain.ProductExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductDAOImpl extends SqlMapClientDaoSupport implements ProductDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public ProductDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public Long insert(Product record) {
		Object newKey = getSqlMapClientTemplate().insert(
				"lgk_product.abatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public int updateByPrimaryKey(Product record) {
		int rows = getSqlMapClientTemplate().update(
				"lgk_product.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public int updateByPrimaryKeySelective(Product record) {
		int rows = getSqlMapClientTemplate().update(
				"lgk_product.abatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	@SuppressWarnings("unchecked") public List<Product> selectByExample(ProductExample example){List<Product> list=(List<Product>)getSqlMapClientTemplate().queryForList("lgk_product.abatorgenerated_selectByExample",example);return list;}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public Product selectByPrimaryKey(Long id_product) {
		Product key = new Product();
		key.setId_product(id_product);
		Product record = (Product) getSqlMapClientTemplate().queryForObject(
				"lgk_product.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public int deleteByPrimaryKey(Long id_product) {
		Product key = new Product();
		key.setId_product(id_product);
		int rows = getSqlMapClientTemplate().delete(
				"lgk_product.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table lgk_product
	 * @abatorgenerated  Tue Jul 07 12:06:29 CDT 2020
	 */
	public int countByExample(ProductExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"lgk_product.abatorgenerated_countByExample", example);
		return count;
	}
}