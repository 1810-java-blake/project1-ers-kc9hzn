SET SCHEMA 'ers';

INSERT INTO ers_user_roles (ers_user_role_id, user_role)
VALUES (1, 'User');

INSERT INTO ers_user_roles (ers_user_role_id, user_role)
VALUES (2, 'Admin');

INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status)
VALUES (1, 'Pending');

INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status)
VALUES (2, 'Approved');

INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status)
VALUES (3, 'Denied');

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
VALUES (1, 'Lodging');

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
VALUES (2, 'Travel');

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
VALUES (3, 'Food');

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
VALUES (4, 'Other');

INSERT INTO ers_users (
    ers_users_id,
    ers_username,
    ers_password,
    user_first_name,
    user_last_name,
    user_email,
    user_role_id
) VALUES (
    1,
    'dstine',
    'TestPassWord1',
    'Daniel',
    'Stine',
    'dstine@chinookcorp.com',
    2
);

INSERT INTO ers_users (
    ers_users_id,
    ers_username,
    ers_password,
    user_first_name,
    user_last_name,
    user_email,
    user_role_id
) VALUES (
    2,
    'dstone',
    'TestPassWord2',
    'Danielle',
    'Stone',
    'dstone@chinookcorp.com',
    1
);

INSERT INTO ers_reimbursement (
    reimb_id,
    reimb_amount,
    reimb_submitted,
    reimb_resolved,
    reimb_description,
    reimb_receipt,
    reimb_author,
    reimb_resolver,
    reimb_status_id,
    reimb_type_id
) VALUES (
    1,
    10.50,
    '2018-11-06 00:00:00',
    '2018-11-06 12:00:00',
    'sample description',
    NULL,
    2,
    1,
    2,
    1
);

INSERT INTO ers_reimbursement (
    reimb_id,
    reimb_amount,
    reimb_submitted,
    reimb_resolved,
    reimb_description,
    reimb_receipt,
    reimb_author,
    reimb_resolver,
    reimb_status_id,
    reimb_type_id
) VALUES (
    2,
    15.99,
    '2018-11-06 06:00:00',
    '2018-11-06 13:55:00',
    'sample description 2',
    NULL,
    2,
    1,
    3,
    2
);

INSERT INTO ers_reimbursement (
    reimb_id,
    reimb_amount,
    reimb_submitted,
    reimb_resolved,
    reimb_description,
    reimb_receipt,
    reimb_author,
    reimb_resolver,
    reimb_status_id,
    reimb_type_id
) VALUES (
    3,
    100.50,
    '2018-11-06 13:00:00',
    NULL,
    'sample description',
    NULL,
    2,
    NULL,
    1,
    1
);