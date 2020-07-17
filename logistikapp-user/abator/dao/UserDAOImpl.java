package dao;

import domain.User;
import domain.UserExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public UserDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public Long insert(User record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_user.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int updateByPrimaryKey(User record) {
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int updateByPrimaryKeySelective(User record) {
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    @SuppressWarnings("unchecked")
    public List<User> selectByExample(UserExample example) {
        List<User> list = (List<User>) getSqlMapClientTemplate().queryForList("lgk_user.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public User selectByPrimaryKey(Long id_user) {
        User key = new User();
        key.setId_user(id_user);
        User record = (User) getSqlMapClientTemplate().queryForObject("lgk_user.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int deleteByPrimaryKey(Long id_user) {
        User key = new User();
        key.setId_user(id_user);
        int rows = getSqlMapClientTemplate().delete("lgk_user.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int countByExample(UserExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_user.abatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int updateByExampleSelective(User record, UserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    public int updateByExample(User record, UserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    private static class UpdateByExampleParms extends UserExample {
        private Object record;

        public UpdateByExampleParms(Object record, UserExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}