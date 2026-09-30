-- Church Management System DDL Schema

CREATE TABLE IF NOT EXISTS admin (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS member_registration (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50),
    dob DATE,
    address VARCHAR(255),
    date_joined DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS announcement (
    id BIGSERIAL PRIMARY KEY,
    announcement_type VARCHAR(100),
    title VARCHAR(255) NOT NULL,
    message TEXT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    effective_date DATE,
    effective_until DATE,
    target_audience VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS event (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description TEXT,
    date DATE DEFAULT CURRENT_DATE,
    time TIME
);

CREATE TABLE IF NOT EXISTS sermon (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    speaker VARCHAR(255),
    date DATE DEFAULT CURRENT_DATE,
    url VARCHAR(500),
    description TEXT
);

CREATE TABLE IF NOT EXISTS member_contribution (
    id BIGSERIAL PRIMARY KEY,
    member_id BIGINT NOT NULL REFERENCES member_registration(id) ON DELETE CASCADE,
    member_name VARCHAR(255),
    contribution_type VARCHAR(100),
    amount NUMERIC(12, 2),
    mode_of_payment VARCHAR(50),
    date_of_contribution TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tithes_and_offerings (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(12, 2),
    date_of_contribution TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
