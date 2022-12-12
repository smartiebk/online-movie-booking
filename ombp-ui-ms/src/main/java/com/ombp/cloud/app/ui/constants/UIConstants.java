package com.ombp.cloud.app.ui.constants;

import java.io.File;

public interface UIConstants {

	String AUTH_COOKIE_NAME = "app-session-uid";
	
	String REFERRING_DOC_DATA_COOKIE_NAME = "referral-info-user-pref";
	
	String OTHER_INSTANCE_LOGIN_INDUCE_LOGOUT = "OTHER_INSTANCE_LOGIN_INDUCE_LOGOUT";
	
	String TAMPERED_COOKIE_RESPONSE = "Full authentication is required to access this resource";
	
	String USER_NOT_FOUND_MESSAGE = "UserDetailsService returned null, which is an interface contract violation";
	
	String WRONG_PASSWORD_MESSAGE = "Bad credentials";
	
	String ACCESS_TOKEN_REQ_ATTR = "justLoggedInaccessToken";
	
	String ACCESS_TOKEN_SESSION_ATTR = "justLoggedInaccessTokenInSession";
	
	String STATIC_DATA_ID_CLI_PREF = "uiComponents";
	
	String LOGGED_IN_USER_INFO = "lUserInfo";
	
	String ENTITY_NAME_TABLE_LANG_FILE_CONST = "ENTITY_NAME_TABLE_LANG_FILE_CONST";
	
	String PAGE_ATTR_CURRENT_USER = "currentUser";
	
	String PAGE_ATTR_CURRENT_USER_PERMISSIONS = "permissions";
	
	String PAGE_ATTR_CURRENT_USER_SUPERADMIN = "isSuperAdmin";
	
	String DOWNLOADED_SCANS_DIRECTORY = System.getProperty("user.dir") + File.separator + "scans" + File.separator;
	
	String DOWNLOADED_SCANS_DIRECTORY_FOR_WEB = "/scans/";
	
	//Following are the constants related to general Read, Edit, Delete, Download, etc. for paginated table
	String CAN_EDIT_ENTITY = "edit";
	String CAN_DELETE_ENTITY = "delete";
	String CAN_CONFIGURE_ENTITY = "configure";
	String SELECT_NEEDED = "select";
	String PAGINATED_TABLE_MSG_RSC_KEY = "messageLabelKey";
	String PAGINATED_TABLE_PAGE_LENGTH_KEY = "pageLength";
	String PAGINATED_TABLE_LENGTH_CHANGE_KEY = "lengthChange";
	String PAGINATED_TABLE_DATA_URL = "dataURL";
	String PAGINATED_TABLE_COLUMNS = "columns";
	String PAGINATED_TABLE_OTHER_COLUMNS = "otherColumns";
	String PAGINATED_TABLE_ORDER = "order";
	String PAGINATED_TABLE_COL_ORDER = "columnNumber";
	String PAGINATED_TABLE_SORT = "sort";
	String PAGINATED_TABLE_CONFIG_FILE = "classpath:paginated_table_config.json";
	String PAGINATED_TABLE_CUSTOM_CONFIG_FILE = "classpath:paginated_table_config_custom.json";
	String STATIC_DATA_VALUES_CONFIG = "classpath:static_data_values_config.json";
	
