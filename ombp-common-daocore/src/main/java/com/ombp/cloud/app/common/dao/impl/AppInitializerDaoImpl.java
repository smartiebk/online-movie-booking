package com.ombp.cloud.app.common.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ombp.cloud.app.common.dao.AppInitializerDao;

@Repository
public class AppInitializerDaoImpl implements AppInitializerDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public Map<String, Set<String>> fetchMethodSecurityACLs(String serviceName) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		StringBuilder query = new StringBuilder();
		query.append("select m.name, aa.action_name from app_method_name m ");
		query.append("inner join m_action_method_acl macl on macl.app_method_name_id = m.app_method_name_id ");
		query.append("inner join app_action aa on aa.app_action_id = macl.app_action_id ");
		query.append("inner join app_service_name ON app_service_name.app_service_name_id = m.app_service_name_id ");
		query.append("where app_service_name.\"name\" = ? ");
		
		
		map = jdbcTemplate.query(query.toString(), new Object[] { serviceName }, new ResultSetExtractor<Map<String, Set<String>>>() {

			@Override
			public Map<String, Set<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Set<String>> methodACLs = new HashMap<>();
				while (rs.next()) {
					String methodName = rs.getString("name");
					String actionName = rs.getString("action_name");
					Set<String> methodAcls = methodACLs.get(methodName);
					if (methodAcls == null) {
						Set<String> methodACL = new HashSet<>();
						methodACL.add(actionName);
						methodACLs.put(methodName, methodACL);
					} else {
						methodAcls.add(actionName);
					}
				}
				return methodACLs;
			}
		});

		return map;
	}

}
