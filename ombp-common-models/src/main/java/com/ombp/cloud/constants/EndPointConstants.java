package com.ombp.cloud.constants;

public interface EndPointConstants {

	String USER_INFO = "/oauth/userinfo";

	String LOGOUT = "/oauth/logout";

	String LIST_USER = "/um/user/list";
	String SAVE_USER = "/um/user/save";
	String SAVE_USER_INFO_FROM_PROFILE = "/um/userinfo/save";
	String DELETE_USER = "/um/user/delete";
	String FETCH_USER = "/um/user/fetch/{id}";
	String FETCH_USER_DETAILS = "/um/user/fetchdetails/{id}";
	String LIST_UNSELECTED_USER_GROUPS_BY_USER_ID = "/um/ug/fetchbyuser/{id}";
	String REMOVE_USER_GROUP_FROM_USER = "/um/user/ug/remove/{userId}/{userGroupId}";
	String SAVE_USER_GROUP_TO_USER = "/um/user/usergroup/save/{userId}";

	String SIGN_UP_WITH_TENANT = "/{tenantIdentifier}/signup";
	String SIGN_UP_BY_LOGGED_IN_USER = "/um/signup";
	String SAVE_TENANT = "/um/tenant/save";
	String LIST_TENANT = "/um/tenant/list";
	String FETCH_TENANT = "/um/tenant/fetch/{pkId}";

	String SAVE_ROLE = "/um/role/save";
	String DELETE_ROLE = "/um/roles/delete";
	String LIST_ROLE = "/um/role/list";
	String LIST_ROLE_TYPE = "/um/roletype/list";
	String LIST_PAGINATED_ROLE = "/um/role/plist";
	String FETCH_ROLE = "/um/role/fetch/{id}";
	String FETCH_ROLE_DETAILS = "/um/role/fetchdetails/{id}";
	String LIST_US_ACTIONS_BY_ROLE_ID = "/um/usactions/fetchbyrole/{id}";
	String REMOVE_ACTION_FROM_ROLE = "/um/actions/roles/remove/{roleId}/{actionId}";
	String SAVE_ACTION_TO_ROLE = "/um/actions/roles/save/{roleId}";

	String SAVE_USER_GROUP = "/um/ug/save";
	String DELETE_USER_GROUP = "/um/ug/delete";
	String FETCH_USER_GROUP = "/um/ug/fetch/{id}";
	String FETCH_UG_DETAILS = "/um/ug/fetchdetails/{id}";
	String REMOVE_ROLE_FROM_UG = "/um/ug/roles/remove/{ugId}/{roleId}";
	String SAVE_ROLE_TO_UG = "/um/ug/roles/save/{ugId}";
	String LIST_US_ROLES_BY_UG_ID = "/um/usroles/fetchbyug/{id}";
	String LIST_ASSIGNED_ROLES_BY_USER_GROUP = "/um/ug/fetchroles/{id}";
	String LIST_USER_GROUP = "/um/ug/list";

	String GENERATE_TOKENS_FOR_USER_GROUP = "/um/ug/generatesignuptokens/{id}";
	String ASSIGN_USER_GROUP = "/um/ug/userassign";
	String ASSIGN_ROLES_TO_USER_GROUP = "/um/ug/roleassign";

	String LIST_ACTIONS = "/um/actions/list";
	String SAVE_ACTION = "/um/actions/save";
	String FETCH_ACTION = "/um/actions/fetch/{pkId}";
	String DELETE_ACTION = "/um/actions/delete";
	String LIST_PAGINATED_ACTIONS = "/um/actions/plist";

	String FETCH_UI_COMPONENTS = "/um/ac/ui/components";
	String FETCH_NOTIFICATIONS = "/um/ac/notifications";
	String VIEW_NOTIFICATION = "/um/ac/notifications/{id}";
	String VIEW_ALL_ALERTS = "/um/ac/notifications/viewallalerts";
	
