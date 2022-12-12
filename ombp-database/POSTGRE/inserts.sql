INSERT INTO public.oauth_client_details(
	 client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
	VALUES ('client-id', NULL, 'client-secret', 'read,write', 
'client_credentials,password,authorization_code,refresh_token,implicit', 
'http://localhost:9099', NULL, 60, 300, NULL, '');

	
INSERT INTO public.city(
	 city_name, state_name, country_name, is_deleted, create_time, update_time)
	VALUES ('Nagpur', 'Maharashtra', 'India', 0, now(), now()),
	('Mumbai', 'Maharashtra', 'India', 0, now(), now()),
	('Delhi', 'Delhi NCR', 'India', 0, now(), now()),
	('Hyderabad', 'Telangana', 'India', 0, now(), now());

	INSERT INTO public.address(
	 address_lines, area, landmark, pincode, city_id, primary_phone, secondary_phone, alternate_phone, 
	email_address_cs, is_deleted, updated_by_user_id, create_time, update_time)
	VALUES (
		'Abc, pqr road,', 'Sitabuldi', 'Burdi','440015', 1, '9960735937', 
		'1234567890', null, 
		'abc@pqr.com', 0, 1, now(), now());
		
INSERT INTO public.theatre(
	 theatre_name, address_id, is_deleted, updated_by_user_id, create_time, update_time)
	VALUES ( 'PVR', 1, 0, 1, NOW(), NOW());
	
INSERT INTO public.screen(
	 screen_name, theatre_id, is_deleted, updated_by_user_id, create_time, update_time)
	VALUES (
		'Screen 1', 1, 0, 1, now(), now()),
		(
		'Screen 2', 1, 0, 1, now(), now()),
		(
		'Screen 3', 1, 0, 1, now(), now()),
		(
		'Screen 4', 1, 0, 1, now(), now());