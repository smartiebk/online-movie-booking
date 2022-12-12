-- Table: public.app_user

-- DROP TABLE public.app_user;

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE public.app_user
(
    app_user_id BIGSERIAL NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15),
    password VARCHAR(1000) NOT NULL,
    enabled integer,
    account_expired integer,
    account_locked integer,
    credentials_expired integer,
    is_deleted integer,
    is_archived integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT "app_user_id_PK" PRIMARY KEY (app_user_id)
);
    
create table public.app_action (
app_action_id BIGSERIAL not null,
action_name VARCHAR(100) null,
description VARCHAR(1000) null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "app_action_id_PK" PRIMARY KEY (app_action_id)
);


create table public.app_role (
app_role_id BIGSERIAL not null,
role_name VARCHAR(100) null,
description VARCHAR(1000) null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "app_role_id_PK" PRIMARY KEY (app_role_id)
);

create table public.m_action_role (
m_action_role_id BIGSERIAL not null,
app_action_id integer not null,
app_role_id integer not null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "m_action_role_id_PK" PRIMARY KEY (m_action_role_id)
);

create table public.app_user_group (
user_group_id BIGSERIAL not null,
name VARCHAR(100) null,
description VARCHAR(1000) null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "user_group_id_PK" PRIMARY KEY (user_group_id)
);

create table public.m_user_group_role (
m_user_group_role_id BIGSERIAL not null,
user_group_id integer not null,
app_role_id integer not null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "m_user_group_role_id_PK" PRIMARY KEY (m_user_group_role_id)
);

create table public.m_user_group_user (
m_user_group_user_id BIGSERIAL not null,
user_group_id integer not null,
app_user_id integer not null,
is_deleted integer not null,
is_archived integer null,
CONSTRAINT "m_user_group_user_id_PK" PRIMARY KEY (m_user_group_user_id)
);


create table public.oauth_client_details (
  oauth_client_details_id BIGSERIAL not null,
  client_id VARCHAR(256),
  resource_ids VARCHAR(1000),
  client_secret VARCHAR(1000),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(1000),
  authorities VARCHAR(1000),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information VARCHAR(4000),
  autoapprove VARCHAR(256),
  CONSTRAINT "oauth_client_details_id_PK" PRIMARY KEY (oauth_client_details_id)
);

create table public.oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  create_time timestamp without time zone null,
  update_time timestamp without time zone null,
  CONSTRAINT "authentication_id_PK" PRIMARY KEY (authentication_id)
);

create table public.oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256),
  create_time timestamp without time zone null,
  update_time timestamp without time zone null,
  CONSTRAINT "oauth_access_token_authentication_id_PK" PRIMARY KEY (authentication_id)
);


create table public.oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

create table public.oauth_code (
  code VARCHAR(256), authentication bytea
);

create table public.oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(256),
	expiresAt timestamp without time zone,
	lastModifiedAt timestamp without time zone
);

CREATE table public.app_login_history (
    app_login_history_id BIGSERIAL not null, 
	username VARCHAR(100) NULL,
	client_id VARCHAR(100) NULL,
	access_token VARCHAR(256) NULL,
	access_token_expiration timestamp without time zone NULL,
	refresh_token VARCHAR(256) NULL,
	refresh_token_expiration timestamp without time zone NULL,
	grant_type_used VARCHAR(256) NULL,
	create_time timestamp without time zone NULL,
	update_time timestamp without time zone NULL,
	CONSTRAINT "app_login_history_id_PK" PRIMARY KEY (app_login_history_id)
) 

CREATE table public.app_user_details (
	app_user_details_id BIGSERIAL NOT NULL,
	first_name VARCHAR(100) NULL,
	last_name VARCHAR(100) NULL,
	city VARCHAR(100) NULL,
	address VARCHAR(1000) NULL,
	addr_state VARCHAR(1000) NULL,
	alt_mobile VARCHAR(50) NULL,
	alt_email VARCHAR(1000) NULL,
	image_path VARCHAR(1000) NULL,
	dob timestamp without time zone NULL,
	is_deleted integer NULL,
	is_archived integer NULL,
	create_time timestamp without time zone NULL,
	update_time timestamp without time zone NULL,
	app_user_id bigint NULL,
	CONSTRAINT app_user_details_PK PRIMARY KEY (app_user_details_id),
	CONSTRAINT app_user_details_app_user_FK FOREIGN KEY (app_user_id) REFERENCES app_user(app_user_id)
) 


