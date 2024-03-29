-- drop all
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- SELECT * FROM Roles;
-- SELECT * FROM Users;
-- SELECT * FROM Clients;
-- SELECT * FROM User_Roles;

-- DELETE FROM User_Roles;

-- create tables
CREATE TABLE IF NOT EXISTS Users(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
	
CREATE TABLE IF NOT EXISTS Clients(
    client_id UUID PRIMARY KEY,
    birth_date DATE NOT NULL,
	
	CONSTRAINT FK_on_User FOREIGN KEY (client_id) REFERENCES Users(id) ON DELETE CASCADE
);
	
CREATE TABLE IF NOT EXISTS Managers(
	manager_id UUID PRIMARY KEY,
	
	CONSTRAINT FK_on_User FOREIGN KEY (manager_id) REFERENCES Users(id) ON DELETE CASCADE
);
	
CREATE TABLE IF NOT EXISTS Roles(
    id UUID PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
);

ALTER TABLE Roles ADD CONSTRAINT roles_name_unique UNIQUE(role_name);
	
CREATE TABLE IF NOT EXISTS User_Roles(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
	
	CONSTRAINT FK_on_User FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Role FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE
);	

	
CREATE TABLE IF NOT EXISTS Credit_Types(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credit_amount DECIMAL(15, 2) NOT NULL,
    interest_rate DECIMAL(2, 2) NOT NULL,
    term_in_months INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS Credit_Request(
    id UUID PRIMARY KEY,
    manager_id UUID NOT NULL,
    client_id UUID NOT NULL,
    credit_type_id UUID NOT NULL,
    date_of_request DATE NOT NULL,
    status BOOLEAN NOT NULL,
	
	CONSTRAINT FK_on_Client FOREIGN KEY (client_id) REFERENCES Clients(client_id) ON DELETE CASCADE,
	CONSTRAINT FK_on_Manager FOREIGN KEY (manager_id) REFERENCES Managers(manager_id) ON DELETE CASCADE,
	CONSTRAINT FK_on_CreditType FOREIGN KEY (credit_type_id) REFERENCES Credit_Types(id) ON DELETE CASCADE
);

	
CREATE TABLE IF NOT EXISTS Credits(
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    credit_type_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status BOOLEAN NOT NULL,
	
	CONSTRAINT FK_on_Client FOREIGN KEY (client_id) REFERENCES Clients(client_id) ON DELETE CASCADE,
	CONSTRAINT FK_on_CreditType FOREIGN KEY (credit_type_id) REFERENCES Credit_Types(id) ON DELETE CASCADE
);
