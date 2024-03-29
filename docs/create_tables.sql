-- drop all
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- create tables
CREATE TABLE IF NOT EXISTS "User"(
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "surname" VARCHAR(100) NOT NULL,
	"login" VARCHAR(100) NOT NULL UNIQUE,
    "email" VARCHAR(100) NOT NULL UNIQUE,
	"birth_date" DATE,
	"password_hash" VARCHAR(255) NOT NULL
);

	
CREATE TABLE IF NOT EXISTS "Role"(
    "id" UUID PRIMARY KEY,
    "role_name" VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS "User_Role"(
    "user_id" UUID NOT NULL,
    "role_id" UUID NOT NULL,
	
	CONSTRAINT FK_on_User FOREIGN KEY (user_id) REFERENCES "User"(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Role FOREIGN KEY (role_id) REFERENCES "Role"(id) ON DELETE CASCADE
);	

	
CREATE TABLE IF NOT EXISTS "Credit_Type"(
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "credit_amount" DECIMAL(15, 2) NOT NULL,
    "interest_rate" DECIMAL(2, 2) NOT NULL,
    "term_in_months" INTEGER NOT NULL
);

	
CREATE TABLE IF NOT EXISTS "Credit"(
    "id" UUID PRIMARY KEY,
    "client_id" UUID NOT NULL,
    "credit_type_id" UUID NOT NULL,
	"paid_amount" DECIMAL(15,2),
    "start_date" DATE NOT NULL,
    "end_date" DATE NOT NULL,
    "status" BOOLEAN NOT NULL,
	
	CONSTRAINT FK_on_Client FOREIGN KEY (client_id) REFERENCES "User"(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_CreditType FOREIGN KEY (credit_type_id) REFERENCES "Credit_Type"(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "Request_Status"(
	"id" UUID PRIMARY KEY,
	"status" VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS "Credit_Request"(
    "id" UUID PRIMARY KEY,
    "manager_id" UUID NOT NULL,
    "credit_id" UUID NOT NULL,
    "date_of_request" DATE NOT NULL,
    "status_id" UUID NOT NULL,
	"rejection_message" TEXT,
	
	CONSTRAINT FK_on_Manager FOREIGN KEY (manager_id) REFERENCES "User"(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Credit FOREIGN KEY (credit_id) REFERENCES "Credit"(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Status FOREIGN KEY (status_id) REFERENCES "Request_Status"(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "Payment"(
    "id" UUID PRIMARY KEY,
    "client_id" UUID NOT NULL,
    "credit_id" UUID NOT NULL,
	"amount" DECIMAL(15,2) NOT NULL,
	"payment_date" DATE NOT NULL,
	
	CONSTRAINT FK_on_Client FOREIGN KEY (client_id) REFERENCES "User"(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Credit FOREIGN KEY (credit_id) REFERENCES "Credit"(id) ON DELETE CASCADE
);

	
-- CREATE TABLE IF NOT EXISTS Client(
--     client_id UUID PRIMARY KEY,
--     birth_date DATE NOT NULL,
	
-- 	CONSTRAINT FK_on_User FOREIGN KEY (client_id) REFERENCES User(id) ON DELETE CASCADE
-- );
	
-- CREATE TABLE IF NOT EXISTS Manager(
-- 	manager_id UUID PRIMARY KEY,
	
-- 	CONSTRAINT FK_on_User FOREIGN KEY (manager_id) REFERENCES User(id) ON DELETE CASCADE
-- );