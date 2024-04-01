-- drop all
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- create tables
CREATE TABLE IF NOT EXISTS "user" (
    "id" UUID PRIMARY KEY NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "surname" VARCHAR(100) NOT NULL,
    "email" VARCHAR(100) NOT NULL UNIQUE,
    "birth_date" DATE,
    "password_hash" VARCHAR(255) NOT NULL
);

	
CREATE TABLE IF NOT EXISTS "role" (
    "id" UUID PRIMARY KEY NOT NULL,
    "role_name" VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS "user_role" (
    "id_user" UUID NOT NULL,
    "id_role" UUID NOT NULL,
	
    CONSTRAINT pk_user_role PRIMARY KEY (id_user, id_role),
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES "role"(id) ON DELETE CASCADE
);	

	
CREATE TABLE IF NOT EXISTS "credit_type" (
    "id" UUID PRIMARY KEY NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "credit_amount" DECIMAL(15, 2) NOT NULL,
    "interest_rate" DECIMAL(2, 2) NOT NULL,
    "term_in_months" INTEGER NOT NULL
);

	
CREATE TABLE IF NOT EXISTS "credit" (
    "id" UUID PRIMARY KEY NOT NULL,
    "id_client" UUID NOT NULL,
    "id_credit_type" UUID NOT NULL,
    "paid_amount" DECIMAL(15,2),
    "start_date" DATE NOT NULL,
    "end_date" DATE NOT NULL,
    "status" BOOLEAN NOT NULL,
	
    CONSTRAINT fk_client FOREIGN KEY (id_client) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_credit_type FOREIGN KEY (id_credit_type) REFERENCES "credit_type"(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "request_status" (
    "id" UUID PRIMARY KEY NOT NULL,
    "status" VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS "credit_request" (
    "id" UUID PRIMARY KEY NOT NULL,
    "id_manager" UUID NOT NULL,
    "id_credit" UUID NOT NULL,
    "date_of_request" DATE NOT NULL,
    "id_status" UUID NOT NULL,
    "rejection_message" TEXT,
	
    CONSTRAINT fk_manager FOREIGN KEY (id_manager) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_credit FOREIGN KEY (id_credit) REFERENCES "credit"(id) ON DELETE CASCADE,
    CONSTRAINT fk_status FOREIGN KEY (id_status) REFERENCES "request_status"(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "payment" (
    "id" UUID PRIMARY KEY NOT NULL,
    "id_client" UUID NOT NULL,
    "id_credit" UUID NOT NULL,
    "amount" DECIMAL(15,2) NOT NULL,
    "payment_date" DATE NOT NULL,
	
    CONSTRAINT fk_client FOREIGN KEY (id_client) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_credit FOREIGN KEY (id_credit) REFERENCES "credit"(id) ON DELETE CASCADE
);
