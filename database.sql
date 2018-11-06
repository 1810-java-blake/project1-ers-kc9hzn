SET SCHEMA 'ers';

CREATE TABLE ers_user_roles
(
    ers_user_role_id SERIAL PRIMARY KEY,
    user_role VARCHAR(10) NOT NULL
);

CREATE TABLE ers_users
(
    ers_users_id SERIAL PRIMARY KEY,
    ers_username VARCHAR(50) NOT NULL,
    ers_password VARCHAR(50) NOT NULL,
    user_first_name VARCHAR(100) NOT NULL,
    user_last_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(150) NOT NULL,
    user_role_id INTEGER REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement_status
(
    reimb_status_id SERIAL PRIMARY KEY,
    reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_type
(
    reimb_type_id SERIAL PRIMARY KEY,
    reimb_type VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement
(
    reimb_id SERIAL PRIMARY KEY,
    reimb_amount NUMERIC(100,2) NOT NULL,
    reimb_submitted TIMESTAMP NOT NULL,
    reimb_resolved TIMESTAMP,
    reimb_description VARCHAR(250),
    reimb_receipt TEXT,
    reimb_author INTEGER REFERENCES ers_users(ers_users_id),
    reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
    reimb_status_id INTEGER REFERENCES ers_reimbursement_status(reimb_status_id),
    reimb_type_id INTEGER REFERENCES ers_reimbursement_type(reimb_type_id)
);
