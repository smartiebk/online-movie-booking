package com.ombp.cloud.app.ui.constants;

public interface UIURLConstants {

	// All URLs
	
	String LANDING_URL = "/";
	
	String BASE_URL = "/oa";

	String CHANGE_LANGUAGE = BASE_URL + "/lang";

	String VIEW_ALERT_DETAILS = BASE_URL + "/alert/{id}";

	String VIEW_MESSAGE_DETAILS = BASE_URL + "/message/{id}";

	String VIEW_ALL_NOTIFICATION_MESSAGES = BASE_URL + "/messages/list";

	String VIEW_ALL_NOTIFICATION_ALERTS = BASE_URL + "/alert/list";

	String REFER_PATIENT = "/home/refer";
	String BOOK_ONLINE_APPOINTMENT = "/home/bookappointment";
	
	String LIST_DATA = "/li/data";
	String CREATE = "/create";
	String EDIT = "/edit/{id}";
	String DETAILS = "/details/{id}";
	String VIEW = "/view";
	String SAVE = "/save";
	String SHARE = "/share";
	String UNSHARE = "/unshare";
	String ALREADY_SHARED = "/alreadyshared";
	String DELETE = "/delete";
	String OPEN_SHARE = "/oshare";
	String EXISTS = "/exists/{paramname}/{paramvalue}";

	// All action related
	String ACTIONS = "/actions";
	String ACTIONS_URI = BASE_URL + ACTIONS;
	String LIST_ACTIONS_DATA = BASE_URL + ACTIONS + LIST_DATA;
	String CREATE_NEW_ACTION = BASE_URL + ACTIONS + CREATE;
	String EDIT_ACTION = BASE_URL + ACTIONS + EDIT;
	String SAVE_ACTION = BASE_URL + ACTIONS + SAVE;
	String DELETE_ACTION = BASE_URL + ACTIONS + DELETE;
	
	// All user profile related
	String USER_PROFILE = "/profile";
	String USER_PROFILE_URI = BASE_URL + USER_PROFILE;
	String EDIT_USER_PROFILE = BASE_URL + USER_PROFILE + EDIT;
	String SAVE_USER_PROFILE = BASE_URL + USER_PROFILE + SAVE;

	// All Roles related
	String ROLES = "/roles";
	String ROLES_URI = BASE_URL + ROLES;
	String LIST_ROLES_DATA = BASE_URL + ROLES + LIST_DATA;
	String CREATE_NEW_ROLE = BASE_URL + ROLES + CREATE;
	String EDIT_ROLE = BASE_URL + ROLES + EDIT;
	String ROLE_DETAILS = BASE_URL + ROLES + DETAILS;
	String SAVE_ROLE = BASE_URL + ROLES + SAVE;
	String DELETE_ROLE = BASE_URL + ROLES + DELETE;
	String LIST_ROLES_ACTION_MAPPING_DATA = BASE_URL + ROLES + ACTIONS + DETAILS;
	String REMOVE_ACTION_FROM_ROLE = BASE_URL + ROLES + ACTIONS + "/{roleId}/{actionId}";
	String SAVE_ACTION_TO_ROLE = BASE_URL + ROLES + ACTIONS + SAVE + "/{roleId}";

	// All User Groups related
	String USER_GROUPS = "/usergroups";
	String USER_GROUPS_URI = BASE_URL + USER_GROUPS;
	String LIST_USER_GROUPS_DATA = BASE_URL + USER_GROUPS + LIST_DATA;
	String CREATE_NEW_USER_GROUP = BASE_URL + USER_GROUPS + CREATE;
	String EDIT_USER_GROUP = BASE_URL + USER_GROUPS + EDIT;
	String USER_GROUP_DETAILS = BASE_URL + USER_GROUPS + DETAILS;
	String SAVE_USER_GROUP = BASE_URL + USER_GROUPS + SAVE;
	String DELETE_USER_GROUP = BASE_URL + USER_GROUPS + DELETE;
	String LIST_USER_GROUPS_ROLES_MAPPING_DATA = BASE_URL + USER_GROUPS + ROLES + DETAILS;
	String REMOVE_ROLES_FROM_USER_GROUP = BASE_URL + USER_GROUPS + ROLES + "/{ugId}/{roleId}";
	String SAVE_ROLE_TO_USER_GROUP = BASE_URL + USER_GROUPS + ROLES + SAVE + "/{ugId}";

	// All Users related
	String USERS = "/users";
	String USERS_URI = BASE_URL + USERS;
	String LIST_USERS_DATA = BASE_URL + USERS + LIST_DATA;
	String CREATE_NEW_USER = BASE_URL + USERS + CREATE;
	String EDIT_USER = BASE_URL + USERS + EDIT;
	String USER_DETAILS = BASE_URL + USERS + DETAILS;
	String SAVE_USER = BASE_URL + USERS + SAVE;
	String DELETE_USER = BASE_URL + USERS + DELETE;
	String LIST_USERS_USERGROUPS_MAPPING_DATA = BASE_URL + USERS + USER_GROUPS + DETAILS;
	String REMOVE_USERGROUPS_FROM_USER = BASE_URL + USERS + USER_GROUPS + "/{userId}/{userGroupId}";
	String SAVE_USERGROUP_TO_USER = BASE_URL + USERS + USER_GROUPS + SAVE + "/{userId}";
	
	
	String MOVIE_SHOW = "/movieshow";
	String MOVIE_SHOW_URI = BASE_URL + MOVIE_SHOW;
	String LIST_MOVIE_SHOW_DATA = BASE_URL + MOVIE_SHOW + LIST_DATA;
	String CREATE_NEW_MOVIE_SHOW = BASE_URL + MOVIE_SHOW + CREATE;
	String EDIT_MOVIE_SHOW = BASE_URL + MOVIE_SHOW + EDIT;
	String SAVE_MOVIE_SHOW = BASE_URL + MOVIE_SHOW + SAVE;
	String DELETE_MOVIE_SHOW = BASE_URL + MOVIE_SHOW + DELETE;
	
	String MOVIE_BY_NAME_URI = BASE_URL + "/fetch/movie/{movieName}";
	
	String LIST_ALL_SCREENS_URI = BASE_URL + "/fetch/theatre/screens/{theatreId}";
	
	String LIST_ALL_MOVIE_SHOWS_URI = BASE_URL + "/fetch/theatre/shows/{theatreId}";
	
	String LIST_ALL_THEATRES_URI = BASE_URL + "/search/theatre";

}
