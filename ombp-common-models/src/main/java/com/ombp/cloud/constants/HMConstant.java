package com.ombp.cloud.constants;

import java.util.HashMap;

public interface HMConstant {

	String ENTITY_STATUS_TYPE_INVESTIGATION = "INVESTIGATION";
	
	String PERSON_TYPE_PATIENT = "PT";
	
	String PERSON_TYPE_DOCTOR = "DOC";
	
	String PERSON_TYPE_POC = "POC";
	
	String PROFILE_PICTURE = "ProfilePic";
	
	String PATIENT_FOLDER = "/patients_data/";
	
	String EXPORTED_DOCUMENTS_FOLDER = "/exported_docs/";
	
	String SCAN_DATA_FOLDER = "/scan_data/";
	
	String DATA_FOLDER = "app-data/";
	
	String DOCTOR_FOLDER = "/doctors_data/";
	
	String TEMPLATE_FOLDER = "/templates_data/";
	
	String INVESTIGATION_FOLDER = "/investigation_data/";
	
	String IMAGING_CURR_OP_CLOUD_FILE_DETAILS_UPLOAD = "IMAGING_CURR_OP_CLOUD_FILE_DETAILS_UPLOAD";
	
	String IMAGING_CURR_OP_CLOUD_UPLOAD_STATUS = "IMAGING_CURR_OP_CLOUD_UPLOAD_STATUS";
	
	String IMAGING_CURR_OP_SERVER_SAVE = "IMAGING_CURR_OP_SERVER_SAVE";
	
	String IMAGING_CURR_OP_SERVER_REMOVE = "IMAGING_CURR_OP_SERVER_REMOVE";
	
	String IMAGING_CURR_OP_UPDATE_DOWNLOAD_LINK = "IMAGING_CURR_OP_UPDATE_DOWNLOAD_LINK";
	
	String IMAGING_CURR_OP_UPDATE_REPORT = "IMAGING_CURR_OP_UPDATE_REPORT";
	
	String FILE_UPLOAD_STATUS_STARTED = "Started";
	
	String FILE_UPLOAD_STATUS_IN_PROGRESS = "In Progress";
	
	String FILE_UPLOAD_STATUS_IN_COMPLETED = "Completed";
	
	String FILE_UPLOAD_STATUS_IN_FAILED = "Failed";
	
	String SCAN_REPORTING_STATUS_PENDING = "Pending";
	
	String SCAN_REPORTING_STATUS_STARTED = "Started";
	
	String SCAN_REPORTING_STATUS_IN_PROGRESS = "In Progress";
	
	String SCAN_REPORTING_STATUS_IN_REVISION = "Revising In Progress";
	
	String SCAN_REPORTING_STATUS_IN_COMPLETED = "Completed";
	
	String SCAN_REPORTING_STATUS_SHARED = "Shared With Doctor/Patient";
	
	String SYNC_REQUEST_TYPE_STARTED = "STARTED";
	
	String SYNC_REQUEST_TYPE_PROGRESS_INFO = "PROGRESS_INFO";
	
	String SYNC_REQUEST_TYPE_COMPLETED = "COMPLETED";
	
	String SYNC_REQUEST_TYPE_FAILED = "FAILED";
	
	String SYNC_REQUEST_TYPE_CONFLICT = "CONFLICT";
	
	String TYPE_OF_CARD_PT_COUNT_TDAY = "TODAYS_PT_COUNT";
	
	String TYPE_OF_CARD_PT_COUNT_YDAY = "YDAYS_PT_COUNT";
	
	String TYPE_OF_CARD_PT_COUNT_MN = "MNTHS_PT_COUNT";
	
	String TYPE_OF_CARD_REFERRING_DOC_CNT = "REF_DOC_COUNT";
	
	String HISTORY_DOCUMENT_MAPPING_TABLE_NAME = "pthistory";
	
	String PERSON_CREATION_MODE_AUTO = "AUTO";
	
	String SHARED_LINK_TYPE_IMAGING = "IMAGING_REPORT_LINK";
	
	String ONLINE_REFERRAL_CARD_LINK = "ONLINE_REFERRAL_CARD_LINK";
	
	String ONLINE_APPOINTMENT_CARD_LINK = "ONLINE_APPOINTMENT_CARD_LINK";
	
	String SHARED_LINK_IMAGING = "/di/";
	
	
	HashMap<Integer,String> CHART_COLORS = new HashMap<Integer, String>() {{
        put(0, "rgb(255, 99, 132)");
        put(1,"rgb(255,159,64)");
        put(2,"rgb(255, 205, 86)");
        put(3,"rgb(75, 192, 192)");
        put(4,"rgb(54, 162, 235)");
        put(5,"rgb(153, 102, 255)");
        put(6,"rgb(201, 203, 207)");
    }};
    
    HashMap<Integer,String> CHART_BACKGROUND_COLORS = new HashMap<Integer, String>() {{
        put(0, "rgba(255, 99, 132, 0.5)");
        put(1,"rgba(255,159,64, 0.5)");
        put(2,"rgba(255, 205, 86, 0.5)");
        put(3,"rgba(75, 192, 192, 0.5)");
        put(4,"rgba(54, 162, 235, 0.5)");
        put(5,"rgba(153, 102, 255, 0.5)");
        put(6,"rgba(201, 203, 207, 0.5)");
    }};
	
}