	String USE_CUSTOM_CONFIG_FOR_PAGINATED_TABLE = "USE_CUSTOM_CONFIG";
	String CUSTOM_KEY_FOR_PAGINATED_TABLE = "CUSTOM_KEY";
	String ENTITY_KEY_ACTIONS = "actions";
	String ENTITY_KEY_ROLES = "roles";
	String ENTITY_KEY_ACTION_ROLE = "mappedActionsToRole";
	String ENTITY_KEY_USER_GROUPS = "userGroups";
	String ENTITY_KEY_ROLE_USER_GROUP = "mappedRolesToUserGroup";
	String ENTITY_KEY_USERS = "users";
	String ENTITY_KEY_USER_GROUP_USERS = "mappedUserGroupsToUsers";
	String ENTITY_KEY_VIDEO_GROUPS = "videogroups";
	String ENTITY_KEY_USER_VIDEO_GROUPS = "uvideogroups";
	String ENTITY_KEY_VIDEOS_UNDER_GROUP = "video";
	String ENTITY_KEY_USER_AS_SHARE = "useras";
	String ENTITY_KEY_USER_GROUP_AS_SHARE = "ugas";
	String ENTITY_KEY_ROLE_AS_SHARE = "roleas";
	String ENTITY_KEY_PATIENTS = "patients";
	String ENTITY_KEY_DOCTORS = "doctors";
	String ENTITY_KEY_INVESTIGATIONS = "invst";
	String ENTITY_KEY_WORKDATA = "workdata";
	String ENTITY_KEY_WORKDATA_VALUES = "workdatavalue";
	String ENTITY_KEY_WORKDATA_VALUES_EXAMS = "workdatavalue_exams";
	String ENTITY_KEY_WORKDATA_VALUES_EXAMS_ALL = "labExamsAll";
	String ENTITY_KEY_WORKDATA_VALUES_EXAMS_FAV = "labExamsFav";
	String ENTITY_KEY_TEMPLATES = "templates";
	String ENTITY_KEY_COMMUNICATIONS = "communications";
	String ENTITY_KEY_PRESCRIPTION = "prescriptions";
	
	String P_TABLE_ACTION_PERMISSIONS = "P_TABLE_ACTION_PERMISSIONS";
	String P_TABLE_URL_DYNAMIC_ID = "P_TABLE_URL_DYNAMIC_ID";
	String P_TABLE_URL_DYNAMIC_FIELD_NAME_IN_URL = "P_TABLE_URL_DYNAMIC_FIELD_NAME_IN_URL";
	String P_TABLE_DATA_PRIMARY_KEY = "P_TABLE_DATA_PRIMARY_KEY";
	String P_TABLE_DATA_PRIMARY_KEY_ADDITIONAL = "P_TABLE_DATA_PRIMARY_KEY_ADDITIONAL";
	String P_TABLE_DATA_URL_PARAMETERS = "P_TABLE_DATA_URL_PARAMETERS";
	
	
	String LABLE_COURSES = "courses";
	
	
	String FETCH_ALL_DATA_OF_TYPE = "all";
	String STATIC_DATA_TYPE_BLOOD_GROUP = "BLOOD_GROUP";
	String STATIC_DATA_TYPE_PT_RELATIVES = "PATIENT_RELATIVES";
	String STATIC_DATA_TYPE_PATIENT_ALLERGIES = "PATIENT_ALLERGIES";
	String STATIC_DATA_TYPE_DISEASES = "DISEASES_";
	String STATIC_DATA_TYPE_INVESTIGATION_TYPE = "TYPE_OF_INVESTIGATION";
	String STATIC_DATA_TYPE_APPOINTMENT_STATUS = "APPOINTMENT_STATUS";
	String STATIC_DATA_TYPE_PAYMENT_TNC = "PAYMENT_TERMS_AND_CONDITIONS";
	String STATIC_DATA_TYPE_PAYMENT_FOR = "PAYMENT_FOR";
	String STATIC_DATA_TYPE_SCAN_UPLOAD_STATUS = "SCAN_UPLOAD_STATUS";
	String STATIC_DATA_TYPE_SCAN_REPORTING_STATUS = "SCAN_REPORTING_STATUS";
	String STATIC_DATA_TYPE_COMMUNICATION_RECORD_TYPES = "COMMUNICATION_RECORD_TYPES";
	String STATIC_DATA_TYPE_EXAMS = "EXAMS";
	String STATIC_DATA_TYPE_COMPLAINTS = "COMPLAINTS";
	String STATIC_DATA_TYPE_DIAGNOSIS = "DIAGNOSIS";
	String STATIC_DATA_TYPE_DOSE = "DOSE";
	String STATIC_DATA_TYPE_DOSE_CONDITIONS = "DOSE_CONDITIONS";
	String STATIC_DATA_TYPE_DOSE_FREQUENCY = "DOSE_FREQUENCY";
	String STATIC_DATA_TYPE_ADVICE = "ADVICE";
	String STATIC_DATA_TYPE_MEDICINES = "MEDICINES";
	
	String STATIC_DATA_ENTITY_GROUP_SUMMARY = "#SUMMARY";
}
