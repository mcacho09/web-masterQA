package dao;

import domain.User;
import domain.UserExample;
import java.util.List;

public interface UserDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    Long insert(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int updateByPrimaryKey(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    User selectByPrimaryKey(Long id_user);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int deleteByPrimaryKey(Long id_user);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int countByExample(UserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int updateByExampleSelective(User record, UserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Mon Mar 23 21:06:46 CST 2020
     */
    int updateByExample(User record, UserExample example);
}