	String FETCH_NEW_PT_NOTIFICATIONS = "/um/pt/notifications/fetch";
	String SAVE_NEW_PT_NOTIFICATION = "/um/pt/notifications/save";
	
	String FETCH_DASHBOARD_NUMBERS = "/um/dashboard/fetchcardnumber/{type}";
	String FETCH_DASHBOARD_CHART_DATA = "/um/dashboard/fetchchartdata/{type}";

	String LIST_VIDEO_GROUPS = "/um/course/list";
	String FETCH_VIDEO_GROUP = "/um/course/fetch/{id}";
	String SAVE_VIDEO_GROUP = "/um/course/save";
	String DELETE_VIDEO_GROUPS = "/um/course/delete";
	String SAVE_VIDEO_GROUP_DETAILS = "/um/coursedetails/save";
	String DELETE_VIDEO_GROUP_DETAILS = "/um/coursedetails/delete";
	String FETCH_VIDEO_GROUP_USER_LOG = "/um/course/userlog/{id}";
	String SAVE_VIDEO_GROUP_USER_EVENT_LOG = "/um/course/userlog/save";
	String SHARE_VIDEO_GROUP = "/um/course/share";
	String UNSHARE_VIDEO_GROUP = "/um/course/unshare";
	String FETCH_VIDEO_GROUP_DETAILS = "/um/coursedetails/fetch/{id}";
	String LIST_VIDEOS_UNDER_GROUP = "/um/coursedetails/list/{id}";
	String LIST_USER_VIDEO_GROUP = "/um/usercourses/list";

	String LIST_SHARE_TYPES_DATA = "/um/list/share/{shareType}/{entity}/{id}";
	String FETCH_ALREADY_SHARED_WITH = "/um/course/alrshare/fetch";

	String LIST_PATIENT = "/um/patient/list";
	String SAVE_PATIENT = "/um/patient/save";
	String SAVE_PATIENT_SEARCH = "/um/patient/savesearch";
	String FETCH_PATIENT_SEARCH = "/um/patient/fetchsavedsearch";
	String DELETE_PATIENT = "/um/patient/delete";
	String FETCH_PATIENT = "/um/patient/fetch/{id}";
	String FETCH_PATIENT_DETAILS = "/um/patient/fetchdetails/{id}";
	String EXPORT_PATIENT = "/um/patient/export";
	String MERGE_PATIENT = "/um/patient/merge";

	String FETCH_PT_SUMMARY_BY_PT_ID = "/um/patient/fetchsummary/{id}";
	String FETCH_PT_SUMMARY_BY_ID = "/um/patient/fetchsummarybyid/{id}";
	String SAVE_PT_SUMMARY = "/um/patient/summary/save";
	String DELETE_PT_SUMMARY = "/um/patient/summary/delete";

	String FETCH_PT_HISTORY_BY_PT_ID = "/um/patient/fetchhistory/{id}";
	String FETCH_PT_HISTORY_BY_ID = "/um/patient/fetchhistorybyid/{id}";
	String SAVE_PT_HISTORY = "/um/patient/history/save";
	String DELETE_PT_HISTORY = "/um/patient/history/delete";

	String FETCH_PT_TRANSACTION_BY_PT_ID = "/um/patient/fetchtransaction/{id}";
	String FETCH_PT_TRANSACTION_BY_ID = "/um/patient/fetchtransactionbyid/{id}";
	String SAVE_PT_TRANSACTION = "/um/patient/transaction/save";
	String DELETE_PT_TRANSACTION = "/um/patient/transaction/delete";

	String FETCH_PT_APPOINTMENT_BY_PT_ID = "/um/patient/fetchappointment/{id}";
	String FETCH_PT_APPOINTMENT_BY_ID = "/um/patient/fetchappointmentbyid/{id}";
	String SAVE_PT_APPOINTMENT = "/um/patient/appointment/save";
	String DELETE_PT_APPOINTMENT = "/um/patient/appointment/delete";
	