CREATE TABLE public.app_service_name
(
    app_service_name_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE table public.app_method_name (
	app_method_name_id SERIAL NOT NULL,
	name VARCHAR(100) NOT NULL,
	app_service_name_id integer NOT NULL,
	CONSTRAINT "app_method_name_service_FK" FOREIGN KEY (app_service_name_id) REFERENCES app_service_name(app_service_name_id)
);

create table public.m_action_method_acl (
m_action_method_acl_id BIGSERIAL not null PRIMARY KEY,
app_action_id integer not null,
app_method_name_id integer not null
);

CREATE TABLE public.tenant
(
    tenant_id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    identifier VARCHAR(200) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15),
    enabled integer,
    account_expired integer,
    account_locked integer,
    is_deleted integer,
    is_archived integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.app_user_group
    ADD COLUMN tenant_id integer;
ALTER TABLE public.app_user_group
    ADD CONSTRAINT tenant_ug_fk FOREIGN KEY (tenant_id)
    REFERENCES public.tenant (tenant_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
    ALTER TABLE public.app_user_group
    ALTER COLUMN tenant_id SET NOT NULL;
    
CREATE TABLE auto_user_group_allocation_token
(
    auto_user_group_allocation_token_id BIGSERIAL PRIMARY KEY NOT NULL,
    token_value VARCHAR(100) NOT NULL,
    user_group_id integer,
    token_used integer,
    token_expiry timestamp without time zone,
    is_deleted integer,
    is_archived integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

CREATE TABLE app_role_type
(
    app_role_type_id BIGSERIAL PRIMARY KEY NOT NULL,
    role_type VARCHAR(100) NOT NULL
);

CREATE TABLE ADMIN_USER_DATA_CONSTRAINT
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id integer,
    username VARCHAR(100) NOT NULL,
    user_group_id integer,
    role_id integer,
    action_id integer,
    details VARCHAR(100),
    CONSTRAINT c_user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.app_user (app_user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT c_user_group_id_fk FOREIGN KEY (user_group_id)
        REFERENCES public.app_user_group (user_group_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT c_role_id_fk FOREIGN KEY (role_id)
        REFERENCES public.app_role (app_role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT c_action_id_fk FOREIGN KEY (action_id)
        REFERENCES public.app_action (app_action_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."ADMIN_USER_DATA_CONSTRAINT"
    OWNER to postgres;
    
    
/* Script for creating admin user group, role, authority*/
    
ALTER TABLE public.auto_user_group_allocation_token
    ADD COLUMN mapped_user_id integer;
    
    
ALTER TABLE public.tenant
    ADD COLUMN public_identifier VARCHAR(100);
    
ALTER TABLE public.tenant
    ALTER COLUMN public_identifier SET NOT NULL;
    
ALTER TABLE public.m_user_group_user
    ADD COLUMN create_time timestamp without time zone;

ALTER TABLE public.m_user_group_user
    ADD COLUMN update_time timestamp without time zone;
    
ALTER TABLE public.app_role
    ADD COLUMN updated_by_user_id integer;
    
ALTER TABLE public.app_user_group
    ADD COLUMN updated_by_user_id integer;
    

CREATE TABLE real_client_org_pref
(
    real_client_org_pref_id BIGSERIAL PRIMARY KEY NOT NULL,
    real_client_org_identifier VARCHAR(1000) NOT NULL,
    org_name_dblabel VARCHAR(1000),
    org_name_keylabel VARCHAR(1000),
    details VARCHAR(4000),
    hover_title VARCHAR(1000),
    url VARCHAR(1000),
    logo_pack_path VARCHAR(1000),
    is_deleted integer,
    is_archived integer
);

ALTER TABLE public.app_user
    ADD COLUMN created_by_user_id integer;

CREATE TABLE app_document
(
    app_document_id BIGSERIAL PRIMARY KEY NOT NULL,
    uploaded_original_filename varchar,
	generated_filename varchar,
	file_extension varchar,
	file_description varchar,
	given_file_name varchar,
	bucketname varchar,
	object_key varchar,
	file_size varchar,
	download_link varchar,
	download_link_valid_till timestamp without time zone,
	is_deleted integer,
    is_archived integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);


CREATE TABLE show_media_documents
(
    pthistory_documents_id BIGSERIAL PRIMARY KEY NOT NULL,
    pthistory_id integer,
    app_document_id integer,
	is_deleted integer,
    is_archived integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);


CREATE TABLE sms_log
(
    sms_log_id BIGSERIAL PRIMARY KEY NOT NULL,
    sent_to varchar,
    sms varchar,
    sms_type varchar,
	api_url varchar,
	status varchar,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);


CREATE TABLE alert_event
(
    alert_event_id BIGSERIAL PRIMARY KEY NOT NULL,
    alert_type varchar, /*email or sms*/
    event_type_constant varchar,
    event_type_db_lable varchar,
    event_type_key_lable varchar,
    alert_event_subject varchar,
    alert_event_template varchar,
    alert_sender_text varchar,
    is_user_specific varchar,
	is_deleted integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

CREATE TABLE email_log
(
    email_log_id BIGSERIAL PRIMARY KEY NOT NULL,
    sent_to varchar,
    sent_cc varchar,
    sent_bcc varchar,
    email_subject varchar,
    email_content varchar,
    email_type varchar,
	email_from varchar,
	is_attachment_present varchar,
	attachment_file_names varchar,
	status varchar,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

-- Business Tables

CREATE TABLE city
(
    city_id BIGSERIAL PRIMARY KEY NOT NULL,
    city_name varchar,
    state_name varchar,
    country_name varchar,
    is_deleted integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

CREATE TABLE address
(
    address_id BIGSERIAL PRIMARY KEY NOT NULL,
	address_lines varchar,
    area varchar,
	landmark varchar,
	pincode varchar,
	city_id integer,
	primary_phone varchar,
	secondary_phone varchar,
	alternate_phone varchar,
	email_address_cs varchar,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.address
    ADD CONSTRAINT city_id_fk FOREIGN KEY (city_id)
    REFERENCES public.city (city_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
CREATE TABLE theatre
(
    theatre_id BIGSERIAL PRIMARY KEY NOT NULL,
	theatre_name varchar,
    address_id integer,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.theatre
    ADD CONSTRAINT address_id_fk FOREIGN KEY (address_id)
    REFERENCES public.address (address_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
CREATE TABLE screen
(
    screen_id BIGSERIAL PRIMARY KEY NOT NULL,
	screen_name varchar,
    theatre_id integer,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.screen
    ADD CONSTRAINT theatre_id_fk FOREIGN KEY (theatre_id)
    REFERENCES public.theatre (theatre_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

CREATE TABLE seat_category
(
    seat_category_id BIGSERIAL PRIMARY KEY NOT NULL,
	seat_category_name varchar,
    screen_id integer,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.seat_category
    ADD CONSTRAINT screen_id_fk FOREIGN KEY (screen_id)
    REFERENCES public.screen (screen_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
CREATE TABLE seat
(
    seat_id BIGSERIAL PRIMARY KEY NOT NULL,
	seat_name varchar,
    seat_category_id integer,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.seat
    ADD CONSTRAINT seat_category_id_fk FOREIGN KEY (seat_category_id)
    REFERENCES public.seat_category (seat_category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
    
CREATE TABLE movie
(
    movie_id BIGSERIAL PRIMARY KEY NOT NULL,
	movie_name varchar,
    release_date timestamp without time zone,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

CREATE TABLE movie_details_attribute
(
    movie_details_attribute_id BIGSERIAL PRIMARY KEY NOT NULL,
	movie_details_attribute_name varchar,
	movie_details_attribute_constant varchar,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

CREATE TABLE movie_details
(
    movie_details_id BIGSERIAL PRIMARY KEY NOT NULL,
    movie_details_attribute_id integer,
    movie_id integer,
	key_text varchar,
    value_text varchar,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.movie_details
    ADD CONSTRAINT movie_details_id_fk FOREIGN KEY (movie_details_attribute_id)
    REFERENCES public.movie_details_attribute (movie_details_attribute_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE public.movie_details
    ADD CONSTRAINT movie_d_id_fk FOREIGN KEY (movie_id)
    REFERENCES public.movie (movie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    
CREATE TABLE movie_show
(
    movie_show_id BIGSERIAL PRIMARY KEY NOT NULL,
    movie_id integer,
    screen_id integer,
	show_timing_from time,
	show_timing_to time,
    show_start_date timestamp without time zone,
    show_end_date timestamp without time zone,
	is_deleted integer,
    updated_by_user_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone
);

ALTER TABLE public.movie_show
    ADD CONSTRAINT movie_screen_id_id_fk FOREIGN KEY (screen_id)
    REFERENCES public.screen (screen_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
ALTER TABLE public.movie_show
    ADD CONSTRAINT movie_show_m_id_fk FOREIGN KEY (movie_id)
    REFERENCES public.movie (movie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
             