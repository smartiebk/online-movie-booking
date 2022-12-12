INSERT INTO "PUBLIC"."OAUTH_CLIENT_DETAILS" ("CLIENT_ID",
                                             "RESOURCE_IDS",
                                             "CLIENT_SECRET",
                                             "SCOPE",
                                             "AUTHORIZED_GRANT_TYPES",
                                             "WEB_SERVER_REDIRECT_URI",
                                             "AUTHORITIES",
                                             "ACCESS_TOKEN_VALIDITY",
                                             "REFRESH_TOKEN_VALIDITY",
                                             "ADDITIONAL_INFORMATION",
                                             "AUTOAPPROVE")
VALUES ('client-id', NULL, 'client-secret', 'read,write', 
'client_credentials,password,authorization_code,refresh_token,implicit', 
'http://localhost:9099', NULL, 10800, 2592000, NULL, '')

INSERT INTO "PUBLIC"."OAUTH_CLIENT_DETAILS" ("CLIENT_ID",
                                             "RESOURCE_IDS",
                                             "CLIENT_SECRET",
                                             "SCOPE",
                                             "AUTHORIZED_GRANT_TYPES",
                                             "WEB_SERVER_REDIRECT_URI",
                                             "AUTHORITIES",
                                             "ACCESS_TOKEN_VALIDITY",
                                             "REFRESH_TOKEN_VALIDITY",
                                             "ADDITIONAL_INFORMATION",
                                             "AUTOAPPROVE")
VALUES ('client-id-1', NULL, 'client-secret-1', 'read,write', 
'client_credentials,password,authorization_code,refresh_token,implicit', 
'http://localhost:9099', NULL, 10800, 2592000, NULL, '')


/*INSERT INTO "PUBLIC"."APP_USER" ("APP_USER_ID",
                                 "USERNAME",
                                 "EMAIL",
                                 "MOBILE_NUMBER",
                                 "PASSWORD",
                                 "ENABLED",
                                 "ACCOUNT_EXPIRED",
                                 "ACCOUNT_LOCKED",
                                 "CREDENTIALS_EXPIRED",
                                 "IS_DELETED",
                                 "IS_ARCHIVED")
VALUES (1, 'pranay', 'pranay@kathade.com', '98989898989', 'pranay', TRUE, 0, 0, 0, 0,0)*/

INSERT INTO "PUBLIC"."APP_USER"
( "APP_USER_ID", "USERNAME", "EMAIL", "MOBILE_NUMBER", "PASSWORD", "ENABLED", "ACCOUNT_EXPIRED", "ACCOUNT_LOCKED", "CREDENTIALS_EXPIRED", "IS_DELETED", "IS_ARCHIVED", "CREATE_TIME", "UPDATE_TIME" )
VALUES ( 1, 'pranay', 'pranay@kathade.com', '9960735937', 'pranay',0 , 0, 0, 0, 0, 0, null, null)

INSERT INTO "PUBLIC"."APP_ACTION"
( "APP_ACTION_ID", "ACTION_NAME", "DESCRIPTION", "IS_DELETED", "IS_ARCHIVED" )
VALUES (1 , 'CREATE_USER', 'Action for creating users', 0, 0)

INSERT INTO "PUBLIC"."APP_ROLE"
( "APP_ROLE_ID", "ROLE_NAME", "DESCRIPTION", "IS_DELETED", "IS_ARCHIVED" )
VALUES ( 1, 'SUPER_ADMIN', 'Super admin for all permissions', 0, 0)

INSERT INTO "PUBLIC"."M_ACTION_ROLE"
( "M_ACTION_ROLE_ID", "APP_ACTION_ID", "APP_ROLE_ID", "IS_DELETED", "IS_ARCHIVED" )
VALUES (1 ,1 , 1, 0, 0)

INSERT INTO "PUBLIC"."USER_GROUP"
( "USER_GROUP_ID", "NAME", "DESCRIPTION", "IS_DELETED", "IS_ARCHIVED" )
VALUES ( 1, 'Super Admin UG', 'Super admins user group', 0, 0)

INSERT INTO "PUBLIC"."M_USER_GROUP_ROLE"
( "M_USER_GROUP_ROLE_ID", "USER_GROUP_ID", "APP_ROLE_ID", "IS_DELETED", "IS_ARCHIVED" )
VALUES ( 1, 1, 1, 0, 0)

INSERT INTO "PUBLIC"."M_USER_GROUP_USER"
( "M_USER_GROUP_USER_ID", "USER_GROUP_ID", "APP_USER_ID", "IS_DELETED", "IS_ARCHIVED" )
VALUES (1 , 1, 1, 0, 0)