	String FETCH_PT_IMAGING_BY_PT_ID = "/um/patient/fetchimaging/{id}";
	String FETCH_PT_IMAGING_BY_ID = "/um/patient/fetchimagingbyid/{id}";
	String SAVE_PT_IMAGING = "/um/patient/imaging/save";
	
	String LIST_PT_PRESCRIPTION_BY_PT_ID = "/um/patient/listprescription/{id}";
	String FETCH_PT_PRESCRIPTION_BY_PT_ID = "/um/patient/fetchprescription/{id}";
	String FETCH_PT_PRESCRIPTION_BY_ID = "/um/patient/fetchprescriptionbyid/{id}";
	String SAVE_PT_PRESCRIPTION = "/um/patient/prescription/save";
	String DELETE_PT_PRESCRIPTION = "/um/patient/prescription/delete";
	
	String SYNC_PT_IMAGING = "/um/patient/imaging/sync";
	String SAVE_PT_IMAGING_SCAN_VIEW_DETAILS = "/um/patient/imaging/scanview/save";
	String DELETE_PT_IMAGING = "/um/patient/imaging/delete";
	String FETCH_IMAGING_SERISE_DETAILS = "/um/patient/imaging/fetch/scandetails/{serieseId}";
	String FETCH_EXPIRED_LOCAL_SCANS = "/um/patient/imaging/fetch/expiredscans";
	String UPDATE_EXPIRED_LOCAL_SCANS_STATUS_IN_IMAGING = "/um/patient/imaging/update/expiredscans";
	
	String SAVE_PT_ACTUAL_TEMPLATE = "/um/patient/actual/template/save";
	String LIST_PT_ACTUAL_TEMPLATE = "/um/patient/actual/template/search/{type}";
	String LOAD_PT_ACTUAL_TEMPLATE = "/um/patient/actual/template/load/{id}";

	String SAVE_SHARED_PUBLIC_LINK = "/open/um/patient/sharedlink/save";
	
	String LIST_DOCTOR = "/um/doc/list";
	String SAVE_DOCTOR = "/um/doc/save";
	String DELETE_DOCTOR = "/um/doc/delete";
	String FETCH_DOCTOR = "/um/doc/fetch/{id}";
	String FETCH_DOCTOR_DETAILS = "/um/doc/fetchdetails/{id}";
	String LIST_PANEL_DOCTOR = "/um/pdoc/list";
	String LIST_REFERREDBY_DOCTOR = "/um/rdoc/list";
	String ASSIGN_PANEL_DOCTOR = "/um/pdoc/assign";
	
	String LIST_TYPE_OF_SCAN = "/um/patient/typeofimaging";

	String LIST_TEMPLATE = "/um/template/list";
	String SAVE_TEMPLATE = "/um/template/save";
	String DELETE_TEMPLATE = "/um/template/delete";
	String FETCH_TEMPLATE = "/um/template/fetch/{id}";
	String FETCH_TEMPLATE_DETAILS = "/um/template/fetchdetails/{id}";
	String SHARE_TEMPLATE = "/um/template/share";
	String UNSHARE_TEMPLATE = "/um/template/unshare";
	String FETCH_SHARED_TEPLATE_WITH = "/um/template/alrshare/fetch";
	String DELETE_ACTUAL_TEMPLATE = "/um/actualtemplate/delete";

	String LIST_INVESTIGATION = "/um/inv/list";
	String SAVE_INVESTIGATION = "/um/inv/save";
	String DELETE_INVESTIGATION = "/um/inv/delete";
	String FETCH_INVESTIGATION = "/um/inv/fetch/{id}";
	String FETCH_INVESTIGATION_DETAILS = "/um/inv/fetchdetails/{id}";

	String LIST_WORKDATA = "/um/workdata/list";
	String SAVE_WORKDATA = "/um/workdata/save";
	String DELETE_WORKDATA = "/um/workdata/delete";
	String FETCH_WORKDATA = "/um/workdata/fetch/{id}";
	String FETCH_WORKDATA_DETAILS = "/um/workdata/fetchdetails/{id}";
	String GET_WORKDATA_LIST_BY_GROUP_TYPE = "/um/workdata/list/{grouptypeconstant}/{keyword}";

