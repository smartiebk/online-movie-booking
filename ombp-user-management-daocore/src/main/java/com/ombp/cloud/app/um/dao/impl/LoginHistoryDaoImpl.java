/**
 * 
 */
package com.ombp.cloud.app.um.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.um.dao.LoginHistoryDao;
import com.ombp.cloud.model.usermanagement.AppLoginHistory;

/**
 * @author PranayKathade
 *
 */
@Repository
public class LoginHistoryDaoImpl implements LoginHistoryDao {
	
	private final String NAMESPACE = this.getClass().getName();

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSession() {
		return this.sqlSession;
	}

	public String getStatementIdWithNamespace(String statementId) {
		return NAMESPACE + "." + statementId;
	}
	public LoginHistoryDaoImpl() {
		super();
	}

	private final String insertAppLoginHistory = "insertAppLoginHistory";
	private final String fetchAppLoginRecord = "fetchAppLoginRecord";
	private final String updateAppLoginHistory = "updateAppLoginHistory";

	
	@Override
	public AppLoginHistory fetchAppLoginRecord(AppLoginHistory appLoginHistory) {
		return getSession().selectOne(getStatementIdWithNamespace(fetchAppLoginRecord), appLoginHistory);
	}

	@Override
	public void insertAppLoginHistory(AppLoginHistory appLoginHistory) {
		getSession().insert(getStatementIdWithNamespace(insertAppLoginHistory), appLoginHistory);
	}

	@Override
	public void updateAppLoginHistory(AppLoginHistory appLoginHistory) {
		// TODO Auto-generated method stub
		
	}

}
