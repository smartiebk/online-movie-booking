DO $$ 
<<outer_block>>
DECLARE actionIdExists integer := 0;
DECLARE insertedActionId integer := 0;
DECLARE roleIdExists integer := 0;
DECLARE insertedRoleId integer := 0;
DECLARE roleTypeId integer := 0;
DECLARE userGroupId integer := 0;
DECLARE insertedUserGroupId integer := 0;
DECLARE adminUserCount integer := 0;
DECLARE insertedUserId integer := 0;

DECLARE mappingRoleActionCount integer := 0;
DECLARE mappingRoleUGCount integer := 0;
DECLARE mappingUGUserCount integer := 0;

DECLARE contractTableCount integer := 0;

begin
select count(app_action_id) cn into actionIdExists from app_action where is_deleted=0 and action_name='SUPER_ADMIN';
RAISE NOTICE 'Action ID :: %', actionIdExists;
select count(app_role_id) capr into roleIdExists from app_role where is_deleted=0 and role_name='SUPER_ADMIN_ROLE';
select app_role_type_id  into roleTypeId from app_role_type where role_type='SUPER_ADMIN';
RAISE NOTICE 'Role ID :: %', roleIdExists;
select count(user_group_id) ugc into userGroupId from app_user_group where name='SUPER_ADMIN UG' and is_deleted=0;
RAISE NOTICE 'User Group ID :: %', userGroupId;
select count(app_user_id) aui into adminUserCount from app_user where username='ombp-admin' and is_deleted=0;
IF actionIdExists = 0 THEN
BEGIN
INSERT INTO app_action(
	 action_name, description, is_deleted, is_archived)
	VALUES ('SUPER_ADMIN', 'super admin', 0, 0);
END;
END IF;

IF roleIdExists = 0 THEN
BEGIN
INSERT INTO app_role(
	 role_name, description, is_deleted, is_archived, app_role_type_id)
	VALUES ( 'SUPER_ADMIN_ROLE', 'ROLE for super admin', 0, 0, roleTypeId);
END;
END IF;

IF userGroupId = 0 THEN
BEGIN
INSERT INTO app_user_group(
	 name, description, is_deleted, is_archived, tenant_id)
	VALUES ( 'SUPER_ADMIN UG', 'User Group for Super Admin', 0, 0, null);
END;
END IF;

IF adminUserCount = 0 THEN
BEGIN
INSERT INTO app_user(
	 username, email, mobile_number, password, enabled, account_expired, account_locked, credentials_expired, is_deleted, is_archived, create_time, update_time)
	VALUES ('ombp-admin', 'pranay.p.kathade@gmail.com', '9960735937', '$2a$06$Ug3WzMLDOtYNHM6x81mYNuImaE.Xi8.JWqlgQkbi.jAYxP0NpCbua', 0, 0, 0, 0, 0, 0	, CURRENT_DATE, CURRENT_DATE);
END;
END IF;

BEGIN
select app_action_id into insertedActionId from app_action where is_deleted=0 and action_name='SUPER_ADMIN';
select app_role_id into insertedRoleId from app_role where is_deleted=0 and role_name='SUPER_ADMIN_ROLE';
select count(m_action_role_id) into mappingRoleActionCount from m_action_role where app_action_id=insertedActionId and app_role_id=insertedRoleId and is_deleted=0;
IF mappingRoleActionCount = 0 THEN
INSERT INTO m_action_role(
	 app_action_id, app_role_id, is_deleted, is_archived)
	VALUES (insertedActionId, insertedRoleId, 0, 0);
END IF;
END;

BEGIN
select user_group_id into insertedUserGroupId from app_user_group where name='SUPER_ADMIN UG' and is_deleted=0;
select app_role_id into insertedRoleId from app_role where is_deleted=0 and role_name='SUPER_ADMIN_ROLE';
select count(m_user_group_role_id) into mappingRoleUGCount from m_user_group_role where user_group_id=insertedUserGroupId and app_role_id=insertedRoleId and is_deleted=0;
IF mappingRoleUGCount = 0 THEN
INSERT INTO m_user_group_role(
	 user_group_id, app_role_id, is_deleted, is_archived)
	VALUES ( insertedUserGroupId, insertedRoleId, 0, 0);
END IF;
END;

BEGIN
select user_group_id into insertedUserGroupId from app_user_group where name='SUPER_ADMIN UG' and is_deleted=0;
select app_user_id into insertedUserId from app_user where username='ombp-admin' and is_deleted=0;
select count(m_user_group_user_id) into mappingUGUserCount from m_user_group_user where user_group_id=insertedUserGroupId and app_user_id=insertedUserId and is_deleted=0;
IF mappingUGUserCount = 0 THEN
INSERT INTO m_user_group_user(
	 user_group_id, app_user_id, is_deleted, is_archived)
	VALUES ( insertedUserGroupId, insertedUserId, 0, 0);
END IF;
END;

BEGIN
select count(id) into contractTableCount from admin_user_data_constraint where user_id=insertedUserId and 
username='ombp-admin' and user_group_id=insertedUserGroupId and role_id=insertedRoleId and
action_id = insertedActionId;
IF contractTableCount = 0 THEN
INSERT INTO admin_user_data_constraint(
	 user_id, username, user_group_id, role_id, action_id, details)
	VALUES ( insertedUserId, 'ombp-admin', insertedUserGroupId, insertedRoleId, insertedActionId, 'Super admin user having all permission.');
	
END IF;
END;

END
 outer_block $$;