	String LIST_WORKDATA_VALUES = "/um/workdatavalue/list/{staticdataentityid}/{staticdataentitycnst}";
	String SAVE_WORKDATA_VALUES = "/um/workdatavalue/save";
	String DELETE_WORKDATA_VALUES = "/um/workdatavalue/delete";
	String FETCH_WORKDATA_VALUES = "/um/workdatavalue/fetch/{id}";
	String FETCH_WORKDATA_VALUES_DETAILS = "/um/workdatavalue/fetchdetails/{id}";
	String GET_WORKDATA_VALUES_BY_CONSTANT = "/um/workdatavalue/listbycnst/{staticdataentitycnst}/{keyword}";
	String GET_WORKDATA_VALUES_BY_ID = "/um/workdatavalue/listbyid/{staticdataentityid}/{keyword}";
	String LIST_ALL_LAB_EXAMS_VALUES = "/um/workdatavalue/exams/list/{staticdataentitycnst}/{examType}";

	String SUCCESS = "SUCCESS";
	
	String LIST_COMM = "/um/comm/list";
	String SAVE_COMM = "/um/comm/save";
	String DELETE_COMM = "/um/comm/delete";
	String FETCH_COMM = "/um/comm/fetch/{id}";
	String FETCH_COMM_DETAILS = "/um/comm/fetchdetails/{id}";
	
	String LIST_DOCUMENTS = "/um/ad/list";
	String SAVE_DOCUMENTS = "/um/ad/save";
	String DELETE_DOCUMENTS = "/um/ad/delete";
	String FETCH_DOCUMENT_BY_ID = "/um/ad/fetch/{id}";
	
	String LIST_OTHER_PERSON_FOR_SELECT = "/um/othp/selectlist";
	String LIST_OTHER_PERSON = "/um/othp/list";
	String SAVE_OTHER_PERSON = "/um/othp/save";
	String DELETE_OTHER_PERSON = "/um/othp/delete";
	String FETCH_OTHER_PERSON = "/um/othp/fetch/{id}";
	String FETCH_OTHER_PERSON_DETAILS = "/um/othp/fetchdetails/{id}";
	
	String SHARED_LINKS = "/sharedlink";
	String FETCH_IMAGING_DATA_BY_ID = SHARED_LINKS + "/imaging/{id}";
	String FETCH_IMAGING_DATA_BY_ID_AFTER_USER_VALIDATION = SHARED_LINKS + "/aftervalidationimaging/{id}";
	String SAVE_SHARED_LINK_ACCESS_DATA = SHARED_LINKS + "/sharedlinkinfo/save";
	String FETCH_SHARED_LINK_ACCESS_DATA = SHARED_LINKS + "/sharedlinkinfo/fetch/{tokenId}";

	String LIST_ALERT_EVENTS = "/um/alerts/{alertType}";
	String FETCH_ALERT_EVENT = "/open/alerts/fetch/{alertConstant}";
	String SAVE_EVENT_SUBSCRIBER = "/um/alerts/savesubscriber";
	String LIST_EVENT_SUBSCRIBER = "/open/alerts/list/subscriber/{constant}/{id}";
	
	String FETCH_PUBLIC_LINK_METADATA = "/open/fetch/publiclink/{id}";
	
	String FETCH_HOMEPAGE_CONFIG = "/open/homepageconfig";
	String SAVE_ONLINE_REFERRAL = "/open/saveonlinereferral";
	String SAVE_ONLINE_APPOINTMENT_BOOKING = "/open/saveonlineappointment";
	String FETCH_ONLINE_REFERRAL = "/open/fetchonlinereferral/{id}";
	String FETCH_ONLINE_APPOINTMENT_BOOKING = "/open/fetchonlineappointment/{id}";
	String FETCH_ONLINE_REFERRAL_CARD_INSTRUCTONS = "/open/ocinstructions/";
	
}
